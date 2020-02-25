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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StatsFrame extends JFrame {

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
					StatsFrame frame = new StatsFrame(new LoginFrame());
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
	// Change constructor to take in a user and database
	// public StatsFrame(LoginFrame main, Database dbase, HospitalAuthority hospstats)
	public StatsFrame(LoginFrame main) {
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
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 0;
		contentPane.add(lblWelcome, gbc_lblWelcome);
		
		// Label for name
		// TODO: connect label to name of user
		// JLabel lblName = new JLabel(hospstats.getName());
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.CYAN);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		// Label for statistics.
		JLabel lblStatistics = new JLabel("Hospital Statistics");
		GridBagConstraints gbc_lblStatistics = new GridBagConstraints();
		gbc_lblStatistics.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatistics.gridx = 0;
		gbc_lblStatistics.gridy = 1;
		contentPane.add(lblStatistics, gbc_lblStatistics);
		
		// TODO: Show the statistics of the hospital by department and lab tests

		// TODO: Selecting department shows a list of doctors in that department and their statistics.

		// Button for returning to login
		btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 6;
		contentPane.add(btnBack, gbc_btnBack);
	}
	
	// Get a button that returns to login
	public JButton getReturnButton() {
		return btnBack;
	}

}
