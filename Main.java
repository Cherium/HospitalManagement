
public class Main {
	
	public static DatabaseModel dbase = new DatabaseModel("dbase.txt");		//Global var; Main Database; available everywhere; access with Login.data outside this class

	public static void main(String[] args) {
		
		//initialize the MVC for the login screen
		//the rest of the program proceeds from here
		LoginController c = new LoginController(new LoginModel(), new LoginView("Login Screen") );
		

		
		
		
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

	}

}
