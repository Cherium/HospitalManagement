import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


import javax.swing.JPanel;

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
		view.setPatientListPanels(model.getPatientNames());
		for (JPanel p : view.getPatientListPanels()) {
			// TODO: Add listener to panel
			p.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent a) {

				}
			});
		}
		
	}
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		
		//does not seem to be any listeners in the view that require interaction with the model
	}

	

}
