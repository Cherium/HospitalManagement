import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * MVC Model: Model that deals with the edit account functionality, interacts as the
 * backend to support controller and view.
 * @author Jeremy Fan
 */

public class EditAdminModel {
	/* 
	* Variables include user and any inherited aspects from user and adminModel.
	*/
	private String username;
	private UserSuperClass user;
	private AdminModel adminModel;


	/**
	 * store new account based on name, username and password given that it passes
	 * a series of tests
	 * @author Jeremy F
	 */
	public int editSchedule()
	{
		//check if the username is NOT on file
		if(!Main.dbase.containsKey(username) )
		{
			return -1;
		}
		else { 
		//otherwise edit the account in the HashMap
			this.user = Main.dbase.get(username);							//retrieve the User object of the logged-in user
			try {
				AdminController a = new AdminController( ((AdminModel) user) , new AdminView("Admin Portal") );
				a.adminView();
				return 1;
			} catch (Exception e) {
				return 0;
			}
        }
		
	}

	/**
	 * store new account based on name, username and password given that it passes
	 * a series of tests
	 * @author Jeremy F
	 */
	public int editPersonalInfo()
	{
		//check if the username is NOT on file
		if(!Main.dbase.containsKey(username) )
		{
			return -1;
		}
		else { 
		//otherwise edit the account in the HashMap
			this.user = Main.dbase.get(username);							//retrieve the User object of the logged-in user
			try {
				this.adminModel = (AdminModel) Main.dbase.get(username);	
				new EditAdminController( new EditAdminModel(), new EditAdminPersonalInfoView("Admin Information Portal"), new AdminModel(adminModel.getUsername(), adminModel.getPassword(),adminModel.getName(), adminModel.getAvailability()));
				return 1;
			} catch (Exception e) {
				return 0;

			}
			
		}
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
			if (this.user.getRole() == "admin") {	
				return "Account successfully edited!";
			} else {
				return "This Account is not an Admin!";
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
