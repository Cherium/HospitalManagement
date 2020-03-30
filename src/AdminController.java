
/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Sajid C
 *
 */
public class AdminController {

	private AdminModel model;
	private AdminView view;
	
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public AdminController(AdminModel model, AdminView view) {

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
		view.getWelcomeLabel().setText("Hello, "+ model.getName() );
		

	}
	
	
	

	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{
		//listen for a button press and
		//		send the role selected in the list of the view class to the model class for processing
		view.getCrtAccount().addActionListener(e -> model.createAccount( 
				view.getRolesDropDown().getItemAt( view.getRolesDropDown().getSelectedIndex() )				//handle 'create account button pressed'
				));
		
		
		view.getDelAccount().addActionListener(e -> model.deleteAccount() );								//handle 'delete account' button pressed
		
		view.getEditAccount().addActionListener(e -> model.editAccount( 
				view.getRolesDropDown().getItemAt( view.getRolesDropDown().getSelectedIndex() )				//handle 'edit account button pressed'
				));
		
		view.getCrtDepartment().addActionListener(e -> addDepartment() );
		
	}
	
	
	

	/**
	 * 
	 * Try to create a new department in the internal database
	 * and provide a success/fail dialog to user
	 * 
	 * @author Sajid C
	 * 
	 */
	public void addDepartment()
	{
		String response;
		
		//if textbox isn't empty- handles Excelption thrown if textbox is empty.
		if(!view.getCreateDeptText().getText().isEmpty() )
		{
			//create the department, send message back to User
			response = model.addDept( view.getCreateDeptText().getText() );
			view.showDialogToUser(response);
		}
		else
		{
			view.showDialogToUser("Enter a Department Name!");
		}
		
	}
}
