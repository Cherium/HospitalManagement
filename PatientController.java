
public class PatientController {
	
	private PatientModel model;
	private PatientView view;
	
	
	
	
	//constructor
	public PatientController(PatientModel m, PatientView v)
	{
		this.model = m;
		this.view = v;
		initView();
		//view.setVisible(true);
		initListeners();
	}

	public void initView()
	{
		view.getLabel().setText("Hello "+ model.getName() );
	}
	
	public void initListeners() 
	{
		view.getBtn().addActionListener(e -> view.setVisible(false));
	}

}
