import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CreateNewPatientView  extends JDialog{
	
	
		private JPanel contentPanel = new JPanel();
		private JTextField nameInput;
		private JTextField usernameInput;
		private JPasswordField passwordInput;
		private JPasswordField passwordInputConfirm;
		private JButton cancelButton;
		private JButton okButton;	
		
		public CreateNewPatientView(String title)
		{
			setTitle(title);
			setSize(400, 200);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());
			
			initializeGUI();
				
		}
		
		
		
		public void initializeGUI()
		{
	
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				getContentPane().add(contentPanel, BorderLayout.CENTER);
				GridBagLayout gbl_contentPanel = new GridBagLayout();
				gbl_contentPanel.columnWidths = new int[]{50, 0, 25, 100, 0};
				gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
				gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};			
				contentPanel.setLayout(gbl_contentPanel);
			
				
	//Labels
			JLabel lblNewLabel = new JLabel("Name");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				contentPanel.add(lblNewLabel, gbc_lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("Username");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 2;
				contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("Password");
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 1;
				gbc_lblNewLabel_2.gridy = 3;
				contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

			JLabel lblNewLabel_3 = new JLabel("Re-type password");
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel_3.gridx = 1;
				gbc_lblNewLabel_3.gridy = 4;
				contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);				
	
	//TextFields	
			nameInput = new JTextField();
				GridBagConstraints gbc_nameInput = new GridBagConstraints();
				gbc_nameInput.insets = new Insets(0, 0, 5, 0);
				gbc_nameInput.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameInput.gridx = 3;
				gbc_nameInput.gridy = 1;
				contentPanel.add(nameInput, gbc_nameInput);
				nameInput.setColumns(10);

			usernameInput = new JTextField();
				GridBagConstraints gbc_usernameInput = new GridBagConstraints();
				gbc_usernameInput.insets = new Insets(0, 0, 5, 0);
				gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
				gbc_usernameInput.gridx = 3;
				gbc_usernameInput.gridy = 2;
				contentPanel.add(usernameInput, gbc_usernameInput);
				usernameInput.setColumns(10);
				
	//Passwords TextFields			
			passwordInput = new JPasswordField();
				final GridBagConstraints gbc_passwordInput = new GridBagConstraints();
				gbc_passwordInput.insets = new Insets(0, 0, 5, 0);
				gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
				gbc_passwordInput.gridx = 3;
				gbc_passwordInput.gridy = 3;
				contentPanel.add(passwordInput, gbc_passwordInput);			
				

			passwordInputConfirm = new JPasswordField();
				GridBagConstraints gbc_passwordInputConfirm = new GridBagConstraints();
				gbc_passwordInputConfirm.fill = GridBagConstraints.HORIZONTAL;
				gbc_passwordInputConfirm.gridx = 3;
				gbc_passwordInputConfirm.gridy = 4;
				contentPanel.add(passwordInputConfirm, gbc_passwordInputConfirm);	

				
	//Panel for buttons
			JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				
	//Buttons
			okButton = new JButton("Create");
				buttonPane.add(okButton);
			cancelButton = new JButton("Cancel");
			
			cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
			
				
				
				
				
				
			setVisible(true);
		}

		
		
		
		
		//clear inputs in current dialog... should not need
		public void clearInputs() 
		{
			nameInput.setText("");
			usernameInput.setText("");
			passwordInput.setText("");
			passwordInputConfirm.setText("");		
		}	
		
		
		public void showDialogToUser(String message)
		{
			JOptionPane.showMessageDialog(contentPanel, message);
		}
		
		
		//GETTERS AND SETTERS//
		
		
		public JTextField getNameInput() {
			return nameInput;
		}



		public void setNameInput(JTextField nameInput) {
			this.nameInput = nameInput;
		}



		public JTextField getUsernameInput() {
			return usernameInput;
		}



		public void setUsernameInput(JTextField usernameInput) {
			this.usernameInput = usernameInput;
		}



		public JPasswordField getPasswordInput() {
			return passwordInput;
		}



		public void setPasswordInput(JPasswordField passwordInput) {
			this.passwordInput = passwordInput;
		}



		public JPasswordField getPasswordInputConfirm() {
			return passwordInputConfirm;
		}



		public void setPasswordInputConfirm(JPasswordField passwordInputConfirm) {
			this.passwordInputConfirm = passwordInputConfirm;
		}



		public JButton getCancelButton() {
			return cancelButton;
		}



		public void setCancelButton(JButton cancelButton) {
			this.cancelButton = cancelButton;
		}



		public JButton getOkButton() {
			return okButton;
		}



		public void setOkButton(JButton okButton) {
			this.okButton = okButton;
		}
		
		
		
		
		
		
		
		
		
}


