import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class DoctorModel extends UserSuperClass {
	
	//private DoctorModel doctor;
	private String department;
	private ArrayList<String> assignedNurseUsernames = new ArrayList<String>(5);
	private ArrayList<String> scheduledPatientsUsernames = new ArrayList<String>(5);
	// private Schedule schedule;
	private HashMap<String, ArrayList<LocalDateTime>> appointments;	//patient appointments with this doctor

	
	//constructor
	
	// public DoctorModel(String username, char[]password, String name, String department, String[] nurses, String[] assigPats, ArrayList<String> available) 
	public DoctorModel(String username, char[]password, String name, String department, String[] nurses, String[] assigPats, String[] avail) 
	{
		
			super(name, username, password);
			this.department = department;
			setRole("doctor");
			
			//add nurses to array
			for(String i: nurses)
			{
				this.assignedNurseUsernames.add(i);
			}
			
			//add the doctors scheduled patients
			for(String i: assigPats)
			{
				this.scheduledPatientsUsernames.add(i);
			}
			
			this.availability = arrayToLDTArray(avail);
			
			
	}
	
	
	
	
	
	
	
	public void addPatient(String p) {
		this.scheduledPatientsUsernames.add(p);
	}

	
	//for all the doctors scheduled shifts, get a list of all the open slots for booking times in the docs schedule
	public ArrayList<LocalDateTime> getRemainingTimes()
	{
		LocalDateTime now = LocalDateTime.now();
		
		//get 14 appointment dates
		for(int i = 0; i < 14; i++)
		{
			//for all doctors appointments for this day, greater than his start shift time, and less than his end shift time
			for (int j = 0; j < availability.length; j++) 
			{
				
			}
			
		}
		//for all appointments the doctor has booked
		ArrayList<String> apptList = new ArrayList<String>(5);
		for(Map.Entry<String, UserSuperClass> i: Main.dbase.entrySet())
		{

		}
		return null;
	}

/**Getters and Setters*/
	public void setAppointments(HashMap<String, ArrayList<LocalDateTime>> xs) {
		this.appointments = xs;
	}

	public HashMap<String, ArrayList<LocalDateTime>> getAppointments() {
		return appointments;
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
		this.assignedNurseUsernames = new ArrayList<String>(nurses);
	}

	public ArrayList<String> getScheduledPatientsUsernames() {
		return scheduledPatientsUsernames;
	}

	public void setScheduledPatientsUsernames(ArrayList<String> pats) {
		this.scheduledPatientsUsernames = new ArrayList<String>(pats);
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
		return bob.toString();
	}









	
}
