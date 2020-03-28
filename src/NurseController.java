import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import net.miginfocom.swing.MigLayout;


/**Handles all interaction between the associated model class and the view class.*/
public class NurseController {
	
	private NurseModel model;
	private NurseView view;
	
	
	
	
	//constructor initialized from the Login Model class
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

	
	
	
	
	//get information from model, and set labels, etc in view
	public void initView()
	{
		view.getWelcomeLabel().setText("Welcome, "+ model.getName() );		
		view.getDrsPatient().setText("Dr. "+ 			
					Main.dbase.get(
							model.getAssignedDocUsername()).getName() +
					", "+ model.getDocDept()
					);
		view.setPatientList(model.getDocPats());
		view.getSchedList().setText("");
		
		//list of departments to set in combobox
		view.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
		
		//list of 14 next shifts to print to text field
		view.getSchedList().setText(model.s.nextShiftsToString(model.getAvailability()) );
	}
	
	//initialize the listeners from the view class that need to interact with model
	//	and give functionality to these listeners once they 'hear' something.
	//	listeners that do not interact with the model should stay in the view class.
	public void initListeners() 
	{
		view.getPatList().addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent a) {		
				 setUpPatientView();
				 
			}
		});
		
	}





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
