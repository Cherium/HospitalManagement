import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Database {
	HashMap<String[], User> users;
	
	// public Database("database.txt");
	public Database() {
		dummyData();
		// initialize("database.txt");
	}
	
	private void dummyData() {
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
	
	public HashMap<String[], User> getUsers() {
		return users;
	}
	
	public void addUser(User u) {
		if (!userExists(u)) {
			String[] pair = new String[2];
			pair[0] = u.getUsername();
			pair[1] = u.getPassword();
			users.put(pair, u);			
		}

	}

	public boolean userExists(User u) {
		Set<String[]> keys = users.keySet();
		for (String[] pair : keys) {
			if (u.getUsername().equals(pair[0])) {
				return true;
			}
		}
		return false;
	}
	
	// TODO: Implement input database from a text file.
	
	// TODO: Implement saving of database to text file.
	// When saving, doctors have no list of assigned nurses. 
	// Nurses are saved with the username of the doctors in the place where the
	// doctor object would go. When initializing nurses, the username will match
	// with the existing doctor object, adding the object to nurse, and adding
	// the created nurse to the list of assigned doctors.
}
