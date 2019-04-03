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
import javax.tools.DocumentationTool.Location;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import static java.nio.file.StandardCopyOption.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
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
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;

public class Scouting {
	private JFrame frmMadeByCole;
	private final Action action = new Enter();
	private JTextField team;
	JComboBox ComboBoxClimb = new JComboBox();
	String sep = File.separator;
	public int[] cargoCycleTime=new int[table.getModel().getRowCount()+20];
	public int[] panelCycleTime=cargoCycleTime;
	JLabel timerLbl = new JLabel("150");
	JComboBox ComboBoxPanel = new JComboBox();
	boolean hatch=false;
	
	JComboBox ComboBoxCargo = new JComboBox();
	public int HighestCargo,HighestHatch=0;
	JTabbedPane CargoOrPanel = new JTabbedPane(JTabbedPane.TOP);
	String Desktop = System.getProperty("user.home")+sep+"Desktop";
	JComboBox Condition = new JComboBox();
	JComboBox level = new JComboBox();
	
	JComboBox Location = new JComboBox();
	File teamFolder = new File(Desktop+sep+"scouting");
	public static JTable table;
	static int selectedRow=0;
	File sa;
	static int interval = 150;
	static gamePieceLocation GPL;
	String currentTime="150";
	JButton btnStartMatch = new JButton("Start match");
    static Timer timer = new Timer();
    int currentRow=0;
    boolean hasStarted = false;
    boolean hasGamePiece= false;
    JButton btnCargo = new JButton("Taken cargo");
	JButton btnHatch = new JButton("Taken hatch");
	JButton btnEnter = new JButton("Enter");
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
				"Game piece grabbed time", "Delivery time", "Delivery location (CS R#)", "Hatch panel or cargo"
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
	private final Action action_5 = new SwingAction_4();
	private final Action action_6 = new SwingAction_5();
	private final Action action_7 = new SwingAction_6();
	private final Action action_8 = new SwingAction_7();
	private final Action action_9 = new SwingAction_8();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scouting window = new Scouting();
					GPL = new gamePieceLocation(window);
					window.frmMadeByCole.setVisible(true);
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
		sa=new File(teamFolder.getAbsolutePath()+sep+"data averages (don't open)");
		sa.mkdir();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
    	int height =(int) screenSize.getHeight();
    	frmMadeByCole.setLocation((width/2)-(frmMadeByCole.getWidth()/2), (height/2)-(frmMadeByCole.getHeight()));
    	table.setModel(tableModel);
    	timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
    	timerLbl.setFont(new Font("Tahoma", Font.PLAIN, 46));
    	
    	
    	timerLbl.setBounds(10, 59, 298, 90);
    	frmMadeByCole.getContentPane().add(timerLbl);
    	
    	btnStartMatch.setAction(action_6);
    	btnStartMatch.setBounds(69, 15, 177, 29);
    	frmMadeByCole.getContentPane().add(btnStartMatch);
    	
