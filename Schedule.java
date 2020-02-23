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

    public void addAppointment(Appointment ap) {
        // Initial thoughts: 
        // Appointments are added as hours, so have the time slot point to the appointment
    }


}