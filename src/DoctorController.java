import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
		Boolean[] scheduledDays = new Boolean[7];
			scheduledDays[0] = false;
			scheduledDays[1] = true;
			scheduledDays[2] = true;
			scheduledDays[3] = true;
			scheduledDays[4] = true;
			scheduledDays[5] = true;
			scheduledDays[6] = false;
		
		LocalDateTime[] appointments = new LocalDateTime[7];
			appointments[0] = LocalDateTime.parse("2020-03-25T12:00:00"); 
			appointments[1] = LocalDateTime.parse("2020-03-25T15:00:00"); 
			appointments[2] = LocalDateTime.parse("2020-03-25T17:00:00"); 
			appointments[3] = LocalDateTime.parse("2020-03-27T09:00:00"); 
			appointments[4] = LocalDateTime.parse("2020-03-27T12:00:00"); 
			appointments[5] = LocalDateTime.parse("2020-03-28T14:00:00"); 
			appointments[6] = LocalDateTime.parse("2020-04-01T16:00:00");

		view.initializeWeeklySchedule(scheduledDays, appointments);
		view.initializeMonthlySchedule(scheduledDays);


		// set the Labels for view
		view.getDeptLabel().setText(model.getDepartment() + ": ");
		view.getNameLabel().setText(model.getName() + ", M.D.");
		view.getScheduleNameLabelWeek().setText(model.getName() + " Weekly Schedule");
		view.getScheduleNameLabelMonth().setText(model.getName() + " Monthly Schedule");

		//set the nurse name dropdown list for view
		for (String n : model.getAssignedNurseUsernames()) {
			view.getNurseComboBox().addItem(model.getObjectsName(n));
		}
		view.getNurseComboBox().setSelectedIndex(-1);	//set default choice to blank
		
		//populate list of patients
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
		LocalDateTime[] appointments = new LocalDateTime[7];
			appointments[0] = LocalDateTime.parse("2020-03-25T12:00:00"); 
			appointments[1] = LocalDateTime.parse("2020-03-25T15:00:00"); 
			appointments[2] = LocalDateTime.parse("2020-03-25T17:00:00"); 
			appointments[3] = LocalDateTime.parse("2020-03-27T09:00:00"); 
			appointments[4] = LocalDateTime.parse("2020-03-27T12:00:00"); 
			appointments[5] = LocalDateTime.parse("2020-03-28T14:00:00"); 
			appointments[6] = LocalDateTime.parse("2020-04-01T16:00:00");

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
					view.initializeWeeklySchedule(scheduledDays, appointments);
					view.initializeMonthlySchedule(scheduledDays);
					view.getScheduleNameLabelWeek().setText(view.getNurseComboBox().getSelectedItem() + " Weekly Schedule");
					view.getScheduleNameLabelMonth().setText(view.getNurseComboBox().getSelectedItem() + " Monthly Schedule");
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
	
				view.initializeWeeklySchedule(scheduledDays, appointments);
				view.initializeMonthlySchedule(scheduledDays);
				}
		});
		
		//listen for mouse clicks on patients names
		view.getListPatients().addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent a) {		
				 setUpPatientView();
			}
		});
		
		//listen for treatment button clicked
		


		view.getButtonTreatmentNotes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add the input box text to the selected patient record
				// Update view
				String updateTreatment = view.getPastTreatmentBox().getText() + "\n" + view.getCurrentTreatmentBox().getText() + "\n\t- " + model.getName() + "\n";
				view.getPastTreatmentBox().setText(updateTreatment);

				saveNotes();
			}
		});
	}
	
	
	//append new notes to current notes
	public void saveNotes()
	{
		int selectedIndex = view.getListPatients().getSelectedIndex();
				
		PatientModel pat = (PatientModel) Main.dbase.get(model.getScheduledPatientsUsernames().get(selectedIndex));
		pat.setRecordNotes(view.getPastTreatmentBox().getText());
		
	}
	
	
	
	public void setUpPatientView() {
		
		//Index selected in GUI == index of patient in model 'scheduledPatientUsername' array
		//use it search hashmap for the patient
		int selectedIndex = view.getListPatients().getSelectedIndex();
		PatientModel pat = (PatientModel) Main.dbase.get(model.getScheduledPatientsUsernames().get(selectedIndex));
		
		//update patient label info in view
		view.getPatientInformation().setText(
				"Name:\t" + pat.getName() +
				"\nAge:\t" + pat.getAge() +
				"\nSex:\t" + pat.getSex() +
				"\nBlood Type:\t" + pat.getBlood() +
				"\nAddress:\t" + pat.getAddress() +
				"\nPhone:\t" + pat.getPhoneNumber() +
				"\nEmail:\t" + pat.getEmail() + "\n"
				);
		
		//update past history box in view
		view.getPastTreatmentBox().setText(pat.getRecordNotes() );
		
		
	}

	

}
