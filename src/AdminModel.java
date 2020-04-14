
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class
 * @author Sajid C
 *
 */
/**
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

	public AdminModel(String username, char[] password, String name, LocalDateTime[] avail) {
	
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
			new CreateNewNurseController(new CreateNewNurseModel(), new CreateNewNurseView("Create a New Nurse"));
		}
		else if(selectedItem.compareTo("Admin") == 0)
		{
			new CreateNewAdminController(new CreateNewAdminModel(), new CreateNewAdminView("Create a New Admin"));
		}
		else if(selectedItem.compareTo("Hospital Authority") == 0)
		{
			new CreateAuthorityController(new CreateAuthorityModel(), new CreateAuthorityView("Create a New Authority"));
		}
		else if(selectedItem.compareTo("Receptionist") == 0)
		{
			new CreateNewReceptionistController(new CreateNewReceptionistModel(), new CreateNewReceptionistView("Create a New Receptionist"));
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

	public void editAccount(String selectedItem) {
		
		if(selectedItem.compareTo("Patient") == 0)
		{
			new EditAccountController(new EditAccountModel(), new EditAccountView("Edit an Account"));
		}
		else if(selectedItem.compareTo("Doctor") == 0)
		{
			new EditDoctorController(new EditDoctorModel(), new EditDoctorView("Edit an Account"));
		}
		else if(selectedItem.compareTo("Nurse") == 0)
		{
			new EditNurseController(new EditNurseModel(), new EditNurseView("Edit an Account"));
		}
		else if(selectedItem.compareTo("Admin") == 0)
		{
			new EditAdminController(new EditAdminModel(), new EditAdminView("Edit an Account"));
		}
		else if(selectedItem.compareTo("Hospital Authority") == 0)
		{
			new EditAuthorityController(new EditAuthorityModel(), new EditAuthorityView("Edit an Account"));
		}
		else if(selectedItem.compareTo("Receptionist") == 0)
		{
			new EditReceptionistController(new EditReceptionistModel(), new EditReceptionistView("Edit an Account"));
		}
		
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


	/**
	 * prints out values for database export
	 * @author Muhammad R
	 * @return the string stored in dbase.txt
	 */
	public String toStringDbase() {
		StringBuilder bob = new StringBuilder();
		bob.append(getRole());
		bob.append("\t");
		bob.append(getUsername());
		bob.append("\t");
		bob.append(getPassword());
		bob.append("\t");
		bob.append(getName());
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");	
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");		
		bob.append("null");

		

		
		return bob.toString();
		
	}
	
	/**
	 * prints out values for database export
	 * @author Muhammad R
	 * @return list of availability to store in Availability.txt
	 */
	public String toStringAvailability() {
		StringBuilder bob = new StringBuilder();
		
		for(int i= 0; i<super.getAvailability().length; i ++) {
			bob.append(super.getAvailability()[i]);
			bob.append("\n");
			
		}
		if(bob.length() > 0)
			bob.deleteCharAt(bob.length()-1);
		return bob.toString();
	}

	


}
