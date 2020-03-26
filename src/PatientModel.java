import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
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

	//constructor
	public PatientModel(String username, char[] password, String name
			, String addr, String phNumber, String email, float amountDue, String dob, String bloodType, String sex2, String record2)
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
	/* 
	From the latest pull, we also need a toString method in PatientModel that takes all the instance variables 
	in the PatientModel class(not as inputs, just uses them) EXCEPT for the last instance variable , 
	and returns a single string, seperated by tabs, in the exact same format as the lines for 'patients' 
	in the src/dbase/dbase.txt file.
	*/

	/*
	private String address;
	private String phoneNumber;
	private String email;
	private char[] password2;
	private float amountDue;
	private LocalDate birthday;
	private String bloodtype;
	private String sex;
	*/

	public String patientModelToString() {
		String info = getAddress() + "\t" + getPhoneNumber() + "\t" + getEmail() + "\t" + getPassword2().toString() + "\t" + getAmountDue() + "\t" + getBirthday() + "\t" + getBlood() + "\t" + getSex();
		return info;
	}

}
