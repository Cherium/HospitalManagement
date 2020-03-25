import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private Boolean[] scheduledDays;

    private HashMap<String, String> appointments;

    private ArrayList<LocalDate> timeOff;

    // Dummy schedule
    public Schedule() {
        initScheduledDays();
        initAppointments();
        this.timeOff = new ArrayList<LocalDate>(0);
    }

    public Schedule(Boolean[] daysWorked, HashMap<String, String> appointments, ArrayList<LocalDate> timeOff) {
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

    private void initAppointments() {
        this.appointments = new HashMap<String, String>(20);
        LocalDateTime today = LocalDateTime.now();
        for (int i = 0; i < 10; i++) {
            LocalDateTime day = today.plusDays(ThreadLocalRandom.current().nextInt(0, 30+1)).withMinute(0).withSecond(0).withNano(0);
            getAppointments().put(day.toString(), "patient");
        }
        for (int i = 0; i < 10; i++) {
            LocalDateTime day = today.plusDays(ThreadLocalRandom.current().nextInt(0, 30+1)).withMinute(0).withSecond(0).withNano(0);
            getAppointments().put(day.toString(), "patient2");
        }
    }

    private void initScheduledDays() {
        this.scheduledDays = new Boolean[7];
        this.scheduledDays[0] = false;
        this.scheduledDays[1] = true;
        this.scheduledDays[2] = true;
        this.scheduledDays[3] = true;
        this.scheduledDays[4] = true;
        this.scheduledDays[5] = true;
        this.scheduledDays[6] = false;
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
    public void setAppointments(HashMap<String,String> ap) {
        this.appointments = ap;
    }

    public HashMap<String, String> getAppointments() {
        return appointments;
    }

    // Time off are stored as an ArrayList of LocalDates
    public void setTimeOff(ArrayList<LocalDate> tO) {
        this.timeOff = tO;
    }

    public ArrayList<LocalDate> getTimeOff() {
        return timeOff;
    }

    public static void main(String[] args) {
        Schedule s = new Schedule();
        System.out.println(s.toString());
        LocalDate now = LocalDate.now();
        LocalDate future = now.plusMonths(2);
        now = future;
        System.out.println(now.toString());
        System.out.println(LocalDate.now());
    }

}