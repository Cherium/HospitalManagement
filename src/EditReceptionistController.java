import javax.swing.DefaultComboBoxModel;

public class EditReceptionistController {
	
	private EditReceptionistModel model;
	private EditReceptionistView view;
	private EditReceptionistPersonalInfoView personalInformationView;
		
	//constructor
	public EditReceptionistController(EditReceptionistModel model, EditReceptionistView view) {
		// TODO Auto-generated constructor stub
		
		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	public EditReceptionistController(EditReceptionistModel model, EditReceptionistPersonalInfoView view2) {
		
		this.model = model;
		this.personalInformationView = view2;
		initView();
		initListeners2();
	}
	
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView()
	{
		
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

		if(returnMessage.compareTo("That Account does not exist!") == 0)
		{
			personalInformationView.showDialogToUser(returnMessage);
		} else if (returnMessage.compareTo("This Account is not a Receptionist!") == 0) {
			personalInformationView.showDialogToUser(returnMessage);
		} else {
			String tempName = personalInformationView.getNameInput().getText();
			char[] tempPass = personalInformationView.getPassInput().getText().toCharArray();
			UserSuperClass user = Main.dbase.get(model.getUsername());
			System.out.println(user.getUsername() + user.getName() + user.getPassword().toString());							
			user.setName(tempName);
			user.setPassword(tempPass);		
            personalInformationView.setVisible(false);
            personalInformationView.showDialogToUser(returnMessage);
			System.out.println(user.getUsername() + user.getName() + user.getPassword().toString());
			

		}
		
	}

}
