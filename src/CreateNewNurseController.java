import javax.swing.DefaultComboBoxModel;

/**
 * MVC Model: Controller that deals with the Create Nurse functionality, interacts with 
 * model and view to help create an nurse.
 * @author Jeremy Fan
 */

public class CreateNewNurseController {

	// Interacts with the model and view 
	private CreateNewNurseModel model;
	private CreateNewNurseView view;
	
	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with 
	 * @param view view that controller interacts with 
	 */

	public CreateNewNurseController(CreateNewNurseModel model, CreateNewNurseView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	/**
	 * from view, get the doctor drop down and create two combo boxes that have a list of the doctors
	 * and the departments
	 * @author Jeremy F
	 */
	
	public void initView()
	{
		//list of nurses to set in combobox
		view.getDoctorDropDown().setModel( new DefaultComboBoxModel(model.getDoctorList()) );
		
		//list of departments to set in combobox
		view.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
	}
	
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "create button" and "add doctor" button.
	 * @author Sajid C, Jeremy F
	 */
	public void initListeners() 
	{
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle create button pressed
		view.getAddDoctor().addActionListener(e -> addAssignedDoctor() );		//handle 'add' button pressed
		
		
		
	}

	

	
	
	/**
	 * add doctor to GUI Textbox if it already isn't in the Textbox. Note that a 
	 * nurse can only be assigned 1 doctor
	 * @author Jeremy F
	 */
    
	public void addAssignedDoctor() 
	{
		//retrieve chosen doctor from the GUI ComboBox
		String doctorChosen = view.getDoctorDropDown().getItemAt(view.getDoctorDropDown().getSelectedIndex() );
		
		//set it in the model for use when 'Create account' is clicked
		model.setDoctorToAdd(doctorChosen);

	}

	
	/**
	 * For model, set the the name, username, password, and password confirmation based on the 
	 * input for the frontend label 
	 * @author Sajid C, Jeremy F
	 */	
	public void parseEntry() 
	{
		
	// Get input from view and pass to model
		
		//get the department chosen and set it in model
		model.setDepartment(view.getDepartmentDropDown().getItemAt(
				view.getDepartmentDropDown().getSelectedIndex()) );		
		model.setName(view.getNameInput().getText());
		model.setUsername(view.getUsernameInput().getText());
		model.setPwd(view.getPasswordInput().getPassword());
		model.setPwd2(view.getPasswordInputConfirm().getPassword());
		
		
		
		//try to update database with the user entered information,
		//	or return an error message if cannot
		String response = model.storeInDatabase();
		if( response.compareTo("Account successfully created!") == 0 )
		{
			//exit dialog after showing success dialog
			view.showDialogToUser(response);
			view.setVisible(false);
		}
		else
		{
			//pass error message to show user
			view.showDialogToUser(response);
			
		}

		
	}
	

	
}
