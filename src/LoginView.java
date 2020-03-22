import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class LoginView {
	
	private JFrame frame;
	private JPanel panel;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private JButton btnLogin;
	private JButton btnNewPatient;
	private JButton btnExit;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	

	
	
	
	
	
	
	
	
	
	
	
	//Constructor
	public LoginView(String title)
	{
		//sets frame containers attributes
		frame = new JFrame(title);
			frame.setSize(460,170);
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
		
		initializeGUI();
	}

	
	
	

	/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI() 
	{
		
		//Main panel background
		JPanel contentPanel = new JPanel(new MigLayout(""));		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(10, 10, 5, 5));	//set insets for the panel		
			contentPanel.setBackground(new Color(179, 173, 191));
			frame.add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame


			
			

		JPanel accountPanel = new JPanel(new MigLayout("wrap 2", "[align right] 40 [align left]") );
		accountPanel.setBorder(BorderFactory.createMatteBorder(
                2, 5, 1, 1, new Color(79,60,115)));
			//accountPanel.setOpaque(false);
				
			usernameLabel = new JLabel("Username");
			passwordLabel = new JLabel("Password");
	
	
			usernameField = new JTextField(25);
			passwordField = new JPasswordField(25);		//25 columns
			
			accountPanel.add(usernameLabel);
			accountPanel.add(usernameField);
			accountPanel.add(passwordLabel);
			accountPanel.add(passwordField);
		
		
		
		
		
		JPanel deptPanel = new JPanel(new MigLayout("wrap 3", "[align center] 16 [align center] 16 [align center]") );
			deptPanel.setOpaque(false);
		//https://stackoverflow.com/questions/33954698/jbutton-change-default-border
		//https://stackoverflow.com/questions/3420311/java-swing-button-colors
			btnLogin = new JButton("Login");
				btnLogin.setBackground(Color.DARK_GRAY);
				btnLogin.setForeground(Color.WHITE);
				btnLogin.setBorder(BorderFactory.createBevelBorder(1, Color.green, Color.orange, Color.red, Color.blue));
			btnNewPatient = new JButton("New Patient");
				btnNewPatient.setBackground(Color.DARK_GRAY);
				btnNewPatient.setForeground(Color.WHITE);
	
			btnExit = new JButton("Exit");
				btnExit.setBackground(new Color(154,50,50));
				btnExit.setForeground(Color.WHITE);
				btnExit.addActionListener(e -> frame.setVisible(false));

			deptPanel.add(btnExit, " sg a");
			deptPanel.add(btnNewPatient, "sg a");
			deptPanel.add(btnLogin, "sg a");
			
			
			
			
			
			
		contentPanel.add(accountPanel, "wrap");
		contentPanel.add(deptPanel);
			
		frame.setVisible(true);
	}


	//clear user inputs 
	protected void clearInputs() 
	{
		usernameField.setText("");
		passwordField.setText("");
	}
	
	//Only happens in login screen. No combination of user and password detected
	public void loginError(String message) 
	{
				JFrame frame = new JFrame();
				frame.setSize(200,100);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JOptionPane.showMessageDialog(frame, message);
	}	


	
	/**Getter and Setter Methods*/
	
	public JFrame getFrame() {
		return frame;
	}






	public void setFrame(JFrame frame) {
		this.frame = frame;
	}






	public JPanel getPanel() {
		return panel;
	}






	public void setPanel(JPanel panel) {
		this.panel = panel;
	}






	public JTextField getUsernameField() {
		return usernameField;
	}






	public void setUsernameField(JTextField usernameField) {
		this.usernameField = usernameField;
	}






	public JPasswordField getPasswordField() {
		return passwordField;
	}






	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}






	public JButton getBtnLogin() {
		return btnLogin;
	}






	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}






	public JButton getBtnNewPatient() {
		return btnNewPatient;
	}






	public void setBtnNewPatient(JButton btnNewPatient) {
		this.btnNewPatient = btnNewPatient;
	}






	public JLabel getUsernameLabel() {
		return usernameLabel;
	}






	public void setUsernameLabel(JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}






	public JLabel getPasswordLabel() {
		return passwordLabel;
	}






	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}
	
	
	

}
