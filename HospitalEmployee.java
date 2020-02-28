
public class HospitalEmployee extends User {
	// Schedule schedule;
	String department;

	public HospitalEmployee(String name, String username, char[] password, String department) {
		super(name, username, password);
		this.department = department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDepartment() {
		return department;
	}
	
//	public void setSchedule(Schedule schedule) {
//		this.schedule = schedule;
//	}
//	
//	public Schedule getSchedule() {
//		return schedule;
//	}
	
}
