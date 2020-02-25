import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class NurseFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnBack;

	/**
	 * Launch the application.
	 * Application should be launched with LaunchGUI. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NurseFrame frame = new NurseFrame(new LoginFrame());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	 // TODO: Add a user argument and database argument to the constructor. 
	 // public NurseFrame(LoginFrame main, Database dbase, Nurse nurse)
	public NurseFrame(LoginFrame main) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Label for Welcome
		JLabel lblWelcome = new JLabel("Welcome, Nurse");
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 0;
		contentPane.add(lblWelcome, gbc_lblWelcome);
		
		// Label for name
		// TODO: Change to get name of user
		// JLabel lblName = new JLabel(nurse.getName());
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.PINK);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		// Button for modifying schedule
		// TODO: Set up schedule modification for the button. 
		// Via a popup window (dialog box) off of Nurse Frame.
		JButton btnModifySchedule = new JButton("Modify Schedule");
		GridBagConstraints gbc_btnModifySchedule = new GridBagConstraints();
		gbc_btnModifySchedule.insets = new Insets(0, 0, 5, 5);
		gbc_btnModifySchedule.gridx = 0;
		gbc_btnModifySchedule.gridy = 1;
		contentPane.add(btnModifySchedule, gbc_btnModifySchedule);
		
		// Button for uploading patient referrals
		// TODO: Set up the uploading of referrals for the button. 
		// Through asking for a referral number
		// (Advanced?) through asking for a form from current pc
		JButton btnUploadReferral = new JButton("Upload Referral");
		GridBagConstraints gbc_btnUploadReferral = new GridBagConstraints();
		gbc_btnUploadReferral.insets = new Insets(0, 0, 5, 5);
		gbc_btnUploadReferral.gridx = 1;
		gbc_btnUploadReferral.gridy = 1;
		contentPane.add(btnUploadReferral, gbc_btnUploadReferral);
		
		// Label for doctors
		JLabel lblDoctors = new JLabel("Assigned Doctors:");
		GridBagConstraints gbc_lblDoctors = new GridBagConstraints();
		gbc_lblDoctors.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoctors.gridx = 0;
		gbc_lblDoctors.gridy = 2;
		contentPane.add(lblDoctors, gbc_lblDoctors);
		
		// Label for list of doctors
		// TODO: set up a for loop iteration through list of assigned doctors to add names to frame.
		JLabel lblDocName = new JLabel("Doctor");
		lblDocName.setForeground(Color.CYAN);
		GridBagConstraints gbc_lblDocName = new GridBagConstraints();
		gbc_lblDocName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDocName.gridx = 1;
		gbc_lblDocName.gridy = 2;
		contentPane.add(lblDocName, gbc_lblDocName);
		
		// Label for department.
		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 3;
		contentPane.add(lblDepartment, gbc_lblDepartment);
		
		// Label for department name.
		// JLabel lblDepartName = new JLabel(nurse.getDepartment);
		// TODO: Change to get department the nurse is currently working in.
		// TODO: Account for case where nurse works in another department for the second half of the schedule.
		JLabel lblDepartName = new JLabel("Department");
		lblDepartName.setForeground(Color.ORANGE);
		lblDepartName.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblDepartName = new GridBagConstraints();
		gbc_lblDepartName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartName.gridx = 1;
		gbc_lblDepartName.gridy = 3;
		contentPane.add(lblDepartName, gbc_lblDepartName);
		
		// Label for schedule
		JLabel lblSchedule = new JLabel("Schedule");
		GridBagConstraints gbc_lblSchedule = new GridBagConstraints();
		gbc_lblSchedule.insets = new Insets(0, 0, 5, 5);
		gbc_lblSchedule.gridx = 0;
		gbc_lblSchedule.gridy = 4;
		contentPane.add(lblSchedule, gbc_lblSchedule);
		
		// TODO: implement schedule as a visual interface that user can interact with to
		// get information about patients scheduled

		// Button for returning to login page
		btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 10;
		contentPane.add(btnBack, gbc_btnBack);
	}

	// Returns a JButton
	public JButton getReturnButton() {
		return btnBack;
	}
}
