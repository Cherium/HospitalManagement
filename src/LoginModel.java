import java.util.Arrays;


/**Handles calculations, database, etc that the Controller may need.
 * Does NOT initialize anything from the view or use any view methods.
 * If the methods of this class return anything, then it is likely the 
 * controller will use them.*/
public class LoginModel {
	
	private String username;
	private char[] password;
	
	private UserModel user;
	private String errorMessage;
	
	
	//constructor
	public LoginModel() {

	}
	
	
	//verify entered information in database and change the GUI view the user sees
	public boolean validate()
	{
		if(Main.dbase.containsKey(username))
		{
			//compare entered password against database password for entered username
			char[] pwdInDatabase = Main.dbase.get(username).getPassword();
			if(Arrays.equals(pwdInDatabase, password) )
			{
				this.user = Main.dbase.get(username);							//retrieve the User object of the logged-in user
//System.out.println(Main.dbase.users.get(username).getPassword());
				changePerspective();												//change the GUI the user sees
				return true;
			}
			else
			{
				//Passwords do not match
				this.errorMessage= "Username and password do not match";
				return false;
			}
		}
		else
		{
			//username does not exist in database
			this.errorMessage= "Username does not exist";
			return false;
		}
	}
	
	
	
	
	//change the GUI the user sees to a 'role screen' (ex.Patient) and update the MVC to the new role
	public void changePerspective()
	{
		String roleFrame = user.getRole();
		if (roleFrame.compareTo("doctor") == 0)
		{
			new DoctorController( ((DoctorModel) user) , new DoctorView("Doctor Portal") );
		}
		if(roleFrame.compareTo("patient") == 0)
		{
			new PatientController( ((PatientModel) user) , new PatientView("Patient Portal") );
		}
		if(roleFrame.compareTo("nurse") == 0)
		{
			new NurseController( ((NurseModel) user) , new NurseView("Nurse Portal") );
		}
		if(roleFrame.compareTo("admin") == 0)
		{
			new AdminController( ((AdminModel) user) , new AdminView("Admin Portal") );
		}
		if(roleFrame.compareTo("authority") == 0)
		{
			new AuthorityController( ((AuthorityModel) user) , new AuthorityView("Authority Portal") );
		}
	}
	
	
	
	
	//open the dialog box MVC for Creating a new patient
	public void openNewPatientDialog()
	{
		new CreateNewPatientController(new CreateNewPatientModel(), new CreateNewPatientView("New Patient Registration") );
	}

	
	
	
	
	
	
	
	
	
	
/**Getter and setter methods*/
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}



	public UserModel getUser() {
		return user;
	}



	public void setUser(UserModel user) {
		this.user = user;
	}



	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
