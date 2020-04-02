import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;

/**
 * handles all calculations, database queries, and the overall work needed to be
 * done for handling this associated role Does NOT interact with the view class
 * directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)
 */
public class AuthorityModel extends UserSuperClass {

	public HashMap<String, UserSuperClass> users;

	private int patientNum;
	private int doctorNum;
	private int nurseNum;

	//constructor
	public AuthorityModel(String username, char[] password, String name) {

		super(name, username, password);
		setRole("authority");
	}
	
	public int getDepNum(){
		ArrayList<String> temp = Main.dbaseClass.getDepartmentList();
		
		return temp.size();
	}

	public void getStats(){
		users = Main.dbaseClass.getUsers();
		Iterator<String> itr = users.keySet().iterator();

		//Iterator<String> itr = users.keySet().iterator();
		int patientNum = 0;
		int doctorNum = 0;
		int nurseNum = 0;

		while(itr.hasNext()){

			String a = itr.next();
			String b = Main.dbase.get(a).getRole();
			if(b.compareTo("patient") == 0){
				patientNum++;
			}
			if(b.compareTo("doctor")==0){
				doctorNum++;
			}
			if (b.compareTo("nurse")==0){
				nurseNum++;
			}

		}
		this.patientNum = patientNum;
		this.doctorNum = doctorNum;
		this.nurseNum = nurseNum;
	}

	public int getPatientNum(){
		return patientNum;
	}

	public void setPatientNum(int val){
		patientNum = val;
	}
	
	public int getDoctorNum(){
		return doctorNum;
	}

	public void setDoctorNum(int val){
		doctorNum = val;
	}

	public int getNurseNum(){
		return nurseNum;
	}

	public void setNurseNum(int val){
		nurseNum = val;
	}

	

}
