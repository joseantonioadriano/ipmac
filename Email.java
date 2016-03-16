import java.io.*;

public class Email
{
	public Email(){} // constructor nulo
	
	public boolean enviar(String cad){
	String s = null;
	boolean exito= true;
        try {
			String comando;
			comando = "cmd /c start chrome http://servidor.com/notificacion.php?pmensaje="+cad;	
            Process p = Runtime.getRuntime().exec(comando);
			System.out.println("  Dispositivo registrado en las conexiones");
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