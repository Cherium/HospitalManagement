import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @param scheduledDays list the days that someone (doctor/nurse for now) is
 *                      working. The first day of the week starts on SUNDAY, by
 *                      the Canadian calendar. If "SUNDAY" is true, then the
 *                      object is scheduled to work on that day. Otherwise they
 *                      are not scheduled to work on that day.
 * @param appointments  list the scheduled appointments that someone (doctor)
 *                      has on a day (String). Appointments will be added to
 *                      this list such that the days only correspond to
 *                      scheduled days that a doctor is working.
 * @param timeOff       list the days that someone has required time off.
 */

public class Schedule {
    private LinkedHashMap<String, Boolean> scheduledDays;

    // Appointments and time off does not have to be implemented as below!
    
    // private HashMap<String, ArrayList<Appointments>> appointments = new HashMap<String, ArrayList<Appointments>>(0);
    // private ArrayList<LocalDate> timeOff = new ArrayList<LocalDate>(0);

    // TODO: Implement appointments

    // TODO: Implement time offs

    public Schedule(Boolean[] daysWorked) {
        this.scheduledDays = initSchedule();
        setScheduledDays(daysWorked);
    }

    public Schedule() {
        this.scheduledDays = initSchedule();
        Boolean[] a = new Boolean[7];
        for (int i = 0; i < 7; i++) {
            if (i == 0 || i == 6) {
                a[i] = false;
            } else {
                a[i] = true;
            }
        }
        setScheduledDays(a);
    }

    public LinkedHashMap<String, Boolean> initSchedule() {
        LinkedHashMap<String, Boolean> week = new LinkedHashMap<String, Boolean>(7);
        week.put("SUNDAY", false);
        week.put("MONDAY", true);
        week.put("TUESDAY", true);
        week.put("WEDNESDAY", true);
        week.put("THURSDAY", true);
        week.put("FRIDAY", true);
        week.put("SATURDAY", false);
        return week;
    }

    public String toString() {
        return getScheduledDays().toString();
    }

    // Getters and setters

    public void setScheduledDays(Boolean[] newDays) {
        int i = 0;
        for (String s : getScheduledDays().keySet()) {
            getScheduledDays().put(s, newDays[i]);
            i++;
        }
    }

    public LinkedHashMap<String, Boolean> getScheduledDays() {
        return scheduledDays;
    }

    public static void main(String[] args) {
        Schedule testSchedule = new Schedule();
        System.out.println(testSchedule.toString());
        Boolean[] a = new Boolean[7];
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 1) {
                a[i] = false;
            } else {
                a[i] = true;
            }
        }
        testSchedule.setScheduledDays(a);
        System.out.println(testSchedule.toString());

    }
}