import java.util.Arrays;

/**
 * handles all calculations, database queries, and the overall work needed to be
 * done for handling this associated role Does NOT interact with the view class
 * directly, and also does NOT interact with the Controller class
 * 
 * @author Sajid C
 *
 */
public class LoginModel {

	private String username;
	private char[] password;

	private UserSuperClass user;
	private String errorMessage;

	/**
	 * default empty constructor
	 * 
	 * @author Sajid C
	 */
	public LoginModel() {

	}

	/**
	 * verify user entered information in database and change the GUI view the user
	 * sees
	 * 
	 * @author Sajid C
	 * @return true if username matches password in database, otherwise false
	 */
	public boolean validate() {
		if (Main.dbase.containsKey(username)) {
			// compare entered password against database password for entered username
			char[] pwdInDatabase = Main.dbase.get(username).getPassword();
			if (Arrays.equals(pwdInDatabase, password)) {
				this.user = Main.dbase.get(username); // retrieve the User object of the logged-in user
				changePerspective(); // change the GUI the user sees
				return true;
			} else {
				// Passwords do not match
				this.errorMessage = "Username and password do not match";
				return false;
			}
		} else {
			// username does not exist in database
			this.errorMessage = "Username does not exist";
			return false;
		}
	}

	/**
	 * change the GUI the user sees to a 'role screen' (ex.Patient) and update the
	 * MVC to the new role
	 * 
	 * @author Sajid C
	 */
	public void changePerspective() {
		// get role of user from database and open the appropriate MVC construct
		String roleFrame = user.getRole();
		if (roleFrame.compareTo("doctor") == 0) {
			new DoctorController(((DoctorModel) user), new DoctorView("Doctor Portal"));
		}
		if (roleFrame.compareTo("patient") == 0) {
			new PatientController(((PatientModel) user), new PatientView("Patient Portal"));
		}
		if (roleFrame.compareTo("nurse") == 0) {
			new NurseController(((NurseModel) user), new NurseView("Nurse Portal"));
		}
		if (roleFrame.compareTo("admin") == 0) {
			new AdminController(((AdminModel) user), new AdminView("Admin Portal"));
		}
		if (roleFrame.compareTo("authority") == 0) {
			new AuthorityController(((AuthorityModel) user), new AuthorityView("Authority Portal"));
		}
		if (roleFrame.compareTo("receptionist") == 0) {
			new ReceptionistController(((ReceptionistModel) user), new ReceptionistView("Receptionist Portal"));
		}
	}

	/**
	 * 
	 * open the dialog box MVC for Creating a new patient
	 * 
	 * @author Sajid C
	 */
	public void openNewPatientDialog() {
		new CreateNewPatientController(new CreateNewPatientModel(),
				new CreateNewPatientView("New Patient Registration"));
	}

	/** Getter and setter methods */

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

	public UserSuperClass getUser() {
		return user;
	}

	public void setUser(UserSuperClass user) {
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
