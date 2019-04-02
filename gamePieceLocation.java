package FrcDeepSpace;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
<<<<<<< HEAD
import javax.swing.WindowConstants;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
=======
>>>>>>> bb1766060f4859f7183628316565e3cb6fe3c556
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class gamePieceLocation {

	JFrame frame;
	Scouting main = null;
	private final Action action_9 = new SwingAction_8();
	private final Action action_10 = new SwingAction_9();
	private final Action action_11 = new SwingAction_10();
	private final Action action_12 = new SwingAction_11();
	private final Action action = new SwingAction();

	/**
	 * Create the application.
	 */
	public gamePieceLocation(Scouting s) {
		initialize();
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		main = s;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 255, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("New button");
		button.setAction(action_9);
		button.setBounds(15, 16, 51, 29);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setAction(action_10);
		button_1.setBounds(75, 16, 93, 29);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setAction(action_11);
		button_2.setBounds(177, 16, 51, 29);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("New button");
		button_3.setAction(action);
		button_3.setBounds(177, 90, 51, 29);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("New button");
		button_4.setAction(action_12);
		button_4.setBounds(177, 53, 51, 29);
		frame.getContentPane().add(button_4);
	}
	private class SwingAction_8 extends AbstractAction {
		public SwingAction_8() {
			putValue(NAME, "CS");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			main.tableModel.setValueAt("CS", main.currentRow, 2);
			done();
		}
	}
	private class SwingAction_9 extends AbstractAction {
		public SwingAction_9() {
			putValue(NAME, "Dropped");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			main.tableModel.setValueAt(main.currentTime+" X", main.currentRow, 1);
			main.tableModel.setValueAt("Failed", main.currentRow, 2);
			main.btnHatch.setVisible(true);
			main.btnCargo.setVisible(true);
			frame.setVisible(false);
			main.currentRow++;
			
		}
		
	}
	private class SwingAction_10 extends AbstractAction {
		public SwingAction_10() {
			
			putValue(NAME, "R1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {

			main.tableModel.setValueAt("R1", main.currentRow, 2);
			if(main.HighestCargo<1 && main.hatch==false) {
				main.HighestCargo=1;
				main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(1));
			}
			if(main.HighestHatch<1 && main.hatch) {
				main.HighestHatch=1;
				main.ComboBoxPanel.getModel().setSelectedItem(main.ComboBoxPanel.getModel().getElementAt(1));
			}
			
			done();
		}
	}
	private class SwingAction_11 extends AbstractAction {
		public SwingAction_11() {
			putValue(NAME, "R2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			main.tableModel.setValueAt("R2", main.currentRow, 2);
			if(main.HighestCargo<1 && main.hatch==false) {
				main.HighestCargo=2;
				main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(2));
			}
			if(main.HighestHatch<1 && main.hatch) {
				main.HighestHatch=2;
				main.ComboBoxPanel.getModel().setSelectedItem(main.ComboBoxPanel.getModel().getElementAt(2));
			}
			
			done();
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "R3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			main.tableModel.setValueAt("R3", main.currentRow, 2);
			if(main.hatch==false) {
				main.HighestCargo=3;
				main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(3));
			}
			if(main.hatch) {
				main.HighestHatch=3;
				main.ComboBoxPanel.getModel().setSelectedItem(main.ComboBoxPanel.getModel().getElementAt(3));
			}
			
			done();
		}
	}
	void done() {
		main.btnHatch.setVisible(true);
		main.btnCargo.setVisible(true);
		frame.setVisible(false);
		main.table.setValueAt(main.currentTime, main.currentRow, 1);
		main.currentRow++;
		
	}
	
}

