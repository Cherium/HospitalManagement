
public class ReceptionistController {

	private ReceptionistModel model;
	private ReceptionistView view;
	
	
	
	
	
	public ReceptionistController(ReceptionistModel model, ReceptionistView view) {

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

		
	}
}
