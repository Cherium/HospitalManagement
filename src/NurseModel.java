/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class NurseModel extends UserModel {
	
	private String department;
	
	private DoctorModel assignedDoc;

	
	//constructor for database reading
	public NurseModel(String username, DoctorModel assignedDoc)
	{
		this.assignedDoc = assignedDoc;
		this.username = username;
	}
	
	//another constructor
	public NurseModel(String name, String username, char[] password
			, String department, DoctorModel assignedDoc){
		
		super(name, username, password);
		this.setAssignedDoc(assignedDoc);
		setRole("nurse");
	}

	
	
	
	
	
	
	
/**Getters and Setters*/
	
	public DoctorModel getAssignedDoc() {
		return assignedDoc;
	}

	public void setAssignedDoc(DoctorModel assignedDoc) {
		this.assignedDoc = assignedDoc;
	}
	

}
