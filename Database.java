import java.util.ArrayList;
import java.util.HashMap;

public class Database {
//	HashMap<ArrayList<String>, User> users;
	HashMap<String[], User> users;
	public HashMap<ArrayList<String>, User>() {
		initialize();
	}
	
	private void initialize() {
		Doctor aDoc = new Doctor("Doctor Who", "doctor", "doctor", "Cardiology" , null);
		Nurse aNurse = new Nurse("Nurse Joy", "nurse", "nurse", "Cardiology", aDoc);
		aDoc.addNurse(aNurse);
		Patient aPat = new Patient("Healthy Person", "patient", "patient");
		Patient anotherPat = new Patient("Sick Person", "patient2", "patient");
		Admin aAdm = new Admin("Lowly Admin", "admin", "admin");
		Authority aHigherPower = new Authority("Higher Power", "stats", "stats");
		addUser(aDoc);
		addUser(aNurse);
		addUser(aPat);
		addUser(anotherPat);
		addUser(aAdm);
		addUser(aHigherPower);
	} 
	
	public void addUser(User u) {
		String[] pair = new String[2];
		pair[0] = u.getUsername();
		pair[1] = u.getPassword();
		users.put(pair, u);
	}
	
	// TODO: check for replications of username when adding to database
}
