import java.util.ArrayList;

public class DoctorModel extends UserModel {
	
	//private DoctorModel doctor;
	private String department;
	private ArrayList<NurseModel> nurses = new ArrayList<NurseModel>(10);
	
	
//	public DoctorModel(UserModel u)
//	{
//		this.doctor = (DoctorModel) u;		//cast the user to a doctor, since it is 
//	}
//	
	//constructor for import/export
	public DoctorModel(String name, String username, char[] password, String department)
	{
		super(name, username, password);
		this.department = department;
		setRole("doctor");
	}
	
	//main constructor
	public DoctorModel(String name, String username, char[]password, String department, ArrayList<NurseModel> nurses) {
		
			super(name, username, password);
			this.department = department;
			setRole("doctor");	//department gets set but not role
			
			//get list of nurses
			for (int i = 0; i < nurses.size(); i++) {
				
				this.nurses.add(nurses.get(i) );
			}
			
			
			
		// TODO Auto-generated constructor stub
	}




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
//	public DoctorModel getDoctor() {
//		return doctor;
//	}
//
//
//	public void setDoctor(DoctorModel doctor) {
//		this.doctor = doctor;
//	}

	
}
