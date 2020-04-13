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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
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
import javax.swing.JCheckBox;
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
 * Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.
 * 
 * @author Jenny Z, Sajid C
 *
 */
public class DoctorView {

	private JFrame frame;

	private JPanel panel;
	private JPanel scheduleWeekly;
	private JPanel scheduleMonthly;
	private JPanel titleContainer;
	private JPanel backPanel;
	private JPanel patientPanel;
	private JPanel btnPaneSchedule;
	private JPanel buttonContainer;
	private JPanel scheduleContainer;
	private JPanel modifyScheduleContainer;

	private JScrollPane scrollWeekly;

	private JButton btnSignOut;
	private JButton btnViewPatient;
	private JButton btnMakeChanges;
	private JButton btnAddTreatmentNotes;
	private JButton btnOwn;
	private JButton btnToggle;
	private JButton btnBookApt;
	private JButton reqAvailChangeBtn;
	private JButton btnSelectFile;
	private JButton btnUpdateReferral;

	private JLabel titleLabel;
	private JLabel nursesLabel;
	private JLabel displayMonth;
	private JLabel nameLabel;
	private JLabel deptLabel;
	private JLabel scheduleNameLabelWeek, scheduleNameLabelMonth;
	private JLabel fileName;

	private JList<String> nurses;

	private JTextField departmentInput;
	private JTextField nameInput;

	private JTextArea patientInformation = new JTextArea(7, 90);
	private JTextArea pastTreatments;
	private JTextArea currentTreatment;

	private ArrayList<JLabel> weekYYYYMMDD = new ArrayList<JLabel>(7); // Labels of the format: Sunday, Monday, etc.
	private ArrayList<JLabel> sunToSatMonth = new ArrayList<JLabel>(7); // Labels of the format: Sunday, Monday, etc.
	private ArrayList<JLabel> sunToSatWeek = new ArrayList<JLabel>(7); // Labels of the format: YYYY-MM-DD
	private ArrayList<JLabel> monthdays = new ArrayList<JLabel>(35);
	private ArrayList<JPanel> patientListPanels = new ArrayList<JPanel>(0);

	JComboBox<String> nurseComboBox;
	JComboBox<String>[] availTimes;

	JCheckBox selfCheck;

	Container container;

	private LocalDate now = LocalDate.now();

	private LinkedHashMap<String, String> appointments;
	private Boolean[] schDays;
	private int[] shiftTimes;
	private String name;

	String[] times = { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "08:00", "09:00", "10:00",
			"11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00",
			"23:00", "24:00" };

	// private JScrollPane scroll;
	private JList listPatients;

	private JComboBox<String> apptType;
	private JComboBox<String> chooseAppt;
	private JComboBox<String> labTime;
	private JComboBox<String> year, month, day;
	/*
	 * ################################################################# # Sign out
	 * # # # # <Department> : <Name>, M.D. #
	 * ################################################################# # <Name>
	 * Weekly/Monthly # Patients # # # Nurses # # Calendar + Appointments # Schedule
	 * # # # UselessBtn # # # # # # # # # # # # # # # # # # # # # # # # # #
	 * _________________________________________________# # # | Back | UselessButton
	 * | SwitchView | Forward # #
	 * #################################################################
	 */

	
	/**
	 *  Constructor
	 *  
	 * @param title
	 */
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

