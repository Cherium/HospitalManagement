import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

public class DoctorFrame extends JFrame {

	Container frame;
	// private Container titlePane, informationPane;
	// private JPanel contentPane;
	// private JPanel backPane, namePane, scheduleDisplayPane, scheduleMovePane, rightButtonPane;
	// private JButton btnReturn;
	// private JLabel lblDepartment, lblName;
	// private JLabel lblNurses;
	// private JComboBox<String> comboBox;
	// private JLabel lblSchedule;
	// private JButton btnOwnSchedule;
	// private JLabel lblSchedulePlaceholder;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// JFrame mframe = new JFrame();
	// mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// mframe.setSize(400,400);
	// DoctorFrame frame = new DoctorFrame(new Database(), new LoginFrame(new
	// Database()), "doctor");
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	// TODO: Divide interface into 3 containers

	// TODO: Set up title container, button container, and schedule container

	/**
	 * Create the frame.
	 * DoctorFrame(Database dbase, StartScreen main, Doctor doc)
	 */
	public DoctorFrame(Database dbase, StartScreen main, User user) {
		// // Test multiple nurses
		// Nurse newNurse = new Nurse("Mewtwo", "mew", "mewo".toCharArray(), user.getDepartment(), user);
		// user.addNurse(newNurse);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setSize(800, 700);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		// contentPane = new JPanel();
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
		// GridBagLayout gbl_contentPane = new GridBagLayout();
		// gbl_contentPane.columnWidths = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		// gbl_contentPane.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 0 };
		// gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		// 		0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		// gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		// contentPane.setLayout(gbl_contentPane);

		// // Simple log out button that goes back to the menu screen 
		// btnReturn = new JButton("Sign out");
		// btnReturn.addMouseListener(new MouseAdapter() {
		// 	@Override
		// 	public void mouseClicked(MouseEvent e) {
		// 		main.setVisible(true);
		// 		main.clearInputs();
		// 		setVisible(false);
		// 	}
		// });
		// GridBagConstraints gbc_btnReturn = new GridBagConstraints();
		// gbc_btnReturn.insets = new Insets(5, 5, 5, 5);
		// gbc_btnReturn.gridx = 0;
		// gbc_btnReturn.gridy = 0;
		// backPane.add(btnReturn);
		// contentPane.add(btnReturn, gbc_btnReturn);

		// // Display name of doctor
		// JLabel lblName = new JLabel("Hello "+user.getName());
		// lblName.setHorizontalAlignment(SwingConstants.LEFT);
		// lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		// GridBagConstraints gbc_lblName = new GridBagConstraints();
		// gbc_lblName.insets = new Insets(5, 5, 5, 5);
		// gbc_lblName.gridx = 1;
		// gbc_lblName.gridy = 1;
		// contentPane.add(lblName, gbc_lblName);

		// // Display department of doctor
		// lblDepartment = new JLabel(user.getDepartment());
		// lblDepartment.setFont(new Font("Sitka Small", Font.ITALIC, 24));
		// GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		// gbc_lblDepartment.insets = new Insets(0, 0, 5, 0);
		// gbc_lblDepartment.gridx = 20;
		// gbc_lblDepartment.gridy = 1;
		// contentPane.add(lblDepartment, gbc_lblDepartment);

		// ////Nurse Display
		// ////TODO Create an array display list for Nurses to be displayed for the day
		// ////Adjust positioning to make this better
		// lblNurses = new JLabel("The nurses you are working with are: ");
		// lblNurses.setHorizontalAlignment(SwingConstants.LEFT);
		// GridBagConstraints gbc_lblNurses = new GridBagConstraints();
		// gbc_lblNurses.anchor = GridBagConstraints.EAST;
		// gbc_lblNurses.insets = new Insets(5, 5, 5, 5);
		// gbc_lblNurses.gridx = 1;
		// gbc_lblNurses.gridy = 3;
		// contentPane.add(lblNurses, gbc_lblNurses);

		// /*
		// ////Added for testing but aslo for greeting
		// JLabel lblMsg = new JLabel("Good morning");
		// GridBagConstraints gbclblMsg = new GridBagConstraints();
		// gbclblMsg.gridx = 1;
		// gbclblMsg.gridy = 0;
		// contentPane.add(lblMsg, gbclblMsg);
		// //*/

		// // Display nurses assigned to doctor and their schedules
		// comboBox = new JComboBox<String>();
		// comboBox.setBackground(Color.WHITE);
		// comboBox.setForeground(new Color(0, 0, 0));
		// for (Nurse n : user.getNurses()){
		// 	comboBox.addItem(n.getName());
		// }
		// comboBox.setSelectedIndex(-1); // Set initial selection to be empty
		// comboBox.addActionListener(new ActionListener() {
		// 	@Override
		// 	public void actionPerformed(ActionEvent e) {
		// 		// TODO: Use index of combo box to get the nurse object (from doctor) and to display the schedule.
		// 		lblSchedulePlaceholder.setText(comboBox.getSelectedItem() + "'s Schedule");
		// 		btnOwnSchedule.setVisible(true);
		// 	}
		// });
		// GridBagConstraints gbc_comboBox = new GridBagConstraints();
		// gbc_comboBox.insets = new Insets(5, 5, 5, 5);
		// gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		// gbc_comboBox.gridx = 2;
		// gbc_comboBox.gridy = 3;
		// contentPane.add(comboBox, gbc_comboBox);
		
		// // Display schedule
		// lblSchedule = new JLabel("Schedule");
		// lblSchedule.setHorizontalAlignment(SwingConstants.TRAILING);
		// GridBagConstraints gbc_lblSchedule = new GridBagConstraints();
		// gbc_lblSchedule.insets = new Insets(5, 5, 5, 5);
		// gbc_lblSchedule.gridx = 1;
		// gbc_lblSchedule.gridy = 5;
		// contentPane.add(lblSchedule, gbc_lblSchedule);
		
		// // Display doctor's schedule
		// btnOwnSchedule = new JButton("See my schedule");
		// btnOwnSchedule.addMouseListener(new MouseAdapter() {
		// 	@Override
		// 	public void mouseClicked(MouseEvent arg0) {
		// 		lblSchedulePlaceholder.setText("Doctor's Schedule");
		// 		btnOwnSchedule.setVisible(false);
		// 	}
		// });		
		// btnOwnSchedule.setVisible(false);
		// GridBagConstraints gbc_btnOwnSchedule = new GridBagConstraints();
		// gbc_btnOwnSchedule.insets = new Insets(5, 5, 5, 5);
		// gbc_btnOwnSchedule.gridx = 2;
		// gbc_btnOwnSchedule.gridy = 5;
		// contentPane.add(btnOwnSchedule, gbc_btnOwnSchedule);
		
		// // Display schedule (either of doctor or a selected nurse)
		// lblSchedulePlaceholder = new JLabel("Doctor's Schedule");
		// GridBagConstraints gbc_lblSchedulePlaceholder = new GridBagConstraints();
		// gbc_lblSchedulePlaceholder.insets = new Insets(0, 0, 0, 5);
		// gbc_lblSchedulePlaceholder.gridx = 1;
		// gbc_lblSchedulePlaceholder.gridy = 6;
		// contentPane.add(lblSchedulePlaceholder, gbc_lblSchedulePlaceholder);

	
	}

