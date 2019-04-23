package FrcDeepSpace;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class sound {

	Clip clip;
	AudioInputStream audioIn;

	public sound(File f) {
		    File yourFile;
		    AudioFormat format;
		    DataLine.Info info;
		    try {
		    	audioIn = AudioSystem.getAudioInputStream(f);
				format = audioIn.getFormat();
			    info = new DataLine.Info(Clip.class, format);
				clip = (Clip) AudioSystem.getLine(info);
			    clip.open(audioIn);
			    clip.start();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	void play() throws LineUnavailableException, IOException {
		clip.open(audioIn);
		clip.start();
	}
}