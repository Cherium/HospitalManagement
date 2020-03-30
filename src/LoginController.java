
/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Sajid C
 *
 */
public class LoginController {
	
	private LoginModel model;
	private LoginView view;
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public LoginController(LoginModel m, LoginView v)
	{
		this.model = m;
		this.view = v;
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
		
	}

	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{
		view.getBtnLogin().addActionListener(e -> verifyCredentials() );
		view.getBtnNewPatient().addActionListener(e -> newPatient() );
	}




	/**
	 * open a new Patient MVC on button press
	 * 
	 * @author Sajid C
	 */
	public void newPatient() {
		// TODO Auto-generated method stub
		model.openNewPatientDialog();
	}




	/**
	 * Get view values, verify them, and provide user a  debug message. Clear the inputs afterwards.
	 */
	public void verifyCredentials() {
		// TODO Auto-generated method stub
		model.setUsername(view.getUsernameField().getText());		//set entered username into model
		model.setPassword(view.getPasswordField().getPassword());	//set entered password into model
		view.clearInputs();											//clear textfields after pressing button
		
		//validate credentials against database; return error message if there is a problem.
		if(model.validate() == false)
		{
			//Show error message in GUI
			view.loginError(model.getErrorMessage() );;
		}
		
	}

	
	

}
