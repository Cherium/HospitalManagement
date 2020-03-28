import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class AdminView extends JFrame {
	
	private JPanel contentPanel;
	
	private JButton btnReturn;
    ////Added for Account creaiton and deletion
    private JButton crtAccount;
	private JButton delAccount;
	private JButton editAccount;
    private JButton crtDepartment;
    private JButton delDepartment;
    
    private JLabel welcomeLabel;
    
    private JTextField createDeptText;
    
    
    private JComboBox<String> rolesDropDown;
    //private JComboBox<String> deptDropDown;
	
	
    
    
    
    
    
    
    
    
    
    
    //constructor
	public AdminView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		
		initializeGUI();
			
	}
	
	
/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI()
	{
//Main panel background
		contentPanel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
		
			btnReturn = new JButton("Sign Out");
				btnReturn.addActionListener(e -> setVisible(false) );

			welcomeLabel = new JLabel();
				welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
				
				
//Inner Panels
	
			JPanel accountPanel = new JPanel(new MigLayout("wrap 2", "[] 16 []") );
				accountPanel.setBorder(BorderFactory.createTitledBorder("Account Management"));
			
	//Labels
				JLabel chooseRole = new JLabel("Choose Role to Manage:");
				rolesDropDown = new JComboBox<String>();
					rolesDropDown.addItem("Patient");
					rolesDropDown.addItem("Doctor");
					rolesDropDown.addItem("Admin");
					rolesDropDown.addItem("Hospital Authority");
					rolesDropDown.addItem("Nurse");
					rolesDropDown.addItem("Receptionist");
					
	//Buttons			
				crtAccount = new JButton("Create Account");
				delAccount = new JButton("Delete Account");
				editAccount = new JButton("Edit Account");
				
	//Add components to inner panel		
				accountPanel.add(chooseRole, "right");
				accountPanel.add(rolesDropDown, "wrap");
				accountPanel.add(crtAccount);
				accountPanel.add(delAccount);
				accountPanel.add(editAccount);
				
				
				
				
				
				
			JPanel deptPanel = new JPanel(new MigLayout("wrap 2", "[] 16 []") );
				deptPanel.setBorder(BorderFactory.createTitledBorder("Department Management"));
			
	//Labels
				JLabel enterDept = new JLabel("Enter Name of Department to Create:");
				//JLabel removeDept = new JLabel("Choose Department to Remove:");	
				
	//TextFields
				createDeptText = new JTextField();
					createDeptText.setColumns(10);
				
	//ComboBoxes
				//deptDropDown = new JComboBox<String>();
				//deptDropDown.addItem("Patient");
				
	//Buttons			
				crtDepartment = new JButton("Create Department");
				//delDepartment = new JButton("Delete Department");
	//Add components to inner panel
				deptPanel.add(enterDept, "span, wrap");
				deptPanel.add(createDeptText, "sg a, gapleft 30");		//sg a= textfield and combobox will be same size
				deptPanel.add(crtDepartment, "wrap");
	
			
			
		
		
			

//Add inner panel to main container, in order
		
		contentPanel.add(btnReturn,"wrap 30px");
		contentPanel.add(welcomeLabel, "wrap");
		//contentPanel.add(accountPanel, "grow, wrap");	
		contentPanel.add(accountPanel, "wrap");			//print label, wrap to the next row which will be 200 pixels lower
		contentPanel.add(deptPanel, "wrap");

			
			
		setVisible(true);
	}


	
	
	
	
	
	//pop up a message-dialog box with a message passed in 
	public void showDialogToUser(String message)
	{
		JOptionPane.showMessageDialog(contentPanel, message);
	}	
	
	
	
/**Getter and Setter Methods*/

	public JButton getCrtAccount() {
		return crtAccount;
	}



	public void setCrtAccount(JButton crtAccount) {
		this.crtAccount = crtAccount;
	}



	public JButton getDltAccount() {
		return delAccount;
	}



	public void setDltAccount(JButton dltAccount) {
		this.delAccount = dltAccount;
	}



	public JButton getEditAccount() {
		return editAccount;
	}



	public void setEditAccount(JButton editAccount) {
		this.editAccount = editAccount;
	}



	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}



	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}



	public JComboBox<String> getRolesDropDown() {
		return rolesDropDown;
	}



	public void setRolesDropDown(JComboBox<String> rolesDropDown) {
		this.rolesDropDown = rolesDropDown;
	}



	public JButton getDelAccount() {
		return delAccount;
	}



	public void setDelAccount(JButton delAccount) {
		this.delAccount = delAccount;
	}


	public JButton getCrtDepartment() {
		return crtDepartment;
	}


	public void setCrtDepartment(JButton crtDepartment) {
		this.crtDepartment = crtDepartment;
	}


	public JButton getDelDepartment() {
		return delDepartment;
	}


	public void setDelDepartment(JButton delDepartment) {
		this.delDepartment = delDepartment;
	}


	public JTextField getCreateDeptText() {
		return createDeptText;
	}


	public void setCreateDeptText(JTextField createDeptText) {
		this.createDeptText = createDeptText;
	}

}
