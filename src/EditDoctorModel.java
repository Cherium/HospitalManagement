import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class EditDoctorModel {
	
	private String username;
	private UserSuperClass user;
	private DoctorModel doctorModel;
	private String department;

	
	
	public String editSchedule()
	{
		//check if the username is NOT on file
		if(!Main.dbase.containsKey(username) )
		{
			return "That Account does not exist!";
		}
		else { 
		//otherwise edit the account in the HashMap
		this.user = Main.dbase.get(username);							//retrieve the User object of the logged-in user
		new DoctorController( ((DoctorModel) user) , new DoctorView("Doctor Portal") );
        
		return "Account successfully edited!";
        }
		
	}


	public String editPersonalInfo()
	{
		//check if the username is NOT on file
		if(!Main.dbase.containsKey(username) )
		{
			return "That Account does not exist!";
		}
		else { 
		//otherwise edit the account in the HashMap
		this.user = Main.dbase.get(username);							//retrieve the User object of the logged-in user
		this.doctorModel = (DoctorModel) Main.dbase.get(username);	
		//new EditDoctorController( new EditDoctorModel(), new EditDoctorPersonalInfoView("Doctor Information Portal");

		new EditDoctorController( new EditDoctorModel(), new EditDoctorPersonalInfoView("Doctor Information Portal"), new DoctorModel(doctorModel.getUsername(), doctorModel.getPassword(), doctorModel.getName(), doctorModel.getDepartment(), doctorModel.getAssignedNurseUsernames(), doctorModel.getAvailability() ));


		//new DoctorController( ((DoctorModel) user) , new DoctorView("Patient Portal") );
		//make it so that you can edit doctor name, role, and password
	


		return "Account successfully edited!";}
		}

	public String checkPersonalInfo()
	{
		//check if the username is NOT on file
		if(!Main.dbase.containsKey(username) ) {
			return "That Account does not exist!";
		} else { 
		this.user = Main.dbase.get(username);							//retrieve the User object of the logged-in user
			if (this.user.getRole() == "doctor") {	
				return "Account successfully edited!";
			} else {
				return "This Account is not a Doctor!";
			}
		}
	
	}

	public String checkDoctorDepartment() {
		//check that a department was chosen
		if(department.compareTo("") == 0)
		{
			return "No department selected!";
		} else {
			return getDepartment();
		}
	}	
	
	//return the department list in a string[] format for use with combobox
	public String[] getDeptList() {
		
		ArrayList<String> temp = Main.dbaseClass.getDepartmentList();
		if(temp.get(0).compareTo("") != 0)
		{
			temp.add(0, "");
		}
		
		
		return temp.toArray(new String[0]);
	}
	
	
	
/**Getters and Setters*/
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public UserSuperClass getUser() {
		return user;
	}

	public void setUser(UserSuperClass user) {
		this.user = user;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


}
