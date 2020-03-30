import java.util.ArrayList;


/**
 * handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class
 * @author Neil M, Sajid C
 *
 */
public class ReceptionistModel extends UserSuperClass {
	
	
	
	
	private ArrayList<String> allPatients = new ArrayList<String>();
	
	

	/**
	 * constructor
	 * 
	 * 
	 * @param username value for this user from the database
	 * @param password value for this user from the database
	 * @param name value for this user from the database
	 * @param avail availability value for this user from the database
	 */
	public ReceptionistModel(String username, char[] password, String name, String[] avail)
	{
		
		super(name, username, password);
		setRole("receptionist");
		
		this.availability = arrayToLDTArray(avail);
	}

	

	public ArrayList<String> getScheduledPatientsUsernames() {
		return allPatients;
	}

	public void setScheduledPatientsUsernames(ArrayList<String> patients) {
		this.allPatients = new ArrayList<String>(patients);
	}
	

}
