import java.util.ArrayList;

public class ReceptionistModel extends UserSuperClass {
	
	
	
	
	private ArrayList<String> allPatients = new ArrayList<String>();
	
	
	//constructor
	public ReceptionistModel(String username, char[] password, String name, String[] avail)
	{
		
		super(name, username, password);
		setRole("receptionist");
		
		this.availability = arrayToLDTArray(avail);
	}

	

	public ArrayList<String> getScheduledPatientsUsernames() {
		return allPatients;
	}

	public void setScheduledPatientsUsernames(ArrayList<String> patients) {
		this.allPatients = new ArrayList<String>(patients);
	}
	

}
