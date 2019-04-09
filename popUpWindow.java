package FrcDeepSpace;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class popUpWindow {

	private JFrame frame;
	JLabel messageBox = new JLabel("place holder");
	JButton btnEnter = new JButton("New button");
	private final Action action = new SwingAction();
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public popUpWindow() {
		initialize();                                                                                                                                         
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void display(JFrame frame, String title, String message, String buttonText){
		initialize();
		btnEnter.setText(buttonText);
		messageBox.setText(message);
		this.frame.setTitle(title);
		this.frame.setVisible(true);
		this.frame.setLocation((frame.getWidth()/2)+(this.frame.getWidth()/2), (frame.getHeight()/2)+(this.frame.getHeight()));
		this.frame.setResizable(false);    
	}
	private void initialize() {
		frame = new JFrame();
		frame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				frame.toFront();
				frame.requestFocus();
			}
		});
		frame.setBounds(100, 100, 450, 191);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		btnEnter.setAction(action);
		btnEnter.setBounds(173, 100, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		
		messageBox.setHorizontalAlignment(SwingConstants.CENTER);
		messageBox.setBounds(0, 0, 434, 115);
		frame.getContentPane().add(messageBox);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, btnEnter.getText());
			
		}
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
}