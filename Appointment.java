import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Appointment
 * Represents an appointment between a doctor, a patient (optional), or other doctors (optional).
 * Appointments have a start and end time.
 */
public class Appointment {
    Doctor mainDoctor = null;
    Patient patient = null;
    ArrayList<Doctor> additionalDoctors = null;
    LocalDateTime startTime = null;
    LocalDateTime endTime = null;
    
    // Copy constructor for an appointment
    public Appointment(Appointment copy) {
        this.mainDoctor = copy.getMainDoctor();
        this.patient = copy.getPatient();
        this.additionalDoctors = copy.getAdditionalDoctors();
        this.startTime = copy.getStart();
        this.endTime = copy.getEnd();
    }
    
    // Constructor for an appointment between a doctor and a patient
    public Appointment(Doctor main, Patient p, LocalDateTime start, LocalDateTime end) {
        this.mainDoctor = main;
        this.patient = p;
        this.startTime = start;
        this.endTime = end;
    }

    // Constructor for an appoint between a doctor and a group of doctors
    public Appointment(Doctor main, ArrayList<Doctor> docs; LocalDateTime start, LocalDateTime end) {
        this.mainDoctor = main;
        this.additionalDoctors = docs;
        this.startTime = start;
        this.endTime = end;
    }
    
    // Get when appointment ends
    LocalDateTime getEnd() {
        return endTime;
    }

    // Get when appointment starts
    LocalDateTime getStart() {
        return startTime;
    }

    // Get list of additional doctors in the appointment
    private ArrayList<Doctor> getAdditionalDoctors() {
        return additionalDoctors;
    }

    // Get the patient 
    private Patient getPatient() {
        return patient;
    }

    // Get the main doctor
    private Doctor getMainDoctor() {
        return mainDoctor;
    }

    // Set the main doctor
    private void setMainDoctor(Doctor main) {
        this.mainDoctor = main;
    }

    // Set the patient
    private void setPatient(Patient patient) {
        this.patient = patient;
    }

    // Set the start and end times of the appointment
    private void setTime(LocalDateTime start, LocalDateTime end){
        this.startTime = start;
        this.endTime = end;
    }


    // Add more doctors to the appointment, other than the main doctor
    public void addMoreDoctor(Doctor addDoc){
        additionalDoctors.add(addDoc);
    }

    // Formats a string containing appointment details in the form of:
    // Main Doctor
    // Patient
    // Start time
    // End time
    // Additional doctors
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
    
    // Returns a formatted string of a given time in the form of DD-MM-YYYY HH
    // Day-Month-Year Hour
    private String getTime(LocalDateTime aTime) {
        DateTimeFormatter timeObj = DateTimeFormatter.ofPattern("dd-MM-YYYY kk");
        return aTime.format(timeObj);
    }
}