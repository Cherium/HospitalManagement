
public class NurseModel extends UserModel {
	
	private String department;
	
	private DoctorModel assignedDoc;

	//constructor for database reading
	public NurseModel(String username, DoctorModel assignedDoc)
	{
		this.assignedDoc = assignedDoc;
		this.username = username;
	}
	
	public NurseModel(String name, String username, char[] password
			, String department, DoctorModel assignedDoc){
		
		super(name, username, password);
		this.setAssignedDoc(assignedDoc);
		setRole("nurse");
	}

	public DoctorModel getAssignedDoc() {
		return assignedDoc;
	}

	public void setAssignedDoc(DoctorModel assignedDoc) {
		this.assignedDoc = assignedDoc;
	}
	

}
