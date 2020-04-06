import javax.swing.DefaultComboBoxModel;

//import javax.swing.JPasswordField;

public class CreateNewReceptionistController {
	
	private CreateNewReceptionistModel model;
	private CreateNewReceptionistView view;
	
	
	
	
	
	
	public CreateNewReceptionistController(CreateNewReceptionistModel model, CreateNewReceptionistView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	
	public void initView()
	{
		//list of nurses to set in combobox
		view.getDoctorDropDown().setModel( new DefaultComboBoxModel(model.getDoctorList()) );
		
		//list of departments to set in combobox
		view.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
	}
	
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle create button pressed
		view.getAddDoctor().addActionListener(e -> addAssignedDoctor() );		//handle 'add' button pressed
		
		
		
	}

	

	
	
	
    //add doctor to GUI Textbox if it already isn't in the Textbox
    //note that a nurse can only be assigned 1 doctor
	public void addAssignedDoctor() 
	{
		//retrieve chosen doctor from the GUI ComboBox
		String doctorChosen = view.getDoctorDropDown().getItemAt(view.getDoctorDropDown().getSelectedIndex() );
		

		//set it in the model for use when 'Create account' is clicked
		model.setDoctorToAdd(doctorChosen);
			
		
		
		
	}

	
	//get and store information entered in GUI
	public void parseEntry() 
	{
		
	// Get input from view and pass to model
		
		//get the department chosen and set it in model
		model.setDepartment(view.getDepartmentDropDown().getItemAt(
				view.getDepartmentDropDown().getSelectedIndex()) );
		
//TODO could also change get methods in view to return .getText versions
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
