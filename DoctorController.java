

public class DoctorController {
	
	private DoctorModel model;
	private DoctorView view;
	
	
	
	public DoctorController(DoctorModel m, DoctorView v)
	{
		this.model = m;
		this.view = v;
		initView();
		initListeners();
	}

	//initialize the view components from grabbing them from the model and sending them to the view
	public void initView()
	{
		//set the Labels for view
		view.getDeptLabel().setText(model.getDepartment()+": ");
		view.getNameLabel().setText(model.getName()+", M.D.");
		
		System.out.println(model.getNurses().get(0).getName());
		for(NurseModel n: model.getNurses())
		{
			
			view.getNurseComboBox().addItem(n.getName() );
		}
		
	}
	
	public void initListeners() 
	{
		
		//does not seem to be any listeners in the view that require interaction with the model
		
	}

	//unused
	public void returnToPrevious() {
		// TODO Auto-generated method stub
		view.setVisibility(false);
	}

}
