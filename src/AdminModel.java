


/**
 * handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class
 * @author Sajid C
 *
 */
public class AdminModel extends UserSuperClass {

	
	
	
	
	
	/**
	 * constructor
	 * 
	 * @author Sajid C
	 * 
	 * @param username users username value from database
	 * @param password users password value from database
	 * @param name users name value form database
	 * @param avail users availability value form database
	 */
	public AdminModel(String username, char[] password, String name, String[] avail) {

		super(name, username, password);
		setRole("admin");
		
		this.availability = arrayToLDTArray(avail);
	}







	/**
	 * bring up the dialog box to handle New Account creation for various roles
	 * 
	 * @param selectedItem user selected role from dropDown menu
	 * 
	 * @author Sajid C
	 */
	public void createAccount(String selectedItem) {
		
		//open a new view based on user entered values
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

	
	
	/**
	 * bring up the dialog to handle Account Deletion
	 * 
	 * @author Sajid C
	 */
	public void deleteAccount() {
		
		new DeleteAccountController(new DeleteAccountModel(), new DeleteAccountView("Delete an Account"));
		
	}





	/**
	 * 
	 * add the department to the DatabaseModel list of departments, send a debug message back
	 * 
	 * @author Sajid C
	 * 
	 * @param toAdd user entered department
	 * @return success/fail message for user to view
	 */
	public String addDept(String toAdd) {
		
		//convert string to have first letter capitalized
		String temp = toAdd.substring(0,1).toUpperCase() + toAdd.substring(1).toLowerCase();
		
		//check if department already exists
		if(Main.dbaseClass.getDepartmentList().contains(temp) )
		{
			return "Department already exists!";
		}

		//else add department
		Main.dbaseClass.getDepartmentList().add(temp);
		return "Department created!";

	}

}
