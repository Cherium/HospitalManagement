import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class CreateNewDoctorView  extends JDialog{
/*
	public static void main(String[] args)
	{
		new CreateNewDoctorView("hehe");
	}
*/
		private JPanel contentPanel = new JPanel();
		
		private JTextField nameInput;
		private JTextField usernameInput;
		
		private JPasswordField passwordInput;
		private JPasswordField passwordInputConfirm;
		
		private JButton cancelButton;
		private JButton createButton;
		private JButton addNurse;
		
		private JComboBox<String> nurseDropDown;
		private JComboBox<String> departmentDropDown;
		
		private JTextArea box;
		
		
		
		
		//constructor
		public CreateNewDoctorView(String title)
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
			JPanel Panel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
			Panel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(Panel, BorderLayout.CENTER);					//add the panel as the container for the frame
			

			
//Inner Panels	
				JPanel contentPanel = new JPanel(new MigLayout("wrap 3", "[] 16 [] 8 []") );		//miglayout with 3 columns, 16px space between the first 2 columns, and 8px space between column 2 and 3
			
				//Labels
					JLabel name = new JLabel("Name:");
					JLabel username = new JLabel("Username:");
					JLabel department = new JLabel("Department:");
					JLabel assignedNurses = new JLabel("Assigned Nurses:");
					JLabel pwd = new JLabel("Password:");
					JLabel pwd2 = new JLabel("Re-enter Password:");
				
				//TextFields
					nameInput = new JTextField();
					usernameInput = new JTextField();
					
				//PasswordFields
					passwordInput = new JPasswordField();
					passwordInputConfirm = new JPasswordField();
					
				//Buttons
					addNurse = new JButton("add");
					
				//TextBoc
					box = new JTextArea("List of nurses to be assigned: \n");
					
				//ComboBox drop down lists	
					nurseDropDown = new JComboBox<String>();
					departmentDropDown = new JComboBox<String>();
						departmentDropDown.addActionListener(e -> disableNurseSelection() );
					
				//Add components to panel in order	
					contentPanel.add(name, "right");								//add name label to the right side of the current column
					contentPanel.add(nameInput, "width 80:160:300, sg a, wrap");	//set width of textfield, set component group to a(optional) and wrap to the next row for the next component(username)
					
					contentPanel.add(username, "right");
					contentPanel.add(usernameInput, "sg a,wrap");
					
					contentPanel.add(department, "right");
					contentPanel.add(departmentDropDown, "sg a,wrap");
					
					contentPanel.add(assignedNurses, "right");
					contentPanel.add(nurseDropDown, "sg a");
					contentPanel.add(addNurse, "wrap");
					contentPanel.add(box, "span 3 2, center, height 50:150:, wrap");	//stretch the box across 3 horizontal columns and 2 vertical rows starting in the current row, set its height, and wrap to next row
					
					
					contentPanel.add(pwd, "right");
					contentPanel.add(passwordInput, "sg a,wrap");
					
					contentPanel.add(pwd2, "right");
					contentPanel.add(passwordInputConfirm, "sg a,wrap");
					
					Panel.add(contentPanel, "wrap");
				
			
				
			
			JPanel buttonPanel = new JPanel(new MigLayout("wrap 2") );			//set an inner panel with 2 columns for the buttons
				
			//Buttons			
				createButton = new JButton("Create Account");
				
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> {clearInputs(); 
				 									setVisible(false);} );		
				
			//Add components to panel in order		
				buttonPanel.add(createButton, "gapleft 50");					//in the current column, place the component with a gap of 50px from the left
				buttonPanel.add(cancelButton, "");								//place the next component in the second column
				Panel.add(buttonPanel);											//add inner panel to the outer panel
			
				
			setVisible(true);
		}

		
	
		
		
		
		
		
		//clear inputs
		public void clearInputs() 
		{
			nameInput.setText("");
			usernameInput.setText("");
			passwordInput.setText("");
			passwordInputConfirm.setText("");		
		}	
		
	
		
		
		//pop up a message-dialog box with a message passed in 
		public void showDialogToUser(String message)
		{
			JOptionPane.showMessageDialog(contentPanel, message);
		}	
		
		
		
		//disable the nurse selection option for when the ER department is chosen by the User
		public void disableNurseSelection()
		{
			if (departmentDropDown.getSelectedItem().equals("ER")) {
				nurseDropDown.setEnabled(false);
				addNurse.setEnabled(false);			
			} else {
				nurseDropDown.setEnabled(true);
				nurseDropDown.setEnabled(true);			
			}
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



		public JButton getCreateButton() {
			return createButton;
		}



		public void setCreateButton(JButton createButton) {
			this.createButton = createButton;
		}



		public JComboBox<String> getNurseDropDown() {
			return nurseDropDown;
		}



		public void setNurseDropDown(JComboBox<String> nurseDropDown) {
			this.nurseDropDown = nurseDropDown;
		}



		public JComboBox<String> getDepartmentDropDown() {
			return departmentDropDown;
		}



		public void setDepartmentDropDown(JComboBox<String> departmentDropDown) {
			this.departmentDropDown = departmentDropDown;
		}



		public JButton getAddNurse() {
			return addNurse;
		}



		public void setAddNurse(JButton addNurse) {
			this.addNurse = addNurse;
		}



		public JTextArea getBox() {
			return box;
		}



		public void setBox(JTextArea box) {
			this.box = box;
		}

		
		
		
		
		
		
		
		
}


