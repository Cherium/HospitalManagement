import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

public class DoctorFrame extends JFrame {

	Container frame;
	ArrayList<JLabel> weekdays = new ArrayList<JLabel>(7);
	LocalDate now = LocalDate.now();

	/*
		default frame (getContentFrame()): BorderLayout
		#################################################################
		# Sign out						 								# title frame: BoxLayout.Y-Axis
		# Department: Name												# BorderLayout.North
		#---------------------------------------------------------------#
		# 				Schedule						|	Patients	# content frame: BoxLayout.X-Axis
		# 												|	 Nurses		# BorderLayout.CENTER
		#												| Own schedule	#
		#												|				#
		#												|				#
		#												|				#
		#												|				#
		#-----------------------------------------------|				#
		#			manipulate schedule					|				#
		#################################################################
	*/

	/**
	 * Constructor
	 */
	public DoctorFrame(Database dbase, StartScreen main, Doctor doc) {
		// Test multiple nurses
		Nurse newNurse = new Nurse("Mewtwo", "mew", "mewo".toCharArray(), doc.getDepartment(), doc);
		doc.addNurse(newNurse);

		// Set main frame to full frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		// Create title frame and set it as top of page
		JPanel titleContainer = new JPanel();
		titleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(titleContainer, BorderLayout.NORTH);
		titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
		
		// Create separate panels for the two lines of information
		// Create a panel for the sign out button
		JPanel backPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) backPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		titleContainer.add(backPanel);
		
