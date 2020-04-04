import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

/**
 * handles all calculations, database queries, and the overall work needed to be
 * done for handling this associated role Does NOT interact with the view class
 * directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.)
 * authority
 */
public class AuthorityModel extends UserSuperClass {

	public HashMap<String, UserSuperClass> users;
	public HashMap<String, UserSuperClass> appoints;

	//Total number of each one
	private int patientNum;
	private int doctorNum;
	private int nurseNum;

	private int upcomeMonth;
	private int upcomeDay;


	private String[] allPatientsUsernames;

	//constructor
	public AuthorityModel(String username, char[] password, String name, String[] strings) {

		super(name, username, password);
		setRole("authority");
		
	}

	/**
	 * Gathers all necessary information and keeps track of them
	 * @author Neil M
	 *
	 */
	public void gatherInfo(){
		LocalDateTime currentDate = LocalDateTime.now();

		//Instatntiates values as 0
			//How many appointments within a timeframe
		upcomeMonth = 0;
		upcomeDay = 0;
			//How many existing users of each role
		patientNum = 0;
		doctorNum = 0;
		nurseNum = 0;

		
		
		

		for(Map.Entry<String, UserSuperClass> i: Main.dbase.entrySet()){
			if(i.getValue().getRole().compareTo("patient") == 0 ){
				patientNum++; 
				String c = i.getValue().getUsername();
				PatientModel pat = (PatientModel) Main.dbase.get(c);
				HashMap<String, ArrayList<LocalDateTime>> appointments = pat.getAppointments();
				for (Map.Entry<String, ArrayList<LocalDateTime>> j:appointments.entrySet()){
					for(LocalDateTime t:j.getValue()){
						boolean isBefore1 = (t.isBefore(currentDate.plusDays(60))&&(t.isAfter(currentDate)));
						if(isBefore1)
							upcomeMonth++;
						boolean isBefore2 = (t.isBefore(currentDate.plusDays(1))&&(t.isAfter(currentDate)));
						if(isBefore2)
							upcomeDay++;
					}
				}
			}
			if(i.getValue().getRole().compareTo("doctor") == 0 ){
				doctorNum++;
			}
			if(i.getValue().getRole().compareTo("nurse") == 0 ){
				nurseNum++;
			}
		}
	}


	/*
	* #To do
	* # of patients seen by a department
	* # of doctors/nurses in a department
	* # of appointments scheduled for a doctor/nurse
	* # of appointments in a department
	* # of patients scheduled by a doctor
	# @author Neil M
	*/
	public void departInfo(){
		ArrayList<String> temp = Main.dbaseClass.getDepartmentList();
		System.out.println();

		for(Map.Entry<String, UserSuperClass> i: Main.dbase.entrySet()){
			i.getValue().getDeptList();
			System.out.println(i);
			i.getValue();
			System.out.println(i);
			//{
				
				//String c = i.getValue().getUsername();
			//}
		}
	}



		/*
	* #To do - find a better method to track this
	* # of patients seen by a department
	* # of doctors/nurses in a department
	* # of appointments scheduled for a doctor/nurse
	* # of appointments in a department
	* # of patients scheduled by a doctor
	* @author Neil M
	*/

	public int getDepNum(){
		ArrayList<String> temp = Main.dbaseClass.getDepartmentList();
		
		return temp.size();
	}

	//Getters and Setters


	public int getUpcomeMonth(){
		return upcomeMonth;
	}
	
	public int getUpcomeDay(){
		return upcomeDay;
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



	public String[] getAllPatientsUsernames() {
		return allPatientsUsernames;
	}
	

}