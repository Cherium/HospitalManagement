import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


public class DoctorController {

	private DoctorModel model;
	private DoctorView view;

	// constructor
	public DoctorController(DoctorModel m, DoctorView v) {
		this.model = m;
		this.view = v;
		initView();
		initListeners();
	}
	

	// initialize the elements that the GUI sees from the database
	// as soon as the view first opens for the user
	public void initView() {
		// Set up the appointments in DoctorModel
		// Cannot do this in constructor as doctors are read from the database before patient
		model.setAppointments(model.s.updateHashMap(model.getScheduledPatientsUsernames(), model.getUsername()));

//model.s.nextOpenSlots(model.getAvailability(), model.getAppointments());	//debug
		view.setShifts(convertToShiftTime(model.getAvailability()));
		view.setScheduledDays(convertToScheduledDays(model.getAvailability()));
		view.setAppointments(convertAppointments(model.getAppointments()));

		view.initializeWeeklySchedule();
		view.initializeMonthlySchedule();

		// set the Labels for view
		view.getDeptLabel().setText(model.getDepartment() + ": ");
		view.getNameLabel().setText(model.getName() + ", M.D.");
		view.setScheduleNameLabels(model.getName());

		// set the nurse name dropdown list for view
		for (String n : model.getAssignedNurseUsernames()) {
			view.getNurseComboBox().addItem(model.getObjectsName(n));
		}
		view.getNurseComboBox().setSelectedIndex(-1); // set default choice to blank

		// populate list of patients
		String[] patients = new String[model.getScheduledPatientsUsernames().size()];
		for (String string : model.getScheduledPatientsUsernames()) {
			int index = model.getScheduledPatientsUsernames().indexOf(string);
			patients[index] = Main.dbase.get(string).getName();
		}
		view.setPatientList(patients);

	}

	// Convert the HashMap of appointments to a LinkedHashMap with keys as appointment times
	// This is the list of appointments for weekly and monthly view
	private LinkedHashMap<String, String> convertAppointments(HashMap<String, ArrayList<LocalDateTime>> appointments) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();

		for (String keyString : appointments.keySet()) {
			for (LocalDateTime ldt : appointments.get(keyString)) {
				lhm.put(ldt.toString(), Main.dbase.get(keyString).getName());
			}
		}

		return lhm;
	}

	// Get an array of size 7 that correspond to the days the doctor is scheduled to work
	// Booleans in the form of {SUNDAY, MONDAY, ..., SATURDAY}
	// This is for the monthly view
	private Boolean[] convertToScheduledDays(LocalDateTime[] availability) {
		Boolean[] days = new Boolean[7];
		for (int i = 0; i < 7; i++) {
			if (availability[i*2].equals(availability[i*2+1])) {
				days[i] = false;
			} else {
				days[i] = true;
			}
		}
		return days;
	}

	// Get an array of size 14 that correspond to the start and end of a scheduled shift time
	// of a day. 
	// Int correspond to start and end times in the form of {SUNDAY_start, SUNDAY_end, ..., SATURDAY_end}
	// This is for the weekly view
	private int[] convertToShiftTime(LocalDateTime[] availability) {
		int[] times = new int[14];
		for (int i = 0; i < 7; i++) {
			// If the two times are the same, this means that the doctor has the day off
			if (availability[i*2].equals(availability[i*2+1])) {
				times[i*2] = -1;
				times[i*2+1] = -1;
			} else {
				times[i*2] = availability[i*2].getHour();
				times[i*2+1] = availability[i*2+1].getHour();
			}
		}

		return times;
	}

	// initialize 'only' the listeners the GUI handles 'that
	// need interaction with the model'
	public void initListeners() {

		// Show the selected nurse's schedule
		view.getNurseComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (view.getNurseComboBox().getSelectedIndex() != -1) {

					int index = view.getNurseComboBox().getSelectedIndex();
					NurseModel nmd = (NurseModel) Main.dbase.get(model.getAssignedNurseUsernames().get(index));
					
					// view.setShifts(convertToShiftTime(nmd.getAvailability()));
					// view.setScheduledDays(convertToScheduledDays(nmd.getAvailability()));
					view.setAppointments(convertAppointments(new HashMap<String, ArrayList<LocalDateTime>>()));
								
					view.initializeWeeklySchedule();
					view.initializeMonthlySchedule();
					if (view.getBtnToggle().getText().equals("Monthly view")) {
						view.isWeekly(true);
					} else {
						view.isWeekly(false);
					}
					view.setScheduleNameLabels(view.getNurseComboBox().getSelectedItem().toString());
				}
			}
		});

		// Shown own schedule after viewing nurse's schedule
		view.getButtonOwn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				view.setShifts(convertToShiftTime(model.getAvailability()));
				view.setScheduledDays(convertToScheduledDays(model.getAvailability()));
				view.setAppointments(convertAppointments(model.getAppointments()));
				
				view.initializeWeeklySchedule();
				view.initializeMonthlySchedule();
				if (view.getBtnToggle().getText().equals("Monthly view")) {
					view.isWeekly(true);
				} else {
					view.isWeekly(false);
				}
				view.setScheduleNameLabels(model.getName());

			}
		});

		// listen for mouse clicks on patients names
		view.getListPatients().addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent a) {
				setUpPatientView();
			}
		});

		// listen for treatment button clicked

		view.getButtonTreatmentNotes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add the input box text to the selected patient record
				// Update view
				String updateTreatment = view.getPastTreatmentBox().getText() + "\n"
						+ view.getCurrentTreatmentBox().getText() + "\n\t- " + model.getName() + "\n";
				view.getPastTreatmentBox().setText(updateTreatment);

				saveNotes();
			}
		});
	}

	
	
	
	
	// append new notes to current notes
	public void saveNotes() {
		int selectedIndex = view.getListPatients().getSelectedIndex();

		PatientModel pat = (PatientModel) Main.dbase.get(model.getScheduledPatientsUsernames().get(selectedIndex));
		pat.setRecordNotes(view.getPastTreatmentBox().getText());

	}

	// set up patients in the center panels of 'View Patient'
	public void setUpPatientView() {

		// Index selected in GUI == index of patient in model 'scheduledPatientUsername'
		// array
		// use it search hashmap for the patient
		int selectedIndex = view.getListPatients().getSelectedIndex();
		PatientModel pat = (PatientModel) Main.dbase.get(model.getScheduledPatientsUsernames().get(selectedIndex));

		// update patient label info in view
		view.getPatientInformation()
				.setText("Name:\t" + pat.getName() + "\nAge:\t" + pat.getAge() + "\nSex:\t" + pat.getSex()
						+ "\nBlood Type:\t" + pat.getBlood() + "\nAddress:\t" + pat.getAddress() + "\nPhone:\t"
						+ pat.getPhoneNumber() + "\nEmail:\t" + pat.getEmail() + "\n");

		// update past history box in view
		view.getPastTreatmentBox().setText(pat.getRecordNotes());

	}


	

}
