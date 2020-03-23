import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
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
		// set the Labels for view
		view.getDeptLabel().setText(model.getDepartment() + ": ");
		view.getNameLabel().setText(model.getName() + ", M.D.");

		//set the nurse name dropdown list for view
		for (String n : model.getAssignedNurseUsernames()) {
			view.getNurseComboBox().addItem(model.getObjectsName(n));
		}
		view.getNurseComboBox().setSelectedIndex(-1);	//set default choice to blank
		
		//populate list of patients
		view.setPatientList(model.getScheduledPatientsUsernames().toArray(new String[0]) );

	}

	// initialize 'only' the listeners the GUI handles 'that
	// need interaction with the model'
	public void initListeners() {
		
		//listen for mouse clicks on patients names
		view.getListPatients().addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent a) {		
				 setUpPatientView();
			}
		});
		
		//listen for treatment button clicked
		view.getBtnAddTreatmentNotes().addActionListener(e -> saveNotes() );
		
//		for (JPanel p : view.getPatientListPanels()) {
//			p.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mousePressed(MouseEvent a) {
//					// Populate patient information
//					view.getPatientInformation().setText(p.getAccessibleContext().getAccessibleName());
//
//					// Populate the past treatments of the patient
//					view.getPastTreatmentBox().setText(p.getAccessibleContext().getAccessibleDescription());
//
//					for (JPanel c : view.getPatientListPanels()) {
//						c.setBackground(Color.WHITE);
//					}
//					
//					if (p.getBackground().equals(Color.RED)) {
//						p.setBackground(Color.WHITE);
//					} else {
//						p.setBackground(Color.RED);
//					}
//				}
//			});
//		}


		view.getButtonTreatmentNotes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add the input box text to the selected patient record
				// Update view
				String updateTreatment = view.getPastTreatmentBox().getText() + "\n" + view.getCurrentTreatmentBox().getText() + "\n\t- " + model.getName() + "\n";
				view.getPastTreatmentBox().setText(updateTreatment);
				// TODO: Update the record in patient object

				// TODO: Make sure that the updated record will show up when the patient is selected again

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
