import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class
 * @author Sajid C
 *
 */
public class ReceptionistModel extends UserSuperClass {
	
	
	private String[] allPatientsUsernames;
	
	

	
	
	/**
	 * constructor
	 * 
	 * @param username value for this user from the database
	 * @param password value for this user from the database
	 * @param name value for this user from the database
	 */
	public ReceptionistModel(String username, char[] password, String name
			, String[] avail){
		
		super(name, username, password);
		setRole("receptionist");
		
		this.availability = arrayToLDTArray(avail);		
	}

	
	/**
	 * Get list of departments in the system other than ER
	 *@author SM
	 *@return list of departments other than ER
	 */
	public String[] getDeptList() {
		
		ArrayList<String> temp = Main.dbaseClass.getDepartmentList();
		temp.remove("ER");
		
		
		return temp.toArray(new String[0]);
	}
	
	/**
	 * generate a list of all patients 'names' in the system. Also create a list of patient usernames for hashmap lookup later on
	 * @author Sajid C
	 * @return list of patient names
	 */
	public String[] getAllPats()
	{
		ArrayList<String> patUsernames = new ArrayList<String>(5);
		ArrayList<String> patNames = new ArrayList<String>(5);
		
		
		for(Map.Entry<String, UserSuperClass> i: Main.dbase.entrySet())
		{
			if(i.getValue().getRole().compareTo("patient") == 0 )
			{
				patUsernames.add(i.getValue().getUsername() );
				patNames.add(i.getValue().getName() );
			}
		}
		
		//shallow copy
		allPatientsUsernames = patUsernames.toArray(new String[0]);

		return patNames.toArray(new String[0]);
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
        PatientModel pat = (PatientModel) Main.dbase.get(allPatientsUsernames[selectedPatient]);
        
        //get patient appointment hashmap
        HashMap<String, ArrayList<LocalDateTime> > patAppts = pat.getAppointments();
        
        //if test type already exists
        if(patAppts.containsKey("labtest" ) )
        {
        	//append to list
        	patAppts.get("labtest").add(temp);
        }
        else //appt type is not a key in hashmap; add it
        {
        	patAppts.put("labtest", new ArrayList<LocalDateTime>(List.of(temp)) );
        }
    }
    
    /**
     * take appointment data from controller and store a new doctor appointment in patients appointment hashmap
     * Updates a doctors patient username list
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
        PatientModel pat = (PatientModel) Main.dbase.get(allPatientsUsernames[selectedPatient]);
        
        //get list of doctors currently in comboBox
        String[] list = getDocList(department);
        
        //get doc object
        DoctorModel doc = (DoctorModel) Main.dbase.get(list[selectedDocNameIndex]);
        
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
        	
        	//add patient to doctors scheduled patients
        	doc.getScheduledPatientsUsernames().add(allPatientsUsernames[selectedPatient]);
        }
        
      //remove appt time from docs availability
        doc.setAppointments(doc.s.updateHashMap(doc.getScheduledPatientsUsernames(), doc.getUsername()));
    }


	    /**
	 * prints out values for database export
	 * @author Muhammad R
	 * @return the string stored in dbase.txt
	 */
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
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
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
	
    
    /**
	 * prints out values for database export
	 * @author Muhammad R
	 * @return list of availability to store in Availability.txt
	 */
	public String toStringAvailability() {
		StringBuilder bob = new StringBuilder();
		
		for(int i= 0; i<super.getAvailability().length; i ++) {
			bob.append(super.getAvailability()[i]);
			bob.append("\n");
			
		}
		if(bob.length() > 0)
			bob.deleteCharAt(bob.length()-1);
		return bob.toString();
	}
	
    
    /**Getters and Setters*/
    
	public String[] getAllPatientsUsernames() {
		return allPatientsUsernames;
	}



	public void setAllPatientsUsernames(String[] allPatientsUsernames) {
		this.allPatientsUsernames = allPatientsUsernames;
	}

	
	

	






}
