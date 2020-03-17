import java.util.ArrayList;
import java.util.Iterator;


/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class DoctorModel extends UserModel {
	
	//private DoctorModel doctor;
	private String department;
	private ArrayList<String> assignedNurseUsernames = new ArrayList<String>(5);
	private ArrayList<PatientModel> scheduledPatients = new ArrayList<PatientModel>(5);
	private ScheduleModel schedule;
	

	
	//constructor
	public DoctorModel(String username, char[]password, String name, String department, String[] nurses) {
		
			super(name, username, password);
			this.department = department;
			setRole("doctor");
			
			//add nurses to array
			for(String i: nurses)
			{
				this.assignedNurseUsernames.add(i);
			}
			
	}
	
	
	

/**Getters and Setters*/
	public void setSchedule(ScheduleModel schedule) {
		this.schedule = schedule;
	}


	public ScheduleModel getSchedule() {
		return schedule;
	}

	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}




	public ArrayList<String> getAssignedNurseUsernames() {
		return assignedNurseUsernames;
	}

	public void setAssignedNurseUsernames(ArrayList<String> nurses) {
		this.assignedNurseUsernames = nurses;
	}

	public ArrayList<PatientModel> getScheduledPatients() {
		return scheduledPatients;
	}

	public void addScheduledPatients(ArrayList<PatientModel> pats) {
		this.scheduledPatients = pats;
	}


	public String[] getPatientNames() {
		String[] nameStrs = new String[getScheduledPatients().size()];
		for (int i = 0; i < getScheduledPatients().size(); i++) {
			nameStrs[i] = getScheduledPatients().get(i).getName();
		}
		return nameStrs;
	}

//testing class; will later be export class for database
public String toString()
{
	StringBuilder bob = new StringBuilder();
	bob.append("Namee: ");
	bob.append(getName());
	bob.append("\n");
	bob.append("Role: ");
	bob.append(getRole());
	bob.append("\n");
	bob.append("Username: " + getUsername() + " Password: " + getPassword().toString());
	bob.append("\n");
	// bob.append("Schedule: " + \n\t + getSchedule().toString());
	return bob.toString();
}









	
}
