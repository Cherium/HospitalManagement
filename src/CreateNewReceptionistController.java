import javax.swing.DefaultComboBoxModel;

/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Jeremy F
 */

public class CreateNewReceptionistController {
	
	// Interacts with the model and view 
	private CreateNewReceptionistModel model;
	private CreateNewReceptionistView view;
	
	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with 
	 * @param view view that controller interacts with 
	 */

	public CreateNewReceptionistController(CreateNewReceptionistModel model, CreateNewReceptionistView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	public void initView()
	{

	}
	
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "create button".
	 * @author Sajid C, Jeremy F
	 */

	public void initListeners() 
	{
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle create button pressed
	}


	
	/**
	 * For model, set the the name, username, password, and password confirmation based on the 
	 * input for the frontend label 
	 * @author Sajid C, Jeremy F
	 */	

	public void parseEntry() 
	{
		
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
