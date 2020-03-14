
public class PatientController {
	
	private PatientModel model;
	private PatientView view;
	
	
	
	
	//constructor
	public PatientController(PatientModel m, PatientView v)
	{
		this.model = m;
		this.view = v;
		initView();
		initListeners();
	}

	
	
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView()
	{
		view.getLabel().setText("Hello "+ model.getName() );
	}
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		
	}

}
