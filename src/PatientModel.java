
/**handles all calculations, database queries, and the overall work needed to be done for handling this associated role
 * Does NOT interact with the view class directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)*/
public class PatientModel extends UserModel {


	//constructor
	public PatientModel(String name, String username, char[] password) 
	{
		
		super(name,username,password);
		setRole("patient");
		// TODO Auto-generated constructor stub
		
		
	}
	
	

}
