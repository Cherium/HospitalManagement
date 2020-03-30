import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Sajid C
 *
 */
public class ReceptionistController {

	private ReceptionistModel model;
	private ReceptionistView view;
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public ReceptionistController(ReceptionistModel model, ReceptionistView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	
	
	/**
	 * initialize the elements that the GUI sees from the database 
	 * as soon as the view first opens for the user.
	 * 
	 * @ author Sajid C
	 */
	public void initView()
	{
		view.getWelcomeLabel().setText("Welcome Receptionist "+ model.getName() );

		/*
				//ArrayList<String> newPatient
				ArrayList<String> a = view.getPatientList();
				model.setScheduledPatientsUsernames(a);
				//*/

		String[] patients = new String[model.getScheduledPatientsUsernames().size()];
		for (String string : model.getScheduledPatientsUsernames()) {
			int index = model.getScheduledPatientsUsernames().indexOf(string);
			patients[index] = Main.dbase.get(string).getName();///Issue is here?
		}
		view.setPatientList(patients);
		
		///


	}
	
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{



			
				//listen for mouse clicks on patients names
				view.getListPatients().addMouseListener(new MouseAdapter() {
					
					
			
					public void mousePressed(MouseEvent a) {		
						 setUpPatientView();
						 
						 System.out.println("Ala");
					}
				});//*/




	}


		/**
		 * set up patients in the center panels of 'View Patient'
		 * @author 
		 */
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
