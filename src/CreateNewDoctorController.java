import javax.swing.DefaultComboBoxModel;


/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Sajid C
 *
 */


public class CreateNewDoctorController {
	
	private CreateNewDoctorModel model;
	private CreateNewDoctorView view;
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public CreateNewDoctorController(CreateNewDoctorModel model, CreateNewDoctorView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	/**
	 * initialize the elements that the GUI sees from the database 
	 * as soon as the view first opens for the user.
	 * 
	 * @ author Sajid C
	 */
	public void initView()
	{
		//list of nurses to set in combobox
		view.getNurseDropDown().setModel( new DefaultComboBoxModel(model.getNurseList()) );
		
		//list of departments to set in combobox
		view.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );

	}
	
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle create button pressed
		view.getAddNurse().addActionListener(e -> addNurseToList() );		//handle 'add' button pressed
		
		
		
	}

	/**
	 * add nurse to GUI Textbox if it already isn't in the Textbox
	 * 
	 * @author Sajid C
	 * 
	 */
	public void addNurseToList() 
	{
		//retrieve chosen nurse from the GUI ComboBox
		String nurseChosen = view.getNurseDropDown().getItemAt(view.getNurseDropDown().getSelectedIndex() );
		
		//if the nurse has not been previously selected
		if(!model.getNurseListToAdd().contains(nurseChosen))
		{
			//set it in the model for use when 'Create account' is clicked
			model.getNurseListToAdd().add(nurseChosen);
			
			//Print to textbox in GUI
			view.getBox().append(nurseChosen+"\n");
		}
		
		
	}

	/**
	 * get and store information entered in GUI
	 * 
	 * @author Sajid C
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
			view.setVisible(false);
			view.showDialogToUser(response);

		}
		else
		{
			//pass error message to show user
			view.showDialogToUser(response);
			
		}

		
	}
	

	
}
