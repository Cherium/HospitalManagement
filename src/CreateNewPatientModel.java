import java.util.Arrays;


/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class CreateNewPatientModel extends UserSuperClass {
	
	private String name;
	private String username;
	private char[] pwd;
	private char[] pwd2;
	private String address;
	private String phoneNumber;
	private String email;
	
	private String dob;
	private String bloodType;
	private String sex;
	
	
	
	
	
	
	
	
	public CreateNewPatientModel()
	{
		//empty constructor
	}
	
	
	
	
	
	
	
	//verify entered information and return an appropriate debug message accordingly
	public String storeInDatabase()
	{
		//check if username already exists in database
		if( Main.dbase.containsKey(username))
		{
			return "Username already exists!";
		}
		
		//check if passwords match
		if(Arrays.equals(pwd,pwd2) == false)
		{
			return "Passwords don't match!";
		}
		
		//check that username is long enough
		if(username.length() < 4)
		{
			return "Username must be at least 4 characters!";
		}
		
		//check that password is long enough; pwd's already verified at this point
		if(pwd.length < 4)
		{
			return "Password must be at least 4 characters!";
		}

		//check that a name was entered
		if(name.length() < 1)
		{
			return "Please enter a name!";
		}
		
		//check that remaining fields are not empty
		if(address.length() == 0 || phoneNumber.length() == 0 || email.length() == 0)
		{
			return "No Field Should be empty!";
		}
		

		//store in database if all checks pass
		Main.dbase.put(username
				, new PatientModel(username, pwd, name, address, phoneNumber, email, 0
						, dob, bloodType, sex, "", null));
		return "Account successfully created!";
		
	}
	
	

	
/**Getters and Setters*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPwd() {
		return pwd;
	}

	public void setPwd(char[] pwd) {
		this.pwd = pwd;
	}

	public char[] getPwd2() {
		return pwd2;
	}

	public void setPwd2(char[] pwd2) {
		this.pwd2 = pwd2;
	}







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

	public String getDob() {
		return dob;
	}

	public void setDob(String bd) {
		this.dob = bd;
	}







	public String getBloodType() {
		return bloodType;
	}







	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}







	public String getSex() {
		return sex;
	}







	public void setSex(String sex) {
		this.sex = sex;
	}
	
	

}
