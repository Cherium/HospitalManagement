import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import net.miginfocom.swing.MigLayout;



/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Sajid C
 *
 */
public class NurseController {
	
	private NurseModel model;
	private NurseView view;
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public NurseController(NurseModel m, NurseView v)
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
	public void initView()
	{
		//set up welcome label
		view.getWelcomeLabel().setText("Welcome, "+ model.getName() );
		
		//set up label on top of patient box
		view.getDrsPatient().setText("Dr. "+ 			
					Main.dbase.get(
							model.getAssignedDocUsername()).getName() +
					", "+ model.getDocDept()
					);
		
		//set up patients inside JList
		view.setPatientList(model.getDocPats());

		
		//list of departments to set in combobox-- initial is Cardiology
		view.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
		
		//list of doctors for initial department
		view.getChooseDoc().setModel(new DefaultComboBoxModel(model.getObjectsNames(model.getDocList("Cardiology"))));
		
		//list of appointments to set in combobox
		String doc = view.getChooseDoc().getItemAt(view.getChooseDoc().getSelectedIndex() );
		System.out.println(doc);
		view.getChooseAppt().setModel( new DefaultComboBoxModel(model.getOpenSlots(doc) ));
		
		//list of 50 next shifts of this user to print to text field
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
		view.getDepartmentDropDown().addActionListener(e -> updateDocBox() );
		//book a patients apointment
		view.getBookAptBtn().addActionListener(e -> bookAppointment() );
		
	}



	//
	/**
	 * update doctor box and Appointments box when department box option changes - Booking panel
	 * 
	 * @author Sajid C
	 */
	public void updateDocBox() {
		// get current selected department option
		String choice = view.getDepartmentDropDown().getItemAt(view.getDepartmentDropDown().getSelectedIndex() );
		
		//update doctor box according to choice
		view.getChooseDoc().setModel(new DefaultComboBoxModel(model.getObjectsNames(model.getDocList(choice))));
		
		//update appointments box according to selected doctor
		String newDoc = view.getChooseDoc().getItemAt(view.getChooseDoc().getSelectedIndex() );
		//////??
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
		int selectedIndex = view.getPatList().getSelectedIndex();
		
		//ensure a patient was selected
		if(selectedIndex == -1) {view.showDialogToUser("Select a Patient!"); return -1;}
		
		if(appointmentType.compareTo("Doctor Appointment") == 0)
		{
			String department = view.getDepartmentDropDown().getItemAt(view.getDepartmentDropDown().getSelectedIndex()).toString();
			String selectAppointment = view.getChooseAppt().getItemAt(view.getChooseAppt().getSelectedIndex()).toString();
			return -1;
		}
		else	//appointment is lab test
		{
			//add appointment to selected patients list
			//a lab test is stored with a time list
			String year = view.getYear().getItemAt(view.getYear().getSelectedIndex()).toString();
			String month = view.getMonth().getItemAt(view.getMonth().getSelectedIndex()).toString();
			String day = view.getDay().getItemAt(view.getDay().getSelectedIndex()).toString();
			String time = view.getLabTime().getItemAt(view.getLabTime().getSelectedIndex()).toString();
			
			model.storeApptInPatient(year+"-"+month+"-"+day+" "+time, selectedIndex);
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
		initView();	//reset availabilty shown to patient
		
		
	}




	/**
	 * show patient information in the view once a JList entry is clicked
	 * 
	 * @author Sajid C
	 */
	public void setUpPatientView() {
		int selectedIndex = view.getPatList().getSelectedIndex();
		PatientModel pat = (PatientModel) Main.dbase.get(model.getDocsPatientsUsernames()[selectedIndex]);
		
		view.getPatName().setText(pat.getName() );
		view.getBirth().setText(pat.getBirthday().toString() );
		view.getSex().setText(pat.getSex());
		view.getBlood().setText(pat.getBlood());
		view.getAddr().setText(pat.getAddress());
		view.getPhone().setText(pat.getPhoneNumber());
		view.getEmail().setText(pat.getEmail() );
	}

}
