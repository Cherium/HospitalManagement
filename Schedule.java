import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Schedule
 */
public class Schedule {
    HashMap<String, ArrayList<Appointment>> userSchedule = null;
    
    /**
     * Creates an empty schedule for the next 14 days, starting from now.
     * Each day is segmented into 24 hours. 
     */
    public Schedule() {
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 14; i++) {
            userSchedule.put(date.plusDays(i).toString(), new ArrayList<Appointment>(24));
        }
    }

    public HashMap<String, ArrayList<Appointment>> getSchedule() {
        return userSchedule;
    }

    /**
     * Can be modified to change or delete appointments
     * @param ap
     */

    public void addAppointment(Appointment ap) {
        // Initial thoughts: 
        // Appointments are added as hours, so have the time slot point to the appointment
        int startIndex = ap.getStart().getHour();
        int endIndex = ap.getEnd().getHour();
        String day = ap.getStart().toLocalDate().toString();
        for (int i = startIndex-1; i < endIndex; i++) {
            userSchedule.replace(day, ap);
        }
    }

    public void clearSchedule() {
        
    }

    public void extendSchedule(int days) {
        LocalDate date = LocalDate.now();
        for (int i = 0; i < days; i++) {
            userSchedule.put(date.plusDays(i+14).toString, new ArrayList<Appointment>(24));
        }
    }


}