package FrcDeepSpace;

import java.awt.EventQueue;
import java.awt.List;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Scouting {

	private JFrame frame;
	private final Action action = new Enter();
	private JTextField team;
	JComboBox comboBoxClimb = new JComboBox();
	JComboBox ComboBoxPanel = new JComboBox();
	JComboBox ComboBoxCargo = new JComboBox();
	String userHome = System.getProperty("user.home");
	File teamFolder = new File(userHome+"\\Desktop\\scouting");
	private JTable table;
	private DefaultTableModel tableModel =new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Game piece grabbed time", "Delivery time", "Delivery location (CS R#)", "Game piece delivered"
			});
	private final Action action_1 = new SwingAction_1();
	private JTextField RoundNum;
	private JTextField textField;
	private final Action action_2 = new SwingAction();
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 956, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(417, 379, 115, 29);
		btnEnter.setAction(action);
		frame.getContentPane().add(btnEnter);
		
		team = new JTextField();
		team.setBounds(414, 16, 146, 26);
		frame.getContentPane().add(team);
		team.setColumns(10);
		
		JLabel lblTeam = new JLabel("Team:");
		lblTeam.setBounds(296, 19, 106, 20);
		lblTeam.setHorizontalAlignment(SwingConstants.TRAILING);
		frame.getContentPane().add(lblTeam);
		
		JTabbedPane CargoOrPanel = new JTabbedPane(JTabbedPane.TOP);
		CargoOrPanel.setBounds(15, 75, 338, 121);
		frame.getContentPane().add(CargoOrPanel);
		
		JPanel Cargo = new JPanel();
		CargoOrPanel.addTab("Cargo", null, Cargo, null);
		Cargo.setLayout(null);
		
		JLabel lblRocketLevel = new JLabel("Rocket level:");
		lblRocketLevel.setBounds(27, 32, 106, 20);
		Cargo.add(lblRocketLevel);
		
		
		ComboBoxCargo.setModel(new DefaultComboBoxModel(new String[] {"N/A", "One", "Two", "Three"}));
		ComboBoxCargo.setBounds(130, 29, 86, 26);
		Cargo.add(ComboBoxCargo);
		
		JPanel panel_1 = new JPanel();
		CargoOrPanel.addTab("Panel", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Rocket level:");
		label.setBounds(27, 32, 106, 20);
		panel_1.add(label);
		
		
		ComboBoxPanel.setModel(new DefaultComboBoxModel(new String[] {"N/A", "One", "Two", "Three"}));
		ComboBoxPanel.setBounds(130, 29, 86, 26);
		panel_1.add(ComboBoxPanel);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 249, 934, 114);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE || arg0.getKeyCode()==KeyEvent.VK_DELETE) {
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
		
		textField = new JTextField();
		scrollPane.setColumnHeaderView(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Add row");
		btnNewButton.setAction(action_2);
		btnNewButton.setBounds(398, 204, 115, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnFileCreationLocation = new JButton("File creation location");
		btnFileCreationLocation.setAction(action_1);
		btnFileCreationLocation.setBounds(152, 379, 247, 29);
		frame.getContentPane().add(btnFileCreationLocation);
		
		RoundNum = new JTextField();
		RoundNum.setBounds(417, 55, 146, 26);
		frame.getContentPane().add(RoundNum);
		RoundNum.setColumns(10);
		
		JLabel lblRound = new JLabel("Round:");
		lblRound.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRound.setBounds(342, 58, 69, 20);
		frame.getContentPane().add(lblRound);
	}

	private class Enter extends AbstractAction {
		public Enter() {
			putValue(NAME, "Enter");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		
		if(team.getText()!="") {
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
				double time=0;
				double amountOfEntrys=0;
				for(int i = 0; i < table.getRowCount(); i++) {
					if(isNumber(getCellValue(tableModle, i, 0))&&isNumber(getCellValue(tableModle, i, 1)) && Boolean.parseBoolean(getCellValue(tableModle, i, 3))) {
						double valueOne,valueTwo;
						amountOfEntrys++;
						valueOne = Double.parseDouble(((String)tableModle.getValueAt(i, 0)));
						valueTwo = Double.parseDouble(((String)tableModle.getValueAt(i, 1)));
						time+=(valueOne-valueTwo);
						//System.out.println(valueOne-valueTwo);
					}
				}
				double avg =  time/amountOfEntrys;
				DecimalFormat dcf= new DecimalFormat("0.##");
				writer.println("\n The averag time for team "+team.getText()+" to successfully place panel is "+dcf.format(avg));
				writer.close();
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
}