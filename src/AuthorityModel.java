import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * handles all calculations, database queries, and the overall work needed to be
 * done for handling this associated role Does NOT interact with the view class
 * directly, and also does NOT interact with the Controller class(The Controller
 * class interacts with this class, not the other way around.) authority
 */
public class AuthorityModel extends UserSuperClass {

	public HashMap<String, UserSuperClass> users;
	public HashMap<String, UserSuperClass> appoints;

	// Total number of each one
	private int patientNum;
	private int doctorNum;
	private int nurseNum;
	private int upcomeMonth;
	private int upcomeDay;

	// Required for today
	private int upcomeDoc;
	private int upcomeNur;

	// Department related
	private int cardioCount;
	private int nephroCount;
	private int neuroCount;
	private int recepCount;
	private int erCount;

	private String[] allPatientsUsernames;

	// constructor
	public AuthorityModel(String username, char[] password, String name, String[] strings) {

		super(name, username, password);
		setRole("authority");

	}

	/**
	 * Gathers all necessary information and keeps track of them
	 * 
	 * @author Neil M
	 *
	 */
	public void gatherInfo() {
		LocalDateTime currentDate = LocalDateTime.now();

		// Instatntiates values as 0
		// How many appointments within a timeframe
		upcomeMonth = 0;
		upcomeDay = 0;
		// How many existing users of each role
		patientNum = 0;
		doctorNum = 0;
		nurseNum = 0;
		upcomeDoc = 0;

		cardioCount = 0;
		nephroCount = 0;
		neuroCount = 0;
		recepCount = 0;
		erCount = 0;
		// Checks for if a Doctor has been checked
		boolean docCheck, nurCheck;

		for (Map.Entry<String, UserSuperClass> i : Main.dbase.entrySet()) {
			String c = i.getValue().getUsername();
			docCheck = false;
			nurCheck = false;

			if (i.getValue().getRole().compareTo("patient") == 0) {
				patientNum++;

				PatientModel pat = (PatientModel) Main.dbase.get(c);

				HashMap<String, ArrayList<LocalDateTime>> appointments = pat.getAppointments();
				for (Map.Entry<String, ArrayList<LocalDateTime>> j : appointments.entrySet()) {
					for (LocalDateTime t : j.getValue()) {
						boolean isAfter = (t.isAfter(currentDate));
						if (isAfter) {
							boolean isBefore1 = t.isBefore(currentDate.plusDays(60));
							if (isBefore1)
								upcomeMonth++;
							boolean isBefore2 = t.isBefore(currentDate.plusDays(1));
							if (isBefore2)
								upcomeDay++;// */
						}
					}
				}
			}
			if (i.getValue().getRole().compareTo("doctor") == 0) {

				doctorNum++;

				DoctorModel doc = (DoctorModel) Main.dbase.get(c);
				String department = doc.getDepartment();

				HashMap<String, ArrayList<LocalDateTime>> appointments = doc.getAppointments();

				/// NOTICE!! This thing crashes if you reopen Authority after creating a new
				/// doctor
				// It might be because there's a null point java error.
				// The new doctor does not have any new appointments
				// Check with Jeremy

				for (Map.Entry<String, ArrayList<LocalDateTime>> j : appointments.entrySet()) {
					if (j != null) {
						for (LocalDateTime t : j.getValue()) {
							boolean isAfter = (t.isAfter(currentDate)) && (t.isBefore(currentDate.plusDays(60)));
							if (isAfter) {
								if (!docCheck) {
									upcomeDoc++;
									docCheck = true;
								}
							}
						}
					}
				} // */

				updateCount(department);

			}
			if (i.getValue().getRole().compareTo("nurse") == 0) {
				nurseNum++;
				NurseModel nur = (NurseModel) Main.dbase.get(c);
				String department = nur.getDepartment();
			}
		}

	}

	public void updateCount(String department) {
		switch (department) {
		case "Cardiology":
			cardioCount++;
			break;
		case "Nephrology":
			nephroCount++;
			break;
		case "Neurology":
			neuroCount++;
			break;
		case "Receptionist":
			recepCount++;
			break;
		case "ER":
			erCount++;
			break;
		}
	}

	public void departInfo() {

	}

	/**
	 * prints out values for database export
	 * 
	 * @author Muhammad R
	 * @return the string stored in dbase.txt
	 */
	public String toStringDbase() {
		StringBuilder bob = new StringBuilder();
		bob.append(getRole());
		bob.append("\t");
		bob.append(getUsername());
		bob.append("\t");
		bob.append(getPassword());
		bob.append("\t");
		bob.append(getName());
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("0");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");
		bob.append("\t");
		bob.append("null");

		return bob.toString();

	}

	/**
	 * prints out values for database export
	 * 
	 * @author Muhammad R
	 * @return list of availability to store in Availability.txt
	 */
	public String toStringAvailability() {
		StringBuilder bob = new StringBuilder();

		for (int i = 0; i < super.getAvailability().length; i++) {
			bob.append(super.getAvailability()[i]);
			bob.append("\n");

		}
		if (bob.length() > 0)
			bob.deleteCharAt(bob.length() - 1);
		return bob.toString();
	}

	/*
	 * Gets the department total number here
	 * 
	 * @author Neil M
	 */

	public int getDepNum() {
		ArrayList<String> temp = Main.dbaseClass.getDepartmentList();

		return temp.size();
	}

	// Getters and Setters

	public int getUpcomeMonth() {
		return upcomeMonth;
	}

	public int getUpcomeDay() {
		return upcomeDay;
	}

	public int getPatientNum() {
		return patientNum;
	}

	public void setPatientNum(int val) {
		patientNum = val;
	}

	public int getDoctorNum() {
		return doctorNum;
	}

	public void setDoctorNum(int val) {
		doctorNum = val;
	}

	public int getNurseNum() {
		return nurseNum;
	}

	public void setNurseNum(int val) {
		nurseNum = val;
	}

	public int getCardio() {
		return cardioCount;
	}

	public int getNephro() {
		return nephroCount;
	}

	public int getNeuro() {
		return neuroCount;
	}

	public int getRecep() {
		return recepCount;
	}

	public int getER() {
		return erCount;
	}

	public int getDocToday() {
		return upcomeDoc;
	}

	public int getNurToday() {
		return upcomeNur;
	}

	public String[] getAllPatientsUsernames() {
		return allPatientsUsernames;
	}

}
