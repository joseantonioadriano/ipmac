
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class utiles {

	public utiles() {} // constructor vacio
	
	public void escribir( String cadena )
	{
		System.out.println(cadena);
	}
	
	public static boolean crearFicheroVacio(String fichero, String titulo) {
	String s = null;
	boolean exito= true;
        try {
			String comando;
			comando = "cmd /c echo "+titulo+". > "+fichero;
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
	
	
	
}