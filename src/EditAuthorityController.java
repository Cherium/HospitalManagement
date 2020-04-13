import javax.swing.DefaultComboBoxModel;
/**
 * MVC Model: Controller that deals with the Edit Authority functionality, interacts with 
 * model and view to help edit authority.
 * @author Jeremy Fan
 */

public class EditAuthorityController {
	
	// Interacts with the model and view 
	private EditAuthorityModel model;
	private EditAuthorityView view;
    private AuthorityModel authorityModel;
    private EditAuthorityPersonalInfoView personalInformationView;

        
	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with 
	 * @param view view that controller interacts with 
	 */	
	public EditAuthorityController(EditAuthorityModel model, EditAuthorityView view) {
		
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
	 * @param view2 a second view that controller interacts with 
	 */

	public EditAuthorityController(EditAuthorityModel model, EditAuthorityPersonalInfoView view2, AuthorityModel authorityModel) {
		
		this.model = model;
		this.personalInformationView = view2;
        this.authorityModel = authorityModel;
        initView2();
		initListeners2();
    }
    
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "create button".
	 * @author Sajid C, Jeremy F
	 */

    public void initView()
	{

	}
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "create button".
	 * @author Sajid C, Jeremy F
	 */

	public void initView2()
	{
		//list of departments to set in combobox
		//personalInformationView.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
	}
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "get edit information".
	 * @author Sajid C, Jeremy F
	 */

	public void initListeners() 
	{
		view.getEditInformationButton().addActionListener(e -> parseEntryPersonalInfo() );
	}
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * In this case, add an action to the "confirm information".
	 * @author Sajid C, Jeremy F
	 */

	public void initListeners2() 
	{
		personalInformationView.getConfirmButton().addActionListener(e -> parseEntryPersonalInfoConfirmation() );	
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
		

		if(returnMessage.compareTo("That Account does not exist!") == 0)
		{
			personalInformationView.showDialogToUser(returnMessage);
		} else if (returnMessage.compareTo("This Account is not an Admin!") == 0) {
			personalInformationView.showDialogToUser(returnMessage);
		} else {
			String tempName = personalInformationView.getNameInput().getText();
			char[] tempPass = personalInformationView.getPassInput().getText().toCharArray();
			String tempPassString = personalInformationView.getPassInput().getText();
			
			
			if(tempPass.length < 4 && tempPassString.compareTo("") != 0)
			{
				personalInformationView.showDialogToUser("Password must be longer than 4 characters!");
			}
				
			else {
				UserSuperClass user = Main.dbase.get(model.getUsername());
				if (tempName.compareTo("") != 0) {
					user.setName(tempName);
					returnMessage = returnMessage + "\n" + "Name changed!"; 
				} 
				if (tempPassString.compareTo("") != 0) {
					user.setPassword(tempPass);
					returnMessage = returnMessage + "\n" + "Password changed!"; 
				} 
				personalInformationView.setVisible(false);
				personalInformationView.showDialogToUser(returnMessage);
			}	
		
		}
		
	}

}
