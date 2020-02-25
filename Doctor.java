import java.util.ArrayList;

public class Doctor extends HospitalEmployee {
	ArrayList<Nurse> assignedNurses;
	
	public Doctor(String name, String username, String password, String department, ArrayList<Nurse> nurses) {
		super(name, username, password, department);
		setRole("doctor");
		this.assignedNurses = nurses;
	}
	
	public void removeNurse(Nurse nurse) {
		assignedNurses.remove(nurse);
	}
	
	public void addNurse(Nurse nurse) {
		assignedNurses.add(nurse);
	}
	
	public ArrayList<Nurse> getNurses() {
		return assignedNurses;
	}
}
