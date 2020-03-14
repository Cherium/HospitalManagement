//import javax.swing.JPasswordField;

public class CreateNewPatientController {
	
	private CreateNewPatientModel model;
	private CreateNewPatientView view;
	
	
	
	
	
	public CreateNewPatientController(CreateNewPatientModel model, CreateNewPatientView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView()
	{
		
	}
	
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		view.getOkButton().addActionListener(e -> parseEntry() );		//handle okay button being pressed
		
	}


	//get and store information entered in GUI
	public void parseEntry() {
		
//TODO could also change get methods in view to return .getText versions		
		// Get input from view and pass to model
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
	

	
}
