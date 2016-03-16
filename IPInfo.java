 
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

class IPInfo {

	public IPInfo() {} // constructor nulo
	
	public static boolean recursoDisponible() {
	String s = null;
	boolean exito= true;
        try {
			String comando;
			comando = "cmd /c ipconfig > info/ipaddress.txt";
            Process p = Runtime.getRuntime().exec(comando);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((s = stdInput.readLine()) != null) System.out.println(s);            
        } catch (IOException e) {
			//System.out.println("Excepción: ");
			e.printStackTrace();
			exito= false;
        }
	return exito;
	}
	
	public String getIP(){
	
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	String ip= "";
 
		try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("info/ipaddress.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
		 boolean encontrado=false;
 
			// Lectura del fichero
			String linea;
			
			while(((linea=br.readLine())!=null)&&(encontrado==false)){
				if ((linea.contains("Direcci")==true)&&(linea.contains("IPv4")==true)){
					linea= linea.replace(". . . . . . . . . . . . . . ","");
					linea= linea.replace("Direcci","");
					linea= linea.replace("n","");
					linea= linea.replace("IPv4","");
					linea= linea.replace(" ","");
					linea= linea.substring(2,15);
					encontrado= true;	
					ip= linea;
					//System.out.println(linea);		
				}
			}
			
		//System.exit(-1);
		}
		catch(Exception e){
         e.printStackTrace();
		}finally{
			try{                    
            if( null != fr ){   
               fr.close();     
            }                  
			}catch (Exception e2){ 
            e2.printStackTrace();
			}
		}
		
	return ip;
	}

}