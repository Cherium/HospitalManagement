
public class Admin extends User {

	public Admin(String name, String username, String password) {
		super(name, username, password);	
		setRole("admin");
	}
	
	public void createAccount(User u) {
		
	}
	
	public void removeAccount(User u) {
		
	}
	

}
