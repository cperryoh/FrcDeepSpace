
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
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
import javax.swing.UIManager.*;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
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
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.swing.border.BevelBorder;
import javax.swing.SpringLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public class Scouting {

	JFrame frame;
	private final Action action = new Enter();
	JButton btnAddFoul = new JButton("Add foul");
	int ratio;
	private JTextField team;
	int oldRowHeight;
	JLabel lblDefended = new JLabel("Defended");
	JMenuItem mntmCargo = new JMenuItem("Cargo");
	JMenuItem mntmHatch = new JMenuItem("Hatch");
	JLabel lblPosition = new JLabel("Position");
	JLabel lblDefendedAgainst = new JLabel("Defended against");
	JLabel lblFaildedClimb = new JLabel("Failed Climb: ");
	int fouls = 0;
	popUpWindow msg = new popUpWindow();
	Dimension originalSize;
	JScrollPane scrollPane = new JScrollPane();
	JLabel lblHighestRocketLevel = new JLabel("Highest rocket level:");
	JLabel lblPenaltiesRecived = new JLabel("Penalties received:");
	JComboBox ComboBoxClimb = new JComboBox();
	String sep = File.separator;
	JButton btnReset = new JButton("Reset");
	JMenu mnStartingGamePiece = new JMenu("Starting game piece");
	JLabel timerLbl = new JLabel("150");
	JComboBox ComboBoxPanel = new JComboBox();
	static int oldAvg = 0;
	boolean hatch = false;
	JComboBox Defended = new JComboBox();
	JComboBox DefendedAgainst = new JComboBox();
	JComboBox ComboBoxCargo = new JComboBox();
	JLabel lblCommentsno = new JLabel("Comments (No commas):");
	JLabel lblTeam = new JLabel("Team:");
	JLabel lblRound = new JLabel("Round:");
	JLabel lblRocketLevel = new JLabel("Highest rocket level:");
	JLabel lblEndGameClimb = new JLabel("End game climb:");
	JLabel lblLevel = new JLabel("level");
	public int HighestCargo = 0, HighestHatch = 0;
	JTabbedPane CargoOrPanel = new JTabbedPane(JTabbedPane.TOP);
	String Desktop = System.getProperty("user.home") + sep + "Desktop";
	JComboBox Condition = new JComboBox();
	JComboBox level = new JComboBox();
	JComboBox Location = new JComboBox();
	JComboBox failedClimb = new JComboBox();
	File teamFolder = new File(Desktop + sep + "scouting");
	public static JTable table;
	Font f = new Font("Tahoma", Font.BOLD, 13);
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
	JComboBox Penalties = new JComboBox();
	JButton btnHatch = new JButton("Taken hatch");
	JButton btnEnter = new JButton("Enter");
	JMenuItem mntmClearRow = new JMenuItem("Clear row");
	JMenuItem mntmDeleteRow = new JMenuItem("Delete row");

	DefaultTableModel tableModel = new DefaultTableModel(
			new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
					{ null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
			new String[] { "Game piece grabbed time", "Delivery time", "Delivery location (CS R#)",
					"Hatch panel or cargo" });
	private JTextField RoundNum;
	private JTextField textField;
	boolean enterable = false;
	private final Action action_2 = new SwingAction();
	private final Action action_3 = new SwingAction_2();
	private final Action action_4 = new SwingAction_3();
	private final Action action_5 = new SwingAction_4();
	private final Action action_6 = new SwingAction_5();
	private final Action action_7 = new SwingAction_6();
	private final Action action_8 = new SwingAction_7();
	private final JTextArea TPComments = new JTextArea();
	private final JProgressBar progressBar = new JProgressBar();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Scouting window = new Scouting();
					window.frame.setVisible(true);
					GPL = new gamePieceLocation(window);
					GPL.frame.setAlwaysOnTop(true);
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
		timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		frame.getContentPane().add(timerLbl, "cell 1 7,grow");
		progressBar.setForeground(Color.GREEN);
		
		frame.getContentPane().add(progressBar, "cell 0 8 3 1,grow");
		
		
		
		frame.getContentPane().add(btnReset, "cell 1 9,grow");
		frame.addComponentListener(new a());
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();

			}
		});

		
		frame.getContentPane().add(scrollPane, "cell 0 10 10 1,grow");
		

		scrollPane.setBorder(null);

		// updates seleted row whenever mouse 2 is pressed
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON2) {
					selectedRow = table.rowAtPoint(e.getPoint());
				}
			}
		});

		table = new JTable();
		
		table.setBorder(null);

		// table selection tools
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

					// selection and delete
					int[][] seleceted = new int[][] { table.getSelectedRows(), table.getSelectedColumns() };
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

		btnHatch.setAction(action_7);
		btnCargo.setAction(action_8);
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(table, popupMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.setAction(action_2);
		popupMenu_1.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mntmNewMenuItem_1.setAction(action_3);
		popupMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmAddRow = new JMenuItem("Add row");
		mntmAddRow.setAction(action_2);
		popupMenu_1.add(mntmAddRow);

		textField = new JTextField();
		scrollPane.setColumnHeaderView(textField);
		textField.setColumns(10);
		table.setModel(tableModel);

		table.getTableHeader().setReorderingAllowed(false);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mntmNewMenuItem_2.setAction(action_4);

		btnStartMatch.setAction(action_6);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(btnEnter, "cell 5 11,grow");

		btnAddFoul.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				
				
				msg.display(frame, "", "Foul added", "Ok");
				fouls++;
				msg.getFrame().setLocation(frame.getX()+(frame.getWidth()/2)+200, frame.getY());
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						msg.getFrame().setVisible(false);
					}
				},2*1000);
			}
		});
		btnCargo.setVisible(false);

		btnHatch.setVisible(false);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuBar.add(mnStartingGamePiece);

		

		mntmCargo.setAction(action_8);
		mnStartingGamePiece.add(mntmCargo);

		

		mntmHatch.setAction(action_7);
		mnStartingGamePiece.add(mntmHatch);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(frame, popupMenu);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mntmNewMenuItem_3.setAction(action_5);
		popupMenu.add(mntmNewMenuItem_3);
		originalSize=frame.getSize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		
		
		frame.setBounds(100, 100, 1417, 601);
		oldAvg = (frame.getWidth() +frame.getHeight()) / 2;
		ratio=frame.getWidth()/frame.getHeight();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[152px][111px][119px][72px][111px][][grow][152px][grow][452px,grow]", "[][16px,grow][23px][][25px][27px][27px][74px][][25px][134px,grow][35px]"));
				
						frame.getContentPane().add(lblCommentsno, "cell 5 0,grow");
				TPComments.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
				TPComments.setBackground(Color.LIGHT_GRAY);
				
				frame.getContentPane().add(TPComments, "cell 5 1,grow");
				TPComments.setLineWrap(true);
		
				frame.getContentPane().add(lblTeam, "cell 5 2,grow");
				
						lblTeam.setHorizontalAlignment(SwingConstants.TRAILING);
		team = new JTextField();
		frame.getContentPane().add(team, "cell 6 2,grow");
		
		// easter egg key listener =)
		team.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (isNumber(team.getText()) == false || isNumber(RoundNum.getText()) == false) {
					btnEnter.setBackground(new Color(100, 100, 100));
					enterable = false;
				} else {
					enterable = true;
					btnEnter.setBackground(btnReset.getBackground());
				}
				if (team.getText().equals("5667")) {
					msg.display(frame, "Hello!!", "Hey that's my team. You are in the presence of greatness!","Yeah ok...");
				}
				if (team.getText().equals("3003") || team.getText().equals("120")) {
					msg.display(frame, "Tell them I say hi", "Hey my team had analiance with them!!", "HIIIII");
				}
				
			}
		});
		team.setColumns(10);
		frame.getContentPane().add(btnAddFoul, "cell 1 4,grow");
		
				frame.getContentPane().add(lblRound, "cell 5 4,grow");
				
						lblRound.setHorizontalAlignment(SwingConstants.TRAILING);
		RoundNum = new JTextField();
		frame.getContentPane().add(RoundNum, "cell 6 4,grow");

		// key listener for enabling/disabling the finish button
		RoundNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (isNumber(team.getText()) == false || isNumber(RoundNum.getText()) == false) {
					btnEnter.setBackground(new Color(99, 100, 100));
					enterable = false;

				} else {
					enterable = true;
					btnEnter.setBackground(btnReset.getBackground());
				}
			}
		});
		RoundNum.setColumns(10);
		frame.getContentPane().add(btnStartMatch, "cell 1 5,grow");
		frame.getContentPane().add(btnHatch, "cell 0 6,grow");
		frame.getContentPane().add(btnCargo, "cell 2 6,grow");
		frame.getContentPane().add(CargoOrPanel, "cell 3 5 5 5,grow");

		JPanel Cargo = new JPanel();
		CargoOrPanel.addTab("Cargo", null, Cargo, null);
		GridBagLayout gbl_Cargo = new GridBagLayout();
		gbl_Cargo.columnWidths = new int[] { 86, 204, 0, 86, 0 };
		gbl_Cargo.rowHeights = new int[] { 36, 26, 0 };
		gbl_Cargo.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_Cargo.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		Cargo.setLayout(gbl_Cargo);

		lblRocketLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblRocketLevel = new GridBagConstraints();
		gbc_lblRocketLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRocketLevel.insets = new Insets(0, 0, 0, 5);
		gbc_lblRocketLevel.gridx = 1;
		gbc_lblRocketLevel.gridy = 1;
		Cargo.add(lblRocketLevel, gbc_lblRocketLevel);
		GridBagConstraints gbc_ComboBoxCargo = new GridBagConstraints();
		gbc_ComboBoxCargo.anchor = GridBagConstraints.WEST;
		gbc_ComboBoxCargo.insets = new Insets(0, 0, 0, 5);
		gbc_ComboBoxCargo.gridx = 2;
		gbc_ComboBoxCargo.gridy = 1;
		Cargo.add(ComboBoxCargo, gbc_ComboBoxCargo);

		ComboBoxCargo.setModel(new DefaultComboBoxModel(new String[] { "N/A", "One", "Two", "Three" }));

		JPanel panel_4 = new JPanel();
		CargoOrPanel.addTab("Defense or Defended Against", null, panel_4, null);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 94, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_4.rowHeights = new int[] { 14, 20, 0, 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);
		frame.setResizable(true);
		GridBagConstraints gbc_lblDefendedAgainst = new GridBagConstraints();
		gbc_lblDefendedAgainst.fill = GridBagConstraints.BOTH;
		gbc_lblDefendedAgainst.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefendedAgainst.gridx = 2;
		gbc_lblDefendedAgainst.gridy = 3;
		panel_4.add(lblDefendedAgainst, gbc_lblDefendedAgainst);
		GridBagConstraints gbc_DefendedAgainst = new GridBagConstraints();
		gbc_DefendedAgainst.insets = new Insets(0, 0, 5, 5);
		gbc_DefendedAgainst.gridx = 3;
		gbc_DefendedAgainst.gridy = 3;
		panel_4.add(DefendedAgainst, gbc_DefendedAgainst);

		DefendedAgainst.setModel(new DefaultComboBoxModel(new String[] { "No", "Yes" }));

		GridBagConstraints gbc_lblDefended = new GridBagConstraints();
		gbc_lblDefended.fill = GridBagConstraints.BOTH;
		gbc_lblDefended.insets = new Insets(0, 0, 5, 5);
		gbc_lblDefended.gridx = 5;
		gbc_lblDefended.gridy = 3;
		panel_4.add(lblDefended, gbc_lblDefended);
		GridBagConstraints gbc_Defended = new GridBagConstraints();
		gbc_Defended.insets = new Insets(0, 0, 5, 5);
		gbc_Defended.fill = GridBagConstraints.HORIZONTAL;
		gbc_Defended.gridx = 7;
		gbc_Defended.gridy = 3;
		panel_4.add(Defended, gbc_Defended);
		

		Defended.setModel(new DefaultComboBoxModel(new String[] { "No", "Yes" }));

		JPanel panel_3 = new JPanel();
		CargoOrPanel.addTab("Starting location", null, panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 26, 14, 0, 0, 0, 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);
		
				
		
				lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblPosition = new GridBagConstraints();
				gbc_lblPosition.anchor = GridBagConstraints.NORTH;
				gbc_lblPosition.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblPosition.insets = new Insets(0, 0, 5, 5);
				gbc_lblPosition.gridx = 6;
				gbc_lblPosition.gridy = 1;
				panel_3.add(lblPosition, gbc_lblPosition);
		
				lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblLevel = new GridBagConstraints();
				gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
				gbc_lblLevel.anchor = GridBagConstraints.NORTH;
				gbc_lblLevel.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblLevel.gridx = 9;
				gbc_lblLevel.gridy = 1;
				panel_3.add(lblLevel, gbc_lblLevel);
		GridBagConstraints gbc_Location = new GridBagConstraints();
		gbc_Location.fill = GridBagConstraints.HORIZONTAL;
		gbc_Location.insets = new Insets(0, 0, 5, 5);
		gbc_Location.gridx = 6;
		gbc_Location.gridy = 3;
		panel_3.add(Location, gbc_Location);
		
				Location.setModel(new DefaultComboBoxModel(new String[] { "Left", "middle", "right" }));
		GridBagConstraints gbc_level = new GridBagConstraints();
		gbc_level.insets = new Insets(0, 0, 5, 5);
		gbc_level.fill = GridBagConstraints.HORIZONTAL;
		gbc_level.gridx = 9;
		gbc_level.gridy = 3;
		panel_3.add(level, gbc_level);
		
				level.setModel(new DefaultComboBoxModel(new String[] { "One", "two" }));

		JPanel panel = new JPanel();
		CargoOrPanel.addTab("End game location", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 122, 64, 82, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 32, 26, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblEndGameClimb.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblEndGameClimb = new GridBagConstraints();
		gbc_lblEndGameClimb.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEndGameClimb.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndGameClimb.gridx = 2;
		gbc_lblEndGameClimb.gridy = 2;
		panel.add(lblEndGameClimb, gbc_lblEndGameClimb);
		GridBagConstraints gbc_ComboBoxClimb = new GridBagConstraints();
		gbc_ComboBoxClimb.anchor = GridBagConstraints.WEST;
		gbc_ComboBoxClimb.insets = new Insets(0, 0, 5, 5);
		gbc_ComboBoxClimb.gridx = 3;
		gbc_ComboBoxClimb.gridy = 2;
		panel.add(ComboBoxClimb, gbc_ComboBoxClimb);

		ComboBoxClimb.setModel(new DefaultComboBoxModel(new String[] { "N/A", "One", "Two", "Three" }));

		lblFaildedClimb.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFaildedClimb = new GridBagConstraints();
		gbc_lblFaildedClimb.anchor = GridBagConstraints.EAST;
		gbc_lblFaildedClimb.insets = new Insets(0, 0, 5, 5);
		gbc_lblFaildedClimb.gridx = 6;
		gbc_lblFaildedClimb.gridy = 2;
		panel.add(lblFaildedClimb, gbc_lblFaildedClimb);
		GridBagConstraints gbc_failedClimb = new GridBagConstraints();
		gbc_failedClimb.insets = new Insets(0, 0, 5, 5);
		gbc_failedClimb.fill = GridBagConstraints.HORIZONTAL;
		gbc_failedClimb.gridx = 7;
		gbc_failedClimb.gridy = 2;
		panel.add(failedClimb, gbc_failedClimb);
		msg.getFrame().setSize((frame.getWidth()/1380)*574, (frame.getHeight()/690)*191);
		failedClimb.setModel(new DefaultComboBoxModel(new String[] { "N/A", "One", "Two", "Three" }));

		JPanel panel_5 = new JPanel();
		CargoOrPanel.addTab("Penalties", null, panel_5, null);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 201, 107, 0, 0, 0, 0 };
		gbl_panel_5.rowHeights = new int[] { 28, 0, 0, 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_5.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_5.setLayout(gbl_panel_5);

		JPanel panel_1 = new JPanel();
		CargoOrPanel.addTab("Panel", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 209, 112, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 26, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		
		
		lblHighestRocketLevel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblHighestRocketLevel = new GridBagConstraints();
		gbc_lblHighestRocketLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHighestRocketLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblHighestRocketLevel.gridx = 1;
		gbc_lblHighestRocketLevel.gridy = 1;
		panel_1.add(lblHighestRocketLevel, gbc_lblHighestRocketLevel);
		GridBagConstraints gbc_ComboBoxPanel = new GridBagConstraints();
		gbc_ComboBoxPanel.anchor = GridBagConstraints.WEST;
		gbc_ComboBoxPanel.insets = new Insets(0, 0, 5, 5);
		gbc_ComboBoxPanel.gridx = 2;
		gbc_ComboBoxPanel.gridy = 1;
		panel_1.add(ComboBoxPanel, gbc_ComboBoxPanel);
		ComboBoxPanel.setModel(new DefaultComboBoxModel(new String[] { "N/A", "One", "Two", "Three" }));

		JPanel panel_2 = new JPanel();
		CargoOrPanel.addTab("Disabilities", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 193, 140, 0, 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 22, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		
		
		lblPenaltiesRecived.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPenaltiesRecived = new GridBagConstraints();
		gbc_lblPenaltiesRecived.anchor = GridBagConstraints.EAST;
		gbc_lblPenaltiesRecived.insets = new Insets(0, 0, 5, 5);
		gbc_lblPenaltiesRecived.gridx = 1;
		gbc_lblPenaltiesRecived.gridy = 1;
		panel_5.add(lblPenaltiesRecived, gbc_lblPenaltiesRecived);
		GridBagConstraints gbc_Penalties = new GridBagConstraints();
		gbc_Penalties.anchor = GridBagConstraints.WEST;
		gbc_Penalties.insets = new Insets(0, 0, 5, 5);
		gbc_Penalties.gridx = 2;
		gbc_Penalties.gridy = 1;
		panel_5.add(Penalties, gbc_Penalties);

		Penalties.setModel(new DefaultComboBoxModel(new String[] { "None", "Yellow", "red" }));
		GridBagConstraints gbc_Condition = new GridBagConstraints();
		gbc_Condition.insets = new Insets(0, 0, 5, 5);
		gbc_Condition.anchor = GridBagConstraints.NORTHWEST;
		gbc_Condition.gridx = 2;
		gbc_Condition.gridy = 2;
		panel_2.add(Condition, gbc_Condition);
		
				Condition.setModel(new DefaultComboBoxModel(new String[] { "working", "not working at all", "broken feature" }));

		btnEnter.setForeground(Color.BLACK);
		btnEnter.setAction(action);

		btnEnter.setBackground(new Color(104, 104, 104));

		mntmDeleteRow.setAction(action_3);

		mntmClearRow.setAction(action_4);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		enterable = false;
		frame.setTitle("Made by Cole Perry from team 5667");
		
	}
	private class a extends ComponentAdapter {
		
		@Override
		
		public void componentResized(ComponentEvent arg0) {
			int newAvg = (frame.getWidth() + frame.getHeight()) / 2;
			f = new Font("Tahoma", Font.BOLD, (13 * newAvg) / oldAvg);
			setFonts();
			System.out.println(frame.getSize().toString());
			GPL.frame.setSize((int)(((double)frame.getWidth()/1380.0)*456.0), (int)(((double)frame.getHeight()/601.0)*304.0));
			frame.setSize( frame.getWidth(), frame.getWidth()/ratio);
			table.setRowHeight((int)((double)table.getWidth()/(1380.0/16.0)));
		}
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
							"Team number,Round number,penalties,foul count,defended,defeneded against,highest cargo,highest panel,starting location,robot condition,climb level,failed climb,comments");
				}
				writer.newLine();
				if (TPComments.getText().equals("")) {
					
					TPComments.setText("None");
				}
				// writes data
				writer.write(team.getText() + "," + RoundNum.getText() + "," + ComboBoxValue(Penalties) + "," + fouls
						+ "," + ComboBoxValue(Defended) + "," + ComboBoxValue(DefendedAgainst) + ","
						+ ComboBoxValue(ComboBoxCargo) + "," + ComboBoxValue(ComboBoxPanel) + ","
						+ ComboBoxValue(Location) + ":" + ComboBoxValue(level) + "," + ComboBoxValue(Condition) + ","
						+ ComboBoxValue(ComboBoxClimb) + "," + ComboBoxValue(failedClimb) + "," + TPComments.getText());
				writer.close();
				FW.close();
				// writer for round text file
				PrintWriter writer2 = new PrintWriter(
						f.getAbsolutePath() + sep + "Round " + RoundNum.getText() + ".txt", "UTF-8");

				// creates top columns
				ArrayList myList = new ArrayList();
				TableModel tableModle = table.getModel();
				writer2.print("Team number, round number,");
				for (int x = 0; x < tableModle.getColumnCount(); x++) {
					// if last column don't add comma
					if (x < tableModle.getColumnCount() - 1) {
						writer2.print(tableModle.getColumnName(x) + ",");
					} else {
						writer2.print(tableModle.getColumnName(x));
					}
				}
				writer2.println();
				// creates table
				for (int i = 0; i < tableModle.getRowCount(); i++) {
					if (tableModle.getValueAt(i, 3) != null) {
						writer2.print(team.getText() + "," + RoundNum.getText() + ",");
						// creates columns
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
				compileData();
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
			reset();

		} else {
			// popup window if they didnt put in the info
			msg.display(frame, "Enter info", "Please enter team and or round numbers", "Ok");
		}
	}

	void setFonts() {
		Font medFont = new Font("Tahoma", Font.BOLD, (int) ((double)13*1.5));
		msg.messageBox.setFont(f);
		ComboBoxPanel.setFont(f);
		
		GPL.droppedr1.setFont(f);
		GPL.droppedCs.setFont(f);
		GPL.droppedR3.setFont(f);
		Penalties.setFont(f);
		GPL.droppedR2.setFont(f);
		JTableHeader header = table.getTableHeader();
		header.setFont(f);
		table.setTableHeader(header);
		msg.btnEnter.setFont(f);
		level.setFont(f);
		btnStartMatch.setFont(f);
		btnHatch.setFont(f);
		btnCargo.setFont(f);
		btnAddFoul.setFont(f);
		TPComments.setFont(f);
		btnEnter.setFont(f);
		Condition.setFont(f);
		
		DefendedAgainst.setFont(f);
		failedClimb.setFont(f);
		lblFaildedClimb.setFont(f);
		CargoOrPanel.setFont(f);
		lblDefended.setFont(f);
		lblDefendedAgainst.setFont(f);
		lblLevel.setFont(medFont);
		ComboBoxClimb.setFont(f);
		lblEndGameClimb.setFont(f);
		ComboBoxCargo.setFont(f);
		Penalties.setFont(f);
		lblRocketLevel.setFont(f);
		Location.setFont(f);
		lblPosition.setFont(medFont);
		mntmHatch.setFont(f);
		mntmCargo.setFont(f);
		mnStartingGamePiece.setFont(f);
		table.setFont(f);
		RoundNum.setFont(medFont);
		lblRound.setFont(medFont);
		team.setFont(medFont);
		lblTeam.setFont(medFont);
		timerLbl.setFont(new Font("Tahoma", Font.BOLD, (46/13)*f.getSize()));
		lblCommentsno.setFont(f);
		lblPenaltiesRecived.setFont(f);
		lblHighestRocketLevel.setFont(f);
		btnReset.setFont(f);
		Defended.setFont(f);
		FontUIResource font = new FontUIResource(f);
		UIManager.put("table.font", font);
		scrollPane.setFont(f);

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

	// popup menu
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

	// test
	// resets the program
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
		int delay = 1000; // how long till the timer begins
		int period = 1000;// how long between each execution of run() inside timer task
		timerLbl.setText("150");
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (hasStarted == true) {
					currentTime = Integer.toString(setInterval());
					timerLbl.setText(currentTime);
					System.out.println();
					progressBar.setValue((int)(100.0-((double)Integer.parseInt(currentTime)/(double)150)*100));
					if(Integer.parseInt(currentTime)>60) {
						progressBar.setForeground(new Color(0,255,0));
					}
					else if(Integer.parseInt(currentTime)>20) {
						progressBar.setForeground(new Color(255,255,0));
					}
					else {
						progressBar.setForeground(new Color(255,0,0));
					}
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
				mnStartingGamePiece.setVisible(false);
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
		progressBar.setValue(100);
		resetComboBox(ComboBoxClimb);
		resetComboBox(Location);
		resetComboBox(level);
		resetComboBox(Penalties);
		resetComboBox(DefendedAgainst);
		resetComboBox(Defended);
		resetComboBox(failedClimb);
		resetComboBox(Condition);
		progressBar.setForeground(new Color(0,255,0));
		fouls = 0;
		btnEnter.setBackground(new Color(100, 100, 100));
		team.setText("");
		mnStartingGamePiece.setVisible(true);
		RoundNum.setText("");
		// clears tables
		for (int x = 0; x < tableModel.getRowCount(); x++) {
			for (int y = 0; y < tableModel.getColumnCount(); y++) {
				tableModel.setValueAt("", x, y);
			}
		}
		GPL.frame.setVisible(false);
		TPComments.setText("");
		btnHatch.setVisible(false);
		HighestCargo = 0;
		HighestHatch = 0;
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

	// picks up hp or cargo
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
	void compileData() {
		try {
			// writes file
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(teamFolder.getAbsolutePath() + sep + "data for spreadsheet.txt"));
			// writers header
			for (int x = 0; x < tableModel.getColumnCount(); x++) {
				// if last column don't add comma
				if (x < tableModel.getColumnCount() - 1) {
					writer.write(tableModel.getColumnName(x) + ",");
				} else {
					writer.write(tableModel.getColumnName(x) + " ");
				}
			}
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