    	JButton btnReset = new JButton("Reset");
    	btnReset.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			reset();
    			
    		}
    	});
    	btnReset.setBounds(106, 152, 115, 29);
    	frmMadeByCole.getContentPane().add(btnReset);
    	btnHatch.setAction(action_7);
    	
    	btnHatch.setVisible(false);
    	btnHatch.setBounds(0, 52, 146, 29);
    	frmMadeByCole.getContentPane().add(btnHatch);
    	btnCargo.setAction(action_8);
    	
    	
    	btnCargo.setVisible(false);
    	btnCargo.setBounds(166, 52, 146, 29);
    	frmMadeByCole.getContentPane().add(btnCargo);
    	
    	JButton btnCompileData = new JButton("Compile data");
    	btnCompileData.setAction(action_9);
    	btnCompileData.setBounds(820, 30, 196, 29);
    	frmMadeByCole.getContentPane().add(btnCompileData);
    	
    	JMenuBar menuBar = new JMenuBar();
    	frmMadeByCole.setJMenuBar(menuBar);
    	
    	JMenu mnStartingGamePiece = new JMenu("Starting game piece");
    	menuBar.add(mnStartingGamePiece);
    	
    	JMenuItem mntmCargo = new JMenuItem("Cargo");
    	mntmCargo.setAction(action_8);
    	mnStartingGamePiece.add(mntmCargo);
    	
    	JMenuItem mntmHatch = new JMenuItem("Hatch");
    	mntmHatch.setAction(action_7);
    	mnStartingGamePiece.add(mntmHatch);
    	
    	JPopupMenu popupMenu = new JPopupMenu();
    	addPopup(frmMadeByCole, popupMenu);
    	
    	JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
    	mntmNewMenuItem_3.setAction(action_5);
    	popupMenu.add(mntmNewMenuItem_3);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMadeByCole = new JFrame();
		frmMadeByCole.setTitle("Made by Cole Perry from team 5567");
		frmMadeByCole.setResizable(false);
		frmMadeByCole.setBounds(100, 100, 1037, 466);
		frmMadeByCole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMadeByCole.getContentPane().setLayout(null);
		
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setBounds(574, 370, 115, 29);
		btnEnter.setAction(action);
		frmMadeByCole.getContentPane().add(btnEnter);
		
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
    	int height =(int) screenSize.getHeight();
		System.out.println(width+","+height);
		frmMadeByCole.setLocation((width/2)-(frmMadeByCole.getWidth()/2), (height/2)-(frmMadeByCole.getHeight()/2));
		btnEnter.setBackground(new Color(104, 104, 104));
		enterable=false;
		team.setBounds(484, 16, 146, 26);
		frmMadeByCole.getContentPane().add(team);
		team.setColumns(10);
		
		JLabel lblTeam = new JLabel("Team:");
		lblTeam.setBounds(374, 19, 106, 20);
		lblTeam.setHorizontalAlignment(SwingConstants.TRAILING);
		frmMadeByCole.getContentPane().add(lblTeam);
		
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
		scrollPane.setBounds(10, 219, 1011, 139);
		frmMadeByCole.getContentPane().add(scrollPane);
		
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
				if(arg0.getKeyCode()==KeyEvent.VK_CONTROL) {
					try {
						enter();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
		btnFileCreationLocation.setBounds(296, 370, 247, 29);
		frmMadeByCole.getContentPane().add(btnFileCreationLocation);
		
		
		RoundNum.setBounds(484, 53, 146, 26);
		frmMadeByCole.getContentPane().add(RoundNum);
		RoundNum.setColumns(10);
		
		JLabel lblRound = new JLabel("Round:");
		lblRound.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRound.setBounds(409, 56, 69, 20);
		frmMadeByCole.getContentPane().add(lblRound);
		CargoOrPanel.setBounds(351, 90, 420, 121);
		frmMadeByCole.getContentPane().add(CargoOrPanel);
		
		JPanel panel_1 = new JPanel();
		CargoOrPanel.addTab("Panel", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblHighestRocketLevel = new JLabel("Highest rocket level:");
		lblHighestRocketLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHighestRocketLevel.setBounds(0, 32, 209, 20);
		panel_1.add(lblHighestRocketLevel);
		
		
		ComboBoxPanel.setModel(new DefaultComboBoxModel(new String[] {"N/A", "One", "Two", "Three"}));
		ComboBoxPanel.setBounds(224, 29, 86, 26);
		panel_1.add(ComboBoxPanel);
		
		
		JPanel Cargo = new JPanel();
		CargoOrPanel.addTab("Cargo", null, Cargo, null);
		Cargo.setLayout(null);
		
		JLabel lblRocketLevel = new JLabel("Highest rocket level:");
		lblRocketLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRocketLevel.setBounds(0, 19, 204, 20);
		Cargo.add(lblRocketLevel);
		
		
		ComboBoxCargo.setModel(new DefaultComboBoxModel(new String[] {"N/A", "One", "Two", "Three"}));
		ComboBoxCargo.setBounds(214, 16, 86, 26);
		Cargo.add(ComboBoxCargo);
		
		JPanel panel = new JPanel();
		CargoOrPanel.addTab("End game location", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblEndGameClimb = new JLabel("End game climb:");
		lblEndGameClimb.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEndGameClimb.setBounds(0, 35, 127, 20);
		panel.add(lblEndGameClimb);
		
		
		ComboBoxClimb.setModel(new DefaultComboBoxModel(new String[] {"N\\A", "One", "Two", "Three"}));
		ComboBoxClimb.setBounds(139, 32, 77, 26);
		panel.add(ComboBoxClimb);
		
		JPanel panel_2 = new JPanel();
		CargoOrPanel.addTab("Disabilities", null, panel_2, null);
		
		
		Condition.setModel(new DefaultComboBoxModel(new String[] {"working","not working at all", "broken feature",  ""}));
		panel_2.add(Condition);
		
		JPanel panel_3 = new JPanel();
		CargoOrPanel.addTab("Staring location", null, panel_3, null);
		panel_3.setLayout(null);
		
		
		Location.setModel(new DefaultComboBoxModel(new String[] {"Left", "middle", "right"}));
		Location.setBounds(97, 16, 85, 26);
		panel_3.add(Location);
		
		
		level.setModel(new DefaultComboBoxModel(new String[] {"One", "two"}));
		level.setBounds(197, 16, 71, 26);
		panel_3.add(level);
	}
	void enter() throws IOException {
		if(enterable) {
			try {
				teamFolder=new File(Desktop+sep+"scouting");
				teamFolder.mkdir();
				//System.out.println(teamFolder.getAbsolutePath());
				//loops through all folders in the scouting folder
				File f = new File(teamFolder.getAbsolutePath()+sep+team.getText());
				f.mkdir();
				//creates and populates text file
				
				File[] files= teamFolder.listFiles();
				boolean foundFile=false;
				for(int i = 0; i < files.length; i++) {
					if(files[i].getName().equals("overView.txt")) {
						foundFile=true;
						System.out.println(files[i].getName());
						break;
					}
				}
				Writer  FW = new FileWriter (teamFolder.getAbsolutePath()+sep+"overView.txt",true);
				BufferedWriter writer = new BufferedWriter(FW);
				if(foundFile==false) {
					writer.write("Team number,highest cargo,higest panel,starting location,robot condition,climb level");
				}
				writer.newLine();
				writer.write(team.getText()+","+ComboBoxValue(ComboBoxCargo)+","+ComboBoxValue(ComboBoxPanel)+","+ComboBoxValue(Location)+":"+ComboBoxValue(level)+","+ComboBoxValue(Condition)+","+ComboBoxValue(ComboBoxClimb));
				writer.close();
				FW.close();
				PrintWriter writer2 = new PrintWriter(f.getAbsolutePath()+sep+"Round "+RoundNum.getText()+".txt", "UTF-8");
				//creates key
				
				
				ArrayList myList = new ArrayList();
				TableModel tableModle = table.getModel();
				
				writer2.print("Team number, round number,");
				for(int x = 0; x < tableModle.getColumnCount(); x++) {
					if(x<tableModle.getColumnCount()-1) {
						writer2.print(tableModle.getColumnName(x)+",");
					}
					else {
						writer2.print(tableModle.getColumnName(x)+" ");
					}
					
				}
				writer2.println();
				//creates table
				int[][] hatchTimes= new int[2][tableModle.getRowCount()],cargoTimes = new int[2][tableModle.getRowCount()];
				for(int x = 0; x < tableModle.getRowCount(); x++) {
					if(tableModel.getValueAt(x, 0)!=null) {
						writer2.print(team.getText()+","+RoundNum.getText()+",");
					}
					for(int y = 0; y < tableModle.getColumnCount(); y++) {
						if(y<tableModle.getColumnCount()-1&&tableModel.getValueAt(x, y)!=null) {
							writer2.print((String) tableModle.getValueAt(x, y)+",");
						}
						else if(tableModel.getValueAt(x, y)!=null){
							writer2.print((String) tableModle.getValueAt(x, y));
						}
						
					}
					if(x!=tableModle.getRowCount()-1 &&tableModel.getValueAt(x, 0)!=null) {
						writer2.println("");
					}
					//calculates the averages 
					if(getTableValue(tableModle,x,3).startsWith("hatch") && (!getTableValue(tableModle,x,2).equals("Failed"))) {
						hatchTimes[0][x]=Integer.parseInt(getTableValue(tableModel,x, 0));
						hatchTimes[1][x]=Integer.parseInt(getTableValue(tableModel,x, 1));
					}
					else if (!getTableValue(tableModle,x,2).equals("Failed")) {
						cargoTimes[0][x]=Integer.parseInt(getTableValue(tableModel,x, 0));
						cargoTimes[1][x]=Integer.parseInt(getTableValue(tableModel,x, 1));
					}
				}
				writer2.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			reset();
			
		}
	}
	String getTableValue(TableModel table, int x, int y){
		return (String) table.getValueAt(x, y);
	}
	private class Enter extends AbstractAction {
		public Enter() {
			putValue(NAME, "Enter");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				enter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	
}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Choose file location");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(Desktop);
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.showDialog(frmMadeByCole, "Choose folder");
			String path = chooser.getSelectedFile().getAbsolutePath();
		    Path folder = Paths.get(path+sep+"scouting");
			try {
				Files.move(teamFolder.toPath(), folder, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	void resetComboBox (JComboBox box) {
		box.getModel().setSelectedItem(box.getModel().getElementAt(0));
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
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Clear all fields");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			reset();
		}
	}
	void timer() {
        int delay = 1000;
        int period = 1000;
        timerLbl.setText("150");
        timer= new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
            	if(hasStarted==true) {
            		currentTime = Integer.toString(setInterval());
            		timerLbl.setText(currentTime);
            	}
            	if(Integer.parseInt(currentTime)==0) {
            		hasStarted=false;
            		btnStartMatch.setText("Start match");
            		btnStartMatch.setVisible(true);
            		GPL.frame.setVisible(false);
            		btnHatch.setVisible(false);
            		btnCargo.setVisible(false);
            		
            	}

            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "Start match");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(hasStarted==false) {
				hasStarted=true;
				timer();
				if(hasGamePiece==false) {
					btnCargo.setVisible(true);
    				btnHatch.setVisible(true);
    				GPL.frame.setVisible(false);
				}
				else {
					btnCargo.setVisible(false);
    				btnHatch.setVisible(false);
    				GPL.frame.setVisible(true);
				}
    			btnStartMatch.setVisible(false);
				btnStartMatch.setText("Pick up game piece");
			}
		}
	}
	private class SwingAction_6 extends AbstractAction {
		public SwingAction_6() {
			putValue(NAME, "Taken hatch");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(currentRow == table.getRowCount()) {
				tableModel.addRow(new Object[]{});
			}
			if(Integer.parseInt(currentTime)>=135) {
				table.setValueAt("hatch(sand storm)", currentRow, 3);
			}
			else {
				table.setValueAt("hatch", currentRow, 3);
			}
			btnHatch.setVisible(false);
			hatch=true;
			btnCargo.setVisible(false);
			GPL.frame.setLocation(frmMadeByCole.getX()-GPL.frame.getWidth(), frmMadeByCole.getY());
			if(hasStarted==true) {
				GPL.frame.setVisible(true);
			}
			else {
				GPL.frame.setVisible(false);
			}
			hasGamePiece=true;
			table.setValueAt(currentTime, currentRow, 0);
		}
	}
	private class SwingAction_7 extends AbstractAction {
		public SwingAction_7() {
			putValue(NAME, "Taken cargo");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(currentRow == table.getRowCount()) {
				tableModel.addRow(new Object[]{});
			}
			if(Integer.parseInt(currentTime)>=135) {
				table.setValueAt("cargo(sand storm)", currentRow, 3);
			}
			else {
				table.setValueAt("cargo", currentRow, 3);
			}
			btnHatch.setVisible(false);
			hatch=false;
			btnCargo.setVisible(false);
			GPL.frame.setLocation(frmMadeByCole.getX()-GPL.frame.getWidth(), frmMadeByCole.getY());
			if(hasStarted==true) {
				GPL.frame.setVisible(true);
			}
			else {
				GPL.frame.setVisible(false);
			}
			hasGamePiece=true;
			table.setValueAt(currentTime, currentRow, 0);
				
		}
	}
	String ComboBoxValue(JComboBox box){
		return (String) box.getModel().getElementAt(box.getSelectedIndex());
	}
	void reset(){
		currentRow=0;
		resetComboBox(ComboBoxPanel);
		resetComboBox(ComboBoxCargo);
		resetComboBox(ComboBoxClimb);
		resetComboBox(Location);
		resetComboBox(level);
		
		resetComboBox(Condition);
		team.setText("");
		RoundNum.setText("");
		for(int x = 0; x < tableModel.getRowCount(); x++) {
			for(int y = 0; y <tableModel.getColumnCount(); y++) {
				tableModel.setValueAt("", x, y);
			}
		}
		GPL.frame.setVisible(false);
		btnHatch.setVisible(false);
		btnCargo.setVisible(false);
		btnStartMatch.setVisible(true);
		btnStartMatch.setText("Start match");
		hasStarted=false;
		hasGamePiece=false;
		interval=150;
		currentTime="150";
		timerLbl.setText("150");
		timer.cancel();
		
	}
	private class SwingAction_8 extends AbstractAction {
		public SwingAction_8() {
			putValue(NAME, "Compile data");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			BufferedWriter writer=null;
			try {
				writer = new BufferedWriter(new FileWriter (Desktop+sep+"scouting.txt"));
				enter();
				writer.write("Team number,highest cargo,higest panel,starting location,robot condition,climb level");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			File[] textFiles = new File[teamFolder.listFiles().length-1];
			try {
				for(int x = 0; x < textFiles.length; x++) {
					File currentFoleder = teamFolder.listFiles()[x]; 
					if(currentFoleder.isDirectory()) {
						for(int i = 0; i < currentFoleder.listFiles().length; i++) {
							textFiles[i] = currentFoleder.listFiles()[i];
							BufferedReader r = new BufferedReader (new FileReader(textFiles[i]));
							String throwAway = r.readLine();
							String st;
							while((st = r.readLine())!=null) {
								writer.write(st);
								writer.newLine();
							}
						}
					}
				}
				writer.close();
			}
			catch(IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	String ArrayToString(Object[]arr){
		String value="";
		for(int i = 0; i < arr.length; i++) {
			
		}
		return value;
	}
}