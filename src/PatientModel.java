import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;

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
	private String record;
	private LocalDate birthday;
	private String bloodtype;
	private char sex;
	

	//constructor
	public PatientModel(String username, char[] password, String name
			, String addr, String phNumber, String email, float amountDue)
	// public PatientModel(String username, char[] password, String name
	// , String addr, String phNumber, String email, float amountDue, String bday, String btype, char sex)
	{
		
		super(name,username,password);
		setRole("patient");
		this.address = addr;
		this.phoneNumber = phNumber;
		this.email = email;
		this.amountDue = amountDue;
		// this.birthday = LocalDate.parse(bday);
		// this.bloodtype = btype;
		// this.sex = sex;
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
		return Period.between(getBirthday(), LocalDate.now()).getYears();
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

	public void setRecord(String record) {
		this.record = record;
	}
	
	public String getRecord() {
		return record;
	}

	public void setBirthday(LocalDate db) {
		this.birthday = db;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setSex(char s) {
		this.sex = s;
	} 

	public char getSex() {
		return sex;
	}

	public void setBlood(String b) {
		if (b.equals("A-") || b.equals("A+") || b.equals("B-") || b.equals("B+") || b.equals("AB-") || b.equals("AB+") || b.equals("O-") || b.equals("O+")) {
			this.bloodtype = b;
		}
	}

	public String getBlood() {
		return bloodtype;
	}

}
