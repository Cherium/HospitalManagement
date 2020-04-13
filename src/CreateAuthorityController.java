/**
 * MVC Model: Controller that deals with the Create Authority functionality, interacts with 
 * model and view to help create an authority.
 * @author Jeremy Fan
 */

public class CreateAuthorityController {

	// Interacts with the model and view 
	private CreateAuthorityModel model;
	private CreateAuthorityView view;
	
	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with 
	 * @param view view that controller interacts with 
	 */
	public CreateAuthorityController(CreateAuthorityModel model, CreateAuthorityView view) {

		this.model = model;
		this.view = view;
		initListeners();
	}
	
	

	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "create button".
	 * @author Sajid C, Jeremy F
	 */
	public void initListeners() 
	{
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle okay button being pressed
	}

	
	/**
	 * For model, set the the name, username, password, and password confirmation based on the 
	 * input for the frontend label 
	 * @author Sajid C, Jeremy F
	 */
	public void parseEntry() {
		
		model.setName(view.getNameInput().getText());
		model.setUsername(view.getUsernameInput().getText());
		model.setPwd(view.getPasswordInput().getPassword());
		model.setPwd2(view.getPasswordInputConfirm().getPassword());
		
		//try to update database with the user entered information,
		//or return an error message if cannot
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
