import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JButton btnLogin;
	private JButton btnNewPatient;

	/**
	 * Launch the application.
	 * Application should be launched from LaunchGUI.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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

	// TODO: Link login to database
	// public LoginFrame(Database dbase)
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 617, 305);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 20, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Label for Username
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 4;
		gbc_lblUsername.gridy = 2;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		// Input field for username
		usernameInput = new JTextField();
		GridBagConstraints gbc_usernameInput = new GridBagConstraints();
		gbc_usernameInput.insets = new Insets(0, 0, 5, 0);
		gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameInput.gridx = 6;
		gbc_usernameInput.gridy = 2;
		contentPane.add(usernameInput, gbc_usernameInput);
		usernameInput.setColumns(10);
		
		// Label for password
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 4;
		gbc_lblPassword.gridy = 3;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		// Password field input for password
		passwordInput = new JPasswordField();
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.insets = new Insets(0, 0, 5, 0);
		gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInput.gridx = 6;
		gbc_passwordInput.gridy = 3;
		contentPane.add(passwordInput, gbc_passwordInput);
		
		// TODO: Create button for creating a new patient
		// TODO: Link button to creating a new patient account. 
		// Via a popup dialog box
		// Screen username against existing usernames
		// Show the login credentials user selected (username only) after successful registration

		// Login button
		btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 4;
		gbc_btnLogin.gridy = 5;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		btnNewPatient = new JButton("New Patient");
		btnNewPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnNewPatient = new GridBagConstraints();
		gbc_btnNewPatient.gridx = 6;
		gbc_btnNewPatient.gridy = 5;
		contentPane.add(btnNewPatient, gbc_btnNewPatient);
	}
	
	// Returns the login button
	public JButton getLoginButton() {
		return btnLogin;
	}
	
	// Returns the text entered in username
	public String getUsername() {
		return usernameInput.getText();
	}

	// Returns the password entered in password field
	public String getPassword() {
		return passwordInput.getText();
	}
}
