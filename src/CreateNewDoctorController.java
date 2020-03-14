import javax.swing.DefaultComboBoxModel;

//import javax.swing.JPasswordField;

public class CreateNewDoctorController {
	
	private CreateNewDoctorModel model;
	private CreateNewDoctorView view;
	
	
	
	
	
	
	public CreateNewDoctorController(CreateNewDoctorModel model, CreateNewDoctorView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	
	public void initView()
	{
		//list of nurses to set in combobox
		view.getNurseDropDown().setModel( new DefaultComboBoxModel(model.getNurseList()) );
		
		//list of departments to set in combobox
		view.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
	}
	
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle create button pressed
		view.getAddNurse().addActionListener(e -> addNurseToList() );		//handle 'add' button pressed
		view.getDepartmentDropDown().addActionListener(e -> 
			{if (view.getDepartmentDropDown().getSelectedItem().equals("ER")) {
				view.getNurseDropDown().setEnabled(false);
				view.getAddNurse().setEnabled(false);			
			} else {
				view.getNurseDropDown().setEnabled(true);
				view.getAddNurse().setEnabled(true);			
			}});
		
	}


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
