package FrcDeepSpace;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;

public class popUpWindow {

	private JFrame frame;
	JTextPane textPane = new JTextPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					popUpWindow window = new popUpWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public popUpWindow() {
		initialize();
		this.frame.setLocation((frame.getWidth()/2)+(this.frame.getWidth()/2), (frame.getHeight()/2)+(this.frame.getHeight()/2));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textPane.setBorder(null);
		textPane.setBackground(frame.getBackground());
		textPane.setBounds(0, 29, 434, 121);
		frame.getContentPane().add(textPane);
	}
}
