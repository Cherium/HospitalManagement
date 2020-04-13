import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Jenny Z, Sajid C
 *
 */
public class DoctorController {

	private DoctorModel model;
	private DoctorView view;

	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public DoctorController(DoctorModel m, DoctorView v) {
		this.model = m;
		this.view = v;
		initView();
		initListeners();
	}
	

	/**
	 * initialize the elements that the GUI sees from the database 
	 * as soon as the view first opens for the user.
	 * 
	 * @ author Jenny Z, Sajid C
	 */
	public void initView() {
		// Set up the appointments in DoctorModel
		// Cannot do this in constructor as doctors are read from the database before patient
		model.setAppointments(model.s.updateHashMap(model.getScheduledPatientsUsernames(), model.getUsername()));

		// model.s.nextOpenSlots(model.getAvailability(), model.getAppointments());	//debug
		view.setShifts(convertToShiftTime(model.getAvailability()));
		view.setScheduledDays(convertToScheduledDays(model.getAvailability()));
		view.setAppointments(convertAppointments(model.getAppointments()));

		view.initializeWeeklySchedule();
		view.initializeMonthlySchedule();
		String[] nurses = new String[model.getAssignedNurseUsernames().size()];

		// set the Labels for view
		view.getDeptLabel().setText(model.getDepartment() + ": ");
		view.getNameLabel().setText(model.getName() + ", M.D.");
		view.setScheduleNameLabels(model.getName());

		// set the nurse name dropdown list for view
		for (String n : model.getAssignedNurseUsernames()) {
			view.getNurseComboBox().addItem(model.getObjectsName(n));
			nurses[model.getAssignedNurseUsernames().indexOf(n)] = model.getObjectsName(n);
		}
		view.getNurseComboBox().setSelectedIndex(-1); // set default choice to blank

		// populate list of patients
		String[] patients = new String[model.getScheduledPatientsUsernames().size()];
		for (String string : model.getScheduledPatientsUsernames()) {
			int index = model.getScheduledPatientsUsernames().indexOf(string);
			patients[index] = Main.dbase.get(string).getName();
		}
		view.setPatientList(patients);
		view.setNurseList(nurses);

		view.getChooseAppt().setModel(new DefaultComboBoxModel(model.getOpenSlots(model.getName())));

		view.getButtonOwn().setEnabled(false);


	}
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Jenny Z, Sajid C
	 */
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
					view.getButtonOwn().setEnabled(true);
					view.getCalendar().setVisible(true);
					view.getModifyPanel().setVisible(false);
	
				}

			}
		});

		// Show own schedule after viewing nurse's schedule
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
				view.getButtonTreatmentNotes().setEnabled(true);
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

		// Books appointment between a selected patient and this doctor
		view.getBtnBookApt().addActionListener(e -> bookAppointment());

		// Updates the availability of the doctor or the selected nurse
		view.getBtnAvailRequest().addActionListener(e -> updateAvailability());

		// Select a file via JFileChooser
		view.getBtnSelectFile().addActionListener(e -> selectReferral());

		// Upload a referral file for a selected patient
		view.getBtnUpdateFerral().addActionListener(e -> assignReferral());
	}

	/**
	 * Add referral(s) to patient file. Checks if a file has been uploaded or if there is input in both of the textfields.
	 * @author Jenny
	 */
	private void assignReferral() {
		int selectedIndex = view.getListPatients().getSelectedIndex();

		try {
			PatientModel pat = (PatientModel) Main.dbase.get(model.getScheduledPatientsUsernames().get(selectedIndex));
			String fileName = view.getFileNameJLabel().getText();
			String departmentInput = view.getDepartmentInput().getText();
			String nameInput = view.getNameInput().getText();
	
			if (view.getFileNameJLabel().isVisible()) {
				pat.getReferrals().add(fileName);
				view.showDialogToUser(fileName+" uploaded for "+pat.getName());
			} 
	
			if ((departmentInput.length() > 0) && (nameInput.length() > 0)) {
				pat.getReferrals().add(departmentInput+" : "+nameInput);
				view.showDialogToUser("Referral generated for "+pat.getName());
			}

			if (!view.getFileNameJLabel().isVisible() && (departmentInput.length() == 0) && (nameInput.length() == 0)) {
				view.showDialogToUser("No file selected and no input detected!");
			}
		} catch (Exception e) {
			view.showDialogToUser("No patient selected!");
		}

		// Clear all input
		view.getFileNameJLabel().setVisible(false);
		view.getDepartmentInput().setText("");
		view.getNameInput().setText("");

	}

	/**
	 * Select a referral file via a JFileChooser
	 * @author Jenny
	 */
	private void selectReferral() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == 0) {
			String file = fc.getSelectedFile().getName();
			view.getFileNameJLabel().setText(file);
			view.getFileNameJLabel().setVisible(true);
		} else {
			view.getFileNameJLabel().setVisible(false);
		}

	}

	//
	// 
	/**
	 * Convert the HashMap of appointments to a LinkedHashMap with keys as appointment times.
	 * This is the list of appointments for weekly and monthly view.
	 * 
	 * @author Jenny Z
	 * @param appointments- list of appointments that this user has
	 * @return LinkedHashMap with keys as appointment times
	 */
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


	
	
	
	// 
	/**
	 * append new notes to current notes in patient tab on button press
	 * 
	 * @author Sajid C
	 */
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

	/**
	 * book an appointment for the patient based on user entered values
	 * 
	 * @return ease-of-use early exit flag
	 */
	public int bookAppointment() {
		
		//get selected appointment type
		String appointmentType = view.getApptType().getItemAt(view.getApptType().getSelectedIndex()).toString();
		
		//get selected patient
		int selectedIndex = view.getListPatients().getSelectedIndex();
		
		//ensure a patient was selected
		if(selectedIndex == -1) {view.showDialogToUser("Select a Patient!"); return -1;}
		
		if(appointmentType.compareTo("Doctor Appointment") == 0)
		{
			String selectAppointment = view.getChooseAppt().getItemAt(view.getChooseAppt().getSelectedIndex()).toString();
			int selectPatient = view.getListPatients().getSelectedIndex();
			model.storeAppointmentInPatient(selectAppointment, selectPatient);

			// Update the schedule of the doctor
			model.setAppointments(model.s.updateHashMap(model.getScheduledPatientsUsernames(), model.getUsername()));
			view.setShifts(convertToShiftTime(model.getAvailability()));
			view.setScheduledDays(convertToScheduledDays(model.getAvailability()));
			view.setAppointments(convertAppointments(model.getAppointments()));
	
			view.initializeWeeklySchedule();
			view.initializeMonthlySchedule();

			view.getChooseAppt().setModel(new DefaultComboBoxModel(model.getOpenSlots(model.getName())));
			view.showDialogToUser("Booked follow-up appointment!");
			return -1;
		}
		else	//appointment is lab test
		{
			
			//check that selected date is greater than current date
			if(! view.isFutureDate() )
			{
				view.showDialogToUser("Choose a Future Date!");
				return -1;
			}
			//add appointment to selected patients list
			//a lab test is stored with a time list
			String year = view.getYear().getItemAt(view.getYear().getSelectedIndex()).toString();
			String month = view.getMonth().getItemAt(view.getMonth().getSelectedIndex()).toString();
			String day = view.getDay().getItemAt(view.getDay().getSelectedIndex()).toString();
			String time = view.getLabTime().getItemAt(view.getLabTime().getSelectedIndex()).toString();
			
			model.storeLabApptInPatient(year+"-"+month+"-"+day+" "+time, selectedIndex);
			view.showDialogToUser("Booked Lab Appointment!");
			return -1;
		}

	}

	/**
	 * update availability in database and view, based on user entered values
	 * 
	 * 
	 */
	public void updateAvailability() {
		
		String[] newHours= new String[14];
		

		//retrieve all comboBoxes storing hour values as Strings
		int i = 0;
		for(JComboBox<String> j: view.getAvailTimes())
		{
			//get String from box and add to String array
			newHours[i++] = j.getItemAt(j.getSelectedIndex() ).toString() ;
		}
		
		if (view.getCheckBox().isSelected()) {
			//update availability for this User
			model.setAvailability(model.s.updateSchedule(newHours) );
		} else {
			// else update availability for the nurse
			int index = view.getNursesList().getSelectedIndex();
			Main.dbase.get(model.getAssignedNurseUsernames().get(index)).s.updateSchedule(newHours);
		}
		
		//show success to user
		view.showDialogToUser("Availability Request Approved");
		// initView();	//reset availabilty shown to patient
		
		
	}

}
