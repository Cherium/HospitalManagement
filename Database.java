import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Database {
			HashMap<String, User> users;

			// public Database("database.txt");
			public Database() {
						users = new HashMap<String, User>();
						dummyData();
						// initialize("database.txt");
			}

			//Initialized dummy values here.
			private void dummyData() {
						Doctor aDoc = new Doctor("Doctor Who", "doctor", "doctor".toCharArray(), "Cardiology", null);
						Nurse aNurse = new Nurse("Nurse Joy", "nurse", "nurse".toCharArray(), "Cardiology", aDoc);
						aDoc.addNurse(aNurse);
						Patient aPat = new Patient("Healthy Person", "patient", "patient".toCharArray());
						Patient anotherPat = new Patient("Sick Person", "patient2", "patient".toCharArray());
						Admin aAdm = new Admin("Lowly Admin", "admin", "admin".toCharArray());
						Authority aHigherPower = new Authority("Higher Power", "stats", "stats".toCharArray());
						addUser(aDoc);
						addUser(aNurse);
						addUser(aPat);
						addUser(anotherPat);
						addUser(aAdm);
						addUser(aHigherPower);
			}

			/**
			 * Function that returns all the users in the database
			 *
			 * @return
			 */
			public HashMap<String, User> getUsers() {
						return users;
			}

			/**
			 * Function that adds a user to the system and pairs it up with the Username
			 * Checking for unique username is handled in another Class
			 * @param u
			 */
			public void addUser(User u) {
						String key = u.getUsername();
						users.put(key, u);
			}

			/**
			 * Function that checks if a user is already present in the system by checking
			 * the username against the database.
			 *
			 * @param u
			 * @return
			 */
			public boolean userExists(User u) {
						Set<String> usernames = users.keySet();
						for (String a : usernames) {
									if (u.getUsername().equals(a)) {
												return true;
									}
						}
						return false;
			}

			/**
			 * Function that removes a user from the database.
			 *
			 * @param u
			 */
			public void removeUser(User u) {
						String userid = u.getUsername();
						users.remove(userid, u);
			}

			public User getUser(String userid){
						if (users.containsKey(userid)){
									User u = users.get(userid);
									return u;
								}
						else
									return null;
			}


			public User getUser(String userid, char[] pwd){
						if (users.containsKey(userid)) {
									User u = users.get(userid);
									if (Arrays.equals(pwd, u.getPassword())) {
												return u;
									}
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

			public static void main(String[] args) {
						Database testDB = new Database();
						System.out.println("Finding a user in the system\n");
						String username = "doctor";
						String password = "doctor";
						User one = testDB.getUser(username, password.toCharArray());
						System.out.println(one.toString());
						one = testDB.getUser("nurse", "nurse".toCharArray());
						System.out.println(one.toString());
			}
}
