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
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;





/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class DoctorView {
	
	private JFrame frame;
	
	private JPanel panel;
	private JPanel scheduleWeekly;
	private JPanel scheduleMonthly;
	private JPanel titleContainer;
	private JPanel backPanel;
	private JPanel patientPanel;
	//private JPanel dayPanel;
	
	private JButton btnSignOut;
	private JButton btnViewPatient;
	private JButton btnMakeChanges;
	
	private JLabel titleLabel;
	private JLabel nursesLabel;
	private JLabel displayMonth;
	private JLabel nameLabel;
	private JLabel deptLabel;
	
	
	private ArrayList<JLabel> weekdays = new ArrayList<JLabel>(7);
	private ArrayList<JLabel> daysOfWeek = new ArrayList<JLabel>(7);
	private ArrayList<JPanel> panelsOfWeekMonth = new ArrayList<JPanel>(7);
	private ArrayList<JLabel> monthdays = new ArrayList<JLabel>(35);
	private ArrayList<JPanel> patientListPanels = new ArrayList<JPanel>(0);
	
	JComboBox<String> nurseComboBox;
	
	Container container;
	
	private LocalDate now = LocalDate.now();

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

	
	
	
	
	
	
	
	
	
	
	
	
	//Constructor
	public DoctorView(String title)
	{
		//create frame container
		//sets frame containers attributes
		frame = new JFrame(title);
			//frame.setSize(400,200);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		initializeVariables();
		initializeGUI();
	}

	
	
	

	/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI() 
	{
		
		// Create title container(JLabel) and set it as top of page in the frame
		titleContainer = new JPanel();
			titleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
			titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
			frame.getContentPane().add(titleContainer, BorderLayout.NORTH);		//Add the JPanel to the frame
			
/*
	#################################################
	#	Back										#
	#	Department: Name, M.D.						#
	#												#
	#################################################
*/
		// Create a panel for the sign out button
		backPanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) backPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			titleContainer.add(backPanel);										//add the JPanel to the existing JPanel
		
		// Sign out button, returns to login screen when clicked
		btnSignOut = new JButton("Sign out");
		backPanel.add(btnSignOut);
		btnSignOut.addActionListener(e -> frame.setVisible(false) );
			
			
			

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
	#####################################
	#			Schedule				#
	#									#
	#									#
	#									#
	#									#
	#									#
	#-----------------------------------#
	#		buttons for schedule		#
	#									#
	#####################################
*/
	
		// Create a content pane centered at the middle of the page
		JPanel contentPane = new JPanel();
			frame.getContentPane().add(contentPane, BorderLayout.CENTER);
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		// Create a container for the schedule, for the schedule itself and for the
		// button panel associated with it
		JPanel scheduleContainer = new JPanel();
			scheduleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
			scheduleContainer.setAlignmentY(Component.TOP_ALIGNMENT);
			scheduleContainer.setLayout(new BoxLayout(scheduleContainer, BoxLayout.Y_AXIS));
			contentPane.add(scheduleContainer);
				
		initializePatients();
		contentPane.add(patientPanel);
			
		
/*
 	#################################
	# 			Schedule			#
	#_______________________________#
	# 	|	|	|	|	|	|	|	#
	# 	|	|	|	|	|	|	|	#
	# 	|	|	|	|	|	|	|	#
	# 	|	|	|	|	|	|	|	#
 	#-------------------------------#
*/			
			
			
		// Create a panel for the schedule itself
		TemporalField fieldISO = WeekFields.of(Locale.CANADA).dayOfWeek();
		for (int i = 1; i < 8; i++) {
			JLabel lblTemp = new JLabel(now.with(fieldISO, i).toString());
			lblTemp.setFont(new Font("Tahoma", Font.PLAIN, 14));
			weekdays.add(lblTemp);
		}
		initializeWeeklySchedule();
		scheduleContainer.add(scheduleWeekly);
		initializeMonthlySchedule();
		scheduleContainer.add(scheduleMonthly);
			
/*
	#-----------------------------------#
	#		buttons for schedule		#
	#									#
	#####################################
*/		
			
			
		// Create a panel for the buttons that manipulate the schedule
		JPanel btnPaneSchedule = new JPanel();
			btnPaneSchedule.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			scheduleContainer.add(btnPaneSchedule);
			
		
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
		JButton btnToggle = new JButton("Monthly view");
			btnPaneSchedule.add(btnToggle);
		btnToggle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: Set things up for showing proper dates when toggling between views

				if (btnToggle.getText().equals("Monthly view")){
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
				} else {
					past = now.minusMonths(1).withDayOfMonth(1);
					tempTime = past.withDayOfMonth(1).with(fieldISO, 1);
					setMonthDateLabels(past);
					now = past;
					if (tempTime.compareTo(LocalDate.now()) < 0) {
						btnBack.setEnabled(false);
						now = LocalDate.now();
					}
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
				}
			});
			
			
			
		JButton btnSaveChanges = new JButton("Save changes");
			btnSaveChanges.setVisible(false);
			btnPaneSchedule.add(btnSaveChanges);

			btnSaveChanges.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnSaveChanges.setVisible(false);
				}
			});
			
			
			
			
		JButton btnOwnSchedule = new JButton("View current schedule");
			btnOwnSchedule.setVisible(false);
			btnPaneSchedule.setMaximumSize(btnPaneSchedule.getPreferredSize());
			btnPaneSchedule.add(btnOwnSchedule);
			
			btnOwnSchedule.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnOwnSchedule.setVisible(false);
					btnSaveChanges.setVisible(false);
					patientPanel.setVisible(false);
				}
			});
			
