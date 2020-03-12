
public class LoginController {
	
	private LoginModel model;
	private LoginView view;
	
	
	
	
	//constructor
	public LoginController(LoginModel m, LoginView v)
	{
		this.model = m;
		this.view = v;
		initListeners();
	}




	public void initListeners() 
	{
		view.getBtnLogin().addActionListener(e -> verifyCredentials() );
		view.getBtnNewPatient().addActionListener(e -> newPatient() );
	}




	public void newPatient() {
		// TODO Auto-generated method stub
		
	}




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
