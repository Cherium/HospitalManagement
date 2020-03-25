import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;
import javax.swing.JTextArea;

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
		// Dummy schedule, randomized scheduled days and appointments, no time off
		model.setSchedule(new Schedule());
		LinkedHashMap<String, String> modAppointments = setUpAppointments(model.getSchedule().getAppointments());

		// LocalDateTime[] appointments = new LocalDateTime[7];
		// appointments[0] = LocalDateTime.parse("2020-03-25T12:00:00");
		// appointments[1] = LocalDateTime.parse("2020-03-25T15:00:00");
		// appointments[2] = LocalDateTime.parse("2020-03-25T17:00:00");
		// appointments[3] = LocalDateTime.parse("2020-03-27T09:00:00");
		// appointments[4] = LocalDateTime.parse("2020-03-28T12:00:00");
		// appointments[5] = LocalDateTime.parse("2020-03-29T14:00:00");
		// appointments[6] = LocalDateTime.parse("2020-04-01T16:00:00");

		view.setScheduledDays(model.getSchedule().getScheduledDays());
		view.setAppointments(modAppointments);

		view.initializeWeeklySchedule();
		view.initializeMonthlySchedule();

		// set the Labels for view
		view.getDeptLabel().setText(model.getDepartment() + ": ");
		view.getNameLabel().setText(model.getName() + ", M.D.");
		view.getScheduleNameLabelWeek().setText(model.getName() + " Weekly Schedule");
		view.getScheduleNameLabelMonth().setText(model.getName() + " Monthly Schedule");

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

	// initialize 'only' the listeners the GUI handles 'that
	// need interaction with the model'
	public void initListeners() {
		// LocalDateTime[] appointments = new LocalDateTime[7];
		// appointments[0] = LocalDateTime.parse("2020-03-25T12:00:00");
		// appointments[1] = LocalDateTime.parse("2020-03-25T15:00:00");
		// appointments[2] = LocalDateTime.parse("2020-03-25T17:00:00");
		// appointments[3] = LocalDateTime.parse("2020-03-27T09:00:00");
		// appointments[4] = LocalDateTime.parse("2020-03-27T12:00:00");
		// appointments[5] = LocalDateTime.parse("2020-03-28T14:00:00");
		// appointments[6] = LocalDateTime.parse("2020-04-01T16:00:00");

		view.getNurseComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (view.getNurseComboBox().getSelectedIndex() != -1) {
					Boolean[] scheduledDays = new Boolean[7];
					scheduledDays[0] = true;
					scheduledDays[1] = true;
					scheduledDays[2] = true;
					scheduledDays[3] = false;
					scheduledDays[4] = false;
					scheduledDays[5] = true;
					scheduledDays[6] = true;
					view.setScheduledDays(scheduledDays);
					view.setAppointments(new LinkedHashMap<String, String>(0));
					view.initializeWeeklySchedule();
					view.initializeMonthlySchedule();
					if (view.getBtnToggle().getText().equals("Monthly view")) {
						view.getScheduleMonthly().setVisible(false);
						view.getScheduleWeekly().setVisible(true);
					} else {
						view.getScheduleMonthly().setVisible(true);
						view.getScheduleWeekly().setVisible(false);
					}
					view.getScheduleNameLabelWeek()
							.setText(view.getNurseComboBox().getSelectedItem() + " Weekly Schedule");
					view.getScheduleNameLabelMonth()
							.setText(view.getNurseComboBox().getSelectedItem() + " Monthly Schedule");
				}
			}
		});

		view.getButtonOwn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view.getScheduleNameLabelWeek().setText(model.getName() + " Weekly Schedule");
				view.getScheduleNameLabelMonth().setText(model.getName() + " Monthly Schedule");
				Boolean[] scheduledDays = new Boolean[7];
				scheduledDays[0] = false;
				scheduledDays[1] = true;
				scheduledDays[2] = true;
				scheduledDays[3] = true;
				scheduledDays[4] = true;
				scheduledDays[5] = true;
				scheduledDays[6] = false;

				view.setScheduledDays(scheduledDays);
				view.setAppointments(new LinkedHashMap<String, String>(0));
				view.initializeWeeklySchedule();
				view.initializeMonthlySchedule();
				if (view.getBtnToggle().getText().equals("Monthly view")) {
					view.getScheduleMonthly().setVisible(false);
					view.getScheduleWeekly().setVisible(true);
				} else {
					view.getScheduleMonthly().setVisible(true);
					view.getScheduleWeekly().setVisible(false);
				}
				view.getScheduleNameLabelWeek().setText(model.getName() + " Weekly Schedule");
				view.getScheduleNameLabelMonth().setText(model.getName() + " Monthly Schedule");

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

	public LinkedHashMap<String, String> setUpAppointments(LinkedHashMap<String, String> apts) {
		LinkedHashMap<String, String> modifiedApts = new LinkedHashMap<String, String>();

		for (Entry<String, String> entry : apts.entrySet()) {
			modifiedApts.put(entry.getKey(), Main.dbase.get(entry.getValue()).getName());
		}

		return modifiedApts;
	}

	

}
