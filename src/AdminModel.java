

/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class AdminModel extends UserModel {

	
	
	
	
	
	//constructor
	public AdminModel(String username, char[] password, String name) {

		super(name, username, password);
		setRole("admin");
	}






	//bring up the dialog box to handle New Account creation for various oles
	public void createAccount(String selectedItem) {
		
		if(selectedItem.compareTo("Patient") == 0)
		{
			new CreateNewPatientController(new CreateNewPatientModel(), new CreateNewPatientView("Create a New Patient"));
		}
		else if(selectedItem.compareTo("Doctor") == 0)
		{
			new CreateNewDoctorController(new CreateNewDoctorModel(), new CreateNewDoctorView("Create a New Doctor"));
		}
		else if(selectedItem.compareTo("Nurse") == 0)
		{
			//TODO
		}
		else if(selectedItem.compareTo("Admin") == 0)
		{
			//TODO
		}
		else if(selectedItem.compareTo("Hospital Authority") == 0)
		{
			//TODO
		}
		else if(selectedItem.compareTo("Receptionist") == 0)
		{
			//TODO
		}
		
	}

	
	//bring up the dialog to handle Account Deletion
	public void deleteAccount() {
		
		new DeleteAccountController(new DeleteAccountModel(), new DeleteAccountView("Delete an Account"));
		
	}

}
