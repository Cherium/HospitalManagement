
/**Handles all interaction between the associated model class and the view class.*/
public class AuthorityController {
	
	private AuthorityModel model;
	private AuthorityView view;
	
	
	
	
	//constructor initialized from the Login controller class
	public AuthorityController(AuthorityModel m, AuthorityView v)
	{
		//incoming objects from LoginController class
		this.model = m;
		this.view = v;
		
		//initialize the elements that the GUI sees from the database 
		//	as soon as the view first opens for the user
		initView();
		
		//initialize only the listeners the GUI handles 'that
		//	need interaction with the model'
		initListeners();
	}

	
	//get information from model, and set labels, etc in view
	public void initView()
	{
		
	}
	
	//initialize the listeners from the view class that need to interact with model
	//	and give functionality to these listeners once they 'hear' something
	public void initListeners() 
	{
		
	}

}
