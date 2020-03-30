

/**
 * handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class
 * @author Sajid C
 *
 */
public class AuthorityModel extends UserSuperClass {


	/**
	 * constructor
	 * 
	 * @author Sajid C
	 * 
	 * @param username users username value from database
	 * @param password users password value from database
	 * @param name users name value form database
	 * @param avail users availability value form database
	 */
	public AuthorityModel(String username, char[] password, String name, String[] avail) {

		super(name, username, password);
		setRole("authority");
		this.availability = arrayToLDTArray(avail);
	}
	
	
	
	

}
