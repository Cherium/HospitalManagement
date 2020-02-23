import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Appointment
 */
public class Appointment {
    Doctor mainDoctor = null;
    Patient patient = null;
    ArrayList<Doctor> additionalDoctors = null;
    LocalDateTime startTime = null;
    LocalDateTime endTime = null;

    public Appointment(Appointment copy) {
        this.mainDoctor = copy.getMainDoctor();
        this.patient = copy.getPatient();
        this.additionalDoctors = copy.getAdditionalDoctors();
        this.startTime = copy.getStart();
        this.endTime = copy.getEnd();
    }

    LocalDateTime getEnd() {
        return endTime;
    }

    LocalDateTime getStart() {
        return startTime;
    }

    private ArrayList<Doctor> getAdditionalDoctors() {
        return additionalDoctors;
    }

    private Patient getPatient() {
        return patient;
    }

    private Doctor getMainDoctor() {
        return mainDoctor;
    }


    private void setMainDoctor(Doctor main) {
        this.mainDoctor = main;
    }

    private void setPatient(Patient patient) {
        this.patient = patient;
    }

    private void setTime(LocalDateTime start, LocalDateTime end){
        this.startTime = start;
        this.endTime = end;
    }

    public Appointment(Doctor main, Patient p, LocalDateTime start, LocalDateTime end) {
        this.mainDoctor = main;
        this.patient = p;
        this.startTime = start;
        this.endTime = end;
    }

    public Appointment(Doctor main, LocalDateTime start, LocalDateTime end) {
        this.mainDoctor = main;
        this.startTime = start;
        this.endTime = end;
    }

    public void addMoreDoctor(Doctor addDoc){
        additionalDoctors.add(addDoc);
    }

    public String formatAppointment(){
        StringBuilder bob = null;
        bob.append("Main Doctor: " + mainDoctor.toString() + "\n");
        if (!patient.equals(null)){
            bob.append("Patient: " + patient.toString() + "\n");
        }
        bob.append("Start: " + getTime(startTime) + "\n");
        bob.append("End: " + getTime(endTime) + "\n");
        if (!additionalDoctors.equals(null)){
            bob.append("Attending Physicians: " + additionalDoctors.toString() + "\n");
        }

    }

    private String getTime(LocalDateTime aTime) {
        DateTimeFormatter timeObj = DateTimeFormatter.ofPattern("dd-MM-YYYY kk");
        return aTime.format(timeObj);
    }
}