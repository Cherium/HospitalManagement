import javax.swing.DefaultComboBoxModel;

public class EditAuthorityController {
	
	private EditAuthorityModel model;
	private EditAuthorityView view;
    private AuthorityModel authorityModel;
    private EditAuthorityPersonalInfoView personalInformationView;

        
    //constructor
	public EditAuthorityController(EditAuthorityModel model, EditAuthorityView view) {
		// TODO Auto-generated constructor stub
		
		this.model = model;
		this.view = view;
		initView();
		initListeners();
    }
    
	public EditAuthorityController(EditAuthorityModel model, EditAuthorityPersonalInfoView view2, AuthorityModel authorityModel) {
		
		this.model = model;
		this.personalInformationView = view2;
        this.authorityModel = authorityModel;
        initView2();
		initListeners2();
    }
    

    public void initView()
	{

	}
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView2()
	{
		//list of departments to set in combobox
		//personalInformationView.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
	}
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		view.getEditInformationButton().addActionListener(e -> parseEntryPersonalInfo() );
	}
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners2() 
	{
		personalInformationView.getConfirmButton().addActionListener(e -> parseEntryPersonalInfoConfirmation() );	
	}

	
	
    //handle the user entered input for schedule editing
    /*
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
    */

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
		

		if(returnMessage.compareTo("That Account does not exist!") == 0)
		{
			personalInformationView.showDialogToUser(returnMessage);
		} else if (returnMessage.compareTo("This Account is not an Authority!") == 0) {
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