		// Sign out button, returns to login screen when clicked
		JButton button = new JButton("Sign out");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setVisible(true);
				main.clearInputs();
				setVisible(false);
			}
		});
		backPanel.add(button);
		
		// Name panel, displays department and name of the doctor
		JPanel namePane = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) namePane.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		titleContainer.add(namePane);
		
		JLabel lblDepartment = new JLabel(doc.getDepartment()+": ");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 34));
		namePane.add(lblDepartment);
		
		JLabel lblName = new JLabel(doc.getName() + ", M.D.");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		namePane.add(lblName);
		
		// Create a content pane centered at the middle of the page
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		// Create a container for the schedule, for the schedule itself and for the 
		// button panel associated with it
		JPanel scheduleContainer = new JPanel();
		scheduleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		scheduleContainer.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(scheduleContainer);
		scheduleContainer.setLayout(new BoxLayout(scheduleContainer, BoxLayout.Y_AXIS));
		
		// Create a panel for the schedule itself
		// TODO: Move this to an external function, to implement a switch between monthly and weekly view
		JPanel schedule = new JPanel();
		schedule.setBackground(Color.WHITE);
		schedule.setBorder(new LineBorder(Color.RED));
		scheduleContainer.add(schedule);
		GridBagLayout gbl_schedule = new GridBagLayout();
		// Do not remove these column restraints!
		// To add a new column (and have the whole schedule not be centered on the X and Y axis)
		// - add 0, 0 (2 zeros! and a comma) to columnWidths
		// - add 0.0, 0.0 (2 zero floats! and a comma) to columnWeights
		// * the last item of _Weights is Double.MIN_VALUE
		// - a new column would be adding to the elements in days of week row
		// For adding a new row, do the above but for different fields
		// - a new row would be adding new row to the times row
		gbl_schedule.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_schedule.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_schedule.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_schedule.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		schedule.setLayout(gbl_schedule);
		
		// Creating labels for the days of the week 
		JLabel lblDayWeek = new JLabel("Monday");
		lblDayWeek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gdb_lbl = new GridBagConstraints();
		gdb_lbl.insets = new Insets(5, 5, 5, 5);
		gdb_lbl.gridx = 2;
		gdb_lbl.gridy = 1;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Tuesday");
		lblDayWeek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gdb_lbl.gridx = 3;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Wednesday");
		lblDayWeek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gdb_lbl.gridx = 4;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Thursday");
		lblDayWeek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gdb_lbl.gridx = 5;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Friday");
		lblDayWeek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gdb_lbl.gridx = 6;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Saturday");
		lblDayWeek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gdb_lbl.gridx = 7;
		schedule.add(lblDayWeek, gdb_lbl);

		
		lblDayWeek = new JLabel("Sunday");
		lblDayWeek.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gdb_lbl.gridx = 8;
		schedule.add(lblDayWeek, gdb_lbl);

				
		// Add dates to the days of the week
		// LocalDate now = LocalDate.now();
		TemporalField fieldISO = WeekFields.of(Locale.CANADA).dayOfWeek();
		for (int i = 1; i < 8; i++) {
			JLabel lblTemp = new JLabel(now.with(fieldISO, i).toString());
			lblTemp.setFont(new Font("Tahoma", Font.PLAIN, 14));
			gdb_lbl.insets = new Insets(15, 15, 15, 15);
			gdb_lbl.gridx = i+1;
			gdb_lbl.gridy = 0;
			schedule.add(lblTemp, gdb_lbl);
			weekdays.add(lblTemp);
		}

		// Creating labels for times
		gdb_lbl.gridx = 1;
		JLabel lblTime = new JLabel("");
		for (int i = 0; i < 16; i++) {
			lblTime = new JLabel(i+7+":00");
			lblTime.setFont(new Font("Tahoma", Font.PLAIN, 10));
			gdb_lbl.insets = new Insets(5, 5, 5, 5);
			gdb_lbl.gridy = 2 + 2*i;
			schedule.add(lblTime, gdb_lbl);
		}

		// Invisible labels for making schedule larger and centering schedule
		gdb_lbl.gridx = 9;
		gdb_lbl.gridy = 0;
		gdb_lbl.insets = new Insets(5, 5, 5, 170);
		lblDayWeek = new JLabel("");
		schedule.add(lblDayWeek, gdb_lbl);

		lblTime = new JLabel("");
		gdb_lbl.gridx = 0;
		gdb_lbl.gridy = 0;
		gdb_lbl.insets = new Insets(5, 170, 5, 5);
		schedule.add(lblTime, gdb_lbl);

		// Create a panel for the buttons that manipulate the schedule
		JPanel btnPaneSchedule = new JPanel();
		scheduleContainer.add(btnPaneSchedule);
		btnPaneSchedule.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		// Create a button that goes back one week/one month
		// Currently only one week
		// ActionListeners handle disabled buttons, mouse click does not
		JButton btnBack = new JButton("<");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDate past = now.minusWeeks(1); // Get previous week
				// Change the labels to the days of the previous week
				for (JLabel jLabel : weekdays) {
					jLabel.setText(past.with(fieldISO, weekdays.indexOf(jLabel)+1).toString());
				}
				// Disable button if this is the current week
				if (past.equals(LocalDate.now())) {
					btnBack.setEnabled(false);
				}
				// Set date object to changed date
				now = past;
			}
		});
		// Button starts out disabled as schedule starts out on current week
		btnBack.setEnabled(false);
		btnPaneSchedule.add(btnBack);

		// Button starts out disabled as schedule starts out on current week		
		btnBack.setEnabled(false); 
		btnPaneSchedule.add(btnBack);
		
		// Button that selects a day from a day picker
		// Not implemented. May remove function altogether
		JButton btnDaySelecter = new JButton("Select day");
		btnPaneSchedule.add(btnDaySelecter);

		
		// Button that changes schedule between weekly and monthly views
		// Currently only weekly view is setup
		JButton btnToggle = new JButton("Monthly view");
		btnToggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnToggle.getText().equals("Monthly view")){
					btnToggle.setText("Weekly view");
				} else {
					btnToggle.setText("Monthly view");
				}
			}
		});
		btnPaneSchedule.add(btnToggle);

		
		// Button that goes foward 1 week/1 month in time
		// Current only set to go foward 1 week
		JButton btnForward = new JButton(">");
		btnForward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocalDate future = now.plusWeeks(1);
				for (JLabel jLabel : weekdays) {
					jLabel.setText(future.with(fieldISO, weekdays.indexOf(jLabel)+1).toString());
				}
				if (!btnBack.isEnabled())
					btnBack.setEnabled(true);
				now = future;
			}
		});
		btnPaneSchedule.add(btnForward);

		JButton btnSaveChanges = new JButton("Save changes");
		btnSaveChanges.setVisible(false);
		btnSaveChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSaveChanges.setVisible(false);
			}
		});
		btnPaneSchedule.add(btnSaveChanges);

		JButton btnOwnSchedule = new JButton("View current schedule");
		btnOwnSchedule.setVisible(false);
		btnOwnSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnOwnSchedule.setVisible(false);
				btnSaveChanges.setVisible(false);
			}
		});
		btnPaneSchedule.add(btnOwnSchedule);


		
		// Create panel for the right hand side components
		JPanel buttonContainer = new JPanel();
		buttonContainer.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(buttonContainer);
		GridBagLayout gbl_buttonContainer = new GridBagLayout();
		gbl_buttonContainer.columnWidths = new int[]{0, 0};
		gbl_buttonContainer.rowHeights = new int[]{35, 0, 0, 35, 0, 0, 0};
		gbl_buttonContainer.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonContainer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonContainer.setLayout(gbl_buttonContainer);
		buttonContainer.setMaximumSize(new Dimension(200, 1000));

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
		JComboBox<String> nurseComboBox = new JComboBox<String>();
		for (Nurse n : doc.getNurses()) {
			nurseComboBox.addItem(n.getName());
		}
		
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
		JButton btnOwn = new JButton("View own schedule");
		btnOwn.setEnabled(false);
		btnOwn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nurseComboBox.setSelectedIndex(-1);
				btnOwn.setEnabled(false);
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


	}


}
