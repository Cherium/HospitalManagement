import java.util.HashMap;

/** Main function that starts the initial GUI*/
public class Main {
	
	/**Global variable; 
	 * The Main Database that is available in all classes.
	 * Usage:
	 * 		'Main.dbase' is used to access the object that represents the HashMap database.
	 * 			ex. 'Main.dbase.containsKey()' checks the HashMap to see if a key is contained within it. */
	
	public static Database dbaseClass = new Database("dbase/dbase.txt");
	public static HashMap<String, UserSuperClass> dbase = dbaseClass.users;

	
	
	
	
	public static void main(String[] args) {
		
		//initialize the MVC for the login screen
		//the rest of the program proceeds from here
		LoginController c = new LoginController(new LoginModel(), new LoginView("Login Screen") );

	}

}
