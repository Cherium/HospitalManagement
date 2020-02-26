
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

	@Override
	public String toString() {
		StringBuilder bob = new StringBuilder(super.toString());
		bob.append("Assigned Doctor: ");
		bob.append(getDoctor().getName());
		bob.append("\n");
		return bob.toString();
	}
}
