import java.util.ArrayList;

/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class NurseModel extends UserSuperClass {
	
	private String department;
	
	private String assignedDocUsername;

	private Schedule schedule;
	
	private String[] docsPatientsUsernames;
	
	
	
	//constructor
	public NurseModel(String username, char[] password, String name
			, String department, String assignedDocUsername){
		
		super(name, username, password);
		this.assignedDocUsername = assignedDocUsername;
		this.department = department;
		setRole("nurse");
		//provide an ease-of-use doctor object for assigned doc of this Nurse, retrieved from the database
		//any changes to this object are not reflected in the database, and must be done manually
	}

	
	public String getDocDept()
	{
		return ((DoctorModel) Main.dbase.get(assignedDocUsername)).getDepartment();
	}
	
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
