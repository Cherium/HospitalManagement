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
		// gbc_btnReturn.insets = new Insets(0, 0, 5, 5);
		// gbc_btnReturn.gridx = 0;
		// gbc_btnReturn.gridy = 0;
		// backPane.add(btnReturn);
		// contentPane.add(btnReturn, gbc_btnReturn);

		// // Display name of doctor
		// JLabel lblName = new JLabel("Hello "+user.getName());
		// lblName.setHorizontalAlignment(SwingConstants.LEFT);
		// lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		// GridBagConstraints gbc_lblName = new GridBagConstraints();
		// gbc_lblName.insets = new Insets(0, 0, 5, 5);
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
		// gbc_lblNurses.insets = new Insets(0, 0, 5, 5);
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
		// gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		// gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		// gbc_comboBox.gridx = 2;
		// gbc_comboBox.gridy = 3;
		// contentPane.add(comboBox, gbc_comboBox);
		
		// // Display schedule
		// lblSchedule = new JLabel("Schedule");
		// lblSchedule.setHorizontalAlignment(SwingConstants.TRAILING);
		// GridBagConstraints gbc_lblSchedule = new GridBagConstraints();
		// gbc_lblSchedule.insets = new Insets(0, 0, 5, 5);
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
		// gbc_btnOwnSchedule.insets = new Insets(0, 0, 5, 5);
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
		setSize(800, 1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel titleContainer = new JPanel();
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
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel scheduleContainer = new JPanel();
		contentPane.add(scheduleContainer);
		scheduleContainer.setLayout(new BoxLayout(scheduleContainer, BoxLayout.Y_AXIS));
		
		JPanel scheduleGrid = new JPanel();
		scheduleGrid.setAlignmentY(Component.TOP_ALIGNMENT);
		scheduleGrid.setBackground(Color.WHITE);
		scheduleContainer.add(scheduleGrid);
		GridBagLayout gbl_scheduleGrid = new GridBagLayout();
		gbl_scheduleGrid.columnWidths = new int[]{0, 50, 50, 50, 50, 50, 50, 50, 0};
		gbl_scheduleGrid.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_scheduleGrid.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_scheduleGrid.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		scheduleGrid.setLayout(gbl_scheduleGrid);
		
		JLabel lbl1 = new JLabel("Monday");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lbl1 = new GridBagConstraints();
		gbc_lbl1.insets = new Insets(0, 0, 5, 5);
		gbc_lbl1.gridx = 1;
		gbc_lbl1.gridy = 0;
		scheduleGrid.add(lbl1, gbc_lbl1);
		
		JLabel lbl2 = new JLabel("Tuesday");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lbl2 = new GridBagConstraints();
		gbc_lbl2.insets = new Insets(0, 0, 5, 5);
		gbc_lbl2.gridx = 2;
		gbc_lbl2.gridy = 0;
		scheduleGrid.add(lbl2, gbc_lbl2);
		
		JLabel lbl3 = new JLabel("Wednesday");
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lbl3 = new GridBagConstraints();
		gbc_lbl3.insets = new Insets(0, 0, 5, 5);
		gbc_lbl3.gridx = 3;
		gbc_lbl3.gridy = 0;
		scheduleGrid.add(lbl3, gbc_lbl3);
		
		JLabel lbl4 = new JLabel("Thursday");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lbl4 = new GridBagConstraints();
		gbc_lbl4.insets = new Insets(0, 0, 5, 5);
		gbc_lbl4.gridx = 4;
		gbc_lbl4.gridy = 0;
		scheduleGrid.add(lbl4, gbc_lbl4);
		
		JLabel lbl5 = new JLabel("Friday");
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lbl5 = new GridBagConstraints();
		gbc_lbl5.insets = new Insets(0, 0, 5, 5);
		gbc_lbl5.gridx = 5;
		gbc_lbl5.gridy = 0;
		scheduleGrid.add(lbl5, gbc_lbl5);
		
		JLabel lbl6 = new JLabel("Saturday");
		lbl6.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lbl6 = new GridBagConstraints();
		gbc_lbl6.insets = new Insets(0, 0, 5, 5);
		gbc_lbl6.gridx = 6;
		gbc_lbl6.gridy = 0;
		scheduleGrid.add(lbl6, gbc_lbl6);
		
		JLabel lbl7 = new JLabel("Sunday");
		lbl7.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lbl7 = new GridBagConstraints();
		gbc_lbl7.insets = new Insets(0, 0, 5, 0);
		gbc_lbl7.gridx = 7;
		gbc_lbl7.gridy = 0;
		scheduleGrid.add(lbl7, gbc_lbl7);
		
		JLabel lbTime1 = new JLabel("7:00");
		GridBagConstraints gbc_lblTime1 = new GridBagConstraints();
		gbc_lblTime1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime1.gridx = 0;
		gbc_lblTime1.gridy = 1;
		scheduleGrid.add(lbTime1, gbc_lblTime1);
		
		JLabel lbTime2 = new JLabel("8:00");
		GridBagConstraints gbc_lblTime2 = new GridBagConstraints();
		gbc_lblTime2.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime2.gridx = 0;
		gbc_lblTime2.gridy = 3;
		scheduleGrid.add(lbTime2, gbc_lblTime2);
		
		JLabel lbTime3 = new JLabel("9:00");
		GridBagConstraints gbc_lblTime3 = new GridBagConstraints();
		gbc_lblTime3.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime3.gridx = 0;
		gbc_lblTime3.gridy = 5;
		scheduleGrid.add(lbTime3, gbc_lblTime3);
		
		JLabel lbTime4 = new JLabel("10:00");
		GridBagConstraints gbc_lblTime4 = new GridBagConstraints();
		gbc_lblTime4.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime4.gridx = 0;
		gbc_lblTime4.gridy = 7;
		scheduleGrid.add(lbTime4, gbc_lblTime4);
		
		JLabel lbTime5 = new JLabel("11:00");
		GridBagConstraints gbc_lblTime5 = new GridBagConstraints();
		gbc_lblTime5.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime5.gridx = 0;
		gbc_lblTime5.gridy = 9;
		scheduleGrid.add(lbTime5, gbc_lblTime5);
		
		JLabel lbTime6 = new JLabel("12:00");
		GridBagConstraints gbc_lblTime6 = new GridBagConstraints();
		gbc_lblTime6.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime6.gridx = 0;
		gbc_lblTime6.gridy = 11;
		scheduleGrid.add(lbTime6, gbc_lblTime6);
		
		JLabel lbTime7 = new JLabel("13:00");
		GridBagConstraints gbc_lblTime7 = new GridBagConstraints();
		gbc_lblTime7.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime7.gridx = 0;
		gbc_lblTime7.gridy = 13;
		scheduleGrid.add(lbTime7, gbc_lblTime7);
		
		JLabel lbTime8 = new JLabel("14:00");
		GridBagConstraints gbc_lblTime8 = new GridBagConstraints();
		gbc_lblTime8.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime8.gridx = 0;
		gbc_lblTime8.gridy = 15;
		scheduleGrid.add(lbTime8, gbc_lblTime8);
		
		JLabel lbTime9 = new JLabel("15:00");
		GridBagConstraints gbc_lblTime9 = new GridBagConstraints();
		gbc_lblTime9.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime9.gridx = 0;
		gbc_lblTime9.gridy = 17;
		scheduleGrid.add(lbTime9, gbc_lblTime9);
		
		JLabel lbTime10 = new JLabel("16:00");
		GridBagConstraints gbc_lblTime10 = new GridBagConstraints();
		gbc_lblTime10.insets = new Insets(0, 0, 0, 5);
		gbc_lblTime10.gridx = 0;
		gbc_lblTime10.gridy = 19;
		scheduleGrid.add(lbTime10, gbc_lblTime10);

		JLabel lbTime11 = new JLabel("17:00");
		GridBagConstraints gbc_lblTime11 = new GridBagConstraints();
		gbc_lblTime11.insets = new Insets(0, 0, 0, 5);
		gbc_lblTime11.gridx = 0;
		gbc_lblTime11.gridy = 21;
		scheduleGrid.add(lbTime11, gbc_lblTime11);

		JLabel lbTime12 = new JLabel("18:00");
		GridBagConstraints gbc_lblTime12 = new GridBagConstraints();
		gbc_lblTime12.insets = new Insets(0, 0, 0, 5);
		gbc_lblTime12.gridx = 0;
		gbc_lblTime12.gridy = 23;
		scheduleGrid.add(lbTime12, gbc_lblTime12);

		JLabel lbTime13 = new JLabel("19:00");
		GridBagConstraints gbc_lblTime13 = new GridBagConstraints();
		gbc_lblTime13.insets = new Insets(0, 0, 0, 5);
		gbc_lblTime13.gridx = 0;
		gbc_lblTime13.gridy = 25;
		scheduleGrid.add(lbTime13, gbc_lblTime13);

		JLabel lbTime14 = new JLabel("20:00");
		GridBagConstraints gbc_lblTime14 = new GridBagConstraints();
		gbc_lblTime14.insets = new Insets(0, 0, 0, 5);
		gbc_lblTime14.gridx = 0;
		gbc_lblTime14.gridy = 27;
		scheduleGrid.add(lbTime14, gbc_lblTime14);

		JLabel lbTime15= new JLabel("21:00");
		GridBagConstraints gbc_lblTime15 = new GridBagConstraints();
		gbc_lblTime15.insets = new Insets(0, 0, 0, 5);
		gbc_lblTime15.gridx = 0;
		gbc_lblTime15.gridy = 29;
		scheduleGrid.add(lbTime15, gbc_lblTime15);
		
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
		buttonContainer.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.add(buttonContainer);
		GridBagLayout gbl_buttonContainer = new GridBagLayout();
		gbl_buttonContainer.columnWidths = new int[]{0, 0};
		gbl_buttonContainer.rowHeights = new int[]{35, 0, 35, 0};
		gbl_buttonContainer.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonContainer.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonContainer.setLayout(gbl_buttonContainer);
		
		
		JButton btnPatients = new JButton("View patients");
		GridBagConstraints gbc_btnPatients = new GridBagConstraints();
		gbc_btnPatients.insets = new Insets(0, 0, 5, 0);
		gbc_btnPatients.gridx = 0;
		gbc_btnPatients.gridy = 0;
		buttonContainer.add(btnPatients, gbc_btnPatients);
		
		JComboBox nurseComboBox = new JComboBox();
		for (Nurse n : doc.getNurses()) {
			nurseComboBox.addItem(n.getName());
		}nurseComboBox.setBackground(Color.WHITE);
		
		nurseComboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_nurseComboBox = new GridBagConstraints();
		gbc_nurseComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_nurseComboBox.gridx = 0;
		gbc_nurseComboBox.gridy = 1;
		buttonContainer.add(nurseComboBox, gbc_nurseComboBox);
		
		JButton btnOwn = new JButton("View own schedule");
		btnOwn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nurseComboBox.setSelectedIndex(-1);
			}
		});
		GridBagConstraints gbc_btnOwn = new GridBagConstraints();
		gbc_btnOwn.gridx = 0;
		gbc_btnOwn.gridy = 2;
		buttonContainer.add(btnOwn, gbc_btnOwn);
	}


}
