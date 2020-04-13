import javax.swing.DefaultComboBoxModel;
/**
 * MVC Model: Controller that deals with the edit doctor functionality, interacts with 
 * model and view to help edit doctor.
 * @author Jeremy Fan
 */

public class EditDoctorController {
	
	// Interacts with the model, personal information view, and view 
	private EditDoctorModel model;
	private EditDoctorView view;
	private EditDoctorPersonalInfoView personalInformationView;
	private DoctorModel doctorModel;
		
	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with 
	 * @param view view that controller interacts with 
	 */

	public EditDoctorController(EditDoctorModel model, EditDoctorView view) {
		
		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}

	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with 
	 * @param view view that controller interacts with 
	 * @param doctorModel doctorModel that the edit code will interact with
	 */
	
	public EditDoctorController(EditDoctorModel model, EditDoctorPersonalInfoView view2, DoctorModel doctorModel) {
		
		this.model = model;
		this.personalInformationView = view2;
		this.doctorModel = doctorModel;
		initView2();
		initListeners2();
	}
	
	
	/* initialize the elements that the GUI sees from the database 
	as soon as the view first opens for the user
	*/
	public void initView()
	{

	}
	
	/**
	 * initialize the view from the view class that need to interact with model
	 * In this case, add an action to the department dropdown box
	 * @author Sajid C, Jeremy F
	 */
	public void initView2()
	{
		//list of departments to set in combobox
		personalInformationView.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
	}
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "edit schedule" and "edit information" button.
	 * @author Sajid C, Jeremy F
	 */
	public void initListeners() 
	{
		view.getEditScheduleButton().addActionListener(e -> parseEntry() );
		view.getEditInformationButton().addActionListener(e -> parseEntryPersonalInfo() );
	}


	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "confirm button".
	 * @author Sajid C, Jeremy F
	 */

	public void initListeners2() 
	{
		personalInformationView.getConfirmButton().addActionListener(e -> parseEntryPersonalInfoConfirmation() );	
	}

	
	
	/**
	 * For model, set the the name, username, password, and password confirmation based on the 
	 * input for the frontend label 
	 * @author Sajid C, Jeremy F
	 */
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

	/**
	 * For model, set the username confirmation
	 * input for the frontend label 
	 * @author Sajid C, Jeremy F
	 */		


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

	/**
	 * For model, set the name and password confirmation based on the 
	 * input for the frontend label 
	 * @author Jeremy F
	 */	

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
				user.setName(tempName);
				user.setPassword(tempPass);
				this.doctorModel = (DoctorModel) user;
				doctorModel.setDepartment(tempDepartment);
				personalInformationView.setVisible(false);
				personalInformationView.showDialogToUser(returnMessage);
			}
		}
		
	}

}
