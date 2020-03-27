import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * handles all calculations, database queries, and the overall work needed to be
 * done for handling this associated role Does NOT interact with the view class
 * directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)
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
	

	//constructor
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
		
		//testing prints
		for(Map.Entry<String, ArrayList<LocalDateTime>> i: this.appointments.entrySet() )
		{
			//print if not lab test
			if(i.getKey().compareTo("labtest") == 0)
			{continue;
				
			}
			System.out.println("Doctor: " + i.getKey() + " Appt: " + i.getValue().get(3).toString() );
		}
	}
	




	//returns a HashMap containing a doctor username on one side and a LIST of appointment start time on the other- patient use
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

	//convert a string of digits to a phone number
	public String convertToPhoneNumber()
	{
		//https://howtodoinjava.com/java/string/format-phone-number/
		return  phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
	}
	
	//convert a float to a String with 2 decimal places
	public String convertToDollar()
	{
		return String.format("$%.2f" , amountDue);
	}

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
	public String verifyInfo()
	{
		//error checks, don't update database in here
		if(true)
		{
			
		}
		
		//update the information in the dbase once all checks pass
		return "Information Successfully Updated";
	}
	
	public String getPatientInformation() {
		StringBuilder bob = new StringBuilder();

		bob.append("Name: " + getName());
		bob.append("\n");
		bob.append("Age: " + getAge());
		bob.append("\n");
		bob.append("Sex: " + getSex());
		bob.append("\n");
		bob.append("Blood type: " + getBlood());
		bob.append("\n");
		bob.append("Address: " + getAddress());
		bob.append("\n");
		bob.append("Phone: " + getPhoneNumber());
		bob.append("\n");
		bob.append("Email: " + getEmail());


		return bob.toString();
	}
	
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
