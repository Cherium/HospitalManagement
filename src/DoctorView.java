import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.BorderUIResource.BevelBorderUIResource;

import net.miginfocom.swing.MigLayout;

/**
 * Creates all the components that are needed to view the GUI for this role.
 * Contains nothing from the controller or view class. Does NOT interact with
 * the associated model class. The controller interacts with this view class,
 * but not the other way around (the view class does not interact with the
 * controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back
 * button listener that closes the view does not need interaction with the
 * model.) Remaining button/field listeners that DO need to interact withe the
 * model are initialized in the controller class.
 */
public class DoctorView {

	private JFrame frame;

	private JPanel panel;
	private JPanel scheduleWeekly;
	private JPanel scheduleMonthly;
	private JPanel titleContainer;
	private JPanel backPanel;
	private JPanel patientPanel;
	// private JPanel dayPanel;
	private JPanel listPatientsPanel;
	private JPanel btnPaneSchedule;
	private JPanel buttonContainer;
	private JPanel scheduleContainer;

	private JButton btnSignOut;
	private JButton btnViewPatient;
	private JButton btnMakeChanges;
	private JButton btnAddTreatmentNotes;
	private JButton btnSaveChanges;
	private JButton btnOwnSchedule;
	private JButton btnOwn;
	private JButton btnToggle;

	private JLabel titleLabel;
	private JLabel nursesLabel;
	private JLabel displayMonth;
	private JLabel nameLabel;
	private JLabel deptLabel;
	private JLabel scheduleNameLabelWeek, scheduleNameLabelMonth;

	private JTextArea patientInformation = new JTextArea(7, 90);
	private JTextArea pastTreatments;
	private JTextArea currentTreatment;

	private ArrayList<JLabel> weekYYYYMMDD = new ArrayList<JLabel>(7); // Labels of the format: Sunday, Monday, etc.
	private ArrayList<JLabel> sunToSatMonth = new ArrayList<JLabel>(7); // Labels of the format: Sunday, Monday, etc.
	private ArrayList<JLabel> sunToSatWeek = new ArrayList<JLabel>(7); // Labels of the format: YYYY-MM-DD
	private ArrayList<JLabel> monthdays = new ArrayList<JLabel>(35);
	private ArrayList<JPanel> patientListPanels = new ArrayList<JPanel>(0);

	JComboBox<String> nurseComboBox;

	Container container;

	private LocalDate now = LocalDate.now();

	private LinkedHashMap<String, String> appointments;
	private Boolean[] schDays;
	private String name;
	private String[] aptsInMonth;

	private JScrollPane scroll;
	private JList listPatients;

	/*
	 * default frame (getContentFrame()): BorderLayout
	 * ################################################################# # Sign out
	 * # title frame: BoxLayout.Y-Axis # Department: Name # BorderLayout.North
	 * #---------------------------------------------------------------# # Schedule
	 * | Patients # content frame: BoxLayout.X-Axis # | Nurses # BorderLayout.CENTER
	 * # | Own schedule # # | # # | # # | # # | #
	 * #-----------------------------------------------| # # manipulate schedule | #
	 * #################################################################
	 */

	// Constructor
	public DoctorView(String title) {
		// create frame container
		// sets frame containers attributes
		frame = new JFrame(title);
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(1000, 700));
		frame.setPreferredSize(new Dimension(1000, 700));
		frame.setLocation(10, 10);

