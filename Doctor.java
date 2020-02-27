import java.util.ArrayList;

public class Doctor extends HospitalEmployee {
	ArrayList<Nurse> assignedNurses = null;
	
	public Doctor(String name, String username, String password, String department, ArrayList<Nurse> nurses) {
		super(name, username, password, department);
		setRole("doctor");
		this.assignedNurses = new ArrayList<Nurse>();
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

	@Override
	public String toString() {
		StringBuilder bob = new StringBuilder(super.toString());
		bob.append("Assigned nurses: \n");
		for (Nurse n : getNurses()) {
			bob.append("\t");
			bob.append(n.getName());
			bob.append("\n");
		}
		return bob.toString();

	}
}
