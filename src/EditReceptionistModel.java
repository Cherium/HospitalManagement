/**
 * MVC Model: Model that deals with the edit account functionality, interacts as the
 * backend to support controller and view.
 * @author Jeremy Fan
 */
public class EditReceptionistModel {
	
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
		new ReceptionistController( ((ReceptionistModel) user) , new ReceptionistView("Receptionist Portal") );
        
		return "Account successfully edited!";
        }
		
	}

	/**
	 * store new account based on name, username and password given that it passes
	 * a series of tests
	 * @author Jeremy F
	 */
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
		new EditReceptionistController( new EditReceptionistModel(), new EditReceptionistPersonalInfoView("Receptionist Information Portal") );
		return "Account successfully edited!";}
		}

	/**
	 * store new account based on name, username and password given that it passes
	 * a series of tests
	 * @author Jeremy F
	 */
	public String checkPersonalInfo()
	{
		//check if the username is NOT on file
		if(!Main.dbase.containsKey(username) ) {
			return "That Account does not exist!";
		} else { 
		this.user = Main.dbase.get(username);							//retrieve the User object of the logged-in user
			if (this.user.getRole() == "receptionist") {
				return "Account successfully edited!";
			} else {
				return "This Account is not a Receptionist!";
			}
		}
	
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

}
