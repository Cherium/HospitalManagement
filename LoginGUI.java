import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI {
	// https://www.color-hex.com/color-palette/88323

	private JFrame frame;
	private JTextField usernameInput;
	private JButton btnLogin;
	private JTextField passwordInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 20};
		gridBagLayout.rowHeights = new int[]{20, 20, 20, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.anchor = GridBagConstraints.EAST;
		gbc_usernameLabel.gridx = 2;
		gbc_usernameLabel.gridy = 2;
		frame.getContentPane().add(usernameLabel, gbc_usernameLabel);
		
		usernameInput = new JTextField();
		GridBagConstraints gbc_usernameInput = new GridBagConstraints();
		gbc_usernameInput.gridwidth = 4;
		gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameInput.insets = new Insets(0, 0, 5, 5);
		gbc_usernameInput.gridx = 5;
		gbc_usernameInput.gridy = 2;
		frame.getContentPane().add(usernameInput, gbc_usernameInput);
		usernameInput.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 2;
		gbc_passwordLabel.gridy = 3;
		frame.getContentPane().add(passwordLabel, gbc_passwordLabel);
		
		passwordInput = new JPasswordField();
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.gridwidth = 4;
		gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInput.gridx = 5;
		gbc_passwordInput.gridy = 3;
		frame.getContentPane().add(passwordInput, gbc_passwordInput);
		passwordInput.setColumns(10);
		
	
		btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String usr = usernameInput.getText();
				if (usr.equals("doctor")) {
					DoctorFrame doc = new DoctorFrame();
					doc.setExtendedState(JFrame.MAXIMIZED_BOTH);
					doc.setVisible(true);
				} else if (usr.equals("nurse")) {
					NurseFrame nur = new NurseFrame();
					nur.setExtendedState(JFrame.MAXIMIZED_BOTH);
					nur.setVisible(true);
				} else if (usr.equals("patient")) {
					PatientFrame pat = new PatientFrame();
					pat.setExtendedState(JFrame.MAXIMIZED_BOTH);
					pat.setVisible(true);
				} else if (usr.equals("admin")) { 
					AdminFrame adm = new AdminFrame();
					adm.setExtendedState(JFrame.MAXIMIZED_BOTH);
					adm.setVisible(true);
				} else if (usr.equals("stats")) {
					StatsFrame stat = new StatsFrame();
					stat.setExtendedState(JFrame.MAXIMIZED_BOTH);
					stat.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid username or password");
				}
			}
		});
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(0, 206, 209));

		
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 6;
		frame.getContentPane().add(btnLogin, gbc_btnLogin);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
