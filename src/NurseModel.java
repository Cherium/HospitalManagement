import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class
 * @author Sajid C
 *
 */
public class NurseModel extends UserSuperClass {
	
	private String department;
	
	private String assignedDocUsername;

	private Schedule schedule;
	
	private String[] docsPatientsUsernames;
	
	

	
	//constructor
	/**
	 * @param username value for this user from the database
	 * @param password value for this user from the database
	 * @param name value for this user from the database
	 * @param department value for this user from the database
	 * @param assignedDocUsername value from the database for this users assigned doctor
	 * @param avail availability value for this user from the database
	 */
	public NurseModel(String username, char[] password, String name
			, String department, String assignedDocUsername, String[] avail){
		
		super(name, username, password);
		this.assignedDocUsername = assignedDocUsername;
		this.department = department;
		setRole("nurse");
		
		this.availability = arrayToLDTArray(avail);
		
		//System.out.println(s.nextShiftsToString(availability));
		
		
	}

	
	/**
	 * Gets a department from a doctor's username
	 * @author Sajid C
	 * @return department of the associated doctor
	 */
	public String getDocDept()
	{
		return ((DoctorModel) Main.dbase.get(assignedDocUsername)).getDepartment();
	}
	
	/**
	 * get patients of a doctors username as a list
	 * @author Sajid C
	 * @return list of patient usernames
	 */
	public String[] getDocPats()
	{
		docsPatientsUsernames = ((DoctorModel) Main.dbase.get(assignedDocUsername)).getScheduledPatientsUsernames().toArray(new String[0]);
		ArrayList<String> temp = new ArrayList<>(5);
		for(String i: docsPatientsUsernames)
		{
			temp.add(getObjectsName(i));
		}
		
		return temp.toArray(new String[0]);
	}
	
	
	
	
	

    /**INCOMPLETE??
     * takes in a single raw availability time, and returns a LocalDateTime object
     * @author Sajid C
     * @param rawData raw availability time in format yyyy-M-d HH:mm
     * @param selectedPatient patient username in a list that is associated 1:1 with index of selection in the JList
     * @return 
     */
    public LocalDateTime storeApptInPatient(String rawData, int selectedPatient)
    {
    	//https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
    	
        LocalDateTime temp = LocalDateTime.parse(rawData, formatter);
        PatientModel pat = (PatientModel) Main.dbase.get(docsPatientsUsernames[selectedPatient]);
        
        return temp;
        
    }
    

	
	
/**Getters and Setters*/
	
	public String getAssignedDocUsername() {
		return assignedDocUsername;
	}

	public void setAssignedDocUsername(String assignedDoc) {
		this.assignedDocUsername = assignedDoc;
	}
	
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule x) {
		this.schedule = x;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String d) {
		this.department = d;
	}


	public String[] getDocsPatientsUsernames() {
		return docsPatientsUsernames;
	}


	public void setDocsPatientsUsernames(String[] docsPatientsUsernames) {
		this.docsPatientsUsernames = docsPatientsUsernames;
	}




}
