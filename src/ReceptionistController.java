import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import net.miginfocom.swing.MigLayout;



/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Sajid C
 *
 */
public class ReceptionistController {
	
	private ReceptionistModel model;
	private ReceptionistView view;
	private boolean isFileUploaded = false;
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public ReceptionistController(ReceptionistModel m, ReceptionistView v)
	{
		//incoming objects from LoginController class
		this.model = m;
		this.view = v;
		
		//initialize the elements that the GUI sees from the database 
		//	as soon as the view first opens for the user
		initView();
		
		//initialize 'only' the listeners the GUI handles 'that
		//	need interaction with the model'
		initListeners();
	}

	
	
	
	
	/**
	 * initialize the elements that the GUI sees from the database 
	 * as soon as the view first opens for the user.
	 * 
	 * @ author Sajid C
	 */
	@SuppressWarnings("unchecked")
	public void initView()
	{
		//set up welcome label
		view.getWelcomeLabel().setText("Welcome, "+ model.getName() );
		
		//set up patients inside JList
		view.setPatientList(model.getAllPats());

		
		//list of departments to set in combobox-- initial is Cardiology
		view.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
		
		//list of doctors for initial department
		view.getChooseDoc().setModel(new DefaultComboBoxModel(model.getObjectsNames(model.getDocList("Cardiology"))));
		
		//list of appointments to set in combobox
		String doc = view.getChooseDoc().getItemAt(view.getChooseDoc().getSelectedIndex() );
		view.getChooseAppt().setModel( new DefaultComboBoxModel(model.getOpenSlots(doc) ));
		
		//list of 14 next shifts of this user to print to text field
		view.getSchedList().setText(model.s.nextShiftsToString(model.getAvailability()) );
	}
	
	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{
		//view patient details when patient is clicked
		view.getPatList().addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent a) {		
				 setUpPatientView();
				 
			}
		});
		
		//update availability when 'send request' button is clicked
		view.getReqAvailChangeBtn().addActionListener(e -> updateAvailability() );
		
		//department is changed
		view.getDepartmentDropDown().addActionListener(e -> updateDocBox(1) );
		
		//doctor box is changed	//TODO test this
		view.getChooseDoc().addActionListener(e -> updateDocBox(2));
		
		//book a patients apointment
		view.getBookAptBtn().addActionListener(e -> bookAppointment() );
	
		//Select a file via a JFileChooser
		view.getBtnSelectFile().addActionListener(e -> selectReferral());

		//Upload a referral for a patient
		view.getBtnUploadReferral().addActionListener(e -> assignReferral());
		
	}
	
	/**
	 * Add referral(s) to patient file. Checks if a file has been uploaded or if there is input in both of the textfields.
	 * @author Jenny
	 */
	private void assignReferral() {
		int selectedIndex = view.getPatList().getSelectedIndex();

		try {
			PatientModel pat = (PatientModel) Main.dbase.get(model.getAllPatientsUsernames()[selectedIndex]);
			String fileName = view.getFileName().getText();
	
			if (view.getFileName().getText().compareTo("") != 0) {
				isFileUploaded = true;
				pat.getReferrals().add(fileName);
				view.showDialogToUser(fileName+" uploaded for "+pat.getName());
			} else {
				view.showDialogToUser("No file selected!");
			}
	
		} catch (Exception e) {
			view.showDialogToUser("No patient selected!");
		}

		// Clear all input
		view.getFileName().setText("");

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
			view.getFileName().setText(file);
		} else {
			view.getFileName().setText("");
		}

	}


	/**
	 * update doctor box and Appointments box when department box option changes - Booking panel
	 * 
	 * @author Sajid C
	 * @param sel the dropbox to update
	 */
	public void updateDocBox(int sel) {
		
		//department was changed and doctor and appointments needs to be updated
		if(sel == 1)
		{
			// get current selected department option
			String choice = view.getDepartmentDropDown().getItemAt(view.getDepartmentDropDown().getSelectedIndex() );
			
			//update doctor box according to choice
			view.getChooseDoc().setModel(new DefaultComboBoxModel(model.getObjectsNames(model.getDocList(choice))));
			
			//update appointments box according to selected doctor
			String newDoc = view.getChooseDoc().getItemAt(view.getChooseDoc().getSelectedIndex() );
			System.out.println("New Doc: "+newDoc);
			///??
			view.getChooseAppt().setModel( new DefaultComboBoxModel(model.getOpenSlots(newDoc) ));
		}
		
		//doctor was changed and appointments needs to be updated
		if(sel == 2)
		{
			// get current selected department option
			String choice = view.getDepartmentDropDown().getItemAt(view.getDepartmentDropDown().getSelectedIndex() );
			
			//get current doc choice
			String docChoice = view.getChooseDoc().getItemAt(view.getChooseDoc().getSelectedIndex() );
			
			//update appointments box according to selected doctor
			view.getChooseAppt().setModel( new DefaultComboBoxModel(model.getOpenSlots(docChoice) ));
		}
		
	}
	
	

	
	/**
	 * book an appointment for the patient based on user entered values
	 * 
	 * @author Sajid C
	 * @return ease-of-use early exit flag
	 */
	public int bookAppointment() {
		
		//get selected appointment type
		String appointmentType = view.getApptType().getItemAt(view.getApptType().getSelectedIndex()).toString();
		
		//check that referral was uploaded
		if(!isFileUploaded)
		{
			view.showDialogToUser("No referral uploaded");
			return -1;
		}
		else
			isFileUploaded = false;	//reset
		
		//get selected patient
		int selectedIndex = view.getPatList().getSelectedIndex();
		
		//ensure a patient was selected
		if(selectedIndex == -1) {view.showDialogToUser("Select a Patient!"); return -1;}
		
		//book a doctor appointment
		if(appointmentType.compareTo("Doctor Appointment") == 0)
		{
			String department = view.getDepartmentDropDown().getItemAt(view.getDepartmentDropDown().getSelectedIndex()).toString();
			int doctor = view.getChooseDoc().getSelectedIndex();	//TODO test for empty doctor slot
			String selectAppointment = view.getChooseAppt().getItemAt(view.getChooseAppt().getSelectedIndex()).toString();
			
			model.storeDoctorApptInPatient(selectAppointment, selectedIndex, department, doctor);
			view.showDialogToUser("Booked Doctor Appointment!");
			
			//list of appointments to set in combobox
			String doc = view.getChooseDoc().getItemAt(view.getChooseDoc().getSelectedIndex() );
			view.getChooseAppt().setModel( new DefaultComboBoxModel(model.getOpenSlots(doc) ));
			
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
		for(JComboBox j: view.getAvailTimes())
		{
			//get String from box and add to String array
			newHours[i++] = j.getItemAt(j.getSelectedIndex() ).toString() ;
		}
		
		//update availability for this User
		model.setAvailability(model.s.updateSchedule(newHours) );
		
		//show success to user
		view.showDialogToUser("Availability Request Approved");
		
		//reset availabilty shown to patient
		view.getSchedList().setText(model.s.nextShiftsToString(model.getAvailability()) );
		
		
		
	}




	/**
	 * show patient information in the view once a JList entry is clicked
	 * 
	 * @author Sajid C
	 */
	public void setUpPatientView() {
		int selectedIndex = view.getPatList().getSelectedIndex();
		PatientModel pat = (PatientModel) Main.dbase.get(model.getAllPatientsUsernames()[selectedIndex]);
		
		view.getPatName().setText(pat.getName() );
		view.getBirth().setText(pat.getBirthday().toString() );
		view.getSex().setText(pat.getSex());
		view.getBlood().setText(pat.getBlood());
		view.getAddr().setText(pat.getAddress());
		view.getPhone().setText(pat.getPhoneNumber());
		view.getEmail().setText(pat.getEmail() );
	}

	public void adminView() {
		view.setAdminView();
	}

}
