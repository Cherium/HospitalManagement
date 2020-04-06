import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class EditAdminModel {
	
	private String username;
	private UserSuperClass user;
	private AdminModel adminModel;

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
		new AdminController( ((AdminModel) user) , new AdminView("Admin Portal") );
        
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
		this.adminModel = (AdminModel) Main.dbase.get(username);	
        new EditAdminController( new EditAdminModel(), new EditAdminPersonalInfoView("Admin Information Portal"), new AdminModel(adminModel.getUsername(), adminModel.getPassword(),adminModel.getName(), adminModel.getAvailability()));
        return "Account successfully edited!";
            }
		}

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
