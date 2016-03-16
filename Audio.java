import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

    /**
     * Abre un fichero de sonido wav y lo reproduce
     * @param args
     */
	
	public Audio() {} // constructor nulo
	 
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
            
			
            //Thread.sleep(5000);
			
            // Se cierra el clip.
            sonido.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

}