		initializeVariables();
		initializeGUI();

	}

	public void initGUI() {
		JPanel mainPane = new JPanel(new MigLayout());

	}

	/** initialize the panels and components that will go inside the frame */
	public void initializeGUI() {
		setNow(LocalDate.now());
		// Create title container(JLabel) and set it as top of page in the frame
		titleContainer = new JPanel();
		titleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
		frame.getContentPane().add(titleContainer, BorderLayout.NORTH); // Add the JPanel to the frame

		/*
		 * ################################################# # Back # # Department:
		 * Name, M.D. # # # #################################################
		 */

		// Create a panel for the sign out button
		backPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) backPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		titleContainer.add(backPanel); // add the JPanel to the existing JPanel

		// Sign out button, returns to login screen when clicked
		btnSignOut = new JButton("Sign out");
		backPanel.add(btnSignOut);
		btnSignOut.addActionListener(e -> frame.setVisible(false));

		// Name panel, displays department and name of the doctor
		JPanel namePane = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) namePane.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		titleContainer.add(namePane);

		deptLabel = new JLabel();
		deptLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		namePane.add(deptLabel);

		nameLabel = new JLabel();
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		namePane.add(nameLabel);

		/*
		 * ##################################### # Schedule # # # # # # # # # # #
		 * #-----------------------------------# # buttons for schedule # # #
		 * #####################################
		 */

		// Create a content pane centered at the middle of the page
		JPanel contentPane = new JPanel();
		frame.getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		// Create a container for the schedule, for the schedule itself and for the
		// button panel associated with it (on the buttom)
		scheduleContainer = new JPanel();
		scheduleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		scheduleContainer.setLayout(new MigLayout("hidemode 1"));
		contentPane.add(scheduleContainer);

		patientPanel = new JPanel();
		patientPanel.setBorder(new LineBorder(Color.CYAN));
		patientPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		// patientPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		patientPanel.setLayout(new BorderLayout());
		patientPanel.setBackground(Color.WHITE);
		patientPanel.setVisible(false);
		contentPane.add(patientPanel);

		initializePatients();

		/*
		 * ################################# # Schedule #
		 * #_______________________________# # | | | | | | | # # | | | | | | | # # | | |
		 * | | | | # # | | | | | | | # #-------------------------------#
		 */

		// Create a panel for the schedule itself
		for (int i = 1; i < 8; i++) {
			JLabel lblTemp = new JLabel(now.with(WeekFields.of(Locale.CANADA).dayOfWeek(), i).toString());
			lblTemp.setFont(new Font("Tahoma", Font.PLAIN, 14));
			weekYYYYMMDD.add(lblTemp);
		}

		scheduleMonthly = new JPanel();
		scheduleWeekly = new JPanel();

		scheduleContainer.add(scheduleWeekly, "align 50% 50%");
		scheduleContainer.add(scheduleMonthly, "align 50% 50%");

		initializeButtonsSchedule(WeekFields.of(Locale.CANADA).dayOfWeek());
		scheduleContainer.add(btnPaneSchedule, "south");

		initializeButtonsRight();
		frame.getContentPane().add(buttonContainer, BorderLayout.EAST);
		frame.setVisible(true);

	}

	////////////////////////////////////////////////////

	/** Getter and Setter Methods */

	public void setVisibility(Boolean a) {
		frame.setVisible(a);
	}

	// show a dialog message if credentials do not validate
	public void loginError(String message) {
		JFrame frame = new JFrame();
		frame.setSize(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, message);
	}

	public void initializeButtonsRight() {
		/*
		 * |############ | Things # | Doctor # | Can do # | # | # | # | # | # | # | #
		 * |############
		 */

		buttonContainer = new JPanel();
		buttonContainer.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagLayout gbl_buttonContainer = new GridBagLayout();
		gbl_buttonContainer.columnWidths = new int[] { 0, 0 };
		gbl_buttonContainer.rowHeights = new int[] { 35, 0, 0, 35, 0, 0, 0 };
		gbl_buttonContainer.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_buttonContainer.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		buttonContainer.setLayout(gbl_buttonContainer);
		buttonContainer.setMaximumSize(new Dimension(200, 1000));
		buttonContainer.setBorder(new LineBorder(new Color(255, 200, 0)));

		buttonContainer.setBorder(new LineBorder(new Color(255, 200, 0)));

		// Button that views all patients currenly scheduled under this doctor
		// Does nothing
		JButton btnPatients = new JButton("View patients");
		GridBagConstraints gbc_btnPatients = new GridBagConstraints();
		gbc_btnPatients.insets = new Insets(0, 5, 5, 5);
		gbc_btnPatients.gridx = 0;
		gbc_btnPatients.gridy = 0;
		buttonContainer.add(btnPatients, gbc_btnPatients);

		// Combobox that shows a given nurses schedule when selected
		// Currently does nothing
		// Comboboxes are messed up under box layout, which is why this is in grid bag
		nurseComboBox = new JComboBox<String>();

		// Label for the above combo box
		JLabel lblNurses = new JLabel("Assigned nurses:");
		lblNurses.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNurses = new GridBagConstraints();
		gbc_lblNurses.insets = new Insets(5, 5, 5, 5);
		gbc_lblNurses.gridx = 0;
		gbc_lblNurses.gridy = 1;
		buttonContainer.add(lblNurses, gbc_lblNurses);

		// Setting up default for the combobox
		nurseComboBox.setBackground(Color.WHITE);
		nurseComboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_nurseComboBox = new GridBagConstraints();
		gbc_nurseComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_nurseComboBox.insets = new Insets(5, 5, 5, 5);
		gbc_nurseComboBox.gridx = 0;
		gbc_nurseComboBox.gridy = 2;
		buttonContainer.add(nurseComboBox, gbc_nurseComboBox);

		// Button that allows the doctor to view his own schedule
		btnOwn = new JButton("View own schedule");
		btnOwn.setEnabled(false);
		btnOwn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nurseComboBox.setSelectedIndex(-1);
				btnOwn.setEnabled(false);
				// contentPane.setVisible(true);
				scheduleContainer.setVisible(true);
				patientPanel.setVisible(false);
			}
		});

		btnPatients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// contentPane.setVisible(false);
				scheduleContainer.setVisible(false);
				patientPanel.setVisible(true);
				btnOwn.setEnabled(true);
				System.out.println("Viewing patients");
			}
		});

		// Action event on the nurse combo box, once a nurse is selected only then
		// can the doctor have the option to returning to his own schedule
		// Is set after the creation of the button
		nurseComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnOwn.setEnabled(true);
			}
		});

		GridBagConstraints gbc_btnOwn = new GridBagConstraints();
		gbc_btnOwn.insets = new Insets(5, 5, 5, 5);
		gbc_btnOwn.gridx = 0;
		gbc_btnOwn.gridy = 3;
		buttonContainer.add(btnOwn, gbc_btnOwn);

		JButton btnChange = new JButton("Make changes");
		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSaveChanges.setVisible(true);
				btnOwnSchedule.setVisible(true);
			}
		});

		gbc_btnOwn.gridy = 4;
		buttonContainer.add(btnChange, gbc_btnOwn);
		buttonContainer.setPreferredSize(buttonContainer.getPreferredSize());
	}

	public void initializeButtonsSchedule(TemporalField fieldISO) {
		/*
		 * #-----------------------------------# # buttons for schedule # # #
		 * #####################################
		 */

		// Create a panel for the buttons that manipulate the schedule
		btnPaneSchedule = new JPanel();
		btnPaneSchedule.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Create a button that goes back one week/one month
		// Currently only one week
		// ActionListeners handle disabled buttons, mouse click does not
		JButton btnBack = new JButton("<");
		// Button starts out disabled as schedule starts out on current week
		btnBack.setEnabled(false);
		btnPaneSchedule.add(btnBack);

		// Button that selects a day from a day picker
		// Not implemented. May remove function altogether
		JButton btnDaySelecter = new JButton("Select day");
		btnPaneSchedule.add(btnDaySelecter);

		// Button that changes schedule between weekly and monthly views
		// Currently only weekly view is setup
		btnToggle = new JButton("Monthly view");
		btnPaneSchedule.add(btnToggle);
		btnToggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnToggle.getText().equals("Monthly view")) {
					btnToggle.setText("Weekly view");
					scheduleMonthly.setVisible(true);
					scheduleWeekly.setVisible(false);
					setMonthDateLabels(getNow());
				} else {
					btnToggle.setText("Monthly view");
					scheduleWeekly.setVisible(true);
					scheduleMonthly.setVisible(false);
					setWeekDateLabels(getNow());
				}
			}
		});

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDate past, tempTime;
				if (btnToggle.getText().equals("Monthly view")) {
					past = now.minusWeeks(1);
					tempTime = past.with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
					setWeekDateLabels(past);
					now = past;
					if (tempTime.compareTo(LocalDate.now()) < 0) {
						btnBack.setEnabled(false);
						now = LocalDate.now();
					}
					String schTitle = getScheduleNameLabelWeek().getText();
					initializeWeeklySchedule();
					getScheduleNameLabelWeek().setText(schTitle);

				} else {
					past = now.minusMonths(1).withDayOfMonth(1);
					tempTime = past.withDayOfMonth(1).with(fieldISO, 1);
					setMonthDateLabels(past);
					now = past;
					if (tempTime.compareTo(LocalDate.now()) < 0) {
						btnBack.setEnabled(false);
						now = LocalDate.now();
					}
					// initializeMonthlySchedule();

				}
			}
		});

		JButton btnForward = new JButton(">");
		btnPaneSchedule.add(btnForward);
		btnForward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocalDate future;
				if (btnToggle.getText().equals("Monthly view")) {
					future = now.plusWeeks(1);
					setWeekDateLabels(future);
				} else {
					future = now.plusMonths(1);
					setMonthDateLabels(future);
				}
				if (!btnBack.isEnabled())
					btnBack.setEnabled(true);
				now = future;
				if (btnToggle.getText().equals("Monthly view")) {
					String schTitle = getScheduleNameLabelWeek().getText();
					initializeWeeklySchedule();
					getScheduleNameLabelWeek().setText(schTitle);
				} else {
					String schTitle = getScheduleNameLabelWeek().getText();
					initializeMonthlySchedule();
					scheduleMonthly.setVisible(true);
					scheduleWeekly.setVisible(false);
					getScheduleNameLabelMonth().setText(schTitle);
				}
			}
		});

		btnSaveChanges = new JButton("Save changes");
		btnPaneSchedule.add(btnSaveChanges);

		btnSaveChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSaveChanges.setVisible(false);
			}
		});

		btnOwnSchedule = new JButton("View current schedule");
		btnPaneSchedule.add(btnOwnSchedule);

		btnOwnSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnOwnSchedule.setVisible(false);
				btnSaveChanges.setVisible(false);
				patientPanel.setVisible(false);
			}
		});

		btnPaneSchedule.setPreferredSize(new Dimension(500, 0));

		btnSaveChanges.setVisible(false);
		btnOwnSchedule.setVisible(false);

		btnPaneSchedule.setBorder(BorderFactory.createEtchedBorder());
	}

	public void initializeVariables() {
		JLabel lblDay = new JLabel("Sunday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		sunToSatWeek.add(lblDay);
		lblDay = new JLabel("Sunday");
		lblDay = new JLabel("Sunday");
		sunToSatMonth.add(lblDay);

		lblDay = new JLabel("Monday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		sunToSatWeek.add(lblDay);
		lblDay = new JLabel("Monday");
		lblDay = new JLabel("Monday");
		sunToSatMonth.add(lblDay);

		lblDay = new JLabel("Tuesday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		sunToSatWeek.add(lblDay);
		lblDay = new JLabel("Tuesday");
		lblDay = new JLabel("Tuesday");
		sunToSatMonth.add(lblDay);

		lblDay = new JLabel("Wednesday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		sunToSatWeek.add(lblDay);
		lblDay = new JLabel("Wednesday");
		lblDay = new JLabel("Wednesday");
		sunToSatMonth.add(lblDay);

		lblDay = new JLabel("Thursday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		sunToSatWeek.add(lblDay);
		lblDay = new JLabel("Thursday");
		lblDay = new JLabel("Thursday");
		sunToSatMonth.add(lblDay);

		lblDay = new JLabel("Friday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		sunToSatWeek.add(lblDay);
		lblDay = new JLabel("Friday");
		lblDay = new JLabel("Friday");
		sunToSatMonth.add(lblDay);

		lblDay = new JLabel("Saturday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		sunToSatWeek.add(lblDay);
		lblDay = new JLabel("Saturday");
		lblDay = new JLabel("Saturday");
		sunToSatMonth.add(lblDay);

		LocalDate firstMonth = now.withDayOfMonth(1).with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		for (int i = 0; i < 35; i++) {
			monthdays.add(new JLabel(firstMonth.plusDays(i).getDayOfMonth() + ""));
			monthdays.get(i).setFont(new Font("Tahoma", Font.BOLD, 14));
		}

	}

	public void initializeWeeklySchedule() {
		String[][] listApsWeek = new String[24][7];
		LocalDate startRange = now.with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		LocalDate endRange = startRange.plusDays(6);
		for (Entry<String, String> entry : getAppointments().entrySet()) {
			LocalDateTime time = LocalDateTime.parse(entry.getKey());
			if ((time.toLocalDate().compareTo(startRange) >= 0) && (time.toLocalDate().compareTo(endRange) <= 0)) {
				listApsWeek[time.getHour()][time.getDayOfWeek().getValue()%7] = entry.getValue();
			}
			System.out.println(time.getHour()+" : "+(time.getDayOfWeek().getValue()%7));

		}

		for (Entry<String, String> entry : getAppointments().entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());

		}


		scheduleWeekly.removeAll();
		scheduleWeekly.revalidate();
		scheduleWeekly.repaint();
			scheduleWeekly.setLayout(new MigLayout("wrap 8", "[align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center]"));
			scheduleWeekly.setBackground(Color.WHITE);
			scheduleWeekly.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		scheduleNameLabelWeek = new JLabel(getName() + "'s Schedule: Weekly");
			scheduleWeekly.add(scheduleNameLabelWeek, "span");
			scheduleWeekly.add(Box.createHorizontalGlue());
		
		setWeekDateLabels(getNow());
		for (JLabel wd : weekYYYYMMDD) {
			scheduleWeekly.add(wd);
			wd.setEnabled(schDays[weekYYYYMMDD.indexOf(wd)]);
		}
		scheduleWeekly.add(Box.createHorizontalGlue());
		for (JLabel lbl : sunToSatWeek) {
			scheduleWeekly.add(lbl);
			lbl.setEnabled(schDays[sunToSatWeek.indexOf(lbl)]);
		}

		for (int i = 0; i < 12; i++) {
			scheduleWeekly.add(new JLabel((i+8)+":00"));
			for (int j = 0; j < 7; j++) {
				JLabel templbl = new JLabel("<html>"+sunToSatWeek.get(j).getText()+"<br>Time:"+(i+8)+":00");
				if (listApsWeek[i+8][j] != null) {
					templbl.setText("<html>Not null!<br>"+listApsWeek[i+8][j]);
					templbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					templbl.setOpaque(true);
					templbl.setBackground(Color.ORANGE);
				}
				scheduleWeekly.add(templbl);
				templbl.setEnabled(schDays[j]);
				if (!templbl.isEnabled()) {
					templbl.setOpaque(false);
				}

			}
		}

		scheduleWeekly.setPreferredSize(new Dimension(2000,1000));

	}
	
	
	/**
	 * Set up the monthly view of the schedule, given a schedule (currently only for scheduled days)
	 * @param schDays
	 */
	public void initializeMonthlySchedule() {
		scheduleMonthly.removeAll();
		scheduleMonthly.revalidate();
		scheduleMonthly.repaint();
			scheduleMonthly.setLayout(new MigLayout("wrap 7", "[align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center]"));
			scheduleMonthly.setBackground(Color.WHITE);
			scheduleMonthly.setBorder(new EmptyBorder(5, 5, 5, 5));
			scheduleMonthly.setBorder(new LineBorder(Color.RED));
		
		
		scheduleNameLabelMonth = new JLabel(getName() + "'s schedule: Monthly");
		scheduleMonthly.add(scheduleNameLabelMonth, "span");
		displayMonth = new JLabel(now.getMonth().toString()+" "+now.getYear());
		scheduleMonthly.add(displayMonth, "span");

		setMonthDateLabels(getNow());

		for (JLabel d : sunToSatMonth) {
			scheduleMonthly.add(d);
			d.setEnabled(schDays[sunToSatMonth.indexOf(d)]);
		}

		// LinkedHashMap<Integer, List<String>> y = collectSortDates(new ArrayList<>(Arrays.asList(appointments)));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				scheduleMonthly.add(monthdays.get(i*7+j));
				monthdays.get(i*7+j).setEnabled(schDays[j]);
			}
			for (int js = 0; js < 7; js++) {
				// List<String> list = y.get(i*7+js);
				// String[] arrayAp = list.toArray(new String[0]);
				JList<String> oneDayList = new JList<String>(new String[0]);
				JScrollPane scroll = new JScrollPane(oneDayList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scroll.setPreferredSize(new Dimension(100, 50));
				scheduleMonthly.add(scroll);
				oneDayList.setEnabled(schDays[js]);
			}
		}


		scheduleMonthly.setVisible(false);
		scheduleMonthly.setPreferredSize(new Dimension(2000,1000));


	}

	/**
	 * Set up the patients view in doctor
	 */
	public void initializePatients() {
		listPatientsPanel = new JPanel();
		listPatientsPanel.setLayout(new MigLayout("wrap 1"));
		listPatientsPanel.setBackground(Color.WHITE);
	
		
		
		listPatientsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		listPatientsPanel.revalidate();
		listPatientsPanel.repaint();

		JPanel selectedPatient = new JPanel(new MigLayout("wrap 1"));
		selectedPatient.setBorder(BorderFactory.createTitledBorder("Patient"));
		selectedPatient.setBackground(Color.WHITE);
		selectedPatient.add(new JLabel("Patient Information"));
		patientInformation.setText("Name\nAge\nSex\nBlood type\nAddress\nPhone\nEmail\n");
		patientInformation.setEnabled(false);
		selectedPatient.add(patientInformation);
		selectedPatient.add(new JLabel("Detailed Treatment History"));
		pastTreatments = new JTextArea(0,200);
		pastTreatments.setLineWrap(true);
		pastTreatments.setText("Past treatment histories and doctors who recommended treatment.\nTreatment 1: Rest\n\t\t- Doctor: First Last\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas pharetra tempus gravida. Vivamus mollis erat sed libero maximus tempor. Aliquam sed orci non sem fringilla gravida. Duis maximus vitae lacus ut scelerisque. Donec quis mauris eget sapien fringilla tempor ut id nunc. Maecenas a diam non neque ornare porta. Sed volutpat in urna et scelerisque. Nulla consequat justo mauris, in aliquet dolor rutrum eget. Vivamus mauris metus, vehicula nec efficitur ac, ullamcorper quis neque. Nulla augue nisi, porttitor quis nisl in, condimentum euismod nisi. Nunc et leo bibendum nisi ultrices sollicitudin vulputate vitae nisi. Cras nec purus vestibulum, vehicula magna a, pharetra est. Sed tristique, nisi nec suscipit sagittis, tellus dolor tempor ipsum, a eleifend magna purus et neque. Curabitur porta non nisl posuere bibendum. Nam sit amet neque quis enim vehicula scelerisque quis id massa.\nIn ut placerat est. Fusce eu scelerisque lorem. Fusce at mi maximus, condimentum erat et, semper lectus. Donec mollis aliquam nibh, et consequat metus pretium ultrices. Morbi blandit placerat orci. Aliquam erat volutpat. Aliquam id metus orci. Maecenas sagittis mollis nisl, eu ornare nulla lacinia a. Cras congue tristique neque, vitae hendrerit odio. Morbi convallis leo sit amet nisi elementum, in tempor augue bibendum.\nDuis sit amet tempor enim. Proin eleifend, metus vel sodales consectetur, quam magna pellentesque lacus, eget lacinia leo nisl eu nisi. Vivamus aliquam urna ut enim ultricies varius. Duis sed tempor libero, non aliquet sapien. Pellentesque accumsan semper efficitur. Vestibulum vel augue eget sapien tincidunt eleifend. Cras vel molestie metus. Vivamus consequat suscipit mauris, id eleifend mauris pharetra in. Nunc hendrerit augue ultrices egestas commodo. Donec vitae ex turpis. Aenean lectus sem, faucibus nec mollis sed, gravida nec ligula.\nVestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam tempus, neque sit amet convallis gravida, risus tellus euismod metus, et cursus augue dui quis urna. Praesent sed dolor volutpat, tincidunt lorem in, tempor sem. Vivamus at venenatis dui, et scelerisque sem. Duis massa orci, fermentum non lobortis blandit, scelerisque ut arcu. Etiam vel purus eu enim molestie interdum. Etiam dictum mi sit amet diam consectetur venenatis. Donec rutrum odio magna, vel elementum lectus pulvinar at. Proin vel sapien bibendum, viverra velit eget, dignissim elit. Praesent posuere consectetur tellus, et ullamcorper dolor malesuada sed. Etiam ac enim placerat, feugiat purus vitae, imperdiet diam. Mauris at tempor sapien. Pellentesque pellentesque ex sem, eget efficitur elit hendrerit ac. Curabitur imperdiet ac mi eu hendrerit. Nulla venenatis augue ac tristique accumsan. Quisque condimentum neque eget lobortis viverra.\nEtiam eu aliquam arcu, vel lobortis metus. Donec sit amet diam placerat, fringilla nunc vel, tristique sem. Suspendisse mattis scelerisque viverra. Nunc cursus sodales augue, ut molestie nunc aliquet vel. Sed elementum ornare ligula sed volutpat. Ut metus mauris, feugiat luctus nunc a, maximus accumsan libero. In vulputate lorem eros, ac feugiat justo condimentum a. Curabitur laoreet nulla feugiat est semper, ac hendrerit nulla pellentesque. Aenean ac porttitor metus. Maecenas fermentum rutrum ex, sed vestibulum velit convallis a. Aliquam quis lacus sem. Proin euismod porta ligula. Cras in neque posuere, hendrerit ipsum id, egestas sapien. Nulla accumsan, risus at tincidunt tristique, quam justo consequat nulla, non tempor velit justo non ipsum. Ut urna dui, semper suscipit nisl ullamcorper, tincidunt dictum ante.\nEtiam eu aliquam arcu, vel lobortis metus. Donec sit amet diam placerat, fringilla nunc vel, tristique sem. Suspendisse mattis scelerisque viverra. Nunc cursus sodales augue, ut molestie nunc aliquet vel. Sed elementum ornare ligula sed volutpat. Ut metus mauris, feugiat luctus nunc a, maximus accumsan libero. In vulputate lorem eros, ac feugiat justo condimentum a. Curabitur laoreet nulla feugiat est semper, ac hendrerit nulla pellentesque. Aenean ac porttitor metus. Maecenas fermentum rutrum ex, sed vestibulum velit convallis a. Aliquam quis lacus sem. Proin euismod porta ligula. Cras in neque posuere, hendrerit ipsum id, egestas sapien. Nulla accumsan, risus at tincidunt tristique, quam justo consequat nulla, non tempor velit justo non ipsum. Ut urna dui, semper suscipit nisl ullamcorper, tincidunt dictum ante.\nEtiam eu aliquam arcu, vel lobortis metus. Donec sit amet diam placerat, fringilla nunc vel, tristique sem. Suspendisse mattis scelerisque viverra. Nunc cursus sodales augue, ut molestie nunc aliquet vel. Sed elementum ornare ligula sed volutpat. Ut metus mauris, feugiat luctus nunc a, maximus accumsan libero. In vulputate lorem eros, ac feugiat justo condimentum a. Curabitur laoreet nulla feugiat est semper, ac hendrerit nulla pellentesque. Aenean ac porttitor metus. Maecenas fermentum rutrum ex, sed vestibulum velit convallis a. Aliquam quis lacus sem. Proin euismod porta ligula. Cras in neque posuere, hendrerit ipsum id, egestas sapien. Nulla accumsan, risus at tincidunt tristique, quam justo consequat nulla, non tempor velit justo non ipsum. Ut urna dui, semper suscipit nisl ullamcorper, tincidunt dictum ante.\n");
		pastTreatments.setEnabled(false);
		JScrollPane sp1 = new JScrollPane(pastTreatments);
		selectedPatient.add(sp1, "span 1 10, height 300");

		currentTreatment = new JTextArea(0, 200);
		currentTreatment.setText("I am a box for a doctor to enter treatment notes in");
		currentTreatment.setLineWrap(true);

		
		JScrollPane sp2 = new JScrollPane(currentTreatment);
		selectedPatient.add(sp2, "span 1 10, height 175");
		

		btnAddTreatmentNotes = new JButton("Add treatment notes");
		selectedPatient.add(btnAddTreatmentNotes);


		patientPanel.add(selectedPatient, BorderLayout.CENTER);

	}
	
	
	
	
	//add list of patients (immutable) to 'Patient View' screen
	public void setPatientList(String[] patients)
	{
		
		listPatients = new JList(patients);

		scroll = new JScrollPane(listPatients, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setPreferredSize(new DimensionUIResource(200, 0));
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		
		patientPanel.add(scroll, BorderLayout.WEST);
	}
	
	
	


	/**
	 * Function sets up the specific day of the month labels for monthly layout
	 * @param ld
	 */
	public void setMonthDateLabels(LocalDate ld) {
		LocalDate tempTime = ld.withDayOfMonth(1).with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		for (JLabel lbl : monthdays) {
			lbl.setText(tempTime.plusDays(monthdays.indexOf(lbl)).getDayOfMonth()+"");
		}
		displayMonth.setText(ld.getMonth().toString()+" "+ld.getYear());
	}

	/**
	 * Function sets up the specific date of the year labels for weekly layout
	 * @param ld
	 */
	public void setWeekDateLabels(LocalDate ld) {
		LocalDate tempTime = ld.with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		for (JLabel lbl : weekYYYYMMDD) {
			lbl.setText(tempTime.plusDays(weekYYYYMMDD.indexOf(lbl)).toString());
		}
	}

	public  LinkedHashMap<Integer, List<String> > collectSortDates(ArrayList<LocalDateTime> ldt){
        LocalDate startTime = getNow().withDayOfMonth(1).with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
        LocalDate endTime = startTime.plusDays(34);
      

        LinkedHashMap<Integer, List<String> > apts = new LinkedHashMap<Integer,List<String> >(35);
        List<LocalDateTime> fileredDates = new ArrayList<LocalDateTime>();
        for(LocalDateTime x : ldt){
            if(x.toLocalDate().isEqual(startTime)|| x.toLocalDate().isAfter(startTime) || x.toLocalDate().isBefore(endTime) || x.toLocalDate().isEqual(endTime) ){
                fileredDates.add(x);
            }
            
        }

        int key = 0;
        while(key<35){

            List<String> dateAvailable = new ArrayList<String>();

            for(LocalDateTime date: fileredDates){
                if((key<= 30 &&date.getDayOfMonth()== key+1 && date.getMonthValue()==LocalDateTime.now().getMonthValue())){
                    dateAvailable.add(date.toString());
                }
                else if(key>30 && key +1 != date.getDayOfMonth() &&  date.getMonthValue()!=LocalDateTime.now().getMonthValue() && key == 30 + date.getDayOfMonth()  ){
                    dateAvailable.add(date.toString());
                }
            }
            apts.put(key,dateAvailable);
            key++;


        }

        return apts;
        
        
    }	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**Getter and Setter Methods*/

	public JFrame getFrame() {
		return frame;
	}






	public void setFrame(JFrame frame) {
		this.frame = frame;
	}






	public JPanel getPanel() {
		return panel;
	}






	public void setPanel(JPanel panel) {
		this.panel = panel;
	}






	public JPanel getScheduleWeekly() {
		return scheduleWeekly;
	}






	public void setScheduleWeekly(JPanel scheduleWeekly) {
		this.scheduleWeekly = scheduleWeekly;
	}






	public JPanel getScheduleMonthly() {
		return scheduleMonthly;
	}






	public void setScheduleMonthly(JPanel scheduleMonthly) {
		this.scheduleMonthly = scheduleMonthly;
	}


	public JPanel getPatientPanel() {
		return patientPanel;
	}

	public void setPatientPanel(JPanel patPanel) {
		this.patientPanel = patPanel;
	}



	public JPanel getTitleContainer() {
		return titleContainer;
	}






	public void setTitleContainer(JPanel titleContainer) {
		this.titleContainer = titleContainer;
	}






	public JPanel getBackPanel() {
		return backPanel;
	}






	public void setBackPanel(JPanel backPanel) {
		this.backPanel = backPanel;
	}






	public JButton getBtnSignOut() {
		return btnSignOut;
	}






	public void setBtnSignOut(JButton btnSignOut) {
		this.btnSignOut = btnSignOut;
	}






	public JButton getBtnViewPatient() {
		return btnViewPatient;
	}






	public void setBtnViewPatient(JButton btnViewPatient) {
		this.btnViewPatient = btnViewPatient;
	}






	public JButton getBtnMakeChanges() {
		return btnMakeChanges;
	}






	public void setBtnMakeChanges(JButton btnMakeChanges) {
		this.btnMakeChanges = btnMakeChanges;
	}






	public JLabel getTitleLabel() {
		return titleLabel;
	}






	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}






	public JLabel getNursesLabel() {
		return nursesLabel;
	}






	public void setNursesLabel(JLabel nursesLabel) {
		this.nursesLabel = nursesLabel;
	}






	public JLabel getDisplayMonth() {
		return displayMonth;
	}






	public void setDisplayMonth(JLabel displayMonth) {
		this.displayMonth = displayMonth;
	}






	public JLabel getNameLabel() {
		return nameLabel;
	}






	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}








	public JLabel getDeptLabel() {
		return deptLabel;
	}






	public void setDeptLabel(JLabel deptLabel) {
		this.deptLabel = deptLabel;
	}











	public ArrayList<JLabel> getWeekdays() {
		return weekYYYYMMDD;
	}






	public void setWeekdays(ArrayList<JLabel> weekYYYYMMDD) {
		this.weekYYYYMMDD = weekYYYYMMDD;
	}






	public ArrayList<JLabel> getDaysOfWeek() {
		return sunToSatWeek;
	}






	public void setDaysOfWeek(ArrayList<JLabel> sunToSatWeek) {
		this.sunToSatWeek = sunToSatWeek;
	}










	public ArrayList<JLabel> getMonthdays() {
		return monthdays;
	}






	public void setMonthdays(ArrayList<JLabel> monthdays) {
		this.monthdays = monthdays;
	}






	public Container getContainer() {
		return container;
	}






	public void setContainer(Container container) {
		this.container = container;
	}






	public LocalDate getNow() {
		return now;
	}






	public void setNow(LocalDate now) {
		this.now = now;
	}






	public JComboBox<String> getNurseComboBox() {
		return nurseComboBox;
	}






	public void setNurseComboBox(JComboBox<String> nurseComboBox) {
		this.nurseComboBox = nurseComboBox;
	}

	public JTextArea getPastTreatmentBox() {
		return pastTreatments;
	}

	public void setPastTreatmentBox(JTextArea x) {
		this.pastTreatments = x;
	}

	public JTextArea getCurrentTreatmentBox() {
		return currentTreatment;
	}

	public void setCurrentTreatmentBox(JTextArea x) {
		this.currentTreatment = x;
	}

	public JTextArea getPatientInformation() {
		return patientInformation;
	}

	public void setPatientInformation(JTextArea pi) {
		this.patientInformation = pi;
	}

	public JPanel getListPatientsPanels() {
		return listPatientsPanel;
	}

	public void setListPatientsPanel(JPanel p) {
		this.listPatientsPanel = p;
	}

	public JButton getButtonTreatmentNotes() {
		return btnAddTreatmentNotes;
	}

	public void setButtonTreatmentNotes(JButton xs) {
		btnAddTreatmentNotes = xs;
	}

	public void setPatientListPanels(ArrayList<JPanel> patList) {
		this.patientListPanels = patList;
	}


	public ArrayList<JPanel> getPatientListPanels() {
		return patientListPanels;
	}

	public void setScheduleNameLabelWeek (JLabel s) {
		this.scheduleNameLabelWeek = s;
	}

	public JLabel getScheduleNameLabelWeek() {
		return scheduleNameLabelWeek;
	}

	public void setScheduleNameLabelMonth (JLabel s) {
		this.scheduleNameLabelMonth = s;
	}

	public JLabel getScheduleNameLabelMonth() {
		return scheduleNameLabelMonth;
	}


	public JList getListPatients() {
		return listPatients;
	}


	public void setListPatients(JList listPatients) {
		this.listPatients = listPatients;
	}


	public JButton getBtnAddTreatmentNotes() {
		return btnAddTreatmentNotes;
	}


	public void setBtnAddTreatmentNotes(JButton btnAddTreatmentNotes) {
		this.btnAddTreatmentNotes = btnAddTreatmentNotes;
	}


	public JTextArea getCurrentTreatment() {
		return currentTreatment;
	}


	public void setCurrentTreatment(JTextArea currentTreatment) {
		this.currentTreatment = currentTreatment;
	}


	public JButton getButtonOwn() {
		return btnOwn;
	}

	public void setButtonOwn(JButton o) {
		this.btnOwn = o;
	}

	public LinkedHashMap<String, String> getAppointments() {
		return appointments;
	}

	public void setAppointments(LinkedHashMap<String, String> appointments) {
		this.appointments = appointments;
	}
	
	public Boolean[] getScheduledDays() {
		return schDays;
	}

	public void setScheduledDays(Boolean[] b) {
		this.schDays = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JButton getBtnToggle() {
		return btnToggle;
	}

	public void setBtnToggle(JButton bt) {
		this.btnToggle = bt;
	}

}