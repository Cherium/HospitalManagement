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
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientFrame frame = new PatientFrame();
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
		
		JLabel lblWelcome = new JLabel("Welcome");
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcome.anchor = GridBagConstraints.WEST;
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 0;
		contentPane.add(lblWelcome, gbc_lblWelcome);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.GREEN);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		JButton btnReferrals = new JButton("Referrals");
		GridBagConstraints gbc_btnReferrals = new GridBagConstraints();
		gbc_btnReferrals.insets = new Insets(0, 0, 5, 5);
		gbc_btnReferrals.gridx = 0;
		gbc_btnReferrals.gridy = 2;
		contentPane.add(btnReferrals, gbc_btnReferrals);
		
		JLabel lblAppointments = new JLabel("Appointments");
		GridBagConstraints gbc_lblAppointments = new GridBagConstraints();
		gbc_lblAppointments.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppointments.gridx = 0;
		gbc_lblAppointments.gridy = 3;
		contentPane.add(lblAppointments, gbc_lblAppointments);
		
		btnBack = new JButton("Back");
		
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 6;
		contentPane.add(btnBack, gbc_btnBack);
	}
	
	public JButton getReturnButton() {
		return btnBack;
	}

}
