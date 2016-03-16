
import java.io.IOException;
import java.net.InetAddress;

public class Ping { 
	
	private InetAddress ping; 
	private String dirIP = ""; // Ip de la máquina remota 
	
	public Ping() {} // constructor nulo
	
	public void setIP(String dIP){
	this.dirIP= dIP;
	}
	
	// @param dIP direccion IP a la que se le hace el ping
	// @param tiempo tiempo de espera de la peticion ping
	public boolean hacerPing(int tiempo){
	boolean exito= false;
		if (dirIP!="") {
			try 
			{ 
				ping = InetAddress.getByName(dirIP);
				if(ping.isReachable(tiempo)) exito= true;
			} catch (IOException ex) { exito= false; }
		}
	// @return exito devuelve true si la direccion ha respondido
	return exito;
	}
	 
} 