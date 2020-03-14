import java.util.ArrayList;


/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class DoctorModel extends UserModel {
	
	//private DoctorModel doctor;
	private String department;
	private ArrayList<NurseModel> nurses = new ArrayList<NurseModel>(0);
	private ArrayList<PatientModel> scheduledPatients = new ArrayList<PatientModel>(0);
	

	//constructor for import/export from database
	public DoctorModel(String name, String username, char[] password, String department)
	{
		super(name, username, password);
		this.department = department;
		setRole("doctor");
	}
	
	//constructor
	public DoctorModel(String name, String username, char[]password, String department, ArrayList<NurseModel> nurses) {
		
			super(name, username, password);
			this.department = department;
			setRole("doctor");
			
			//get list of nurses and add them to the instance variable to later add attributes to the database
			for (int i = 0; i < nurses.size(); i++) {
				
				this.nurses.add(nurses.get(i) );
			}
			
			
	}


	
	
	
	

/**Getters and Setters*/
	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}




	public ArrayList<NurseModel> getNurses() {
		return nurses;
	}




	public void setNurses(ArrayList<NurseModel> nurses) {
		this.nurses = nurses;
	}

	public ArrayList<PatientModel> getScheduledPatients() {
		return scheduledPatients;
	}

	public void addScheduledPatients(ArrayList<PatientModel> pats) {
		this.scheduledPatients = pats;
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
	bob.append("Nurse1: "+ nurses.get(0).getName());
	return bob.toString();
}

	
}
