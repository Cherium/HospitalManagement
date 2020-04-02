import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class
 * @author Sajid C
 *
 */
public class PatientModel extends UserSuperClass {

	private String address;
	private String phoneNumber;
	private String email;
	private char[] password2;
	private float amountDue;
	private LocalDate birthday;
	private String bloodtype;
	private String sex;
	private String recordNotes;
	
	//stores list of appointments
	private HashMap<String, ArrayList<LocalDateTime>> appointments;
	

	/**
	 * constructor
	 * 
	 * @author Sajid C
	 * 
	 * @param username value from database
	 * @param password value from database
	 * @param name value from database
	 * @param addr address value from database
	 * @param phNumber phone number value from database
	 * @param email value from database
	 * @param amountDue users amount due value from database
	 * @param dob date of birth value from database
	 * @param bloodType value from database
	 * @param sex2 sex value from database
	 * @param record2 patients record value from database
	 * @param appointments list of appointments value from database
	 */
	public PatientModel(String username, char[] password, String name
			, String addr, String phNumber, String email, float amountDue, String dob, String bloodType, String sex2, String record2, ArrayList<String> appointments)
	{
		
		super(name,username,password);
		setRole("patient");
		this.address = addr;
		this.phoneNumber = phNumber;
		this.email = email;
		this.amountDue = amountDue;
		
		//convert date to LocalDate object
		this.birthday = dateStringToLocalDate(dob);
		this.bloodtype = bloodType;
		this.sex = sex2;
		this.recordNotes = record2;
		
		//get list of appointments
		this.appointments = listToAppointmentMap(appointments);

		//testing prints-- dont delete
//		for(Map.Entry<String, ArrayList<LocalDateTime>> i: this.appointments.entrySet() )
//		{
//			//print if not lab test
//			if(i.getKey().compareTo("labtest") == 0)
//			{
//				continue;
//			}
//			System.out.println("Doctor: " + i.getKey() + " Appt: " + i.getValue().get(0).toString()  );
//		}
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
    public void storeDoctorApptInPatient(String appt, PatientModel selectedPatient, String department, int selectedDocNameIndex)
    {
    	//get patient object
        PatientModel pat = selectedPatient;
        
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

    /**
     * takes in a single raw availability time, and a selected patient (index) and stores the lab appt in the patient
     * @author Sajid C
     * @param rawData raw availability time in format yyyy-M-d HH:mm
     * @param selectedPatient patient username in a list that is associated 1:1 with index of selection in the JList
     * @return 
     */
    public void storeLabApptInPatient(String rawData, PatientModel selectedPatient)
    {
    	//https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
        LocalDateTime temp = LocalDateTime.parse(rawData, formatter);
        
        //get patient object
        PatientModel pat = selectedPatient;
        
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
	 * store raw appointment data into a hashmap
	 * @author Sajid C
	 * @param rawAppts string of raw formatted appointments from the controller
	 * @return a HashMap containing a doctor username on one side and a LIST of appointment start time on the other
	 */
	public HashMap<String, ArrayList<LocalDateTime> > listToAppointmentMap(ArrayList<String> rawAppts)
	{
		HashMap<String, ArrayList<LocalDateTime>> temp = new HashMap<>(5);
		
		//go through the database import list
		for(int i=0; i < rawAppts.size(); i=i+2)
		{
			String docname = rawAppts.get(i);
			LocalDateTime apptTime = LocalDateTime.parse(rawAppts.get(i+1) );
			
			//if doc is in patients appointment hashmap
			if(temp.containsKey(docname) )
			{
				//if appt time is not already in the list
				if(!temp.get(docname).contains(apptTime))
				{
					//add this appt to the appt list for that doc
					temp.get(docname).add(apptTime);
					
				}					
			}
			else //doc is not in hashmap
			{
				//create a new hashmap entry
				temp.put(docname, new ArrayList<LocalDateTime>(
						List.of(apptTime)
						
						));
			}
		}
		return temp;
		
	}

	
	/**
	 * convert a string of digits to a phone number
	 * @author Sajid C
	 * @return string formatted phone number
	 */
	public String convertToPhoneNumber()
	{
		//https://howtodoinjava.com/java/string/format-phone-number/
		return  phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
	}
	
	
	/**
	 * convert a float to a String with 2 decimal places
	 * @author Sajid C
	 * @return formatted currency
	 */
	public String convertToDollar()
	{
		return String.format("$%.2f" , amountDue);
	}

	/**
	 * validate user password entries and store new password values in database on success, otherwise provide error debug message
	 * @author Sajid C
	 * @return debug message for user on success/fail
	 */
	public String updatePassword()
	{
		//check that password is long enough; pwd's already verified at this point
		if(password.length < 4)
		{
			return "Password must be at least 4 characters!";
		}
		
		//check if passwords match
		if(Arrays.equals(password, password2) == false)
		{
			return "Passwords don't match!";
		}
		
		//else tests pass, store password for the user
		Main.dbase.get(username).setPassword(password);
		
		return "Password Changed Successfully!";
		
		
	}
	
	
//TODO error checking on instance variables set by model.updateInfo()
	/**
	 * verify use entered infoPanel information and provide debug message
	 * @author Sajid C
	 * @return debug message for user
	 */
	public String verifyInfo()
	{
		//error checks, don't update database in here
		if(true)
		{
			
		}
		
		//update the information in the dbase once all checks pass
		return "Information Successfully Updated";
	}
	


	
	/**
	 * 
	 * @author Jenny Z
	 * @return find the time (years) between today and patient birthdate
	 */
	public int getAge() {
		// https://stackoverflow.com/questions/1116123/how-do-i-calculate-someones-age-in-java
		//https://howtodoinjava.com/java/date-time/localdate-localdatetime-conversions/
		return Period.between(birthday, LocalDate.now()).getYears();
	}
	
	
	
/**Getters and Setters*/
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public float getAmountDue() {
		return amountDue;
	}


	public void setAmountDue(float amountDue) {
		this.amountDue = amountDue;
	}






	public char[] getPassword2() {
		return password2;
	}






	public void setPassword2(char[] password2) {
		this.password2 = password2;
	}
	// TODO: Change constructor to load patient record

	// Getters and setters
	
	public void setBirthdayFromString(String date)
	{
		this.birthday = dateStringToLocalDate(date);
	}

	public void setBirthday(LocalDate db) {
		this.birthday = db;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setSex(String s) {
		this.sex = s;
	} 

	public String getSex() {
		return sex;
	}

	public void setBlood(String b) {
		this.bloodtype = b;
	}

	public String getBlood() {
		return bloodtype;
	}







	public String getRecordNotes() {
		return recordNotes;
	}







	public void setRecordNotes(String recordNotes) {
		this.recordNotes = recordNotes;
	}





	public HashMap<String, ArrayList<LocalDateTime>> getAppointments() {
		return appointments;
	}





	public void setAppointments(HashMap<String, ArrayList<LocalDateTime>> appointments) {
		this.appointments = appointments;
	}


}
