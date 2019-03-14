package FrcDeepSpace;

import java.io.File;

import javax.swing.JFileChooser;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (e.getSource() == openButton) {
	        int returnVal = fc.showOpenDialog(FileChooserDemo.this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            //This is where a real application would open the file.
	            log.append("Opening: " + file.getName() + "." + newline);
	        } else {
	            log.append("Open command cancelled by user." + newline);
	        }
	   } 
	}
	void run() {
	}

}
