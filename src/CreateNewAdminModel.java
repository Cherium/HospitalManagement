import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.time.LocalDateTime;

/**
 *
 */
public class CreateNewAdminModel extends UserSuperClass {
	
	private String name;
	private String username;
	private char[] pwd;
    private char[] pwd2;
    private String[] avail;
	
	
	
	
	public String storeInDatabase()
	{
		//check if username already exists in database
		if( Main.dbase.containsKey(username))
		{
			return "Username already exists!";
		}
		
		
		//check that password is long enough; pwd's already verified at this point
		if(pwd.length < 4)
		{
			return "Password must be at least 4 characters!";
		}
		
		
		//check if passwords match
		if(Arrays.equals(pwd,pwd2) == false)
		{
			return "Passwords don't match!";
		}
		
		//check that username is long enough
		if(username.length() < 4)
		{
			return "Username must be at least 4 characters!";
		}
		


		//check that a name was entered
		if(name.length() < 1)
		{
			return "Please enter a name!";
		}
				
		/**store in database if all checks pass*/
		Main.dbase.put(username, new AdminModel(username, pwd, name, new String[0]));
		return "Account successfully created!";
	}
	
/**Getters and Setters*/


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPwd() {
		return pwd;
	}

	public void setPwd(char[] pwd) {
		this.pwd = pwd;
	}

	public char[] getPwd2() {
		return pwd2;
	}

	public void setPwd2(char[] pwd2) {
        this.pwd2 = pwd2;
    }


	
	

}

