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
		// IMPORTANT!!!
		// ADDING A NEW SCHEDULE TO THIS DOCTOR!!!
		model.setSchedule(new Schedule());
		LinkedHashMap<String, String> modAppointments = setUpAppointments(model.getSchedule().getAppointments());

		view.setScheduledDays(model.getSchedule().getScheduledDays());
		view.setAppointments(modAppointments);
		for (Entry<String, String> entry : model.getSchedule().getAppointments().entrySet()) {
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}

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
		view.getNurseComboBox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (view.getNurseComboBox().getSelectedIndex() != -1) {

					// IMPORTANT!!!
					// ADDING A NEW SCHEDULE TO A NURSE IN DATABASE IF NO SCHEDULE IS DETECTED!!!

					int index = view.getNurseComboBox().getSelectedIndex();
					NurseModel nmd = (NurseModel) Main.dbase.get(model.getAssignedNurseUsernames().get(index));

					if (nmd.getSchedule() == null) {
						nmd.setSchedule(new Schedule());
						nmd.getSchedule().setAppointments(new LinkedHashMap<String, String>(0));
					}

					view.setScheduledDays(nmd.getSchedule().getScheduledDays());
					view.setAppointments(nmd.getSchedule().getAppointments());
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

				view.setScheduledDays(model.getSchedule().getScheduledDays());
				LinkedHashMap<String, String> modAppointments = setUpAppointments(model.getSchedule().getAppointments());
				view.setAppointments(modAppointments);
		
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

	// Modify the list of <LocalDateTime, username> from a doctor's schedule into <LocalDateTime, name>
	public LinkedHashMap<String, String> setUpAppointments(LinkedHashMap<String, String> apts) {
		LinkedHashMap<String, String> modifiedApts = new LinkedHashMap<String, String>();

		for (Entry<String, String> entry : apts.entrySet()) {
			modifiedApts.put(entry.getKey(), Main.dbase.get(entry.getValue()).getName());
		}

		return modifiedApts;
	}

	

}
