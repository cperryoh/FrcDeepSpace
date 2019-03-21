package FrcDeepSpace;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import static java.nio.file.StandardCopyOption.*;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class Scouting {

	private JFrame frame;
	private final Action action = new Enter();
	private JTextField team;
	JComboBox comboBoxClimb = new JComboBox();
	JComboBox ComboBoxPanel = new JComboBox();
	JComboBox ComboBoxCargo = new JComboBox();
	JTabbedPane CargoOrPanel = new JTabbedPane(JTabbedPane.TOP);
	String userHome = System.getProperty("user.home");
	File teamFolder = new File(userHome+"\\Desktop\\scouting");
	public static JTable table;
	static int selectedRow=0;
	JMenuItem mntmClearRow = new JMenuItem("Clear row");
	JMenuItem mntmDeleteRow = new JMenuItem("Delete row");
	DefaultTableModel tableModel= new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Game piece grabbed time", "Delivery time", "Delivery location (CS R#)", "Game piece delivered"
			}
	);
	private final Action action_1 = new SwingAction_1();
	private JTextField RoundNum;
	private JTextField textField;
	boolean enterable = false;
	JButton btnFileCreationLocation = new JButton("File creation location");
	private final Action action_2 = new SwingAction();
	private final Action action_3 = new SwingAction_2();
	private final Action action_4 = new SwingAction_3();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scouting window = new Scouting();
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
	public Scouting() {
		initialize();
		teamFolder.mkdir();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
    	int height =(int) screenSize.getHeight();
    	frame.setLocation((width/2)-(frame.getWidth()/2), (height/2)-(frame.getHeight()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 678, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setBounds(323, 374, 115, 29);
		btnEnter.setAction(action);
		frame.getContentPane().add(btnEnter);
		
		team = new JTextField();
		RoundNum = new JTextField();
		RoundNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(isNumber(team.getText())==false||isNumber(RoundNum.getText())==false) {
					btnEnter.setBackground(new Color(100,100,100));
					enterable=false;
					
				}
				else {
					enterable=true;
					btnEnter.setBackground(btnFileCreationLocation.getBackground());
				}
			}
		});
		team.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(isNumber(team.getText())==false||isNumber(RoundNum.getText())==false) {
					btnEnter.setBackground(new Color(100, 100, 100));
					enterable=false;
				}
				else {
					enterable=true;
					btnEnter.setBackground(btnFileCreationLocation.getBackground());
				}
			}
		});
		btnEnter.setBackground(new Color(104, 104, 104));
		enterable=false;
		team.setBounds(310, 11, 146, 26);
		frame.getContentPane().add(team);
		team.setColumns(10);
		
		JLabel lblTeam = new JLabel("Team:");
		lblTeam.setBounds(200, 14, 106, 20);
		lblTeam.setHorizontalAlignment(SwingConstants.TRAILING);
		frame.getContentPane().add(lblTeam);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON2) {
					selectedRow = table.rowAtPoint(e.getPoint());
					System.out.print(selectedRow);
				}
			}
		});
		scrollPane.setBounds(29, 219, 619, 114);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(null);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
					int [][] seleceted = new int[][] {table.getSelectedRows(),table.getSelectedColumns()};
					for(int x = 0; x < seleceted[0].length; x++) {
						for(int y = 0; y < seleceted[1].length; y++) {
							tableModel.setValueAt(null, seleceted[0][x], seleceted[1][y]);
						}
					}
				}
			}
		});
		table.setCellSelectionEnabled(true);
		
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(table, popupMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.setAction(action_2);
		popupMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mntmNewMenuItem_1.setAction(action_3);
		popupMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mntmNewMenuItem_2.setAction(action_4);
		popupMenu_1.add(mntmNewMenuItem_2);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				if(table.getSelectedColumns().length>1) {
					mntmClearRow.setText("Clear rows");
					mntmDeleteRow.setText("Delete rows");
				}
				else {
					mntmClearRow.setText("Clear row");
					mntmDeleteRow.setText("Delete row");
				}
			}
		});
		addPopup(table, popupMenu);
		
		mntmDeleteRow.setAction(action_3);
		popupMenu.add(mntmDeleteRow);
		
		
		mntmClearRow.setAction(action_4);
		popupMenu.add(mntmClearRow);
		
		JMenuItem mntmAddRow = new JMenuItem("Add row");
		mntmAddRow.setAction(action_2);
		popupMenu.add(mntmAddRow);
		
		textField = new JTextField();
		scrollPane.setColumnHeaderView(textField);
		textField.setColumns(10);
		btnFileCreationLocation.setBackground(new Color(240, 240, 240));
		
		
		btnFileCreationLocation.setAction(action_1);
		btnFileCreationLocation.setBounds(45, 374, 247, 29);
		frame.getContentPane().add(btnFileCreationLocation);
		
		
		RoundNum.setBounds(310, 50, 146, 26);
		frame.getContentPane().add(RoundNum);
		RoundNum.setColumns(10);
		
		JLabel lblRound = new JLabel("Round:");
		lblRound.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRound.setBounds(235, 53, 69, 20);
		frame.getContentPane().add(lblRound);
		CargoOrPanel.setBounds(177, 87, 338, 121);
		frame.getContentPane().add(CargoOrPanel);
		
		JPanel panel_1 = new JPanel();
		CargoOrPanel.addTab("Panel", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Rocket level:");
		label.setBounds(27, 32, 106, 20);
		panel_1.add(label);
		
		
		ComboBoxPanel.setModel(new DefaultComboBoxModel(new String[] {"N/A", "One", "Two", "Three"}));
		ComboBoxPanel.setBounds(130, 29, 86, 26);
		panel_1.add(ComboBoxPanel);
		
		
		JPanel Cargo = new JPanel();
		CargoOrPanel.addTab("Cargo", null, Cargo, null);
		Cargo.setLayout(null);
		
		JLabel lblRocketLevel = new JLabel("Rocket level:");
		lblRocketLevel.setBounds(14, 32, 106, 20);
		Cargo.add(lblRocketLevel);
		
		
		ComboBoxCargo.setModel(new DefaultComboBoxModel(new String[] {"N/A", "One", "Two", "Three"}));
		ComboBoxCargo.setBounds(130, 29, 86, 26);
		Cargo.add(ComboBoxCargo);
		
		JPanel panel = new JPanel();
		CargoOrPanel.addTab("End game location", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblEndGameClimb = new JLabel("End game climb:");
		lblEndGameClimb.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEndGameClimb.setBounds(0, 35, 127, 20);
		panel.add(lblEndGameClimb);
		
		
		comboBoxClimb.setModel(new DefaultComboBoxModel(new String[] {"N\\A", "1", "2", "3"}));
		comboBoxClimb.setBounds(139, 32, 77, 26);
		panel.add(comboBoxClimb);
	}

	private class Enter extends AbstractAction {
		public Enter() {
			putValue(NAME, "Enter");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		
		if(enterable) {
			try {
				System.out.println(teamFolder.getAbsolutePath());
				//loops through all folders in the scouting folder
				File f = new File(teamFolder.getAbsolutePath()+"\\"+team.getText());
				f.mkdir();
				//creates and populates text file
				PrintWriter writer = new PrintWriter(f.getAbsolutePath()+"\\Round"+RoundNum.getText()+".txt", "UTF-8");
				writer.println("Cargo highest level: "+ComboBoxCargo.getModel().getElementAt(ComboBoxCargo.getSelectedIndex())+"\n");
				writer.println("Panel higest level: "+ComboBoxPanel.getModel().getElementAt(ComboBoxPanel.getSelectedIndex())+"\n");
				writer.println("Climb level: "+comboBoxClimb.getModel().getElementAt(comboBoxClimb.getSelectedIndex())+"\n");
				//creates key
				
				ArrayList myList = new ArrayList();
				TableModel tableModle = table.getModel();
				
				for(int x = 0; x < tableModle.getColumnCount(); x++) {
					if(x<tableModle.getColumnCount()-1) {
						writer.print(tableModle.getColumnName(x)+", ");
					}
					else {
						writer.print(tableModle.getColumnName(x)+" ");
					}
				}
				
				//creates table
				writer.println();
				for(int i = 0; i < tableModle.getRowCount(); i++) {
					writer.print((i+1)+") ");
					for(int y = 0; y < tableModle.getColumnCount(); y++) {
						if(y<tableModle.getColumnCount()-1) {
							writer.print(" "+(String) tableModle.getValueAt(i, y)+", ");
						}
						else {
							writer.print(" "+(String) tableModle.getValueAt(i, y));
						}
					}
					writer.println("");
				}
				
				//calculating avg and printing other info
				Double time=0.0;
				Double amountOfEntrys=0.0;
				for(int i = 0; i < table.getRowCount(); i++) {
					if(isNumber(getCellValue(tableModle, i, 0))&&isNumber(getCellValue(tableModle, i, 1)) && Boolean.parseBoolean(getCellValue(tableModle, i, 3))) {
						double valueOne,valueTwo;
						amountOfEntrys++;
						valueOne = Double.parseDouble(((String)tableModle.getValueAt(i, 0)));
						valueTwo = Double.parseDouble(((String)tableModle.getValueAt(i, 1)));
						time+=(valueOne-valueTwo);
					}
				}
				Double avg =  time/amountOfEntrys;
				DecimalFormat dcf= new DecimalFormat("0.##");
				if(!Double.isNaN(avg)|| avg!=0.0) {
					writer.println("\n The averag time for team "+team.getText()+" to successfully place a game piece is "+dcf.format(avg)+" seconds.");
					writer.close();
				}
				else {
					System.out.println(avg);
					writer.println("Somthing went wrong while calculating the\n average, you might not have defined if the team was actually able to \nplace the hatch panel/cargo. You also may have not given one of the value required");
				}
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Choose file location");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(userHome +"\\Desktop");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.showDialog(frame, "Choose folder");
			String path = chooser.getSelectedFile().getAbsolutePath();
		    Path folder = Paths.get(path+"\\scouting");
			try {
				Files.move(teamFolder.toPath(), folder, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Add row");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			tableModel.addRow(new Object[]{});
		}
	}
	String getCellValue(TableModel tbl, int row, int cell) {
		return (String) tbl.getValueAt(row, cell);
	}
	boolean isNumber(String number) {
		boolean isNumber=false;
		try 
		{ 
			int num = Integer.parseInt(number);
			isNumber =  true;
		} 
		catch (NumberFormatException ex) {
			isNumber = false;
		}
		return isNumber;
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "clear row");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<table.getColumnCount(); i++) {
				tableModel.setValueAt(null, selectedRow, i);
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Delete row");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println(selectedRow);
			tableModel.removeRow(selectedRow);
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				selectedRow = table.rowAtPoint(e.getPoint());
				if (e.isPopupTrigger()) {
					selectedRow = table.rowAtPoint(e.getPoint());
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}