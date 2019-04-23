package FrcDeepSpace;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.JTextArea;

public class popUpWindow {

	private JFrame frame;
	JButton btnEnter = new JButton("New button");
	int ratio;
	private final Action action = new SwingAction();
	final JTextPane messageBox = new JTextPane();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public popUpWindow() {
		initialize();
		frame.setBounds(100, 100, 574, 191);
	}

	JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void display(JFrame frame, String title, String message, String buttonText) {
		initialize();
		this.frame.setSize(frame.getWidth()/4,frame.getHeight()/4);
		btnEnter.setText(buttonText);
		messageBox.setText(message);
		this.frame.setTitle(title);
		this.frame.setVisible(true);
		
		this.frame.setLocation((frame.getWidth() / 2) + (this.frame.getWidth() / 2),(frame.getHeight() / 2) + (this.frame.getHeight()));
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
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[434px,grow]", "[152px,grow][grow]"));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		messageBox.setEditable(false);
		
		frame.getContentPane().add(messageBox, "cell 0 0,grow");
		

		btnEnter.setAction(action);
		frame.getContentPane().add(btnEnter, "cell 0 1,alignx center,aligny top");
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