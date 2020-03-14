import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class AdminView extends JFrame {
	
	
	private JButton btnReturn;
    ////Added for Account creaiton and deletion
    private JButton crtAccount;
    private JButton delAccount;
    
    private JLabel welcomeLabel;
    
    
    private JPanel managePanel;
   
    private JComboBox<String> rolesDropDown;
	
	
    
    
    
    
    
    
    
    
    
    
    //constructor
	public AdminView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		
		initializeGUI();
			
	}
	
	
/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI()
	{
		//Main panel background
		JPanel contentPanel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
		
			btnReturn = new JButton("Sign Out");
				btnReturn.addActionListener(e -> setVisible(false) );

			welcomeLabel = new JLabel();
				welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
				
				
//Inner Panels
	
			managePanel = new JPanel(new MigLayout("wrap 2", "[] 16 []") );
				managePanel.setBorder(BorderFactory.createTitledBorder("Account Management"));
			
	//Labels
				JLabel chooseRole = new JLabel("Choose Role to Manage:");
				rolesDropDown = new JComboBox<String>();
					rolesDropDown.addItem("patient");
					rolesDropDown.addItem("doctor");
					rolesDropDown.addItem("admin");
					rolesDropDown.addItem("hospital authority");
					rolesDropDown.addItem("nurse");
					rolesDropDown.addItem("receptionist");
					
	//Buttons			
				crtAccount = new JButton("Create Account");
				delAccount = new JButton("Delete Account");
				
	//Add components to inner panel		
				managePanel.add(chooseRole, "right");
				managePanel.add(rolesDropDown, "wrap");
				managePanel.add(crtAccount);
				managePanel.add(delAccount);
				
				

		

		
			
			
		
		
			

//Add components to main container, in order
		
		contentPanel.add(btnReturn,"wrap 30px");
		contentPanel.add(welcomeLabel, "wrap");
		contentPanel.add(managePanel, "grow, wrap");			//print label, wrap to the next row which will be 200 pixels lower

			
			
		setVisible(true);
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
	

}
