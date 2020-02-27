import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import java.awt.GridLayout;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JButton btnLogin;
	private NewPatientDialog popupMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame(new Database());
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
	public LoginFrame(Database dbase) {
		popupMenu = new NewPatientDialog(dbase);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,200);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{10, 10, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{10, 10, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Username");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		usernameInput = new JTextField();
		GridBagConstraints gbc_usernameInput = new GridBagConstraints();
		gbc_usernameInput.insets = new Insets(0, 0, 5, 0);
		gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameInput.gridx = 4;
		gbc_usernameInput.gridy = 1;
		contentPane.add(usernameInput, gbc_usernameInput);
		usernameInput.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		passwordInput = new JPasswordField();
		passwordInput.setColumns(10);
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.insets = new Insets(0, 0, 5, 0);
		gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInput.gridx = 4;
		gbc_passwordInput.gridy = 2;
		contentPane.add(passwordInput, gbc_passwordInput);
		
		btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] loginCreds = getCredentials();
				User logUser = dbase.getUser(loginCreds);
				if (!logUser.equals(null)) {
					
				} else {
					// JOptionPane.showMessageDialog(new JFrame(), "Invalid username or password");
				}
			}
		});
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 4;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		JButton btnNewPatient = new JButton("New Patient");
		btnNewPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearInputs();
				popupMenu.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewPatient = new GridBagConstraints();
		gbc_btnNewPatient.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewPatient.gridx = 3;
		gbc_btnNewPatient.gridy = 5;
		contentPane.add(btnNewPatient, gbc_btnNewPatient);
		
	}
	
	protected void clearInputs() {
		usernameInput.setText("");
		passwordInput.setText("");
	}

	public JButton getLogin() {
		return btnLogin;
	}
	
	public String[] getCredentials() {
		String[] pair = new String[2];
		pair[0] = usernameInput.getText();
		pair[1] = passwordInput.getPassword().toString();
		return pair;
	}

}
