import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	

	
	
	
	
	
	
	
	
	
	
	
	//Constructor
	public LoginView(String title)
	{
		//sets frame containers attributes
		frame = new JFrame(title);
			frame.setSize(400,200);
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
		
		initializeGUI();
	}

	
	
	

	/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI() 
	{
		
		//set panel container(with a layout) inside the frame
		panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(panel);						//set panel as frames inner container
		
		GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{10, 10, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{10, 10, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};		
		panel.setLayout(gbl_panel);

		
		
		//create UI elements and add to panel
		GridBagConstraints c = new GridBagConstraints();
		
		usernameLabel = new JLabel("Username");
			c.insets = new Insets(0, 0, 5, 5);
			c.gridx = 2;
			c.gridy = 1;
			panel.add(usernameLabel, c);
			
		passwordLabel = new JLabel("Password");
			c.insets= new Insets(0, 0, 5, 5);
			c.gridx = 2;
			c.gridy = 2;
			panel.add(passwordLabel, c);

		usernameField = new JTextField(10);
			c.insets = new Insets(0, 0, 5, 0);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 4;
			c.gridy = 1;
			panel.add(usernameField, c);
			
		passwordField = new JPasswordField(10);		//10 columns
			c.insets= new Insets(0, 0, 5, 0);
			c.gridx = 4;
			c.gridy = 2;
			panel.add(passwordField, c);	
			
		btnLogin = new JButton("Login");
			c.insets = new Insets(0, 0, 5, 5);
			c.gridx = 3;
			c.gridy = 4;
			panel.add(btnLogin, c);
			
		btnNewPatient = new JButton("New Patient");
			c.insets = new Insets(0, 0, 0, 5);
			c.gridx = 3;
			c.gridy = 5;
			panel.add(btnNewPatient, c);	
			
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
