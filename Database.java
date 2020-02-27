import java.util.HashMap;
import java.util.Set;

public class Database {
	HashMap<String[], User> users;
	
	// public Database("database.txt");
	public Database() {
		users = new HashMap<String[], User>();
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
	
	/**
	 * Function that returns all the users in the database
	 * @return
	 */
	public HashMap<String[], User> getUsers() {
		return users;
	}

	/**
	 * Function that adds a user to the system, checking for unique username. 
	 * @param u
	 */
	public void addUser(User u) {
		if (users.isEmpty()) {
			String[] pair = new String[2];
			pair[0] = u.getUsername();
			pair[1] = u.getPassword();
			users.put(pair, u);	
		} else if (!userExists(u)) {
			String[] pair = new String[2];
			pair[0] = u.getUsername();
			pair[1] = u.getPassword();
			users.put(pair, u);		
		}

	}

	/**
	 * Function that checks if a user is already present in the system by checking
	 * the username against the database.
	 * @param u
	 * @return
	 */
	public boolean userExists(User u) {
		Set<String[]> keys = users.keySet();
		for (String[] pair : keys) {
			if (u.getUsername().equals(pair[0])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Function that removes a user from the database.
	 * @param u
	 */
	public void removeUser(User u) {
		String[] pair = new String[2];
		pair[0] = u.getUsername();
		pair[1] = u.getPassword();
		users.remove(pair, u);
	}
	
	public User getUser(String[] login) {
		if (users.containsKey(login)) {
			return users.get(login);
		} 
		return null;
	}
	
	// TODO: Implement input database from a text file.
	
	// TODO: Implement saving of database to text file.
	// When saving, doctors have no list of assigned nurses. 
	// Nurses are saved with the username of the doctors in the place where the
	// doctor object would go. When initializing nurses, the username will match
	// with the existing doctor object, adding the object to nurse, and adding
	// the created nurse to the list of assigned doctors.
}
