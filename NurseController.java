
public class NurseController {
	
	private NurseModel model;
	private NurseView view;
	
	
	
	
	//constructor
	public NurseController(NurseModel m, NurseView v)
	{
		//incoming objects from LoginController class
		this.model = m;
		this.view = v;
		
		//initialize the elements the GUI sees from the database 
		//	as soon as the view first opens
		initView();
		
		//initialize the listeners the GUI handles that
		//	need interaction with the model
		initListeners();
	}

	
	//get information from model, and set labels, etc in view
	public void initView()
	{
		view.getLabel().setText("Hello "+ model.getName() );
		view.getLabel2().setText("The Doctor you are working with is: "+ model.getAssignedDoc().getName() );
	}
	
	//initialize the listeners from the view that need to interact with model
	//	and five functionality to this listeners once they 'hear' something
	public void initListeners() 
	{
		view.getBtn().addActionListener(e -> view.setVisible(false));
	}

}