/*
		|############
		| Things 	#
		| Doctor 	#
		| Can do 	#
		|		 	#
		|		 	#
		|		 	#
		|		 	#
		|		 	#
		|		 	#
		|		 	#
		|############
*/
		
		// Create panel for the right hand side components
		JPanel buttonContainer = new JPanel();
			buttonContainer.setAlignmentY(Component.TOP_ALIGNMENT);
			GridBagLayout gbl_buttonContainer = new GridBagLayout();
				gbl_buttonContainer.columnWidths = new int[]{0, 0};
				gbl_buttonContainer.rowHeights = new int[]{35, 0, 0, 35, 0, 0, 0};
				gbl_buttonContainer.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_buttonContainer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			buttonContainer.setLayout(gbl_buttonContainer);
			buttonContainer.setMaximumSize(new Dimension(200, 1000));
			buttonContainer.setBorder(new LineBorder(new Color(255, 200, 0)));
			frame.getContentPane().add(buttonContainer, BorderLayout.EAST);
			
			buttonContainer.setBorder(new LineBorder(new Color(255, 200, 0)));
			
			
			
			// Button that views all patients currenly scheduled under this doctor
			// Does nothing
			JButton btnPatients = new JButton("View patients");
				GridBagConstraints gbc_btnPatients = new GridBagConstraints();
					gbc_btnPatients.insets = new Insets(0, 5, 5, 5);
					gbc_btnPatients.gridx = 0;
					gbc_btnPatients.gridy = 0;
				buttonContainer.add(btnPatients, gbc_btnPatients);
				btnPatients.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						contentPane.setVisible(false);
						patientPanel.setVisible(true);
					}
				});
					
		
				
				
				
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
			JButton btnOwn = new JButton("View own schedule");
			btnOwn.setEnabled(false);
			btnOwn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					nurseComboBox.setSelectedIndex(-1);
					btnOwn.setEnabled(false);
					contentPane.setVisible(true);
					patientPanel.setVisible(false);
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
			
	
			frame.setVisible(true);
		
		
		//create UI elements and add to panel

	}

