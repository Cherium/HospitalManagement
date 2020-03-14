import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class CreateNewDoctorModel {
	
	private String name;
	private String username;
	private String department;
	private ArrayList<String> nurseListToAdd = new ArrayList<>(5);
	private char[] pwd;
	private char[] pwd2;
	
	
	
	
	
	
	
	
	public CreateNewDoctorModel()
	{
		//empty constructor
	}
	
	
	
	
	
	
	
	
	//verify entered information and return an appropriate debug message accordingly
	public String storeInDatabase()
	{
		//check if username already exists in database
		if( Main.dbase.users.containsKey(username))
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
		
		//check that a department was chosen
		if(department.compareTo("") == 0)
		{
			return "Please select a department!";
		}
		
		//check that at least one nurse was selected
		if(nurseListToAdd.size() == 0)
		{
			return "Please assign at least one nurse!";
		}
		
		
		/**store in database if all checks pass*/
		
		//get NurseModel object list of nurses to assign
		ArrayList<NurseModel> temp = new ArrayList<NurseModel>(10);
		for(Map.Entry<String, UserModel> i: Main.dbase.users.entrySet())
		{
			//if Object in DBase has name that matches User selected name
			if(nurseListToAdd.contains(i.getValue().getName()) )
			{
				//add that Object to the Nurse List
				temp.add((NurseModel)i.getValue() );
			}
		}
		//Create a new doctor with all information collected and store in database
		Main.dbase.users.put(username, new DoctorModel(name, username, 
													pwd, department, temp) );
		return "Account successfully created!";
	}
	
	
	//return the list of nurses names currently in the database, as a String array for the purpose of combobox elements
	public String[] getNurseList() {
		
		ArrayList<String> temp = new ArrayList<>(10);
		
		//first item is blank for the drop down list
		temp.add("");
		
		//get all nurses in HashMap database into an array
		//https://javatutorial.net/java-iterate-hashmap-example
		for(Map.Entry<String, UserModel> i: Main.dbase.users.entrySet())
		{
			//if role is nurse, return nurse name
			if(i.getValue().getRole().compareTo("nurse") == 0)
			{
				
				temp.add(i.getValue().getName());
			}
		}
		
		//https://stackoverflow.com/questions/4042434/converting-arrayliststring-to-string-in-java
		return temp.toArray(new String[0]);
	}

	
	
/**Getters and Setters*/
	//return the department list in a string[] format for use with combobox
	public String[] getDeptList() {
		
		ArrayList<String> temp = Main.dbase.getDepartmentList();
		temp.add(0, "");
		
		return temp.toArray(new String[0]);
	}

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

	public ArrayList<String> getNurseListToAdd() {
		return nurseListToAdd;
	}

	public void setNurseListToAdd(ArrayList<String> nurseListToAdd) {
		this.nurseListToAdd = nurseListToAdd;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}




	
	

}

