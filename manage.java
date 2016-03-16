
import java.io.*;
import java.net.InetAddress;

public class manage {

	public manage() {} // constructor nulo
	
	public boolean MACpermitida(String mac){
	
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	boolean encontrado=false;
 
		try {

		 utiles ut= new utiles();
		 archivo = new File ("db/allowMACs.txt");
		 
			if (!archivo.exists()) ut.crearFicheroVacio("db/allowMACs.txt","MAC Allows list");
		 
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
		 			
			String linea;
			
			while(((linea=br.readLine())!=null)&&(encontrado==false))
				if (linea.contains(mac)==true) encontrado= true;

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
		
	return encontrado;
	}
	
	public String getMACbyIP(String ip){
	
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	boolean encontrado=false;
	String mac= ip;
 
		try {

		 utiles ut= new utiles();
		 archivo = new File ("db/connectedMACs.txt");
		 
			if (!archivo.exists()) ut.crearFicheroVacio("db/connectedMACs.txt","MAC IP Connected list");
		 
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
		 			
			String linea;
			
			while(((linea=br.readLine())!=null)&&(encontrado==false)){
				if (linea.contains("*"+ip+"*")==true) {
					encontrado= true;					
					linea= linea.replace("*"+ip+"*","");
					linea= linea.replace(" ","");
					mac= linea;

				}
			}

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
		
	return mac;
	}
	
	public String getNombrebyMAC(String mac){
	
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	boolean encontrado=false;
	String nombre= mac;
 
		try {

		 utiles ut= new utiles();
		 archivo = new File ("db/allowMACs.txt");
		 
			if (!archivo.exists()) ut.crearFicheroVacio("db/allowMACs.txt","MAC Allowed list");
		 
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
		 			
			String linea;
			
			while(((linea=br.readLine())!=null)&&(encontrado==false)){
				if (linea.contains(mac+"*")==true) {
					encontrado= true;					
					linea= linea.replace(mac+"*","");	
					linea= linea.replace(" ","");
					nombre= linea;
				}
			}

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
		
	return nombre;
	}
	
	public boolean MACConectado(String mac, String ip){
	
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	boolean encontrado=false;
 
		try {

		 utiles ut= new utiles();
		 archivo = new File ("db/connectedMACs.txt");
		 
			if (!archivo.exists()) ut.crearFicheroVacio("db/connectedMACs.txt","MAC IP Connected list");
		 
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
		 			
			String linea;
			
			while(((linea=br.readLine())!=null)&&(encontrado==false)){
				if (linea.contains("*"+ip+"*")==true) {
					encontrado= true;					
					System.out.println("Se ha encontrado en la lista con ip: " + ip + " linea: " + linea);
				}
			}

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
		
	return encontrado;
	}
	
	public boolean MACConectado(String mac){
	
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	boolean encontrado=false;
 
		try {

		 utiles ut= new utiles();
		 archivo = new File ("db/connectedMACs.txt");
		 
			if (!archivo.exists()) ut.crearFicheroVacio("db/connectedMACs.txt","MAC IP Connected list");
		 
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
		 			
			String linea;
			
			while(((linea=br.readLine())!=null)&&(encontrado==false))
			{	
				if (linea.contains(mac)==true) {
					encontrado= true;
				}
			}

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
		
	return encontrado;
	}
	
	public boolean MACBorrarConexion (String ip) {
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	boolean encontrado=false;
	String lineasFichero= "";
	boolean exito= true;
 
		try {

		 utiles ut= new utiles();
		 archivo = new File ("db/connectedMACs.txt");
		 
			if (!archivo.exists()) ut.crearFicheroVacio("db/connectedMACs.txt","MAC IP Connected list");
		 
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
		 			
			String linea;
			int i=0;
			while(((linea=br.readLine())!=null)){
				if (linea.contains("*"+ip+"*")==true) 
				{
					encontrado= true;
					linea= "";
				}
				if (linea!=""){
					String s = null;
					try {
					String comando;
					comando = "cmd /c echo."+linea+" >> db/connectedMACstmp.txt";	
					Process p = Runtime.getRuntime().exec(comando);

					BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
					BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
					while ((s = stdInput.readLine()) != null) System.out.println(s);            
					} catch (IOException e) {
					e.printStackTrace();
					exito= false;
					}
				}
				linea= "";i++;
			}

		}
		catch(Exception e){
         e.printStackTrace();
		}finally{
			try{                    
            if( null != fr ){  
			   exito= false;
               fr.close();     
            }                  
			}catch (Exception e2){   
			   exito= false;
			   e2.printStackTrace();
			}
		}
		
		if (encontrado==true){
		String s = null;
			try {
				String comando;
				comando = "cmd /c rutina.bat";	
				Process p = Runtime.getRuntime().exec(comando);

				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while ((s = stdInput.readLine()) != null) System.out.println(s);            
			} catch (IOException e) {
				e.printStackTrace();
				exito= false;
			}
		}
	return exito;
	}
	
	public boolean MACRegistrarConexion (String mac, String ip) {
	String s = null;
	boolean exito= true;
        try {
			String comando;
			comando = "cmd /c echo."+mac+"*"+ip+"* >> db/connectedMACs.txt";	
            Process p = Runtime.getRuntime().exec(comando);
			System.out.println("  Dispositivo registrado en las conexiones");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((s = stdInput.readLine()) != null) System.out.println(s);            
        } catch (IOException e) {
			e.printStackTrace();
			exito= false;
        }
	return exito;
	}
}