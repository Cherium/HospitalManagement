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

import net.miginfocom.swing.MigLayout;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class CreateNewPatientView  extends JDialog{
	
	private JPanel contentPanel;
	
	private JTextField nameInput;
	private JTextField usernameInput;
	private JTextField address;
	private JTextField phone;
	private JTextField email;
	
	private JPasswordField passwordInput;
	private JPasswordField passwordInputConfirm;
	
	private JButton cancelButton;
	private JButton createButton;
		
		
		
		
		
		
		
		
		
		
		
		//constructor
		public CreateNewPatientView(String title)
		{
			//sets frame containers attributes
			setTitle(title);
			setSize(370, 450);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			initializeGUI();
				
		}
		
		
		/**initialize the panels and components that will go inside the frame*/
		public void initializeGUI()
		{
	
			//Main panel background
			contentPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
			
		
			//Labels
				JLabel nameLab = new JLabel("Name:");
				JLabel usernameLab = new JLabel("Username:");
				JLabel addressLab = new JLabel("Address:");
				JLabel PhoneNumLab = new JLabel("Phone #:");
				JLabel emailLab = new JLabel("Email:");
				JLabel pwd = new JLabel("Password:");
				JLabel pwd2 = new JLabel("Re-enter Password:");
			
				
			//TextFields
				nameInput = new JTextField(20);
				usernameInput = new JTextField(20);
				address = new JTextField(20);
				phone = new JTextField(20);
				email = new JTextField(20);
				
			//PasswordFields
				passwordInput = new JPasswordField(20);
				passwordInputConfirm = new JPasswordField(20);
				
			//Buttons
				createButton = new JButton("Create Account");
				cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(e -> {setVisible(false);} );		

				
			//Add to main panel
			contentPanel.add(nameLab);
			contentPanel.add(nameInput, "wrap");
			contentPanel.add(addressLab);
			contentPanel.add(address, "wrap");
			contentPanel.add(PhoneNumLab);
			contentPanel.add(phone, "wrap");
			contentPanel.add(emailLab);
			contentPanel.add(email, "wrap");
			contentPanel.add(usernameLab);
			contentPanel.add(usernameInput, "wrap");
			contentPanel.add(pwd);
			contentPanel.add(passwordInput, "wrap");
			contentPanel.add(pwd2);
			contentPanel.add(passwordInputConfirm, "wrap 10");
			
			contentPanel.add(createButton, "sg a");
			contentPanel.add(cancelButton, "sg a");
			
			
				
				
				
				
				
			setVisible(true);
		}

		
		
		
		
		//clear inputs- prob dont need this function, since closing a dialog deletes all references to the instantiation
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
		
		

		
/**Getter and Setter Methods*/		
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


		public JPanel getContentPanel() {
			return contentPanel;
		}


		public void setContentPanel(JPanel contentPanel) {
			this.contentPanel = contentPanel;
		}


		public JTextField getAddress() {
			return address;
		}


		public void setAddress(JTextField address) {
			this.address = address;
		}


		public JTextField getPhone() {
			return phone;
		}


		public void setPhone(JTextField phone) {
			this.phone = phone;
		}


		public JTextField getEmail() {
			return email;
		}


		public void setEmail(JTextField email) {
			this.email = email;
		}


		public JButton getCreateButton() {
			return createButton;
		}


		public void setCreateButton(JButton createButton) {
			this.createButton = createButton;
		}
		
		
		
		
		
		
}


