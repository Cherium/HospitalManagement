import java.util.ArrayList;

public class ReceptionistModel extends UserSuperClass {
	
	
	
	
	private ArrayList<String> allPatients = new ArrayList<String>();
	
	
	//constructor
	public ReceptionistModel(String username, char[] password, String name)
	{
		
		super(name, username, password);
		setRole("receptionist");
	}

	

	public ArrayList<String> getScheduledPatientsUsernames() {
		return allPatients;
	}

	public void setScheduledPatientsUsernames(ArrayList<String> patients) {
		this.allPatients = new ArrayList<String>(patients);
	}
	

}
