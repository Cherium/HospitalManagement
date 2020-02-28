import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class NewPatientDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameInput;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JPasswordField passwordInputConfirm;
	private JButton cancelButton;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewPatientDialog dialog = new NewPatientDialog(new Database());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewPatientDialog(Database dbase) {
		setSize(400, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{50, 0, 25, 100, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Name");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			nameInput = new JTextField();
			GridBagConstraints gbc_nameInput = new GridBagConstraints();
			gbc_nameInput.insets = new Insets(0, 0, 5, 0);
			gbc_nameInput.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameInput.gridx = 3;
			gbc_nameInput.gridy = 1;
			contentPanel.add(nameInput, gbc_nameInput);
			nameInput.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Username");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 2;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			usernameInput = new JTextField();
			GridBagConstraints gbc_usernameInput = new GridBagConstraints();
			gbc_usernameInput.insets = new Insets(0, 0, 5, 0);
			gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
			gbc_usernameInput.gridx = 3;
			gbc_usernameInput.gridy = 2;
			contentPanel.add(usernameInput, gbc_usernameInput);
			usernameInput.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Password");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 3;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			passwordInput = new JPasswordField();
			GridBagConstraints gbc_passwordInput = new GridBagConstraints();
			gbc_passwordInput.insets = new Insets(0, 0, 5, 0);
			gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordInput.gridx = 3;
			gbc_passwordInput.gridy = 3;
			contentPanel.add(passwordInput, gbc_passwordInput);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Re-type password");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_3.gridx = 1;
			gbc_lblNewLabel_3.gridy = 4;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			passwordInputConfirm = new JPasswordField();
			GridBagConstraints gbc_passwordInputConfirm = new GridBagConstraints();
			gbc_passwordInputConfirm.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordInputConfirm.gridx = 3;
			gbc_passwordInputConfirm.gridy = 4;
			contentPanel.add(passwordInputConfirm, gbc_passwordInputConfirm);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Create");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						createAPatient(dbase);						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						closeDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void createAPatient(Database dbase) {
		String name = nameInput.getText();
		String username = usernameInput.getText();
		char[] pwd = passwordInput.getPassword();
		char[] pwdCheck = passwordInputConfirm.getPassword();
		
		if (Arrays.equals(pwd, pwdCheck)) {
			Patient newPat = new Patient(name, username, pwd.toString());
			if (dbase.userExists(newPat)) {
				//dbase.addUser(newPat);
				//closeDialog();
				JOptionPane.showMessageDialog(null, "Username taken.");
				//System.out.println("Username taken");
			} else {
				dbase.addUser(newPat);
				closeDialog();
				JOptionPane.showMessageDialog(null, "Account made succesfully!");
				//System.out.println("Account made succesfully!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Passwords don't match.");
			//System.out.println("Passwords don't match");
		}
		
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	
	public JButton getOkButton() {
		return okButton;
	}
	
	public void clearInputs() {
		nameInput.setText("");
		usernameInput.setText("");
		passwordInput.setText("");
		passwordInputConfirm.setText("");
	}
	
	public void closeDialog() {
		setVisible(false);
		clearInputs();
	}

}
