
public class AdminController {

	private AdminModel model;
	private AdminView view;
	
	
	
	
	
	public AdminController(AdminModel model, AdminView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView()
	{
		view.getWelcomeLabel().setText("Hello, "+ model.getName() );
	}
	
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		//listen for a button press and
		//		send the role selected in the list of the view class to the model class for processing
		view.getCrtAccount().addActionListener(e -> model.createAccount( 
				view.getRolesDropDown().getItemAt( view.getRolesDropDown().getSelectedIndex() )				//handle 'create account button pressed'
				));
		
		
		view.getDelAccount().addActionListener(e -> model.deleteAccount() );								//handle 'delete account' button pressed
		
	}
}
