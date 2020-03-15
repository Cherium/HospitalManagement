
/**Handles all interaction between the associated model class and the view class.*/
public class NurseController {
	
	private NurseModel model;
	private NurseView view;
	
	
	
	
	//constructor initialized from the Login Model class
	public NurseController(NurseModel m, NurseView v)
	{
		//incoming objects from LoginController class
		this.model = m;
		this.view = v;
		
		//initialize the elements that the GUI sees from the database 
		//	as soon as the view first opens for the user
		initView();
		
		//initialize 'only' the listeners the GUI handles 'that
		//	need interaction with the model'
		initListeners();
	}

	
	
	
	
	//get information from model, and set labels, etc in view
	public void initView()
	{
		view.getLabel().setText("Hello "+ model.getName() );						//retrieve name for displaying
		
		view.getLabel2().setText("The Doctor you are working with is: "+ 			//retrieve doc name for displaying
					Main.dbase.get(
							model.getAssignedDocUsername()).getName()
					);
	}
	
	//initialize the listeners from the view class that need to interact with model
	//	and give functionality to these listeners once they 'hear' something.
	//	listeners that do not interact with the model should stay in the view class.
	public void initListeners() 
	{
		
	}

}
