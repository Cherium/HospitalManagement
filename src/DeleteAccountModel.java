
/**
 * handles all calculations, database queries, and the overall work needed to be
 * done for handling this associated role Does NOT interact with the view class
 * directly, and also does NOT interact with the Controller class
 * 
 * @author Sajid C
 *
 */
public class DeleteAccountModel {

	private String username;

	/**
	 * default empty constructor
	 * 
	 * @author Sajid C
	 */
	public DeleteAccountModel() {

	}

	/**
	 * delete a username key and associated value from the database
	 * 
	 * @author Sajid C
	 * 
	 * @return debug message for user to view
	 */
	public String deleteAccount() {
		// check if the username is NOT on file
		if (!Main.dbase.containsKey(username)) {
			return "That Account does not exist!";
		}

		// otherwise delete the account from the HashMap
		Main.dbase.remove(username);
		return "Account successfully deleted!";

	}

	/** Getters and Setters */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
