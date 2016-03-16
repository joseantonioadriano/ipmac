
import java.io.*;
import java.util.concurrent.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.concurrent.locks.*;

class ClipAudio {

    /**
     * Abre un fichero de sonido wav y lo reproduce
     * @param args
     */
	
	public ClipAudio() {} // constructor nulo
	 
    public void play (int code) {
        try {
            
            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
			if (code==1)
				sonido.open(AudioSystem.getAudioInputStream(new File("sounds/notify.wav")));
			else if (code==2)
				sonido.open(AudioSystem.getAudioInputStream(new File("sounds/pluck.wav")));
			else if (code==3)
				sonido.open(AudioSystem.getAudioInputStream(new File("sounds/warn.wav")));
            
            // Comienza la reproducción
            sonido.start();
			System.out.println(">>");
			
            // Espera mientras se esté reproduciendo.
            while (sonido.isRunning())
                Thread.sleep(1000);
            
			
            Thread.sleep(3000);
			
            // Se cierra el clip.
            sonido.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

}

class scanProcess
	implements Runnable 
{
	private final ReentrantLock cerrojo= new ReentrantLock();

	public String ip; // ip que el proceso esta rastreando

	public scanProcess( long ipdec ) {
		IPFormat ipf= new IPFormat();
		this.ip= ipf.convertDirection(ipdec);
	}
	
	public void run(){
		Ping p= new Ping();
		ARPInfo ARPi= new ARPInfo();
		String MAC="";
		utiles u= new utiles();
		manage man= new manage();
		Email em= new Email();
		ClipAudio au= new ClipAudio();
			
		System.out.println( Thread.currentThread().getName() + ": Escaneando.. " + this.ip );
		p.setIP(this.ip);
		
		if (p.hacerPing(5000)==true){
		
			// hacer control de concurrencia desde aqui hasta el final del if hacerping..
			//synchronized(this){
			cerrojo.lock();
			try{
			
			  if (ARPi.recursoDisponible(this.ip)==true) {
				MAC= ARPi.getMAC(this.ip);
				u.escribir( Thread.currentThread().getName() + ": Dispositivo encontrado con IP= " + this.ip + " MAC: " +MAC );
				if (man.MACpermitida(MAC)==false) {				
					//au.play(3);
					u.escribir(">>>>> Se ha encontrado un dispositivo conectado no permitido con IP= " + this.ip + " MAC: " +MAC);
					// notificar email
					//em.enviar("Se%20ha%20encontrado%20un%20dispositivo%20conectado%20no%20permitido%20con%20IP=%20" + this.ip + " MAC: " +MAC);
				}
				else
				{
					if (man.MACConectado(MAC)==false){
						u.escribir("Se ha conectado a la red un nuevo dispositivo con IP= " + this.ip + " MAC: " +MAC);
						   //em.enviar("Se%20ha%20conectado%20a%20la%20red%20un%20nuevo%20dispositivo%20con%20IP=" + this.ip + "%20MAC:%20" +MAC);						   
						   //au.play(1);					
						   //em.enviar(man.getNombrebyMAC(MAC)+"%20se%20acaba%20de%20conectar%20a%20la%20red%20");
						// fichero log
						if (man.MACRegistrarConexion(MAC,this.ip)==false) {
							u.escribir("Error registrando conexion del dispositivo con IP= " + this.ip + " MAC: " +MAC);						
							// fichero log
						}
					}
				}
			  }
			
			}finally{
			cerrojo.unlock();
			}
		
		}
		else // si el dispositivo no esta conectado miramos si lo estuvo antes, en ese caso lo borramos de la lista
		{
			if (man.MACConectado(null, this.ip)==true){ //figura como conectado pero no lo esta, es decir, se ha caido
				u.escribir("Se ha perdido la conexion con el dispositivo de IP: "+this.ip);
				//au.play(2);				
				//em.enviar("%20"+man.getNombrebyMAC(man.getMACbyIP(this.ip))+"%20se%20ha%20desconectado%20de%20la%20red%20");
				//System.out.println("%20"+man.getNombrebyMAC(man.getMACbyIP(this.ip))+"%20se%20ha%20desconectado");
				//System.exit(-1);
				if (man.MACBorrarConexion(this.ip)==true)//escribe para verificar de que se ha borrado
					u.escribir("Dispositivo borrado de las conexiones con IP: "+this.ip);
				// notificar email
			}
		}
		// terminar aqui el control de la concurrencia
	}	
	
}

public class test {
	

	public static void main ( String[] args )
		throws InterruptedException
	{
		//int hilos= 32; //poner a 4, 2, 1..
		String startingIP = "192.168.0.100";
		String endingIP   = "192.168.0.199";
		/*String startingIP = "10.181.166.1";
		String endingIP   = "10.181.166.255";*/
		IPFormat ipfo= new IPFormat();	
		IPInfo ipi= new IPInfo();
		boolean ejecutar= true;
		utiles u= new utiles();
		Audio au= new Audio();
		//au.play(2);				
		int nNuc = Runtime.getRuntime().availableProcessors(); // numero de nucleos de la cpu
		float Cb = (float)0.5; // coeficiente de bloqueo
		int tamPool = (int)(nNuc/(1-Cb)); // ecuacion de subramanian

		//ExecutorService ejecutor = Executors.newFixedThreadPool(hilos);
		ThreadPoolExecutor ejecutor = new ThreadPoolExecutor( tamPool, tamPool, 5000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
			
		while(ejecutar==true){					
				for ( long ipdec= ipfo.convertDirtoDec(startingIP); ipdec<= ipfo.convertDirtoDec(endingIP); ipdec++ ){
					//System.out.println("Buscando "+ipdec+" desde la "+ipfo.convertDirtoDec(ipi.getIP())+" ( "+ipi.getIP()+" ) ");					
					if (ipi.recursoDisponible()==true) 
						if(ipdec!=ipfo.convertDirtoDec(ipi.getIP()))
							ejecutor.execute(new scanProcess(ipdec));
				}
		
			ejecutar= comprobarSenialExterna();
		}
		while(!ejecutor.isTerminated()){}
		ejecutor.shutdown(); 
		
			
		
	}
	
	public static boolean comprobarSenialExterna(){
	return true;
	}
	
}

/*

		//System.out.println("En binario es "+ ipf.convertDirtoBin("192.168.80.13"));
		//System.out.println("En decimal es "+ipf.convertDirtoDec("192.168.80.13"));
		//System.out.println("En direccion es "+ipf.convertDirection(3232256013L));

*/