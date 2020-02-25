
public class Nurse extends HospitalEmployee {
	Doctor assignedDoctor;

	public Nurse(String name, String username, String password, String department, Doctor doc) {
		super(name, username, password, department);
		setRole("nurse");
		this.assignedDoctor = doc;
	}

	public void setDoctor(Doctor doc) {
		assignedDoctor = doc;
	}
	
	public Doctor getDoctor() {
		return assignedDoctor;
	}
}