	/**
	 * @wbp.parser.constructor
	 */
	public DoctorFrame(Database dbase, StartScreen main, Doctor doc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel titleContainer = new JPanel();
		titleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(titleContainer, BorderLayout.NORTH);
		titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
		
		JPanel backPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) backPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		titleContainer.add(backPanel);
		
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
		
		JPanel namePane = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) namePane.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		titleContainer.add(namePane);
		
		JLabel lblDepartment = new JLabel(doc.getDepartment()+": ");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 34));
		namePane.add(lblDepartment);
		
		JLabel lblName = new JLabel(doc.getName());
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		namePane.add(lblName);
		
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.WEST);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel scheduleContainer = new JPanel();
		scheduleContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		scheduleContainer.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(scheduleContainer);
		scheduleContainer.setLayout(new BoxLayout(scheduleContainer, BoxLayout.Y_AXIS));
		
		JPanel schedule = new JPanel();
		schedule.setBackground(Color.WHITE);
		schedule.setBorder(new LineBorder(Color.RED));
		scheduleContainer.add(schedule);
		GridBagLayout gbl_schedule = new GridBagLayout();
		gbl_schedule.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_schedule.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_schedule.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_schedule.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		schedule.setLayout(gbl_schedule);
		
		JLabel lblNewLabel_1 = new JLabel("Monday");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		schedule.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_8 = new JLabel("Tuesday");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 0;
		schedule.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JLabel lblNewLabel_7 = new JLabel("Wednesday");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_7.gridx = 3;
		gbc_lblNewLabel_7.gridy = 0;
		schedule.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Thursday");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_6.gridx = 4;
		gbc_lblNewLabel_6.gridy = 0;
		schedule.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("Friday");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 0;
		schedule.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Saturday");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_4.gridx = 6;
		gbc_lblNewLabel_4.gridy = 0;
		schedule.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Sunday");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_3.gridx = 7;
		gbc_lblNewLabel_3.gridy = 0;
		schedule.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		// Add dates to the days of the week
		LocalDate now = LocalDate.now();
		TemporalField fieldISO = WeekFields.of(Locale.CANADA).dayOfWeek();
		for (int i = 1; i < 8; i++) {
			JLabel lblTemp = new JLabel(now.with(fieldISO, i).toString());
			GridBagConstraints gbc_lblTemp = new GridBagConstraints();
			gbc_lblTemp.insets = new Insets(5, 5, 5, 5);
			gbc_lblTemp.gridx = i;
			gbc_lblTemp.gridy = 1;
			schedule.add(lblTemp, gbc_lblTemp);
			System.out.println(now.with(fieldISO, i));
		}


		JLabel lblNewLabel = new JLabel("8:00");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		schedule.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_9 = new JLabel("9:00");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 4;
		schedule.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("10:00");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 6;
		schedule.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("12:00");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 8;
		schedule.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("13:00");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_12.gridx = 0;
		gbc_lblNewLabel_12.gridy = 10;
		schedule.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("14:00");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_13.gridx = 0;
		gbc_lblNewLabel_13.gridy = 12;
		schedule.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("15:00");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_14.gridx = 0;
		gbc_lblNewLabel_14.gridy = 14;
		schedule.add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("16:00");
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_15.gridx = 0;
		gbc_lblNewLabel_15.gridy = 16;
		schedule.add(lblNewLabel_15, gbc_lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("17:00");
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_16.gridx = 0;
		gbc_lblNewLabel_16.gridy = 18;
		schedule.add(lblNewLabel_16, gbc_lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("18:00");
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_17.gridx = 0;
		gbc_lblNewLabel_17.gridy = 20;
		schedule.add(lblNewLabel_17, gbc_lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("19:00");
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
		gbc_lblNewLabel_18.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_18.gridx = 0;
		gbc_lblNewLabel_18.gridy = 22;
		schedule.add(lblNewLabel_18, gbc_lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("20:00");
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
		gbc_lblNewLabel_19.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_19.gridx = 0;
		gbc_lblNewLabel_19.gridy = 24;
		schedule.add(lblNewLabel_19, gbc_lblNewLabel_19);
		
		JPanel btnPaneSchedule = new JPanel();
		scheduleContainer.add(btnPaneSchedule);
		btnPaneSchedule.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBack = new JButton("<");
		btnPaneSchedule.add(btnBack);
		
		JButton btnDaySelecter = new JButton("Select day");
		btnPaneSchedule.add(btnDaySelecter);
		
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
		
		JButton btnForward = new JButton(">");
		btnPaneSchedule.add(btnForward);
		
		
		JPanel buttonContainer = new JPanel();
		buttonContainer.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(buttonContainer);
		GridBagLayout gbl_buttonContainer = new GridBagLayout();
		gbl_buttonContainer.columnWidths = new int[]{0, 0};
		gbl_buttonContainer.rowHeights = new int[]{35, 0, 0, 35, 0};
		gbl_buttonContainer.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonContainer.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonContainer.setLayout(gbl_buttonContainer);
		buttonContainer.setMaximumSize(new Dimension(200, 1000));

		buttonContainer.setBorder(new LineBorder(new Color(255, 200, 0)));

		JButton btnPatients = new JButton("View patients");
		GridBagConstraints gbc_btnPatients = new GridBagConstraints();
		gbc_btnPatients.insets = new Insets(0, 0, 5, 0);
		gbc_btnPatients.gridx = 0;
		gbc_btnPatients.gridy = 0;
		buttonContainer.add(btnPatients, gbc_btnPatients);
		
		JComboBox<String> nurseComboBox = new JComboBox<String>();
		for (Nurse n : doc.getNurses()) {
			nurseComboBox.addItem(n.getName());
		}
		
		JLabel lblNurses = new JLabel("Assigned nurses:");
		lblNurses.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNurses = new GridBagConstraints();
		gbc_lblNurses.insets = new Insets(0, 0, 5, 0);
		gbc_lblNurses.gridx = 0;
		gbc_lblNurses.gridy = 1;
		buttonContainer.add(lblNurses, gbc_lblNurses);
		nurseComboBox.setBackground(Color.WHITE);
		nurseComboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_nurseComboBox = new GridBagConstraints();
		gbc_nurseComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_nurseComboBox.gridx = 0;
		gbc_nurseComboBox.gridy = 2;
		buttonContainer.add(nurseComboBox, gbc_nurseComboBox);
		
		JButton btnOwn = new JButton("View own schedule");
		btnOwn.setVisible(false);
		btnOwn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nurseComboBox.setSelectedIndex(-1);
				btnOwn.setVisible(false);
			}
		});
		
		nurseComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnOwn.setVisible(true);
			}
		});
		
		GridBagConstraints gbc_btnOwn = new GridBagConstraints();
		gbc_btnOwn.gridx = 0;
		gbc_btnOwn.gridy = 3;
		buttonContainer.add(btnOwn, gbc_btnOwn);
	}


}
