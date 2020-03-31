import javax.swing.DefaultComboBoxModel;

public class EditDoctorController {
	
	private EditDoctorModel model;
	private EditDoctorView view;
	private EditDoctorPersonalInfoView personalInformationView;
	private DoctorModel doctorModel;
		
	//constructor
	public EditDoctorController(EditDoctorModel model, EditDoctorView view) {
		// TODO Auto-generated constructor stub
		
		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	public EditDoctorController(EditDoctorModel model, EditDoctorPersonalInfoView view2, DoctorModel doctorModel) {
		
		this.model = model;
		this.personalInformationView = view2;
		this.doctorModel = doctorModel;
		initView2();
		initListeners2();
	}
	
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView()
	{

	}
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView2()
	{
		//list of departments to set in combobox
		personalInformationView.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
	}
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		view.getEditScheduleButton().addActionListener(e -> parseEntry() );
		view.getEditInformationButton().addActionListener(e -> parseEntryPersonalInfo() );
	}


	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners2() 
	{
		personalInformationView.getConfirmButton().addActionListener(e -> parseEntryPersonalInfoConfirmation() );	
	}

	
	
	//handle the user entered input for schedule editing
	public void parseEntry() {
		
		model.setUsername(view.getUsernameInput().getText());

		String returnMessage = model.editSchedule();
		if(returnMessage.compareTo("That Account does not exist!") == 0)
		{
			view.showDialogToUser(returnMessage);
		}
		else
		{
			//view.showDialogToUser(returnMessage);
			view.setVisible(false);
		}
	}

	//handle the user entered input for editing personal information
	public void parseEntryPersonalInfo() {
	
		model.setUsername(view.getUsernameInput().getText());

		String returnMessage = model.editPersonalInfo();
		if(returnMessage.compareTo("That Account does not exist!") == 0)
		{
			view.showDialogToUser(returnMessage);
		}
		else
		{
			//view.showDialogToUser(returnMessage);
			view.setVisible(false);
		}
	}

	public void parseEntryPersonalInfoConfirmation() {
		//the below happens once "Confirmed" button is clicked

		model.setUsername(personalInformationView.getUsernameInput().getText());
		String returnMessage = model.checkPersonalInfo();

		//get the department chosen and set it in model
		model.setDepartment(personalInformationView.getDepartmentDropDown().getItemAt(
			personalInformationView.getDepartmentDropDown().getSelectedIndex()) );
		String returnDepartmentMessage = model.checkDoctorDepartment();

		if(returnMessage.compareTo("That Account does not exist!") == 0)
		{
			personalInformationView.showDialogToUser(returnMessage);
		} else if (returnMessage.compareTo("This Account is not a Doctor!") == 0) {
			personalInformationView.showDialogToUser(returnMessage);
		} else {
			String tempName = personalInformationView.getNameInput().getText();
			char[] tempPass = personalInformationView.getPassInput().getText().toCharArray();

			if(returnDepartmentMessage.compareTo("No department selected!") == 0) {
				personalInformationView.showDialogToUser(returnMessage);
			}
			else {
				String tempDepartment = returnDepartmentMessage;
				UserSuperClass user = Main.dbase.get(model.getUsername());
				System.out.println(user.getUsername() + user.getName() + user.getPassword().toString());							
				user.setName(tempName);
				user.setPassword(tempPass);
				this.doctorModel = (DoctorModel) user;
				doctorModel.setDepartment(tempDepartment);
				personalInformationView.setVisible(false);
				System.out.println(user.getUsername() + user.getName() + user.getPassword().toString());
			}
		}
		
	}

}
