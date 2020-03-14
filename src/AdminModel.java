

/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class AdminModel extends UserModel {

	
	
	
	
	
	//constructor
	public AdminModel(String name, String username, char[] password) {
		super(name, username, password);
		setRole("admin");
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	//bring up the dialog box to handle New Account creation for various oles
	public void createAccount(String selectedItem) {
		
		if(selectedItem.compareTo("patient") == 0)
		{
			new CreateNewPatientController(new CreateNewPatientModel(), new CreateNewPatientView("Create a New Patient"));
		}
		else if(selectedItem.compareTo("doctor") == 0)
		{
			new CreateNewDoctorController(new CreateNewDoctorModel(), new CreateNewDoctorView("Create a New Doctor"));
		}
		else if(selectedItem.compareTo("nurse") == 0)
		{
			//TODO
		}
		else if(selectedItem.compareTo("admin") == 0)
		{
			//TODO
		}
		else if(selectedItem.compareTo("hospital authority") == 0)
		{
			//TODO
		}
		else if(selectedItem.compareTo("receptionist") == 0)
		{
			//TODO
		}
		
	}

	
	//bring up the dialog to handle Account Deletion
	public void deleteAccount() {
		
		new DeleteAccountController(new DeleteAccountModel(), new DeleteAccountView("Delete an Account"));
		
	}

}
