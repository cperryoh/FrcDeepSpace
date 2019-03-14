package FrcDeepSpace;

import java.io.File;

import javax.swing.JFileChooser;

public class fileChooser {
	JFileChooser chooser;
	fileChooser(){
		JFileChooser chooser = new JFileChooser();
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	}
	

}
