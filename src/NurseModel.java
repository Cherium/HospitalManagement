import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
//		System.out.println(this.toStringDbase());
//		System.out.println("------------------------------------------");
//		System.out.println(this.toStringAvailability());
//		
		
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
	
	
	
	
	

    /**
     * takes in a single raw availability time, and a selected patient (index) and stores the lab appt in the patient
     * @author Sajid C
     * @param rawData raw availability time in format yyyy-M-d HH:mm
     * @param selectedPatient patient username in a list that is associated 1:1 with index of selection in the JList
     * @return 
     */
    public void storeLabApptInPatient(String rawData, int selectedPatient)
    {
    	//https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
        LocalDateTime temp = LocalDateTime.parse(rawData, formatter);
        
        //get patient object
        PatientModel pat = (PatientModel) Main.dbase.get(docsPatientsUsernames[selectedPatient]);
        
        //get patient appointment hashmap
        HashMap<String, ArrayList<LocalDateTime> > patAppts = pat.getAppointments();
        
        //if test type already exists
        if(patAppts.containsKey("Lab Test" ) )
        {
        	//append to list
        	patAppts.get("Lab Test").add(temp);
        }
        else //appt type is not a key in hashmap; add it
        {
        	patAppts.put("Lab Test", new ArrayList<LocalDateTime>(List.of(temp)) );
        }
    }
    
    /**
     * take appointment data from controller and store a new doctor appointment in patients appointment hashmap
     * 
     * @author Sajid
     * @param appt string appointment time formatted to work with LocalDateTime.parse
     * @param selectedPatient index of selected JList patient to use to pull patient username from an array
     * @param department departmet selected from combobox
     * @param selectedDocNameIndex index of chosen doctor in doctor combobox to use to pull doctor username from an array
     */
    public void storeDoctorApptInPatient(String appt, int selectedPatient, String department, int selectedDocNameIndex)
    {
    	//get patient object
        PatientModel pat = (PatientModel) Main.dbase.get(docsPatientsUsernames[selectedPatient]);
        
        //get list of doctors currently in comboBox
        String[] list = getDocList(department);
        
        //get patient appointment hashmap
        HashMap<String, ArrayList<LocalDateTime> > patAppts = pat.getAppointments();
        
        //parse appointment
        LocalDateTime temp = LocalDateTime.parse(appt);
        
        //if doc already exists in patients hashmap
        if(patAppts.containsKey(list[selectedDocNameIndex]) )
        {
        	//append to list
        	patAppts.get(list[selectedDocNameIndex]) .add(temp);
        }
        else //doc is not a key in hashmap; add it
        {
        	patAppts.put(list[selectedDocNameIndex] , new ArrayList<LocalDateTime>(List.of(temp))) ;
        }
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

	
	public String toStringDbase() {
		StringBuilder bob = new StringBuilder();
		bob.append(getRole());
		bob.append("\t");
		bob.append(getUsername());
		bob.append("\t");
		bob.append(getPassword());
		bob.append("\t");
		bob.append(getName());
		bob.append("\t");
		bob.append(getDepartment());
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append(getAssignedDocUsername());
		bob.append("\t");
		bob.append("null");	
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");


		
		return bob.toString();
	}
	public String toStringAvailability() {
		StringBuilder bob = new StringBuilder();
		
		for(int i= 0; i<super.getAvailability().length; i ++) {
			bob.append(super.getAvailability()[i]);
			bob.append("\n");
			
		}

		return bob.toString();
	}
		



}