////////////////////////////////////////////////////
		
		
		
		
		
		
	/** Getter and Setter Methods */
		
		
		
		
		
		
	public void setVisibility(Boolean a)
	{
		frame.setVisible(a);
	}
		
		
		
		
	//show a dialog message if credentials do not validate
	public void loginError(String message) 
	{
				JFrame frame = new JFrame();
				frame.setSize(200,100);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showMessageDialog(frame, message);
	}

	
	
	public void initializeVariables() {
		JLabel lblDay = new JLabel("Sunday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		daysOfWeek.add(lblDay);
		JPanel dayPanel7 = new JPanel();
		dayPanel7.setLayout(new BoxLayout(dayPanel7, BoxLayout.Y_AXIS));
		lblDay = new JLabel("Sunday");
		dayPanel7.add(lblDay);
		panelsOfWeekMonth.add(dayPanel7);

		lblDay = new JLabel("Monday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		daysOfWeek.add(lblDay);
		JPanel dayPanel1 = new JPanel();
		dayPanel1.setLayout(new BoxLayout(dayPanel1, BoxLayout.Y_AXIS));
		lblDay = new JLabel("Monday");
		dayPanel1.add(lblDay);
		panelsOfWeekMonth.add(dayPanel1);

		lblDay = new JLabel("Tuesday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		daysOfWeek.add(lblDay);
		JPanel dayPanel2 = new JPanel();
		dayPanel2.setLayout(new BoxLayout(dayPanel2, BoxLayout.Y_AXIS));
		lblDay = new JLabel("Tuesday");
		dayPanel2.add(lblDay);
		panelsOfWeekMonth.add(dayPanel2);

		lblDay = new JLabel("Wednesday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		daysOfWeek.add(lblDay);
		JPanel dayPane3 = new JPanel();
		dayPane3.setLayout(new BoxLayout(dayPane3, BoxLayout.Y_AXIS));
		lblDay = new JLabel("Wednesday");
		dayPane3.add(lblDay);
		panelsOfWeekMonth.add(dayPane3);

		lblDay = new JLabel("Thursday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		daysOfWeek.add(lblDay);
		JPanel dayPanel4 = new JPanel();
		dayPanel4.setLayout(new BoxLayout(dayPanel4, BoxLayout.Y_AXIS));
		lblDay = new JLabel("Thursday");
		dayPanel4.add(lblDay);
		panelsOfWeekMonth.add(dayPanel4);

		lblDay = new JLabel("Friday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		daysOfWeek.add(lblDay);
		JPanel dayPanel5 = new JPanel();
		dayPanel5.setLayout(new BoxLayout(dayPanel5, BoxLayout.Y_AXIS));
		lblDay = new JLabel("Friday");
		dayPanel5.add(lblDay);
		panelsOfWeekMonth.add(dayPanel5);

		lblDay = new JLabel("Saturday");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 18));
		daysOfWeek.add(lblDay);
		JPanel dayPanel6 = new JPanel();
		dayPanel6.setLayout(new BoxLayout(dayPanel6, BoxLayout.Y_AXIS));
		lblDay = new JLabel("Saturday");
		dayPanel6.add(lblDay);
		panelsOfWeekMonth.add(dayPanel6);

		LocalDate firstMonth = now.withDayOfMonth(1).with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		for (int i = 0; i < 35; i++) {
			monthdays.add(new JLabel(firstMonth.plusDays(i).getDayOfMonth()+""));
			monthdays.get(i).setFont(new Font ("Tahoma", Font.BOLD, 14));
		}


	}
	
	

	public void initializeWeeklySchedule() {
		scheduleWeekly = new JPanel();
			scheduleWeekly.setBackground(Color.WHITE);
			scheduleWeekly.setBorder(new LineBorder(Color.RED));
			FlowLayout flowLayout = (FlowLayout) scheduleWeekly.getLayout();
			flowLayout.setAlignment(FlowLayout.CENTER);
		
		// Create a panel for the times
		JPanel timePanel = new JPanel();
			timePanel.setBackground(Color.WHITE);
			timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));
			scheduleWeekly.add(timePanel);
		
		JLabel dates = new JLabel(" ");
			timePanel.add(dates);

		dates = new JLabel(" ");
			timePanel.add(dates);

		for (int i = 8; i < 20; i++) 
		{
			dates = new JLabel(i+":00");
			dates.setFont(new Font("Tahoma", Font.PLAIN, 14));
			timePanel.add(dates);
			dates = new JLabel(" ");
			timePanel.add(dates);
		}
		
		// Create a panel for Monday
		for (int i = 0; i < 7; i++) 
		{
			JPanel dayPanel = new JPanel();
				dayPanel.setBackground(Color.WHITE);
				dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
				dayPanel.add(weekdays.get(i));
				dayPanel.add(daysOfWeek.get(i));
				scheduleWeekly.add(dayPanel);
				
				//listener for the panels
				dayPanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(final MouseEvent arg0) {
						
						if (dayPanel.getBackground().equals(Color.WHITE)) {
							dayPanel.setBackground(Color.LIGHT_GRAY);
						} else {
							dayPanel.setBackground(Color.WHITE);
						}
					}
				});

			for (int j = 8; j < 20; j++) 
			{
				dates = new JLabel("TIME SLOT");
				dates.setFont(new Font("Tahoma", Font.PLAIN, 14));
				dayPanel.add(dates);
				dates = new JLabel(" ");
				dayPanel.add(dates);
			}

		}

	}
	
	
	
	public void initializeMonthlySchedule() {
		scheduleMonthly = new JPanel();
			scheduleMonthly.setBackground(Color.WHITE);
			// scheduleMonthly.setBorder(new LineBorder(Color.RED)); // highlights borders for visual gauging
			scheduleMonthly.setLayout(new BoxLayout(scheduleMonthly, BoxLayout.Y_AXIS));
		
		JPanel monthPanel = new JPanel();
			monthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			monthPanel.setBackground(new Color(203, 217, 249)); 

		displayMonth = new JLabel(now.getMonth().toString()+" "+now.getYear());
			displayMonth.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel monthLabelPanel = new JPanel();
			monthLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			monthLabelPanel.add(displayMonth);
			monthLabelPanel.setMaximumSize(displayMonth.getPreferredSize()); // uncommenting this makes the label very small
			monthLabelPanel.setBackground(new Color(158, 182, 238));

		scheduleMonthly.add(monthLabelPanel);
		scheduleMonthly.add(monthPanel);

		for (int j = 0; j < 7; j++) {
			monthPanel.add(panelsOfWeekMonth.get(j));
		}


		for (int i = 0; i < 35; i++) {
			// Overall panel for one day
			JPanel day = new JPanel();
				day.setBackground(Color.WHITE);
				day.setForeground(Color.WHITE);
				day.setLayout(new BoxLayout(day, BoxLayout.Y_AXIS));
			JPanel monthDate = new JPanel();
				// monthDate.setAlignmentX(FlowLayout.RIGHT);
				monthDate.add(monthdays.get(i));
				day.add(monthDate);
			JPanel appointments = new JPanel();
				appointments.setLayout(new BoxLayout(appointments, BoxLayout.Y_AXIS));
			JScrollPane scrollAppoint = new JScrollPane(appointments);
				scrollAppoint.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollAppoint.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				day.add(scrollAppoint);
				monthDate.setBackground(new Color(59, 198, 198));
				scrollAppoint.setBackground(new Color(147, 234, 234));
				
			day.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(final MouseEvent arg0) {
					if (monthDate.getBackground().equals(new Color(59, 198, 198))){
						monthDate.setBackground(new Color(255, 157, 76));
						scrollAppoint.setBackground(new Color(255, 203, 160));
					} else {
						monthDate.setBackground(new Color(59, 198, 198));
						scrollAppoint.setBackground(new Color(147, 234, 234));
					}
				}
			});


			// LocalDate meh = startDate.plusDays(i);
			// System.out.println(meh);
			switch (i%7) {
				case 0:
					panelsOfWeekMonth.get(0).add(day);
					break;
				case 1:
					panelsOfWeekMonth.get(1).add(day);
					break;
				case 2:
					panelsOfWeekMonth.get(2).add(day);
					break;
				case 3:
					panelsOfWeekMonth.get(3).add(day);

					break;
				case 4:
					panelsOfWeekMonth.get(4).add(day);

					break;
				case 5:
					panelsOfWeekMonth.get(5).add(day);

					break;
				case 6:
					panelsOfWeekMonth.get(6).add(day);

					break;
			}
		}

		scheduleMonthly.setVisible(false);

	}

	
	public void initializePatients() {
		patientPanel = new JPanel();
		patientPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel listPatientsPanel = new JPanel();
		listPatientsPanel.setLayout(new BoxLayout(listPatientsPanel, BoxLayout.Y_AXIS));
		// For each patient scheduled, add a panel to listPatientsPanel
		for (JPanel patient : getPatientListPanels()) {
			listPatientsPanel.add(patient);
		}
		listPatientsPanel.setMaximumSize(listPatientsPanel.getPreferredSize());
		JPanel specificPatientPanel = new JPanel();
		specificPatientPanel.setLayout(new BoxLayout(specificPatientPanel, BoxLayout.Y_AXIS));



		patientPanel.add(listPatientsPanel);
		patientPanel.add(specificPatientPanel);

		patientPanel.setVisible(false);
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
		return weekdays;
	}






	public void setWeekdays(ArrayList<JLabel> weekdays) {
		this.weekdays = weekdays;
	}






	public ArrayList<JLabel> getDaysOfWeek() {
		return daysOfWeek;
	}






	public void setDaysOfWeek(ArrayList<JLabel> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}






	public ArrayList<JPanel> getPanelsOfWeekMonth() {
		return panelsOfWeekMonth;
	}






	public void setPanelsOfWeekMonth(ArrayList<JPanel> panelsOfWeekMonth) {
		this.panelsOfWeekMonth = panelsOfWeekMonth;
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


	public void setPatientListPanels(ArrayList<JPanel> patList) {
		this.patientListPanels = patList;
	}


	public ArrayList<JPanel> getPatientListPanels() {
		return patientListPanels;
	}



	public void setPatientListPanels(String[] patList) {
		for (String p : patList) {
			JPanel aPat = new JPanel();
			aPat.add(new JLabel(p));
			patientListPanels.add(aPat);
		}
	}


	public void setMonthDateLabels(LocalDate ld) {
		LocalDate tempTime = ld.withDayOfMonth(1).with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		for (JLabel lbl : monthdays) {
			lbl.setText(tempTime.plusDays(monthdays.indexOf(lbl)).getDayOfMonth()+"");
		}
		displayMonth.setText(ld.getMonth().toString()+" "+ld.getYear());
	}

	public void setWeekDateLabels(LocalDate ld) {
		LocalDate tempTime = ld.with(WeekFields.of(Locale.CANADA).dayOfWeek(), 1);
		for (JLabel lbl : weekdays) {
			lbl.setText(tempTime.plusDays(weekdays.indexOf(lbl)).toString());
		}
	}

	

	

}