	/**
	 * Initialize the view
	 * @author Jenny Z
	 */
	public void initGUI() {
		JPanel mainPane = new JPanel(new MigLayout());
		mainPane.setBackground(new Color(231,239,255));
		
		//add scrolling to main container
		JScrollPane scroll = new JScrollPane(mainPane
				, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.getVerticalScrollBar().setUnitIncrement(10);
			scroll.getHorizontalScrollBar().setUnitIncrement(10);
			frame.add(scroll, BorderLayout.CENTER);					//add the panel as the container for the frame

	}


	/**
	 * initialize the panels and components that will go inside the frame
	 */
	public void initializeGUI() {
		setNow(LocalDate.now());
		// Create title container(JLabel) and set it as top of page in the frame
		titleContainer = new JPanel();
		titleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
		frame.getContentPane().add(titleContainer, BorderLayout.NORTH); // Add the JPanel to the frame

		/**
		 * ################################################################# # Sign out
		 * # # # # <Department> : <Name>, M.D. #
		 * #################################################################
		 */

		// Create a panel for the sign out button
		backPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) backPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		titleContainer.add(backPanel); // add the JPanel to the existing JPanel

		// Sign out button, returns to login screen when clicked
		btnSignOut = new JButton("Sign out");
		btnSignOut.setBackground(new Color(154,50,50));
		btnSignOut.setForeground(Color.WHITE);
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

		/**
		 * ################################################################# # <Name>
		 * Weekly/Monthly # Patients # # # Nurses # # Calendar + Appointments # Schedule
		 * # # # UselessBtn # # # # # # # # # # # # # # # # # # # # # # # # # #
		 * _________________________________________________# # # | Back | UselessButton
		 * | SwitchView | Forward # #
		 * #################################################################
		 */
		// Create a content pane centered at the middle of the page
		JPanel contentPane = new JPanel();
		frame.getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		// Create a container for the schedule, for the schedule itself and for the
		// button panel associated with it (on the buttom)
		scheduleContainer = new JPanel();
		scheduleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		scheduleContainer.setLayout(new MigLayout("hidemode 3"));
		contentPane.add(scheduleContainer);

		// Create a container for modifying the schedule
		modifyScheduleContainer = new JPanel();
		initializeModify();
		contentPane.add(modifyScheduleContainer);

		// Create container for patient panel

		/**
		 * ################################################################# # Sign out
		 * # # # # <Department> : <Name>, M.D. #
		 * ################################################################# # Patient1
		 * # Patient information # # Patient2 # # # PatientX # # # # Past treatment # #
		 * # # # # # # # # # # Add treatment notes # # # # # # # # # # # #
		 * AddTreatmentButton #
		 * #################################################################
		 */

		initializePatients();
		contentPane.add(patientPanel);

		// Create a panel for the schedule itself
		// Initialize YYYYMMDD labels for weekly schedule
		for (int i = 1; i < 8; i++) {
			JLabel lblTemp = new JLabel(now.with(WeekFields.of(Locale.CANADA).dayOfWeek(), i).toString());
			lblTemp.setFont(new Font("Tahoma", Font.PLAIN, 14));
			weekYYYYMMDD.add(lblTemp);
		}

		scheduleMonthly = new JPanel();
		scheduleWeekly = new JPanel();

		scrollWeekly = new JScrollPane(scheduleWeekly, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollWeekly.setLayout(new ScrollPaneLayout());
		scrollWeekly.getVerticalScrollBar().setUnitIncrement(10);
		scrollWeekly.setPreferredSize(new Dimension(2000,1000));


		// scheduleContainer.add(scheduleWeekly);
		scheduleContainer.add(scrollWeekly, "growx");
		scheduleContainer.add(scheduleMonthly);

		// Create buttons for manipulating the schedule
		initializeButtonsSchedule(WeekFields.of(Locale.CANADA).dayOfWeek());
		scheduleContainer.add(btnPaneSchedule, "south");

		// Create the buttons on the right panel for viewing patients and nurses
		initializeButtonsRight();
		frame.getContentPane().add(buttonContainer, BorderLayout.EAST);

		frame.setVisible(true);

	}

	////////////////////////////////////////////////////
	/**
	 * 
	 */
	public void initializeModify() {
		modifyScheduleContainer = new JPanel(new MigLayout());
		modifyScheduleContainer.setBackground(Color.WHITE);
		modifyScheduleContainer.setBorder(BorderFactory.createLineBorder(Color.PINK));

		String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

		JPanel availChangePanel = new JPanel(
				new MigLayout("wrap 3", "[align right] 20 [align right] 40 [grow, align left]"));
		availChangePanel.setBackground(Color.WHITE);
		availChangePanel.setPreferredSize(new Dimension(325, 200));
		availChangePanel.setBorder(BorderFactory.createTitledBorder("Request Availability Change"));

		availChangePanel.add(new JLabel("Start"), "span 2");
		availChangePanel.add(new JLabel("End"), " wrap");

		availTimes = new JComboBox[14];
		for (int i = 0; i < 7; i++) {
			availChangePanel.add(new JLabel(days[i]));
			availTimes[i * 2] = new JComboBox(times);
			availTimes[i * 2 + 1] = new JComboBox(times);
			availChangePanel.add(availTimes[i * 2]);
			availChangePanel.add(availTimes[i * 2 + 1]);
		}
		for (JComboBox<String> jComboBox : availTimes) {
			jComboBox.setBackground(Color.WHITE);
		}

		reqAvailChangeBtn = new JButton("Send Request");
		availChangePanel.add(reqAvailChangeBtn, "span, align right");

		modifyScheduleContainer.add(availChangePanel, "dock east");
		modifyScheduleContainer.setVisible(false);
	}

	

	/**
	 * 
	 * @param a
	 */
	public void setVisibility(Boolean a) {
		frame.setVisible(a);
	}

	/**
	 * 
	 * @param message
	 */
	public void loginError(String message) {
		JFrame frame = new JFrame();
		frame.setSize(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(frame, message);
	}

	/**
	 * 
	 */
	public void initializeButtonsRight() {
		buttonContainer = new JPanel(new MigLayout("wrap 1, alignx center, fillx"));

		JLabel optionlbl = new JLabel("Select Action:");
		JButton btnPatients = new JButton("View patients");
		JLabel nurselbl = new JLabel("Nurse schedules:");
		nurseComboBox = new JComboBox<String>();
		nurseComboBox.setBackground(Color.WHITE);
		nurseComboBox.setSelectedIndex(-1);
		JButton btnChange = new JButton("Modify Schedules");

		btnOwn = new JButton("View own schedule");
		btnOwn.setEnabled(false);
		btnOwn.addActionListener(e -> {
			scheduleContainer.setVisible(true);
			modifyScheduleContainer.setVisible(false);
			patientPanel.setVisible(false);
			btnOwn.setEnabled(false);
		});

		btnPatients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// contentPane.setVisible(false);
				scheduleContainer.setVisible(false);
				patientPanel.setVisible(true);
				btnOwn.setEnabled(true);
				modifyScheduleContainer.setVisible(false);
			}
		});

		nurseComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnOwn.setEnabled(true);
			}
		});

		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modifyScheduleContainer.setVisible(true);
				patientPanel.setVisible(false);
				scheduleContainer.setVisible(false);
				btnOwn.setEnabled(true);
			}
		});
		
		buttonContainer.add(optionlbl);
		buttonContainer.add(btnPatients, "grow");
		buttonContainer.add(Box.createRigidArea(new Dimension(0,15)));
		buttonContainer.add(btnChange, "grow");
		buttonContainer.add(Box.createRigidArea(new Dimension(0,15)));
		buttonContainer.add(nurselbl);
		buttonContainer.add(nurseComboBox, "grow");
		buttonContainer.add(btnOwn, "grow");
		// buttonContainer.add(Box.createRigidArea(new Dimension(0,15)));
		// buttonContainer.add(btnChange, "grow");


	}

	/**
	 * 
	 * @param fieldISO
	 */
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

		// Button that changes schedule between weekly and monthly views
		// Currently only weekly view is setup
		btnToggle = new JButton("Monthly view");
		btnPaneSchedule.add(btnToggle);
		btnToggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnToggle.getText().equals("Monthly view")) {
					btnToggle.setText("Weekly view");
					setMonthDateLabels(getNow());
					String schTitle = getScheduleNameLabelMonth().getText();
					initializeMonthlySchedule();
					getScheduleNameLabelMonth().setText(schTitle);
					isWeekly(false);
				} else {
					btnToggle.setText("Monthly view");
					setWeekDateLabels(getNow());
					String schTitle = getScheduleNameLabelWeek().getText();
					initializeWeeklySchedule();
					getScheduleNameLabelWeek().setText(schTitle);
					isWeekly(true);
				}
			}
		});

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnToggle.getText().equals("Monthly view")) {
					setNow(now.minusWeeks(1));
					setWeekDateLabels(getNow());
					String schTitle = getScheduleNameLabelWeek().getText();
					initializeWeeklySchedule();
					getScheduleNameLabelWeek().setText(schTitle);
					isWeekly(true);
				} else {
					setNow(now.minusMonths(1));
					setMonthDateLabels(getNow());
					String schTitle = getScheduleNameLabelMonth().getText();
					initializeMonthlySchedule();
					getScheduleNameLabelMonth().setText(schTitle);
					isWeekly(false);
				}
				if (getNow().compareTo(LocalDate.now()) <= 0) {
					setNow(LocalDate.now());
					btnBack.setEnabled(false);
				}
			}
		});

		JButton btnForward = new JButton(">");
		btnPaneSchedule.add(btnForward);
		btnForward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnToggle.getText().equals("Monthly view")) {
					setNow(now.plusWeeks(1));
					setWeekDateLabels(getNow());
					String schTitle = getScheduleNameLabelWeek().getText();
					initializeWeeklySchedule();
					getScheduleNameLabelWeek().setText(schTitle);
					isWeekly(true);
				} else {
					setNow(now.plusMonths(1));
					setMonthDateLabels(getNow());
					String schTitle = getScheduleNameLabelMonth().getText();
					initializeMonthlySchedule();
					isWeekly(false);
					getScheduleNameLabelMonth().setText(schTitle);
				}
				if (!btnBack.isEnabled())
					btnBack.setEnabled(true);
			}
		});

		btnPaneSchedule.setPreferredSize(new Dimension(500, 0));

		btnPaneSchedule.setBorder(BorderFactory.createEtchedBorder());
	}

	/**
	 * 
	 */
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

	/**
	 * 
	 */
	public void initializeWeeklySchedule() {
		Boolean[][] ava = new Boolean[24][7];
		// i is for the hour
		for (int i = 0; i < 24; i++) {
			// j is for the day and also used as the index for shiftTimes
			for (int j = 0; j < 7; j++) {
				if (shiftTimes[j * 2] == shiftTimes[j * 2 + 1]) {
					ava[i][j] = false;
				} else {
					if ((i >= shiftTimes[j * 2]) && (i <= shiftTimes[j * 2 + 1])) {
						ava[i][j] = true;
					} else {
						ava[i][j] = false;
					}
				}
			}
		}

		String[][] listApsWeek = new String[24][7];

		LocalDate startRange = now.with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		LocalDate endRange = startRange.plusDays(6);
		for (Entry<String, String> entry : getAppointments().entrySet()) {
			LocalDateTime time = LocalDateTime.parse(entry.getKey());
			int day = time.getDayOfWeek().getValue() % 7;
			int hour = time.getHour();
			if ((time.toLocalDate().compareTo(startRange) >= 0) && (time.toLocalDate().compareTo(endRange) <= 0)
					&& (ava[hour][day] == true)) {
				listApsWeek[time.getHour()][time.getDayOfWeek().getValue() % 7] = entry.getValue();
			}

		}

		scheduleWeekly.removeAll();
		scheduleWeekly.revalidate();
		scheduleWeekly.repaint();
		scheduleWeekly.setLayout(new MigLayout("wrap 8, gap 10px 10px",
				"[align center] 10 [align center] 10 [align center] 10 [align center] 10 [align center] 10 [align center] 10 [align center] 10 [align center]"));
		scheduleWeekly.setBackground(Color.WHITE);
		scheduleWeekly.setBorder(new EmptyBorder(5, 5, 5, 5));

		scheduleNameLabelWeek = new JLabel(getName() + "'s Schedule: Weekly");
		scheduleWeekly.add(scheduleNameLabelWeek, "span");
		scheduleWeekly.add(Box.createHorizontalGlue());

		setWeekDateLabels(getNow());
		for (JLabel wd : weekYYYYMMDD) {
			scheduleWeekly.add(wd, "sg a");
			wd.setEnabled(schDays[weekYYYYMMDD.indexOf(wd)]);
			wd.setEnabled(true);
		}
		scheduleWeekly.add(Box.createHorizontalGlue(), "sg a");
		for (JLabel lbl : sunToSatWeek) {
			scheduleWeekly.add(lbl, "sg a");
			lbl.setEnabled(schDays[sunToSatWeek.indexOf(lbl)]);
			lbl.setEnabled(true);
		}

		for (int i = 0; i < 24; i++) {
			scheduleWeekly.add(new JLabel(i + ":00"), "sg a, alignx right");
			for (int j = 0; j < 7; j++) {
				JLabel templbl = new JLabel("  ");
				templbl.setBackground(Color.WHITE);
				if (listApsWeek[i][j] != null) {
					templbl.setText(listApsWeek[i][j]);
					templbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					templbl.setBackground(Color.ORANGE);
				}
				templbl.setOpaque(true);
				scheduleWeekly.add(templbl, "sg a");
				templbl.setEnabled(ava[i][j]);
				if (!ava[i][j]) {
					templbl.setBackground(Color.LIGHT_GRAY);
				}
				templbl.setPreferredSize(new Dimension(100, 35));

			}
		}

		// scheduleWeekly.setPreferredSize(new Dimension(2000, 1000));
	}

	/**
	 * 
	 */
	public void initializeMonthlySchedule() {
		scheduleMonthly.removeAll();
		scheduleMonthly.revalidate();
		scheduleMonthly.repaint();
		scheduleMonthly.setLayout(new MigLayout("wrap 7",
				"[align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center] 20 [align center]"));
		scheduleMonthly.setBackground(Color.WHITE);
		scheduleMonthly.setBorder(new EmptyBorder(5, 5, 5, 5));
		scheduleMonthly.setBorder(new LineBorder(Color.RED));

		scheduleNameLabelMonth = new JLabel(getName() + "'s schedule: Monthly");
		scheduleMonthly.add(scheduleNameLabelMonth, "span");
		displayMonth = new JLabel(now.getMonth().toString() + " " + now.getYear());
		scheduleMonthly.add(displayMonth, "span");

		setMonthDateLabels(getNow());

		for (JLabel d : sunToSatMonth) {
			scheduleMonthly.add(d);
			d.setEnabled(schDays[sunToSatMonth.indexOf(d)]);
			d.setEnabled(true);
		}

		ArrayList<String[]> apts = filterAppointmentsMonth();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				scheduleMonthly.add(monthdays.get(i * 7 + j));
				monthdays.get(i * 7 + j).setEnabled(schDays[j]);
			}
			for (int js = 0; js < 7; js++) {
				JList<String> oneDayList = new JList<String>(apts.get(i * 7 + js));
				JScrollPane scroll = new JScrollPane(oneDayList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scroll.setPreferredSize(new Dimension(100, 50));
				scheduleMonthly.add(scroll);
				oneDayList.setEnabled(schDays[js]);
				oneDayList.setEnabled(true);
			}
		}

		scheduleMonthly.setVisible(false);
		scheduleMonthly.setPreferredSize(new Dimension(2000, 1000));

	}

	/**
	 * Calculates the set of years encompassing the next 3 months
	 * @return set of years encompassing the next 3 months in a JComboBox
	 */
	public JComboBox<String> initYearComboForward()
	{
		LocalDate date3MonthsAway = LocalDate.now().plusMonths(3);
		LocalDate now = LocalDate.now();
		
		JComboBox<String> temp = new JComboBox<String>();
		temp.addItem(Integer.toString(now.getYear()) );
		while(now.isBefore(date3MonthsAway) )
		{
			if(now.getYear() == date3MonthsAway.getYear() )
				now = now.plusDays(1);
			else
			{
				temp.addItem(Integer.toString(now.getYear()) );
				now = now.plusDays(1);
			}
		}		
		return temp;
	}
	
	/**
	 * Check that selected lab date is in the future
	 * @return true if selected lab date is in the future
	 */
	public boolean isFutureDate()
	{
		//current values
		int yr = LocalDate.now().getYear();
		int mn = LocalDate.now().getMonthValue();
		int dy = LocalDate.now().getDayOfMonth();
		
		//selected values
		int selYr = Integer.parseInt(year.getItemAt(year.getSelectedIndex()).toString() );
		int selMn = Integer.parseInt(month.getItemAt(month.getSelectedIndex()).toString() );
		int selDay = Integer.parseInt(day.getItemAt(day.getSelectedIndex()).toString() );
		
		if(selMn == mn && selDay > dy)
			return true;
		else if(selMn == mn && selDay <= dy)
			return false;
		else if(selMn < mn)
			return false;
		else
			return true;
	}
	/**
	 * Set up the patients view in doctor
	 */
	public void initializePatients() {
		patientPanel = new JPanel();
		patientPanel.setBorder(new LineBorder(Color.CYAN));
		patientPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		// patientPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		patientPanel.setLayout(new BorderLayout());
		patientPanel.setBackground(Color.WHITE);
		patientPanel.setVisible(false);

		JPanel topPanel = new JPanel(new MigLayout("wrap 1"));
		JPanel appointmentPanel = new JPanel(new MigLayout());
		appointmentPanel.setBackground(Color.WHITE);
		appointmentPanel.setBorder(BorderFactory.createTitledBorder("Book an appointment"));
		appointmentPanel.setPreferredSize(new Dimension(1300, 100));
		appointmentPanel.setMaximumSize(new Dimension(1400, 125));
		btnBookApt = new JButton("Book Appointment");

		apptType = new JComboBox<String>();
		apptType.addItem("Doctor Appointment");
		apptType.addItem("Lab Test");
		apptType.addActionListener(e -> disableLab());

		chooseAppt = new JComboBox<String>();
		labTime = new JComboBox<String>(times);
		labTime.setEnabled(false);
		year = initYearComboForward();
		year.setEnabled(false);
		year.addActionListener(e -> initDaysinBox());
		month = initMonthCombo();
		month.setEnabled(false);
		month.addActionListener(e -> initDaysinBox());
		day = initDayCombo();
		day.setEnabled(false);
		initDaysinBox();

		appointmentPanel.add(new JLabel("Type:"));
		appointmentPanel.add(apptType);

		appointmentPanel.add(new JLabel("Select Appointment: "));
		appointmentPanel.add(chooseAppt, "span, pushx, growx, wrap");

		appointmentPanel.add(new JLabel("Lab Date: "), "skip 2, align right");
		appointmentPanel.add(year, "sg c, split");
		appointmentPanel.add(month, "sg c, split");
		appointmentPanel.add(day, "sg c");
		appointmentPanel.add(new JLabel("Time: "), "gapleft 10, split");
		appointmentPanel.add(labTime, "sg c");

		appointmentPanel.add(btnBookApt, "skip 2, align right");

		JPanel referralPanel = new JPanel(new MigLayout("hidemode 3"));
		referralPanel.setBorder(BorderFactory.createTitledBorder("Referrals"));
		referralPanel.setPreferredSize(new Dimension(1300, 0));
		referralPanel.setBackground(Color.WHITE);

		btnSelectFile = new JButton("Select file");
		fileName = new JLabel("Can't see me");
		fileName.setVisible(false);

		departmentInput = new JTextField(20);
		departmentInput.setText("Input Department/Lab test");
		nameInput = new JTextField(20);
		nameInput.setText("Input Doctor/Test type");

		departmentInput.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				if (departmentInput.getText().equals("Input Department/Lab test"))
					departmentInput.setText("");

			}
			@Override
			public void focusLost(FocusEvent fe) {
				if (departmentInput.getText().length() == 0) {
					departmentInput.setText("Input Department/Lab test");
				} 
			}
		});

		nameInput.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				if (nameInput.getText().equals("Input Doctor/Test type"))
					nameInput.setText("");

			}
			@Override
			public void focusLost(FocusEvent fe) {
				if (nameInput.getText().length() == 0) {
					nameInput.setText("Input Doctor/Test type");
				} 
			}
		});

		btnUpdateReferral = new JButton("Assign referral");

		referralPanel.add(new JLabel("Upload referral: "));
		referralPanel.add(fileName);
		referralPanel.add(btnSelectFile, "wrap");
		referralPanel.add(new JLabel("Give referral: "));
		referralPanel.add(departmentInput, "span 2");
		referralPanel.add(nameInput, "wrap");
		referralPanel.add(btnUpdateReferral);

		topPanel.add(appointmentPanel, "north, span");
		topPanel.add(referralPanel, "span");

		patientPanel.add(topPanel, BorderLayout.NORTH);





		JPanel selectedPatient = new JPanel(new MigLayout("wrap 1"));
		selectedPatient.setBorder(BorderFactory.createTitledBorder("Patient"));
		selectedPatient.setBackground(Color.WHITE);
		selectedPatient.add(new JLabel("Patient Information"));
		patientInformation.setText("Name\nAge\nSex\nBlood type\nAddress\nPhone\nEmail\n");
		patientInformation.setEnabled(false);
		selectedPatient.add(patientInformation);
		selectedPatient.add(new JLabel("Detailed Treatment History"));
		pastTreatments = new JTextArea(0, 200);
		pastTreatments.setLineWrap(true);
		pastTreatments.setText("Select a patient from the left");
		pastTreatments.setEnabled(false);
		JScrollPane sp1 = new JScrollPane(pastTreatments);
		selectedPatient.add(sp1, "span 1 10, height 300");

		currentTreatment = new JTextArea(0, 200);
		currentTreatment.setText("I am a box for a doctor to enter treatment notes in");
		currentTreatment.setLineWrap(true);

		JScrollPane sp2 = new JScrollPane(currentTreatment);
		selectedPatient.add(sp2, "span 1 10, height 175");

		btnAddTreatmentNotes = new JButton("Add treatment notes");
		btnAddTreatmentNotes.setEnabled(false);
		selectedPatient.add(btnAddTreatmentNotes);

		patientPanel.add(selectedPatient, BorderLayout.CENTER);

	}

	/**
	 * 
	 * @param patients
	 */
	public void setPatientList(String[] patients) {

		listPatients = new JList(patients);

		JScrollPane scroll = new JScrollPane(listPatients, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setPreferredSize(new DimensionUIResource(200, 0));
		scroll.getVerticalScrollBar().setUnitIncrement(10);

		patientPanel.add(scroll, BorderLayout.WEST);
	}

	/**
	 * 
	 * @param n
	 */
	public void setNurseList(String[] n) {
		nurses = new JList<String>(n);

		JScrollPane scroll = new JScrollPane(nurses, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setPreferredSize(new Dimension(300, 300));
		scroll.getVerticalScrollBar().setUnitIncrement(10);

		JPanel namesPanel = new JPanel(new MigLayout("wrap 1"));
		namesPanel.setBorder(BorderFactory.createTitledBorder("Upcoming shifts"));
		selfCheck = new JCheckBox("Own availability", true);
		selfCheck.setBackground(Color.WHITE);
		nurses.setEnabled(false);
		selfCheck.addItemListener(e -> {
			if (e.getStateChange() == 1) {
				nurses.setEnabled(false);
			} else {
				nurses.setEnabled(true);
			}
		});
		namesPanel.add(selfCheck);
		namesPanel.add(scroll);
		modifyScheduleContainer.add(namesPanel, "dock west");
	}

	/**
	 * Function sets up the specific day of the month labels for monthly layout
	 * 
	 * @param ld
	 */
	public void setMonthDateLabels(LocalDate ld) {
		LocalDate tempTime = ld.withDayOfMonth(1).with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		for (JLabel lbl : monthdays) {
			lbl.setText(tempTime.plusDays(monthdays.indexOf(lbl)).getDayOfMonth() + "");
		}
		displayMonth.setText(ld.getMonth().toString() + " " + ld.getYear());
	}

	/**
	 * Function sets up the specific date of the year labels for weekly layout
	 * 
	 * @param ld
	 */
	public void setWeekDateLabels(LocalDate ld) {
		LocalDate tempTime = ld.with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		for (JLabel lbl : weekYYYYMMDD) {
			lbl.setText(tempTime.plusDays(weekYYYYMMDD.indexOf(lbl)).toString());
		}
	}

	/**
	 * Filters out a list of appointments starting from the first day (of the first month) of the current month. Returns a list of appointments (as a list of list of names of patients) for a period of 35 days
	 * @return
	 */
	public ArrayList<String[]> filterAppointmentsMonth() {
		ArrayList<ArrayList<String>> interFilter = new ArrayList<ArrayList<String>>(0);
		for (int i = 0; i < 35; i++) {
			interFilter.add(new ArrayList<String>());
		}
		LocalDate startRange = getNow().withDayOfMonth(1).with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		LocalDate endRange = startRange.plusDays(34);

		for (Entry<String, String> entry : getAppointments().entrySet()) {
			LocalDate time = LocalDateTime.parse(entry.getKey()).toLocalDate();
			if ((time.compareTo(startRange) >= 0) && (time.compareTo(endRange) <= 0)) {
				int index = (int) ChronoUnit.DAYS.between(startRange, time);
				interFilter.get(index).add(entry.getValue());
			}

		}

		ArrayList<String[]> filtered = new ArrayList<String[]>(0);
		for (ArrayList<String> x : interFilter) {
			filtered.add(x.toArray(new String[0]));
		}

		return filtered;
	}

	/**
	 * Sets the weekly/monthly schedule view
	 * @param b
	 */
	public void isWeekly(Boolean b) {
		if (b) {
			scheduleMonthly.setVisible(false);
			// scheduleWeekly.setVisible(true);
			scrollWeekly.setVisible(true);
		} else {
			scheduleMonthly.setVisible(true);
			// scheduleWeekly.setVisible(false);
			scrollWeekly.setVisible(false);
		}
	}

	/**
	 * Sets the name labels for the schedule
	 * @param name
	 */
	public void setScheduleNameLabels(String name) {
		getScheduleNameLabelMonth().setText(name + " Monthly Schedule");
		getScheduleNameLabelWeek().setText(name + " Weekly Schedule");
	}

	/**
	 * 
	 */
	public void disableLab() {

		if (apptType.getSelectedItem().equals("Lab Test")) {
			chooseAppt.setEnabled(false);

			year.setEnabled(true);
			month.setEnabled(true);
			day.setEnabled(true);
			labTime.setEnabled(true);
		} else {
			chooseAppt.setEnabled(true);

			year.setEnabled(false);
			month.setEnabled(false);
			day.setEnabled(false);
			labTime.setEnabled(false);
		}
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> initYearCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		for (int i = 0; i < 120; i++) {
			temp.addItem((LocalDate.now().getYear() - i) + "");
		}
		temp.setBackground(Color.WHITE);

		return temp;
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> initMonthCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		for (int i = 0; i < 12; i++) {
			temp.addItem((i + 1) + "");
		}
		temp.setBackground(Color.WHITE);

		return temp;
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> initDayCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		temp.setBackground(Color.WHITE);

		return temp;
	}

	/**
	 * 
	 */
	public void initDaysinBox() {//
		
		day.removeAllItems();
									// https://www.youtube.com/watch?v=yylaqeWkPmM
									// https://stackoverflow.com/questions/33666456/java8-datetimeformatter-parse-date-with-both-single-digit-and-double-digit-day
		DateTimeFormatter df = DateTimeFormatter.ofPattern("uuuu/M/d").withResolverStyle(ResolverStyle.STRICT);
		for (int i = 1; i <= 31; i++) {
			try {
				df.parse((String) year.getSelectedItem() + "/" + (String) month.getSelectedItem() + "/"
						+ Integer.toString(i));
				day.addItem(Integer.toString(i));
			} catch (Exception e) {
				continue;
			}
		}
	}

	/**
	 * pop up a message-dialog box with a message passed in 
	 * @param message message to show user
	 */
	public void showDialogToUser(String message)
	{
		JOptionPane.showMessageDialog(getFrame().getContentPane(), message);
	}	

	/** Getter and Setter Methods */

	/**
	 * 
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * 
	 * @param frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * 
	 * @param panel
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getScheduleWeekly() {
		return scheduleWeekly;
	}

	/**
	 * 
	 * @param scheduleWeekly
	 */
	public void setScheduleWeekly(JPanel scheduleWeekly) {
		this.scheduleWeekly = scheduleWeekly;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getScheduleMonthly() {
		return scheduleMonthly;
	}

	/**
	 * 
	 * @param scheduleMonthly
	 */
	public void setScheduleMonthly(JPanel scheduleMonthly) {
		this.scheduleMonthly = scheduleMonthly;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getPatientPanel() {
		return patientPanel;
	}

	/**
	 * 
	 * @param patPanel
	 */
	public void setPatientPanel(JPanel patPanel) {
		this.patientPanel = patPanel;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getTitleContainer() {
		return titleContainer;
	}

	/**
	 * 
	 * @param titleContainer
	 */
	public void setTitleContainer(JPanel titleContainer) {
		this.titleContainer = titleContainer;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getBackPanel() {
		return backPanel;
	}

	/**
	 * 
	 * @param backPanel
	 */
	public void setBackPanel(JPanel backPanel) {
		this.backPanel = backPanel;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnSignOut() {
		return btnSignOut;
	}

	/**
	 * 
	 * @param btnSignOut
	 */
	public void setBtnSignOut(JButton btnSignOut) {
		this.btnSignOut = btnSignOut;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnViewPatient() {
		return btnViewPatient;
	}

	/**
	 * 
	 * @param btnViewPatient
	 */
	public void setBtnViewPatient(JButton btnViewPatient) {
		this.btnViewPatient = btnViewPatient;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnMakeChanges() {
		return btnMakeChanges;
	}

	/**
	 * 
	 * @param btnMakeChanges
	 */
	public void setBtnMakeChanges(JButton btnMakeChanges) {
		this.btnMakeChanges = btnMakeChanges;
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getTitleLabel() {
		return titleLabel;
	}

	/**
	 * 
	 * @param titleLabel
	 */
	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getNursesLabel() {
		return nursesLabel;
	}

	/**
	 * 
	 * @param nursesLabel
	 */
	public void setNursesLabel(JLabel nursesLabel) {
		this.nursesLabel = nursesLabel;
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getDisplayMonth() {
		return displayMonth;
	}

	/**
	 * 
	 * @param displayMonth
	 */
	public void setDisplayMonth(JLabel displayMonth) {
		this.displayMonth = displayMonth;
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getNameLabel() {
		return nameLabel;
	}

	/**
	 * 
	 * @param nameLabel
	 */
	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getDeptLabel() {
		return deptLabel;
	}

	/**
	 * 
	 * @param deptLabel
	 */
	public void setDeptLabel(JLabel deptLabel) {
		this.deptLabel = deptLabel;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<JLabel> getWeekdays() {
		return weekYYYYMMDD;
	}

	/**
	 * 
	 * @param weekYYYYMMDD
	 */
	public void setWeekdays(ArrayList<JLabel> weekYYYYMMDD) {
		this.weekYYYYMMDD = weekYYYYMMDD;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<JLabel> getDaysOfWeek() {
		return sunToSatWeek;
	}

	/**
	 * 
	 * @param sunToSatWeek
	 */
	public void setDaysOfWeek(ArrayList<JLabel> sunToSatWeek) {
		this.sunToSatWeek = sunToSatWeek;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<JLabel> getMonthdays() {
		return monthdays;
	}

	/**
	 * 
	 * @param monthdays
	 */
	public void setMonthdays(ArrayList<JLabel> monthdays) {
		this.monthdays = monthdays;
	}

	/**
	 * 
	 * @return
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * 
	 * @param container
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * 
	 * @return
	 */
	public LocalDate getNow() {
		return now;
	}

	/**
	 * 
	 * @param now
	 */
	public void setNow(LocalDate now) {
		this.now = now;
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> getNurseComboBox() {
		return nurseComboBox;
	}

	/**
	 * 
	 * @param nurseComboBox
	 */
	public void setNurseComboBox(JComboBox<String> nurseComboBox) {
		this.nurseComboBox = nurseComboBox;
	}

	/**
	 * 
	 * @return
	 */
	public JTextArea getPastTreatmentBox() {
		return pastTreatments;
	}

	/**
	 * 
	 * @param x
	 */
	public void setPastTreatmentBox(JTextArea x) {
		this.pastTreatments = x;
	}

	/**
	 * 
	 * @return
	 */
	public JTextArea getCurrentTreatmentBox() {
		return currentTreatment;
	}

	/**
	 * 
	 * @param x
	 */
	public void setCurrentTreatmentBox(JTextArea x) {
		this.currentTreatment = x;
	}

	/**
	 * 
	 * @return
	 */
	public JTextArea getPatientInformation() {
		return patientInformation;
	}

	/**
	 * 
	 * @param pi
	 */
	public void setPatientInformation(JTextArea pi) {
		this.patientInformation = pi;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getButtonTreatmentNotes() {
		return btnAddTreatmentNotes;
	}

	/**
	 * 
	 * @param xs
	 */
	public void setButtonTreatmentNotes(JButton xs) {
		btnAddTreatmentNotes = xs;
	}

	/**
	 * 
	 * @param patList
	 */
	public void setPatientListPanels(ArrayList<JPanel> patList) {
		this.patientListPanels = patList;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<JPanel> getPatientListPanels() {
		return patientListPanels;
	}

	/**
	 * 
	 * @param s
	 */
	public void setScheduleNameLabelWeek(JLabel s) {
		this.scheduleNameLabelWeek = s;
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getScheduleNameLabelWeek() {
		return scheduleNameLabelWeek;
	}

	/**
	 * 
	 * @param s
	 */
	public void setScheduleNameLabelMonth(JLabel s) {
		this.scheduleNameLabelMonth = s;
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getScheduleNameLabelMonth() {
		return scheduleNameLabelMonth;
	}

	/**
	 * 
	 * @return
	 */
	public JList getListPatients() {
		return listPatients;
	}

	/**
	 * 
	 * @param listPatients
	 */
	public void setListPatients(JList listPatients) {
		this.listPatients = listPatients;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnAddTreatmentNotes() {
		return btnAddTreatmentNotes;
	}

	/**
	 * 
	 * @param btnAddTreatmentNotes
	 */
	public void setBtnAddTreatmentNotes(JButton btnAddTreatmentNotes) {
		this.btnAddTreatmentNotes = btnAddTreatmentNotes;
	}

	/**
	 * 
	 * @return
	 */
	public JTextArea getCurrentTreatment() {
		return currentTreatment;
	}

	/**
	 * 
	 * @param currentTreatment
	 */
	public void setCurrentTreatment(JTextArea currentTreatment) {
		this.currentTreatment = currentTreatment;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getButtonOwn() {
		return btnOwn;
	}

	/**
	 * 
	 * @param o
	 */
	public void setButtonOwn(JButton o) {
		this.btnOwn = o;
	}

	/**
	 * 
	 * @return
	 */
	public LinkedHashMap<String, String> getAppointments() {
		return appointments;
	}

	/**
	 * 
	 * @param appointments
	 */
	public void setAppointments(LinkedHashMap<String, String> appointments) {
		this.appointments = appointments;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean[] getScheduledDays() {
		return schDays;
	}

	/**
	 * 
	 * @param b
	 */
	public void setScheduledDays(Boolean[] b) {
		this.schDays = b;
	}

	/**
	 * 
	 * @return
	 */
	public int[] getShifts() {
		return shiftTimes;
	}

	/**
	 * 
	 * @param xs
	 */
	public void setShifts(int[] xs) {
		this.shiftTimes = xs;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnToggle() {
		return btnToggle;
	}

	/**
	 * 
	 * @param bt
	 */
	public void setBtnToggle(JButton bt) {
		this.btnToggle = bt;
	}

	/**
	 * 
	 * @return
	 */
	public JList<String> getNursesList() {
		return nurses;
	}

	/**
	 * 
	 * @param n
	 */
	public void setNursesList(JList<String> n) {
		this.nurses = n;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnBookApt() {
		return btnBookApt;
	}

	/**
	 * 
	 * @param btn
	 */
	public void setBtnBookApt(JButton btn) {
		this.btnBookApt = btn;
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> getApptType() {
		return apptType;
	}

	/**
	 * 
	 * @param apty
	 */
	public void setApptType(JComboBox<String> apty) {
		this.apptType = apty;
	}
	

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> getChooseAppt() {
		return chooseAppt;
	}

	/**
	 * 
	 * @param ca
	 */
	public void setChooseAppt(JComboBox<String> ca) {
		this.chooseAppt = ca;
	}
	
	/**
	 * 
	 * @return
	 */
	public JComboBox<String> getLabTime() {
		return labTime;
	}

	/**
	 * 
	 * @param lt
	 */
	public void setLabTime(JComboBox<String> lt) {
		this.labTime = lt;
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> getYear() {
		return year;
	}

	/**
	 * 
	 * @param y
	 */
	public void setYear(JComboBox<String> y) {
		this.year = y;
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> getMonth() {
		return month;
	}

	/**
	 * 
	 * @param m
	 */
	public void setMonth(JComboBox<String> m) {
		this.month = m;
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String> getDay() {
		return day;
	}

	/**
	 * 
	 * @param d
	 */
	public void setDay(JComboBox<String> d) {
		this.day = d;
	}

	/**
	 * 
	 * @return
	 */
	public JComboBox<String>[] getAvailTimes() {
		return availTimes;
	}

	/**
	 * 
	 * @param at
	 */
	public void setAvailTimes(JComboBox<String>[] at) {
		this.availTimes = at;
	}

	/**
	 * 
	 * @return
	 */
	public JCheckBox getCheckBox() {
		return selfCheck;
	}

	/**
	 * 
	 * @param j
	 */
	public void setCheckBox(JCheckBox j) {
		this.selfCheck = j;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnAvailRequest() {
		return reqAvailChangeBtn;
	}

	/**
	 * 
	 * @param bar
	 */
	public void setBtnAvailRequest(JButton bar) {
		this.reqAvailChangeBtn = bar;
	}

	public JButton getBtnSelectFile() {
		return btnSelectFile;
	}

	public JLabel getFileNameJLabel() {
		return fileName;
	}

	public JTextField getDepartmentInput() {
		return departmentInput;
	}

	public JTextField getNameInput() {
		return nameInput;
	}

	public JButton getBtnUpdateFerral() {
		return btnUpdateReferral;
	}

}