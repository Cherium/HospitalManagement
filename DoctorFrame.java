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

	/**
	 * Constructor
	 */
	public DoctorFrame(Database dbase, StartScreen main, Doctor doc) {
		// Test multiple nurses
		Nurse newNurse = new Nurse("Mewtwo", "mew", "mewo".toCharArray(), doc.getDepartment(), doc);
		doc.addNurse(newNurse);

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
		
		JLabel lblDayWeek = new JLabel("Monday");
		GridBagConstraints gdb_lbl = new GridBagConstraints();
		gdb_lbl.insets = new Insets(15, 15, 15, 15);
		gdb_lbl.gridx = 1;
		gdb_lbl.gridy = 0;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Tuesday");
		gdb_lbl.gridx = 2;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Wednesday");
		gdb_lbl.gridx = 3;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Thursday");
		gdb_lbl.gridx = 4;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Friday");
		gdb_lbl.gridx = 5;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Saturday");
		gdb_lbl.gridx = 6;
		schedule.add(lblDayWeek, gdb_lbl);
		
		lblDayWeek = new JLabel("Sunday");
		gdb_lbl.gridx = 7;
		schedule.add(lblDayWeek, gdb_lbl);
				
		// Add dates to the days of the week
		// LocalDate now = LocalDate.now();
		TemporalField fieldISO = WeekFields.of(Locale.CANADA).dayOfWeek();
		for (int i = 1; i < 8; i++) {
			JLabel lblTemp = new JLabel(now.with(fieldISO, i).toString());
			gdb_lbl.insets = new Insets(5, 5, 5, 5);
			gdb_lbl.gridx = i;
			gdb_lbl.gridy = 1;
			schedule.add(lblTemp, gdb_lbl);
			weekdays.add(lblTemp);
			// System.out.println(now.with(fieldISO, i));
		}

		gdb_lbl.gridx = 0;
		JLabel lblTime = new JLabel("");
		for (int i = 0; i < 12; i++) {
			lblTime = new JLabel(i+8+":00");
			gdb_lbl.insets = new Insets(5, 5, 5, 5);
			gdb_lbl.gridy = 2 + 2*i;
			schedule.add(lblTime, gdb_lbl);
		}

		// Invisible labels for making schedule larger
		gdb_lbl.gridx = 8;
		gdb_lbl.gridy = 0;
		gdb_lbl.insets = new Insets(5, 5, 5, 300);
		lblDayWeek = new JLabel("");
		schedule.add(lblDayWeek, gdb_lbl);

		lblTime = new JLabel("");
		gdb_lbl.gridx = 0;
		gdb_lbl.gridy = 0;
		gdb_lbl.insets = new Insets(5, 300, 5, 5);
		schedule.add(lblTime, gdb_lbl);

		JPanel btnPaneSchedule = new JPanel();
		scheduleContainer.add(btnPaneSchedule);
		btnPaneSchedule.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBack = new JButton("<");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LocalDate past = now.minusWeeks(1);
				for (JLabel jLabel : weekdays) {
					jLabel.setText(past.with(fieldISO, weekdays.indexOf(jLabel)+1).toString());
				}
				if (past.equals(LocalDate.now())) {
					btnBack.setEnabled(false);
				}
				now = past;
			}
		});
		btnBack.setEnabled(false);
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

		JLabel lblStretchLabel = new JLabel("");
		gdb_lbl.insets = new Insets(10, 90, 10, 90);
		gdb_lbl.gridx = 0;
		gdb_lbl.gridy = 0;
		buttonContainer.add(lblStretchLabel, gdb_lbl);

		JButton btnPatients = new JButton("View patients");
		GridBagConstraints gbc_btnPatients = new GridBagConstraints();
		gbc_btnPatients.insets = new Insets(0, 0, 5, 0);
		gbc_btnPatients.gridx = 0;
		gbc_btnPatients.gridy = 1;
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
		gbc_lblNurses.gridy = 2;
		buttonContainer.add(lblNurses, gbc_lblNurses);
		nurseComboBox.setBackground(Color.WHITE);
		nurseComboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_nurseComboBox = new GridBagConstraints();
		gbc_nurseComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_nurseComboBox.gridx = 0;
		gbc_nurseComboBox.gridy = 3;
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
		gbc_btnOwn.insets = new Insets(5, 5, 5, 5);
		gbc_btnOwn.gridx = 0;
		gbc_btnOwn.gridy = 4;
		buttonContainer.add(btnOwn, gbc_btnOwn);


	}


}
