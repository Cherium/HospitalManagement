//import javax.swing.JPasswordField;

public class CreateNewPatientController {
	
	private CreateNewPatientModel model;
	private CreateNewPatientView view;
	
	
	
	
	
	public CreateNewPatientController(CreateNewPatientModel model, CreateNewPatientView view) {

		this.model = model;
		this.view = view;
		initListeners();
	}
	
	public void initListeners() 
	{
		view.getOkButton().addActionListener(e -> parseEntry() );
		view.getCancelButton().addActionListener(e -> parseCancel() );
	}


	//get and store information entered in GUI
	public void parseEntry() {
		
		// Get input from view and pass to model
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
			//exit after showing success dialog
			view.showDialogToUser(response);
			view.setVisible(false);
		}
		else
		{
			//pass error message to show user
			view.showDialogToUser(response);
			
		}

		
	}
	
	public void parseCancel() {
		// clear inputs and close dialog box
		view.clearInputs();
		view.setVisible(false);
	}
	
}
