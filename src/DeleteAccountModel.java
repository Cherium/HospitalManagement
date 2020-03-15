

/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class DeleteAccountModel {
	
	private String username;
	
	
	
	
	
	
	

	public DeleteAccountModel()
	{
		
	}
	
	
	public String deleteAccount()
	{
		//check if the username is NOT on file
		if(!Main.dbase.containsKey(username) )
		{
			return "That Account does not exist!";
		}
		
		//otherwise delete the account from the HashMap
		Main.dbase.remove(username);
		return "Account successfully deleted!";
		
		
	}

	
	
	
	
	
	
/**Getters and Setters*/
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

}
