import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class EditAuthorityView extends JDialog{
	/* Variables include one contentPane to put in content, 
	* username inputs, 
	* two buttons for creating, cancelling the creation process
	*/
	JPanel contentPanel;
	
	
	private JTextField usernameInput;
	private JButton editInformationButton;
	private JButton cancelButton;

	
	
	
	
	
	
	
	/**
	 * constructor
	 * 
	 * @author Jeremy Fan, Sajid C
	 * @param title title of the popup box
	 */
	public EditAuthorityView(String title) 
	{
		//sets frame containers attributes
		setTitle(title);
		setSize(370, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		initializeGUI();
		
	}

	
	/**initialize the panels and components that will go inside the frame
	 * @author Sajid C, Jeremy F
	 */	
	public void initializeGUI() {
		// TODO Auto-generated method stub
		
//Main panel background
		JPanel Panel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
			Panel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(Panel, BorderLayout.CENTER);				//add the panel as the container for the frame
		
		
//Inner Panels	
	contentPanel = new JPanel(new MigLayout("wrap 2", "[] 16 []") );		//miglayout with 2 columns, 16px space between them
		
	//Labels
		JLabel username = new JLabel("Username:");
		
	//TextFields
		usernameInput = new JTextField();
		
	//Add components to panel in order
		contentPanel.add(username, "right");
		contentPanel.add(usernameInput, "width 80:160:300 ,wrap");	//set width of the component, wrap to next row for the next component to be added
		
		
	JPanel buttonPanel = new JPanel(new MigLayout("wrap 2") );
		
	//Buttons
		editInformationButton = new JButton("Edit Personal Info");

		cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(e -> setVisible(false) );
			
	//Add components to panel in order	
		buttonPanel.add(editInformationButton, "gapleft 100");		//in the current column, place the component with a gap of 100px from the left
		buttonPanel.add(editInformationButton, "");					//place the next component in the third column
			
		
		
		
//add inner panels to outer panel
		Panel.add(contentPanel, "wrap");				//add inner panel to main panel, wrap to next row to add the next panel to it
		Panel.add(buttonPanel);							//add this inner panel below the previous panel
		
		
		
		setVisible(true);
	}

	
	
	/**
	 * pop up a message-dialog box with a message passed in 
	 * @author Jenny Z
	 * @param message message to show user
	 */
	
	public void showDialogToUser(String message)
	{
		JOptionPane.showMessageDialog(contentPanel, message);
	}
	
	
	
	
	
/**Getter and Setter Methods*/	
	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JTextField getUsernameInput() {
		return usernameInput;
	}

	public void setUsernameInput(JTextField usernameInput) {
		this.usernameInput = usernameInput;
	}

	public JButton getEditInformationButton() {
		return editInformationButton;
	}

	public void setEditInformationButton(JButton editInformationButton) {
		this.editInformationButton = editInformationButton;
	}
	

}
