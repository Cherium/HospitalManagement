import java.awt.Color;

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

		for (String n : model.getAssignedNurseUsernames()) {

			view.getNurseComboBox().addItem(model.getObjectsName(n));
		}
		view.getNurseComboBox().setSelectedIndex(-1);

		for (String pU : model.getScheduledPatientsUsernames()) {
			// TODO: Figure out how to get the age, treatment history, and detailed patient information of the patient from here
			view.addPatient(model.getObjectsName(pU));
		}


	}

	// initialize 'only' the listeners the GUI handles 'that
	// need interaction with the model'
	public void initListeners() {
		for (JPanel p : view.getPatientListPanels()) {
			p.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent a) {
					// Populate patient information
					view.getPatientInformation().setText(p.getAccessibleContext().getAccessibleName());

					// Populate the past treatments of the patient
					view.getPastTreatmentBox().setText(p.getAccessibleContext().getAccessibleDescription());

					for (JPanel c : view.getPatientListPanels()) {
						c.setBackground(Color.WHITE);
					}
					
					if (p.getBackground().equals(Color.RED)) {
						p.setBackground(Color.WHITE);
					} else {
						p.setBackground(Color.RED);
					}



				}
			});
		}


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

	

}
