import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


/**
 * MVC Model: Model that deals with the Create Nurse functionality, interacts as the
 * backend to support controller and view.
 * @author Jeremy Fan
 */

public class CreateNewNurseModel extends UserSuperClass {
	
	/* 
	* Variables include name and username inputs, 
	* department of nurse, assigned doctor, and password inputs.
	*/
	private String name;
	private String username;
    private String department;
    private String doctorToAdd;
	private char[] pwd;
	private char[] pwd2;
	
	

	/**
	 * store new authority based on name, username and password given that it passes
	 * a series of tests
	 * @author Sajid C, Jeremy F
	 */
	public String storeInDatabase()
	{
		//check if username already exists in database
		if( Main.dbase.containsKey(username))
		{
			return "Username already exists!";
		}
		
		
		//check that password is long enough; pwd's already verified at this point
		if(pwd.length < 4)
		{
			return "Password must be at least 4 characters!";
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
		
		//check that at least one doctor was selected
		if(doctorToAdd == null)
		{
			return "Please assign at least one doctor!";
		}
		
		
		/**store in database if all checks pass*/
		
		//Create a new nurse with all information collected and store in database
		Main.dbase.put(username, new NurseModel(username, pwd, name,  department, doctorToAdd, new String[0] ) );
		return "Account successfully created!";
	}
	
	
	/**
	 * return the list of doctor names currently in the database, as a String array for the purpose of combobox elements
	 * @author Sajid C
	 * @return list of doctor 
	 */	
	public String[] getDoctorList() {
		
		ArrayList<String> temp = new ArrayList<>(10);
		
		//first item is blank for the drop down list
		temp.add("");
		
		//get all nurses in HashMap database into an array
		//https://javatutorial.net/java-iterate-hashmap-example
		for(Map.Entry<String, UserSuperClass> i: Main.dbase.entrySet())
		{
			//if role is nurse, return nurse name
			if(i.getValue().getRole().compareTo("doctor") == 0)
			{
				
				temp.add(i.getValue().getName() );
			}
		}
		
		//https://stackoverflow.com/questions/4042434/converting-arrayliststring-to-string-in-java
		return temp.toArray(new String[0]);
	}

	
	/**
	 * retrieve from main database a list of departments as a String array for 
	 * input into the combobox elements in Views
	 * @author Sajid C
	 * @return list of department
	 */		
	public String[] getDeptList() {
		
		ArrayList<String> temp = Main.dbaseClass.getDepartmentList();
		if(temp.get(0).compareTo("") != 0)
		{
			temp.add(0, "");
		}
		
		
		return temp.toArray(new String[0]);
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

    public String getDoctorToAdd() {
		return doctorToAdd;
	}

	public void setDoctorToAdd(String doctorToAdd) {
		this.doctorToAdd = doctorToAdd;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}




	
	

}

