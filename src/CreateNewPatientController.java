//import javax.swing.JPasswordField;

/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Sajid C
 *
 */
public class CreateNewPatientController {
	
	private CreateNewPatientModel model;
	private CreateNewPatientView view;
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public CreateNewPatientController(CreateNewPatientModel model, CreateNewPatientView view) {

		this.model = model;
		this.view = view;
		initView();
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

	}
	
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle okay button being pressed
		
	}


	/**
	 * get and store information entered in GUI into the model
	 * 
	 * @author Sajid C
	 */
	public void parseEntry() {
		model.setName(view.getNameInput().getText());
		model.setAddress(view.getAddress().getText() );
		model.setPhoneNumber(view.getPhone().getText() );
		model.setEmail(view.getEmail().getText() );
		model.setUsername(view.getUsernameInput().getText());
		model.setPwd(view.getPasswordInput().getPassword());
		model.setPwd2(view.getPasswordInputConfirm().getPassword());
		
		model.setDob(view.getBirthday() );	//uuuu-M-d
		model.setBloodType(view.getBlood().getItemAt(view.getBlood().getSelectedIndex() ));
		model.setSex(view.getSex().getItemAt(view.getSex().getSelectedIndex() ));
		
		
		
		
		//try to update database with the user entered information,
		//	or return an error message if cannot
		String response = model.storeInDatabase();
		if( response.compareTo("Account successfully created!") == 0 )
		{
			//exit after showing success dialog
			view.showDialogToUser(response);
			view.setVisible(false);
		}
		else
		{
			//pass error message to show user
			view.showDialogToUser(response);
			
		}

		
	}
	

	
}
