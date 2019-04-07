package FrcDeepSpace;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	 * 
	 * @throws IOException
	 */
	public gamePieceLocation(Scouting s) throws IOException {
		initialize();
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		main = s;
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {

		frame = new JFrame();

		frame.setResizable(false);
		frame.setBounds(100, 100, 456, 304);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton droppedCs = new JButton("Dropped cs");
		droppedCs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropped("cs");
			}
		});
		droppedCs.setBounds(10, 214, 114, 29);
		frame.getContentPane().add(droppedCs);

		JButton 
		droppedr1 = new JButton("Dropped r1");
		droppedr1.setBounds(155, 214, 114, 29);
		droppedr1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropped("r1");
			}
		});
		frame.getContentPane().add(droppedr1);

		JButton droppedR2 = new JButton("Dropped r2");
		droppedR2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropped("r2");
			}
		});
		droppedR2.setBounds(155, 174, 114, 29);
		frame.getContentPane().add(droppedR2);

		JButton droppedR3 = new JButton("Dropped r3");
		droppedR3.setBounds(155, 134, 114, 29);
		droppedR3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropped("r3");
			}
		});
		frame.getContentPane().add(droppedR3);

		ImageIcon icn = new ImageIcon(getClass().getClassLoader().getResource("rocket.PNG"));
		BufferedImage ref = ImageIO.read(getClass().getClassLoader().getResource("rocketRef.png"));
		JLabel Rocket = new JLabel(icn);
		Rocket.setSize(icn.getIconWidth(), icn.getIconHeight());
		Rocket.setBounds(271, 24, 169, 203);
		frame.getContentPane().add(Rocket);
		Rocket.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Color c = GetColor(ref, arg0.getPoint());
				if (isEqual(c, Color.white)) {
					main.tableModel.setValueAt("R3", main.currentRow, 2);
					if (main.hatch == false) {
						main.HighestCargo = 3;
						main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(3));
					}
					if (main.hatch) {
						main.HighestHatch = 3;
						main.ComboBoxPanel.getModel().setSelectedItem(main.ComboBoxPanel.getModel().getElementAt(3));
					}
					done();
				}
				if (isEqual(c, Color.red)) {
					main.tableModel.setValueAt("R2", main.currentRow, 2);
					if (main.HighestCargo < 1 && main.hatch == false) {
						main.HighestCargo = 2;
						main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(2));
					}
					if (main.HighestHatch < 1 && main.hatch) {
						main.HighestHatch = 2;
						main.ComboBoxPanel.getModel().setSelectedItem(main.ComboBoxPanel.getModel().getElementAt(2));
					}

					done();
				}
				if (isEqual(c, Color.blue)) {
					main.tableModel.setValueAt("R1", main.currentRow, 2);
					if (main.HighestCargo < 1 && main.hatch == false) {
						main.HighestCargo = 1;
						main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(1));
					}
					if (main.HighestHatch < 1 && main.hatch) {
						main.HighestHatch = 1;
						main.ComboBoxPanel.getModel().setSelectedItem(main.ComboBoxPanel.getModel().getElementAt(1));
					}

					done();
				}
			}
		});

		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("cargoShip.PNG"));
		JLabel CargoShip = new JLabel(icon);
		CargoShip.setSize(icon.getIconWidth(), icon.getIconHeight());

		CargoShip.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				main.tableModel.setValueAt("CS", main.currentRow, 2);
				done();
			}
		});
		CargoShip.setBounds(21, 11, 248, 112);
		frame.getContentPane().add(CargoShip);

	}

	boolean isEqual(Color one, Color two) {
		;
		if (one.getRed() > two.getRed() - 30 && one.getRed() < two.getRed() + 30) {
			if (one.getBlue() > two.getBlue() - 30 && one.getBlue() < two.getBlue() + 30) {
				if (one.getGreen() > two.getGreen() - 30 && one.getGreen() < two.getGreen() + 30) {
					return true;
				}
			}
		}
		return false;
	}

	Color GetColor(BufferedImage image, Point p) {
		return new Color(image.getRGB(p.x, p.y));
	}

	void dropped(String location) {
		main.tableModel.setValueAt(main.currentTime + " X", main.currentRow, 1);
		main.tableModel.setValueAt("Failed " + location, main.currentRow, 2);
		main.btnHatch.setVisible(true);
		main.btnCargo.setVisible(true);
		frame.setVisible(false);
		main.currentRow++;
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
			putValue(NAME, "Dropped cs");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			main.tableModel.setValueAt("CS", main.currentRow, 2);
			done();
		}

	}

	private class SwingAction_10 extends AbstractAction {
		public SwingAction_10() {

			putValue(NAME, "R1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {

			main.tableModel.setValueAt("R1", main.currentRow, 2);
			if (main.HighestCargo < 1 && main.hatch == false) {
				main.HighestCargo = 1;
				main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(1));
			}
			if (main.HighestHatch < 1 && main.hatch) {
				main.HighestHatch = 1;
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
			if (main.HighestCargo < 1 && main.hatch == false) {
				main.HighestCargo = 2;
				main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(2));
			}
			if (main.HighestHatch < 1 && main.hatch) {
				main.HighestHatch = 2;
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
			if (main.hatch == false) {
				main.HighestCargo = 3;
				main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(3));
			}
			if (main.hatch) {
				main.HighestHatch = 3;
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