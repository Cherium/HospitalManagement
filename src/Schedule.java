import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

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
    private Boolean[] scheduledDays;

    private LinkedHashMap<String, String> appointments;

    private ArrayList<LocalDate> timeOff;

    public Schedule(Boolean[] daysWorked, LinkedHashMap<String, String> appointments, ArrayList<LocalDate> timeOff) {
        this.scheduledDays = daysWorked;
        this.appointments = appointments;
        this.timeOff = timeOff;
    }

    public String toString() {
        StringBuilder bob = new StringBuilder();

        for (Boolean b : getScheduledDays()) {
            if (b)
                bob.append(1);
            else
                bob.append(0);
        }
        bob.append("#");
        for (Entry<String, String> pair : getAppointments().entrySet()) {
            bob.append(pair.getKey()+","+pair.getValue()+"&");
        }
        bob.append("#");
        for (LocalDate lDate : getTimeOff()) {
            bob.append(lDate.toString()+",");
        }

        return bob.toString();
    }

    // Getters and setters

    // Scheduled days are of the form Boolean[7], corresponding to 7 days of a week
    public void setScheduledDays(Boolean[] newDays) {
        this.scheduledDays = newDays;
    }

    public Boolean[] getScheduledDays() {
        return scheduledDays;
    }

    // Appointments are of the form <LocalDateTime.toString(), username>
    public void setAppointments(LinkedHashMap<String,String> ap) {
        this.appointments = ap;
    }

    public LinkedHashMap<String, String> getAppointments() {
        return appointments;
    }

    // Time off are stored as an ArrayList of LocalDates
    public void setTimeOff(ArrayList<LocalDate> tO) {
        this.timeOff = tO;
    }

    public ArrayList<LocalDate> getTimeOff() {
        return timeOff;
    }


}