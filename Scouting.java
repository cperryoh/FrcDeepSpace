
package FrcDeepSpace;

import java.awt.EventQueue;
//import java.awt.List;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
//import java.awt.BorderLayout;
import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
//import javax.tools.DocumentationTool.Location;
import javax.swing.JPanel;
import javax.swing.JButton;
//import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.event.ActionListener;

public class Scouting {

	JFrame frame;
	popUpWindow messageBox = new popUpWindow();
	private final Action action = new Enter();
	JButton btnAddFoul = new JButton("Add foul");
	private JTextField team;
	int fouls = 0;
	JComboBox ComboBoxClimb = new JComboBox();
	String sep = File.separator;
	JLabel timerLbl = new JLabel("150");
	JComboBox ComboBoxPanel = new JComboBox();
	boolean hatch = false;
	JComboBox Defended = new JComboBox();
	JComboBox DefendedAgainst = new JComboBox();
	JComboBox ComboBoxCargo = new JComboBox();
	public int HighestCargo=0, HighestHatch = 0;
	JTabbedPane CargoOrPanel = new JTabbedPane(JTabbedPane.TOP);
	String Desktop = System.getProperty("user.home") + sep + "Desktop";
	JComboBox Condition = new JComboBox();
	JComboBox level = new JComboBox();
	JComboBox Location = new JComboBox();
	JComboBox failedClimb = new JComboBox();
	File teamFolder = new File(Desktop + sep + "scouting");
	public static JTable table;
	static int selectedRow = 0;
	static int interval = 150;
	static gamePieceLocation GPL;
	String currentTime = "150";
	JButton btnStartMatch = new JButton("Start match");
	static Timer timer = new Timer();
	int currentRow = 0;
	boolean hasStarted = false;
	boolean hasGamePiece = false;
	JButton btnCargo = new JButton("Taken cargo");
	JComboBox Penaltys = new JComboBox();
	JButton btnHatch = new JButton("Taken hatch");
	JButton btnEnter = new JButton("Enter");
	JMenuItem mntmClearRow = new JMenuItem("Clear row");
	JMenuItem mntmDeleteRow = new JMenuItem("Delete row");
	DefaultTableModel tableModel = new DefaultTableModel(
			new Object[][] { 
				{ null, null, null, null }, 
				{ null, null, null, null }, 
				{ null, null, null, null },
				{ null, null, null, null }, 
				{ null, null, null, null }, 
				{ null, null, null, null }, },
			new String[] { "Game piece grabbed time", "Delivery time", "Delivery location (CS R#)",
					"Hatch panel or cargo" });
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scouting window = new Scouting();
					GPL = new gamePieceLocation(window);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Scouting() throws IOException {
		initialize();
		teamFolder.mkdir();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// places window in the middle of the screen
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		frame.setLocation((width / 2) - (frame.getWidth() / 2), (height / 2) - (frame.getHeight() / 2));
		table.setModel(tableModel);
		timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		timerLbl.setFont(new Font("Tahoma", Font.PLAIN, 46));

		timerLbl.setBounds(10, 59, 298, 90);
		frame.getContentPane().add(timerLbl);

		btnStartMatch.setAction(action_6);
		btnStartMatch.setBounds(69, 15, 177, 29);
		frame.getContentPane().add(btnStartMatch);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();

			}
		});
		btnReset.setBounds(106, 152, 115, 29);
		frame.getContentPane().add(btnReset);
		btnHatch.setAction(action_7);

		btnHatch.setVisible(false);
		btnHatch.setBounds(0, 52, 146, 29);
		frame.getContentPane().add(btnHatch);
		btnCargo.setAction(action_8);

		frame.setResizable(false);
		btnCargo.setVisible(false);
		btnCargo.setBounds(166, 52, 146, 29);
		frame.getContentPane().add(btnCargo);

		JButton btnCompileData = new JButton("Compile data");
		btnCompileData.setAction(action_9);
		btnCompileData.setBounds(820, 30, 196, 29);
		frame.getContentPane().add(btnCompileData);

		
		btnAddFoul.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnAddFoul.setText("Foul added");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
				btnAddFoul.setText("Add foul");
			}
		});
		// add foul action listener
		btnAddFoul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fouls++;
			}
		});
		btnAddFoul.setBounds(275, 18, 125, 23);
		frame.getContentPane().add(btnAddFoul);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnStartingGamePiece = new JMenu("Starting game piece");
		menuBar.add(mnStartingGamePiece);

		JMenuItem mntmCargo = new JMenuItem("Cargo");
		mntmCargo.setAction(action_8);
		mnStartingGamePiece.add(mntmCargo);

		JMenuItem mntmHatch = new JMenuItem("Hatch");
		mntmHatch.setAction(action_7);
		mnStartingGamePiece.add(mntmHatch);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(frame, popupMenu);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mntmNewMenuItem_3.setAction(action_5);
		popupMenu.add(mntmNewMenuItem_3);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("Made by Cole Perry from team 5667");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1037, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setBounds(574, 370, 115, 29);
		btnEnter.setAction(action);
		frame.getContentPane().add(btnEnter);

		team = new JTextField();
		RoundNum = new JTextField();

		// key listener for enabling/disabling the finish button
		RoundNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (isNumber(team.getText()) == false || isNumber(RoundNum.getText()) == false) {
					btnEnter.setBackground(new Color(100, 100, 100));
					enterable = false;

				} else {
					enterable = true;
					btnEnter.setBackground(btnFileCreationLocation.getBackground());
				}
			}
		});

		// easter egg key listener =)
		team.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (isNumber(team.getText()) == false || isNumber(RoundNum.getText()) == false) {
					btnEnter.setBackground(new Color(100, 100, 100));
					enterable = false;
				} else {
					enterable = true;
					btnEnter.setBackground(btnFileCreationLocation.getBackground());
				}
				if (team.getText().equals("5667")) {
					messageBox.display(frame, "Hello!!", "Hey that's my team You are in the presence of greatness!","Yeah ok...");

				}
			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		btnEnter.setBackground(new Color(104, 104, 104));
		enterable = false;
		team.setBounds(484, 16, 146, 26);
		frame.getContentPane().add(team);
		team.setColumns(10);

		JLabel lblTeam = new JLabel("Team:");
		lblTeam.setBounds(374, 19, 106, 20);
		lblTeam.setHorizontalAlignment(SwingConstants.TRAILING);
		frame.getContentPane().add(lblTeam);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);

		// updates seleted row whenever mouse 2 is pressed
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON2) {
					selectedRow = table.rowAtPoint(e.getPoint());
					System.out.print(selectedRow);
				}
			}
		});
		scrollPane.setBounds(10, 220, 1011, 139);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setBorder(null);

		// table selection tools
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

					// selection and delete
					int[][] seleceted = new int[][] { table.getSelectedRows(), table.getSelectedColumns()};
					for (int x = 0; x < seleceted[0].length; x++) {
						for (int y = 0; y < seleceted[1].length; y++) {
							tableModel.setValueAt(null, seleceted[0][x], seleceted[1][y]);
						}
					}
				}
				if (arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
					try {
						try {
							enter();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
		frame.getContentPane().add(btnFileCreationLocation);

		RoundNum.setBounds(484, 53, 146, 26);
		frame.getContentPane().add(RoundNum);
		RoundNum.setColumns(10);

		JLabel lblRound = new JLabel("Round:");
		lblRound.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRound.setBounds(409, 56, 69, 20);
		frame.getContentPane().add(lblRound);
		CargoOrPanel.setBounds(307, 87, 395, 121);
		frame.getContentPane().add(CargoOrPanel);

		JPanel panel_1 = new JPanel();
		CargoOrPanel.addTab("Panel", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblHighestRocketLevel = new JLabel("Highest rocket level:");
		lblHighestRocketLevel.setBounds(0, 32, 209, 20);
		lblHighestRocketLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblHighestRocketLevel);
		ComboBoxPanel.setBounds(224, 29, 86, 26);

		ComboBoxPanel.setModel(new DefaultComboBoxModel(new String[] { "N/A", "One", "Two", "Three" }));
		panel_1.add(ComboBoxPanel);

		JPanel Cargo = new JPanel();
		CargoOrPanel.addTab("Cargo", null, Cargo, null);
		Cargo.setLayout(null);

		JLabel lblRocketLevel = new JLabel("Highest rocket level:");
		lblRocketLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRocketLevel.setBounds(0, 19, 204, 20);
		Cargo.add(lblRocketLevel);

		ComboBoxCargo.setModel(new DefaultComboBoxModel(new String[] { "N/A", "One", "Two", "Three" }));
		ComboBoxCargo.setBounds(214, 16, 86, 26);
		Cargo.add(ComboBoxCargo);

		JPanel panel_5 = new JPanel();
		CargoOrPanel.addTab("Penaltys", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblPenaltysRecived = new JLabel("Penaltys received:");
		lblPenaltysRecived.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPenaltysRecived.setBounds(0, 27, 201, 14);
		panel_5.add(lblPenaltysRecived);

		Penaltys.setModel(new DefaultComboBoxModel(new String[] { "None", "Yellow", "red" }));
		Penaltys.setBounds(211, 24, 77, 20);
		panel_5.add(Penaltys);

		JPanel panel = new JPanel();
		CargoOrPanel.addTab("End game location", null, panel, null);
		panel.setLayout(null);

		JLabel lblEndGameClimb = new JLabel("End game climb:");
		lblEndGameClimb.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEndGameClimb.setBounds(0, 35, 127, 20);
		panel.add(lblEndGameClimb);

		ComboBoxClimb.setModel(new DefaultComboBoxModel(new String[] { "N/A", "One", "Two", "Three" }));
		ComboBoxClimb.setBounds(139, 32, 77, 26);
		panel.add(ComboBoxClimb);

		JLabel lblFaildedClimb = new JLabel("Failded Climb: ");
		lblFaildedClimb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFaildedClimb.setBounds(213, 38, 90, 14);
		panel.add(lblFaildedClimb);

		failedClimb.setModel(new DefaultComboBoxModel(new String[] { "N/a", "One", "Two", "Three" }));
		failedClimb.setBounds(308, 35, 72, 20);
		panel.add(failedClimb);

		JPanel panel_4 = new JPanel();
		CargoOrPanel.addTab("defense or defended against", null, panel_4, null);
		panel_4.setLayout(null);

		DefendedAgainst.setModel(new DefaultComboBoxModel(new String[] { "No", "Yes" }));
		DefendedAgainst.setBounds(78, 36, 77, 20);
		panel_4.add(DefendedAgainst);

		Defended.setModel(new DefaultComboBoxModel(new String[] { "No", "Yes" }));
		Defended.setBounds(220, 36, 77, 20);
		panel_4.add(Defended);

		JLabel lblDefendedAgainst = new JLabel("Defended against");
		lblDefendedAgainst.setBounds(48, 11, 121, 14);
		panel_4.add(lblDefendedAgainst);

		JLabel lblDefended = new JLabel("Defended");
		lblDefended.setBounds(223, 11, 91, 14);
		panel_4.add(lblDefended);

		JPanel panel_2 = new JPanel();
		CargoOrPanel.addTab("Disabilities", null, panel_2, null);

		Condition.setModel(new DefaultComboBoxModel(new String[] { "working", "not working at all", "broken feature" }));
		panel_2.add(Condition);

		JPanel panel_3 = new JPanel();
		CargoOrPanel.addTab("Staring location", null, panel_3, null);
		panel_3.setLayout(null);

		Location.setModel(new DefaultComboBoxModel(new String[] { "Left", "middle", "right" }));
		Location.setBounds(97, 16, 85, 26);
		panel_3.add(Location);

		level.setModel(new DefaultComboBoxModel(new String[] { "One", "two" }));
		level.setBounds(197, 16, 71, 26);
		panel_3.add(level);

		JLabel lblPosition = new JLabel("Position");
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition.setBounds(97, 52, 85, 14);
		panel_3.add(lblPosition);

		JLabel lblLevel = new JLabel("level");
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setBounds(197, 52, 71, 14);
		panel_3.add(lblLevel);
	}

	void enter() throws IOException, InterruptedException {
		if (enterable) {
			try {
				// creates scouting folder
				teamFolder = new File(Desktop + sep + "scouting");
				teamFolder.mkdir();
				File f = new File(teamFolder.getAbsolutePath() + sep + team.getText());
				f.mkdir();
				// creates and populates the teams info text file

				File[] files = teamFolder.listFiles();
				boolean foundFile = false;
				// figures out if there is a teams info text file
				for (int i = 0; i < files.length; i++) {
					if (files[i].getName().equals("teams info.txt")) {
						foundFile = true;
						break;
					}
				}
				Writer FW = new FileWriter(teamFolder.getAbsolutePath() + sep + "teams info.txt", true);
				BufferedWriter writer = new BufferedWriter(FW);
				if (foundFile == false) {
					// writes header if there was no teams info.txt found
					writer.write(
							"Team number,Round number,penaltys,foul count,defended,defeneded against,highest cargo,higest panel,starting location,robot condition,climb level,failed climb");
				}
				writer.newLine();
				// writes data
				writer.write(team.getText() + "," + RoundNum.getText() + "," + ComboBoxValue(Penaltys) + "," + fouls
						+ "," + ComboBoxValue(Defended) + "," + ComboBoxValue(DefendedAgainst) + ","
						+ ComboBoxValue(ComboBoxCargo) + "," + ComboBoxValue(ComboBoxPanel) + ","
						+ ComboBoxValue(Location) + ":" + ComboBoxValue(level) + "," + ComboBoxValue(Condition) + ","
						+ ComboBoxValue(ComboBoxClimb) + "," + ComboBoxValue(failedClimb));
				writer.close();
				FW.close();
				// writer for round text file
				PrintWriter writer2 = new PrintWriter(f.getAbsolutePath() + sep + "Round " + RoundNum.getText() + ".txt", "UTF-8");
				
				
				//creates top columns
				ArrayList myList = new ArrayList();
				TableModel tableModle = table.getModel();
				writer2.print("Team number, round number,");
				for (int x = 0; x < tableModle.getColumnCount(); x++) {
					// if last column don't add comma
					if (x < tableModle.getColumnCount() - 1) {
						writer2.print(tableModle.getColumnName(x) + ",");
					} else {
						writer2.print(tableModle.getColumnName(x) + " ");
					}
				}
				writer2.println();
				// creates table
				for (int i = 0; i < tableModle.getRowCount(); i++) {
					if (tableModle.getValueAt(i, 3) != null) {
						//prints team and round numbers at the top
						writer2.print(team.getText() + "," + RoundNum.getText() + ",");
						
						//creates columns
						for (int y = 0; y < tableModle.getColumnCount(); y++) {
							if (y < tableModle.getColumnCount() - 1) {
								writer2.print(getCellValue(tableModel, i, y) + ",");
							} else {
								writer2.print(getCellValue(tableModel, i, y));
							}
						}
						writer2.println("");
					}
				}
				writer2.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			reset();

		} else {
			// popup window if they didnt put in the info
			messageBox.display(frame, "Enter info", "Please enter team and or round numbers", "Ok");
		}
	}

	private class Enter extends AbstractAction {
		public Enter() {
			putValue(NAME, "Finish match");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				enter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	// file chooser
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Choose file location");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(Desktop);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.showDialog(frame, "Choose folder");
			String path = chooser.getSelectedFile().getAbsolutePath();
			Path folder = Paths.get(path + sep + "scouting");
			try {
				Files.move(teamFolder.toPath(), folder, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	// reset combo box function cuz i'm lazy
	void resetComboBox(JComboBox box) {
		box.getModel().setSelectedItem(box.getModel().getElementAt(0));
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Add row");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			tableModel.addRow(new Object[] {});
		}
	}

	// still lazy
	String getCellValue(TableModel tbl, int row, int cell) {
		return (String) tbl.getValueAt(row, cell);
	}

	// wow really lazy
	boolean isNumber(String number) {
		boolean isNumber = false;
		try {
			int num = Integer.parseInt(number);
			isNumber = true;
		} catch (NumberFormatException ex) {
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
			for (int i = 0; i < table.getColumnCount(); i++) {
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
			tableModel.removeRow(selectedRow);
		}
	}
	//popup menu
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
	//resets the program
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
		int delay = 1000; //how long till the timer begins
		int period = 1000;//how long between each execution of run() inside timer task
		timerLbl.setText("150");
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				if (hasStarted == true) {
					currentTime = Integer.toString(setInterval());
					timerLbl.setText(currentTime);
				}
				if (Integer.parseInt(currentTime) == 0) {
					hasStarted = false;
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
			if (hasStarted == false) {
				hasStarted = true;
				timer();
				if (hasGamePiece == false) {
					btnCargo.setVisible(true);
					btnHatch.setVisible(true);
					GPL.frame.setVisible(false);
				} else {
					btnCargo.setVisible(false);
					btnHatch.setVisible(false);
					GPL.frame.setVisible(true);
				}
				btnStartMatch.setVisible(false);
			}
		}
	}

	private class SwingAction_6 extends AbstractAction {
		public SwingAction_6() {
			putValue(NAME, "Taken hatch");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			piecePickedUp(true);
		}
	}

	private class SwingAction_7 extends AbstractAction {
		public SwingAction_7() {
			putValue(NAME, "Taken cargo");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			piecePickedUp(false);
		}
	}

	// gets the value of a combo box
	String ComboBoxValue(JComboBox box) {
		return (String) box.getModel().getElementAt(box.getSelectedIndex());
	}

	void reset() {
		currentRow = 0;
		// clears combo boxes
		resetComboBox(ComboBoxPanel);
		resetComboBox(ComboBoxCargo);
		resetComboBox(ComboBoxClimb);
		resetComboBox(Location);
		resetComboBox(level);
		resetComboBox(Penaltys);
		resetComboBox(DefendedAgainst);
		resetComboBox(Defended);
		resetComboBox(failedClimb);
		resetComboBox(Condition);

		fouls = 0;
		btnEnter.setBackground(new Color(100, 100, 100));
		team.setText("");
		RoundNum.setText("");
		// clears tables
		for (int x = 0; x < tableModel.getRowCount(); x++) {
			for (int y = 0; y < tableModel.getColumnCount(); y++) {
				tableModel.setValueAt("", x, y);
			}
		}
		GPL.frame.setVisible(false);
		btnHatch.setVisible(false);
		HighestCargo=0;
		HighestHatch=0;
		btnCargo.setVisible(false);
		btnStartMatch.setVisible(true);
		btnStartMatch.setText("Start match");
		hasStarted = false;
		hasGamePiece = false;
		interval = 150;
		currentTime = "150";
		timerLbl.setText("150");
		timer.cancel();

	}
	//picks up hp or cargo
	void piecePickedUp(boolean hasHatch) {
		if (currentRow == table.getRowCount()) {
			tableModel.addRow(new Object[] {});
		}
		if (hasHatch) {
			if (Integer.parseInt(currentTime) >= 135) {
				table.setValueAt("hatch(sand storm)", currentRow, 3);
			} else {
				table.setValueAt("hatch", currentRow, 3);
			}
		} else {
			if (Integer.parseInt(currentTime) >= 135) {
				table.setValueAt("cargo(sand storm)", currentRow, 3);
			} else {
				table.setValueAt("cargo", currentRow, 3);
			}
		}
		btnHatch.setVisible(false);
		hatch = hasHatch;
		btnCargo.setVisible(false);
		GPL.frame.setLocation(frame.getX() + 400, frame.getY());
		if (hasStarted == true) {
			GPL.frame.setVisible(true);
		} else {
			GPL.frame.setVisible(false);
		}
		hasGamePiece = true;
		table.setValueAt(currentTime, currentRow, 0);
	}

	// smooshes all the text files together
	private class SwingAction_8 extends AbstractAction {
		public SwingAction_8() {
			putValue(NAME, "Compile data");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {

			try {
				// writes file
				BufferedWriter writer = new BufferedWriter(
						new FileWriter(teamFolder.getAbsolutePath() + sep + "data for spreadsheet.txt"));
				// writers header
				writer.write("Team number,highest cargo,higest panel,starting location,robot condition,climb level");
				writer.newLine();

				for (int x = 0; x < teamFolder.listFiles().length; x++) {
					// gets the current folder
					File currentFolder = teamFolder.listFiles()[x];
					if (currentFolder.isDirectory()) {
						// loops through text files in current folder
						for (int i = 0; i < currentFolder.listFiles().length; i++) {
							BufferedReader r = new BufferedReader(new FileReader(currentFolder.listFiles()[i]));
							// does not print the header
							String throwAway = r.readLine();
							String st;
							// prints everything in the text file
							while ((st = r.readLine()) != null) {
								writer.write(st);

								writer.newLine();
							}
						}
					}
				}
				writer.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
}