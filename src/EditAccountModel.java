/**
 * MVC Model: Model that deals with the edit account functionality, interacts as the
 * backend to support controller and view.
 * @author Jeremy Fan
 */
public class EditAccountModel {
	
	/* 
	* Variables include user and any inherited aspects from user.
	*/
	private String username;
	private UserSuperClass user;

	
	/**
	 * store new account based on name, username and password given that it passes
	 * a series of tests
	 * @author Jeremy F
	 */
	public String editAccount()
	{
		//check if the username is NOT on file
		if(!Main.dbase.containsKey(username) )
		{
			return "That Account does not exist!";
		}
		else { 
		//otherwise edit the account in the HashMap
		this.user = Main.dbase.get(username);							//retrieve the User object of the logged-in user
		new PatientController( ((PatientModel) user) , new PatientView("Patient Portal") );

		return "Account successfully edited!";
        }
		
	}

	
	
	
	
	
	
/**Getters and Setters*/
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

}
