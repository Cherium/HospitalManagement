import javax.swing.DefaultComboBoxModel;
/**
 * MVC Model: Controller that deals with the Editing Account functionality, interacts with 
 * model and view to help edit a patient.
 * @author Jeremy Fan
 */

public class EditAccountController {
	
	// Interacts with the model and view 
	private EditAccountModel model;
	private EditAccountView view;
		
	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with 
	 * @param view view that controller interacts with 
	 */
	public EditAccountController(EditAccountModel model, EditAccountView view) {
		
		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * @author Sajid C, Jeremy F
	 */

	public void initView()
	{
		
	}
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "edit button".
	 * @author Sajid C, Jeremy F
	 */	

	public void initListeners() 
	{
		view.getEditButton().addActionListener(e -> parseEntry() );	
	}

	
	
	/**
	 * For model, set the username confirmation
	 * input for the frontend label 
	 * @author Sajid C, Jeremy F
	 */	
	public void parseEntry() {
		
		model.setUsername(view.getUsernameInput().getText());
		
		String returnMessage = model.editAccount();
		if(returnMessage.compareTo("That Account does not exist!") == 0)
		{
			view.showDialogToUser(returnMessage);
		}
		else
		{
			//view.showDialogToUser(returnMessage);
			view.setVisible(false);
		}
	}

}
