import java.util.Arrays;

public class LoginModel {
	
	private String username;
	private char[] password;
	
	private UserModel user;
	private String errorMessage;
	
	
	//constructor
	public LoginModel() {

	}
	
	
	
	public boolean validate()
	{
		if(Main.dbase.users.containsKey(username))
		{
			//compare entered password against database password for entered username
			char[] pwdInDatabase = Main.dbase.users.get(username).getPassword();
			if(Arrays.equals(pwdInDatabase, password) )
			{
				this.user = Main.dbase.users.get(username);		//retrieve the User object of the logged-in user
				System.out.println(Main.dbase.users.get(username).getPassword());
				changePerspective();							//change the GUI the user sees
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
	
	////change the GUI the user sees and update the MVC to the new role
	public void changePerspective()
	{
		String roleFrame = user.getRole();
		if (roleFrame.compareTo("doctor") == 0)
		{
			new DoctorController( ((DoctorModel) user) , new DoctorView("") );
		}
		
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
