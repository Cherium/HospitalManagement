import java.util.HashMap;

/** Main function that starts the initial GUI*/
public class Main {
	
	/**Global variable; 
	 * The Main Database that is available in all classes.
	 * Usage:
	 * 		'Main.dbase' is used to access the object that represents the HashMap database.
	 * 			ex. 'Main.dbase.containsKey()' checks the HashMap to see if a key is contained within it. */
	
	public static Database dbaseClass = new Database("dbase.txt");
	public static HashMap<String, UserSuperClass> dbase = dbaseClass.users;

	
	
	
	
	public static void main(String[] args) {
		
		//initialize the MVC for the login screen
		//the rest of the program proceeds from here
		LoginController c = new LoginController(new LoginModel(), new LoginView("Login Screen") );
		


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/*	
//testing stuff, disregard///////////////////
		UserModel t;
		UserModel s = (dbase.users.get("doctor") );
		//UserModel s = ((DoctorModel) dbase.users.get("doctor") ).getDepartment();
		
		//System.out.println("Doctors department is: "+ s);
			
			System.out.println(dbase.users.get("doctor") );
			System.out.println(dbase.users.get("nurse") );
			System.out.println(dbase.users.get("patient") );
			System.out.println(dbase.users.get("doctor") );
			System.out.println(dbase.users.get("nancy") );
			//System.out.println(((DoctorModel) dbase.users.get("doctor")).getNurses().size());
			System.out.println(((DoctorModel) dbase.users.get("doctor")).toString());
/////////////end testing stuff////////////////////////////////////////////////////////////////////
*/
	}

}
