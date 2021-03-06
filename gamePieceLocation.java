package FrcDeepSpace;

import java.awt.Color;
 
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;

public class gamePieceLocation {

	JFrame frame;	
	JButton droppedR3 = new JButton("Dropped r3");
	JButton droppedR2 = new JButton("Dropped r2");
	JButton droppedr1 = new JButton("Dropped r1");
	JButton droppedCs = new JButton("Dropped cs");
	Scouting main = null;
	int heightRatio,widthRatio;
	public gamePieceLocation(Scouting s) throws IOException {
		initialize();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		
		
		
		main = s;
	}
	private void initialize() throws IOException {

		frame = new JFrame();

		frame.setResizable(true);
		frame.setBounds(100, 100, 456, 304);

		
		
		
		droppedCs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropped("cs");
			}
		});
		frame.getContentPane().setLayout(new MigLayout("", "[114px,grow][31px,grow][114px,grow][169px,grow]", "[112px,grow][29px,grow][29px,grow][29px,grow]"));
		frame.getContentPane().add(droppedCs, "cell 0 3,grow");

		
		
		droppedr1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropped("r1");
			}
		});
		frame.getContentPane().add(droppedr1, "cell 2 3,grow");

		
		
		droppedR2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropped("r2");
			}
		});
		frame.getContentPane().add(droppedR2, "cell 2 2,grow");

		
		
		droppedR3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropped("r3");
			}
		});
		frame.getContentPane().add(droppedR3, "cell 2 1,grow");

		ImageIcon icn = new ImageIcon(getClass().getClassLoader().getResource("rocket.PNG"));
		BufferedImage ref = ImageIO.read(getClass().getClassLoader().getResource("rocketRef.png"));
		JLabel Rocket = new JLabel(icn);
		frame.getContentPane().add(Rocket, "cell 3 0 1 4,growx,aligny center");
		Rocket.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Color c = GetColor(ref, arg0.getPoint());
				if (isEqual(c, Color.white)) {
					rocket("R3");
				}
				if (isEqual(c, Color.red)) {
					rocket("R2");
				}
				if (isEqual(c, Color.blue)) {
					rocket("R1");
				}
			}
		});

		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("cargoShip.PNG"));
		JLabel CargoShip = new JLabel(icon);

		CargoShip.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				main.tableModel.setValueAt("CS", main.currentRow, 2);
				done();
			}
		});
		frame.getContentPane().add(CargoShip, "cell 0 0 3 1,grow");
	}
	void rocket(String levelString){
		int level = Character.getNumericValue(levelString.charAt(1));
		main.tableModel.setValueAt(levelString, main.currentRow, 2);
		if (main.HighestCargo <= level && main.hatch == false) {
			main.HighestCargo = level;
			main.ComboBoxCargo.getModel().setSelectedItem(main.ComboBoxCargo.getModel().getElementAt(level));
		}
		if (main.HighestHatch <= level && main.hatch) {
			main.HighestHatch = level;
			main.ComboBoxPanel.getModel().setSelectedItem(main.ComboBoxPanel.getModel().getElementAt(level));
		}

		done();
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
		main.tableModel.setValueAt(main.currentTime, main.currentRow, 1);
		main.tableModel.setValueAt("Failed " + location, main.currentRow, 2);
		main.btnHatch.setVisible(true);
		main.btnCargo.setVisible(true);
		frame.setVisible(false);
		if (Integer.parseInt(main.currentTime) >= 135) {
			main.leftPlatform.getModel().setSelectedItem(main.leftPlatform.getModel().getElementAt(1));
		}
		else {
			if(main.hatch) {
				main.table.setValueAt("hatch", main.currentRow, 3);
			}
			else {
				main.table.setValueAt("cargo", main.currentRow, 3);
			}
		}
		main.currentRow++;
	}

	private class SwingAction_8 extends AbstractAction {
		public SwingAction_8() {
			putValue(NAME, "CS");
			
		}

		public void actionPerformed(ActionEvent e) {
			main.tableModel.setValueAt("CS", main.currentRow, 2);
			done();
		}
	}

	private class SwingAction_9 extends AbstractAction {
		public SwingAction_9() {
			putValue(NAME, "Dropped cs");
			
		}

		public void actionPerformed(ActionEvent e) {
			main.tableModel.setValueAt("CS", main.currentRow, 2);
			done();
		}

	}

	private class SwingAction_10 extends AbstractAction {
		public SwingAction_10() {
			putValue(NAME, "R1");
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
		if (Integer.parseInt(main.currentTime) >= 135) {
			main.leftPlatform.getModel().setSelectedItem(main.leftPlatform.getModel().getElementAt(1));
		}
		else {
			if(main.hatch) {
				main.table.setValueAt("hatch", main.currentRow, 3);
			}
			else {
				 
				main.table.setValueAt("cargo", main.currentRow, 3);
			}
		}
		frame.setVisible(false);
		main.table.setValueAt(main.currentTime, main.currentRow, 1);
		main.currentRow++;

	}
}