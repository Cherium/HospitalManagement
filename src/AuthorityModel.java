import java.util.ArrayList;
import java.util.HashMap;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.util.Iterator;
>>>>>>> parent of 25a57b9... Better accurate version of Authority
import java.util.Map;
=======
import java.util.Iterator;
>>>>>>> parent of 54654e0... Updating Authority

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

<<<<<<< HEAD
<<<<<<< HEAD
	private int upcomeMonth;
	private int upcomeDay;

=======
>>>>>>> parent of 25a57b9... Better accurate version of Authority

	private String[] allPatientsUsernames;

=======
>>>>>>> parent of 54654e0... Updating Authority
	//constructor
	public AuthorityModel(String username, char[] password, String name, String[] strings) {

		super(name, username, password);
		setRole("authority");
	}
<<<<<<< HEAD


	//Get the available appointments within 2 months
	//Author Neil
	public int getAvail(){
		int appointVal = 0;
		LocalDateTime compare = LocalDateTime.now().plusDays(60);
		for(Map.Entry<String, UserSuperClass> i: Main.dbase.entrySet()){
			if(i.getValue().getRole().compareTo("patient") == 0 ){
				String c = i.getValue().getUsername();
				PatientModel pat = (PatientModel) Main.dbase.get(c);
				HashMap<String, ArrayList<LocalDateTime>> appointments = pat.getAppointments();
				for (Map.Entry<String, ArrayList<LocalDateTime>> j:appointments.entrySet()){
					for(LocalDateTime t:j.getValue()){
						boolean isBefore = t.isBefore(compare);
						if(isBefore)
							appointVal++;
					}
				}
				
			}
		}
		return appointVal;
	}




	//Gets the amount of existing patients, doctors, and nurses
	//Author - Neil
	public void getStats(){
		users = Main.dbaseClass.getUsers();
		Iterator<String> itr = users.keySet().iterator();

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

		System.out.println("The date is "+LocalDateTime.now().plusDays(60));
	}


	//Getters and Setters
	

	public int getDepNum(){
		ArrayList<String> temp = Main.dbaseClass.getDepartmentList();
		
		return temp.size();
=======
	
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
>>>>>>> parent of 54654e0... Updating Authority
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