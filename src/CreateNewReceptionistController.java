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

	}
	
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
	
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle create button pressed
	}


	
	//get and store information entered in GUI
	public void parseEntry() 
	{
		
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
