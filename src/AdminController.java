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
public class AdminController {

	private AdminModel model;
	private AdminView view;
	
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public AdminController(AdminModel model, AdminView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	
	

	/**
     * initialize the elements that the GUI sees from the database 
     * as soon as the view first opens for the user.
     * 
     * @ author Sajid C and Neil M
     */
    public void initView()
    {
        view.getWelcomeLabel().setText("Hello, "+ model.getName() );


        //list of 14 next shifts of this user to print to text field
        //Null pointer error just in case
		try 
		{
			view.getSchedList().setText(model.s.nextShiftsToString(model.getAvailability()));
		} 
		catch (NullPointerException exception){
        }


    }
	
	
	

	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{
		//listen for a button press and
		//		send the role selected in the list of the view class to the model class for processing
		view.getCrtAccount().addActionListener(e -> model.createAccount( 
				view.getRolesDropDown().getItemAt( view.getRolesDropDown().getSelectedIndex() )				//handle 'create account button pressed'
				));
		
		
		view.getDelAccount().addActionListener(e -> model.deleteAccount() );								//handle 'delete account' button pressed
		
		view.getEditAccount().addActionListener(e -> model.editAccount( 
				view.getRolesDropDown().getItemAt( view.getRolesDropDown().getSelectedIndex() )				//handle 'edit account button pressed'
				));
		
		view.getCrtDepartment().addActionListener(e -> addDepartment() );

		view.getReqAvailChangeBtn().addActionListener(e -> updateAvailability() );

		
	}
	
	
	

	/**
	 * 
	 * Try to create a new department in the internal database
	 * and provide a success/fail dialog to user
	 * 
	 * @author Sajid C
	 * 
	 */
	public void addDepartment()
	{
		String response;
		
		//if textbox isn't empty- handles Excelption thrown if textbox is empty.
		if(!view.getCreateDeptText().getText().isEmpty() )
		{
			//create the department, send message back to User
			response = model.addDept( view.getCreateDeptText().getText() );
			view.showDialogToUser(response);
		}
		else
		{
			view.showDialogToUser("Enter a Department Name!");
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
}
