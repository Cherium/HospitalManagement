

/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Neil Mariano
 *
 */
public class AuthorityController {
	
	private AuthorityModel model;
	private AuthorityView view;
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
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

	
	/**
	 * initialize the elements that the GUI sees from the database 
	 * as soon as the view first opens for the user.
	 * 
	 * @ author Sajid C
	 */
	public void initView()
	{
		view.getWelcomeLabel().setText("Hello, "+ model.getName() );
		view.getDepNumLabel().setText("# of Departments: "+ model.getDepNum() );
		model.getStats();
		view.getPatientTotalLabel().setText("# of Registered Patients: "+model.getPatientNum());
		view.getDoctorTotalLabel().setText("# of Registered Doctors: "+model.getDoctorNum());
		view.getNurseTotalLabel().setText("# of Registered Nurses: "+model.getNurseNum());

	}
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{
		
	}

}