 
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
 
public class ARPInfo{
 
	public ARPInfo() {} // constructor nulo
	
	public static boolean recursoDisponible(String ip) {
	String s = null;
	boolean exito= true;
        try {
			String comando;
			comando = "cmd /c arp -a > info/"+ip+".txt";
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
	
	public String getMAC(String ip){
	
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	String MAC= "";
 
		try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("info/"+ip+".txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
		 boolean encontrado=false;
 
			// Lectura del fichero
			String linea;
			
			while(((linea=br.readLine())!=null)&&(encontrado==false)){
				if (linea.contains(ip)==true){
					linea= linea.replace(ip,"");
					linea= linea.replace("dinamico","");
					linea= linea.replace("dinámico","");
					linea= linea.replace("din mico","");
					linea= linea.replace("estatico","");
					linea= linea.replace("estático","");
					linea= linea.replace("est tico","");
					linea= linea.replace(" ","");
					linea= linea.substring(0,17);
					linea= linea.replace(" ","");
					linea= linea.replace("","");
					MAC= linea;
					encontrado= true;
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
		
	return MAC;
	}
 
}