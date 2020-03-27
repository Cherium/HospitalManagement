import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

    /**
     * A class containing useful methods
     */

	public class Schedule {
	
	    // constructor
	    public Schedule() {
	
	    }
    
    
    // Takes a list of patient usernames and a doctor username, returning all the appointments that the patients
    // in the list have scheduled with the doctor
    public HashMap<String, ArrayList<LocalDateTime>> updateHashMap(ArrayList<String> patU, String docU) {

        HashMap<String, ArrayList<LocalDateTime>> hm = new HashMap<String, ArrayList<LocalDateTime>>();

        for (String p : patU) {
            // get all the appointments for the patient for a specific doctor, assuming that the appointment
            // information in patient is stored as a HashMap, keys = doctor username, values = ArrayList<LocalDateTime>

            // Assuming that the list of appointments is unique to PatientModel only

            PatientModel pat = (PatientModel) Main.dbase.get(p);
            ArrayList<LocalDateTime> apts = pat.getAppointments().get(docU);

            // check that there are appointments between this patient and doctor, failsafe
            if (apts != null)
                hm.put(p, apts);

        }

        return hm;
    }

    //1-takes in raw availability change request data and returns a properly formatted, and updated, availability array
    public LocalDateTime[] updateSchedule(String[] rawData)
    {
        LocalDateTime[] temp = new LocalDateTime[14];
        for (int i = 0; i < rawData.length; i++) {
            LocalDateTime convert = LocalDateTime.parse("2001-01-01T"+rawData[i]);
            temp[i] = convert;
        }
        return temp;
        
    }

    //2-retrieves this users availability as a string array for use in view
    //input: users availability array; output: strings in format HH:MM for use in view
    public String[] updateSchedule(LocalDateTime[] currentAvailability)
    {
        String[] rawTimes = new String[14];
        
        for (int i = 0; i < currentAvailability.length; i++) 
        {
            rawTimes[i] = currentAvailability[i].getHour() + ":" + currentAvailability[i].getMinute();
        }
        
        return rawTimes;
        
    }

}