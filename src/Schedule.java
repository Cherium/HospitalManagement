import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @param scheduledDays list the days that someone (doctor/nurse for now) is
 *                      working. The first day of the week starts on SUNDAY, by
 *                      the Canadian calendar. If true, they are scheduled to work 
 *                      on that day, if false, they're not scheduled.
 * @param appointments  list the scheduled appointments that someone (doctor)
 *                      has on a day (String). Appointments will be added to
 *                      this list such that the days only correspond to
 *                      scheduled days that a doctor is working.
 *                      Appointments are of the form <String, String> that
 *                      correspond to <LocalDateTime.toString(), username>.
 * @param timeOff       list the days that someone has requested time off.
 */

	public class Schedule {
	
	    // constructor
	    public Schedule() {
	
	    }
    
    
    
    
  
    

   // Initialize 60 random appointments for 2 patients, within 30 days of the current date. 
    private void initAppointments() {
    	LinkedHashMap<String, String> appointments = new LinkedHashMap<String, String>(20);
        LocalDateTime today = LocalDateTime.now();
        for (int i = 0; i < 30; i++) {
            LocalDateTime day = today.plusDays(ThreadLocalRandom.current().nextInt(0, 30+1)).withMinute(0).withSecond(0).withNano(0).withHour(ThreadLocalRandom.current().nextInt(0, 24));
            appointments.put(day.toString(), "patient");
        }
        for (int i = 0; i < 30; i++) {
            LocalDateTime day = today.plusDays(ThreadLocalRandom.current().nextInt(0, 30+1)).withMinute(0).withSecond(0).withNano(0).withHour(ThreadLocalRandom.current().nextInt(0, 24));
            appointments.put(day.toString(), "patient2");
        }

    }

    // Initialize a random schedule, may end up working 7 days or not working at all 
    private void initScheduledDays() {
        Boolean[] scheduledDays = new Boolean[7];
        for (int i = 0; i < 7; i++) {
            if (ThreadLocalRandom.current().nextInt(0,1+1) == 1) {
                scheduledDays[i] = true;
            } else {
                scheduledDays[i] = false;
            }
        }
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
    // Getters and setters

    // Scheduled days are of the form Boolean[7], corresponding to 7 days of a week
//    public void setScheduledDays(Boolean[] newDays) {
//        this.scheduledDays = newDays;
//    }
//
//    public Boolean[] getScheduledDays() {
//        return scheduledDays;
//    }
//
//    // Appointments are of the form <LocalDateTime.toString(), username>
//    public void setAppointments(LinkedHashMap<String,String> ap) {
//        this.appointments = ap;
//    }
//
//    public LinkedHashMap<String, String> getAppointments() {
//        return appointments;
//    }
//
//    // Time off are stored as an ArrayList of LocalDates
//    public void setTimeOff(ArrayList<LocalDate> tO) {
//        this.timeOff = tO;
//    }
//
//    public ArrayList<LocalDate> getTimeOff() {
//        return timeOff;
//    }

//    public static void main(String[] args) {
//        LocalDateTime[] dummy = new LocalDateTime[14];
//        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
//        for (int i = 0; i < 7; i++) {
//            dummy[i*2] = now.withHour(8);
//            dummy[i*2+1] = now.withHour(17);
//        }
//        for (LocalDateTime localDateTime : dummy) {
//            System.out.println(localDateTime);
//        }
//
//    }

}