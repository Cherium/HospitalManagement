import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatientFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnBack;

	/**
	 * Launch the application.
	 * Launch application from LaunchGUI.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientFrame frame = new PatientFrame(new LoginFrame());
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

	 // Change constructor to take in a patient and database as input.
	 // public PatientFrame(LoginFrame main, Database dbase, Patient pat)
	public PatientFrame(LoginFrame main) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Label for welcome
		JLabel lblWelcome = new JLabel("Welcome");
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcome.anchor = GridBagConstraints.WEST;
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 0;
		contentPane.add(lblWelcome, gbc_lblWelcome);
		
		// Label for name of user
		// JLabel lblName = new JLabel(patient.getName());
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		// Button for viewing referrals assigned
		// TODO: Set up button to show all the referrals the patient has (specialist and lab tests)
		// A dialog box
		// Can select referrals and choose to book appointment/lab test
		JButton btnReferrals = new JButton("Referrals");
		GridBagConstraints gbc_btnReferrals = new GridBagConstraints();
		gbc_btnReferrals.insets = new Insets(0, 0, 5, 5);
		gbc_btnReferrals.gridx = 0;
		gbc_btnReferrals.gridy = 2;
		contentPane.add(btnReferrals, gbc_btnReferrals);

		// TODO: Set up booking appointments with specialist based on referral

		// TODO: Set up booking appointments for lab tests based on referral
		
		// Label for appointments
		JLabel lblAppointments = new JLabel("Appointments");
		GridBagConstraints gbc_lblAppointments = new GridBagConstraints();
		gbc_lblAppointments.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppointments.gridx = 0;
		gbc_lblAppointments.gridy = 3;
		contentPane.add(lblAppointments, gbc_lblAppointments);

		// TODO: Set up a list of upcoming appointments
		// A for loop iteration through all appointments

		// TODO: Set up viewing penalty fees if cancellation penalty is detacted

		// TODO: Set up a button that allows modification of personal information on record
		// Via a dialog box

		// Button for returning to login screen		
		btnBack = new JButton("Back");
		
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 6;
		contentPane.add(btnBack, gbc_btnBack);
	}
	
	// Get a JButton
	public JButton getReturnButton() {
		return btnBack;
	}

}
