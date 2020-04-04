import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Controller for this MVC construct
 * Handles all interaction between the associated model class and the view class.
 * @author Sajid C
 *
 */
/**
 * @author Sajid C
 *
 */
public class PatientController {
	
	private PatientModel model;
	private PatientView view;
	
	
	
	
	/**
	 * Constructor- sets references to associated view and model of this MVC construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view the associated view with this controller
	 */
	public PatientController(PatientModel m, PatientView v)
	{
		this.model = m;
		this.view = v;
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
		view.getWelcomeLabel().setText("Welcome, "+ model.getName() );
		view.getNameText().setText(model.getName() );
		view.getAddrText().setText(model.getAddress() );
		view.getPhText().setText(model.convertToPhoneNumber() );
		view.getEmailText().setText(model.getEmail() );
		view.getAmountDue().setText(model.convertToDollar() );
		view.getUsernameLabel().setText(model.getUsername() );
		view.getAge().setText(Integer.toString(model.getAge()));
		view.getYear().setSelectedItem(Integer.toString(model.getBirthday().getYear()));
		view.getMonth().setSelectedItem(Integer.toString(model.getBirthday().getMonthValue()));
		view.getDay().setSelectedItem(Integer.toString(model.getBirthday().getDayOfMonth()));
		view.getBlood().setSelectedItem(model.getBlood());
		view.getSex().setSelectedItem(model.getSex());

		view.setReferrals(model.getReferrals());
		view.setupReferralModel();
		
		//list of departments to set in combobox-- initial is Cardiology
		view.getDepartmentDropDown().setModel( new DefaultComboBoxModel(model.getDeptList()) );
		
		//list of doctors for initial department
		view.getChooseDoc().setModel(new DefaultComboBoxModel(model.getObjectsNames(model.getDocList("Cardiology"))));
		
		//list of doctor appointments to set in combobox
		String doc = view.getChooseDoc().getItemAt(view.getChooseDoc().getSelectedIndex() );
		view.getChooseAppt().setModel( new DefaultComboBoxModel(model.getOpenSlots(doc) ));
		
		//list of patient appointments to set in combobox
		view.getCancelAppt().setModel( new DefaultComboBoxModel(model.printApptList() ));
	}
	


	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{/*
		commenting out, low prioirty but still functional
		//add listener to each TextField in infopanel that listens for any change in text
		//	It enables th save button in its handler
		for( JTextField j: view.getInfoFields() )
		{
			j.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void insertUpdate(DocumentEvent e) {
					activateButton();
					
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					activateButton();
					
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					activateButton();
					
				}
				
				//activate the save button if text changes at all
				public void activateButton() {
					
					view.getSave().setEnabled(true);					
				}
				
			});
		}
		
		
		*/
		view.getSave().addActionListener(e -> updateInfo() );
		view.getChangePassword().addActionListener(e -> changePass() );
		
		view.getBtnAddReferral().addActionListener(e -> selectReferralToUpload() );

		//department is changed
		view.getDepartmentDropDown().addActionListener(e -> updateDocBox() );
		
		//doctor box is changed	//TODO test this
		view.getChooseDoc().addActionListener(e -> updateDocBox());
		
		//book a patients apointment
		view.getBookAptBtn().addActionListener(e -> bookAppointment() );
		
		//cancel a selected appointment
		view.getCancelApptBtn().addActionListener(e -> cancelAppt() );


	}

	/**
	 * Opens a dialog window, allowing the selection of a file for upload.
	 */
	public void selectReferralToUpload() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == 0) {
			String file = fc.getSelectedFile().getName();
			model.getReferrals().add(file);
			// Update the list of referrals shown.
			view.setReferrals(model.getReferrals());
			view.setupReferralModel();
		}
	}
	
	
	/**
	 * Cancel the selected view appointment on the button press
	 * @author Sajid C
	 * 
	 */
	public void cancelAppt() {
		
		//get index of appointment to cancel
		int apptIndex = view.getCancelAppt().getSelectedIndex();
		
		//cancel selected appointment
		model.cancelAppt(apptIndex);
//TODO maybe need to remove the patient from the doctors assigned patients		
		
		//update cancel box in view
		view.getCancelAppt().setModel( new DefaultComboBoxModel(model.printApptList() ));
	}




	/**
	 * book an appointment for the patient based on user entered values
	 * 
	 * @author Sajid C
	 * @return ease-of-use early exit flag
	 */
	public int bookAppointment() {
//TODO maybe need to add this patient to the doctors patient list; depends on how doctor retrieves its patient list
		//get selected appointment type
		String appointmentType = view.getApptType().getItemAt(view.getApptType().getSelectedIndex()).toString();
		
//		//get selected patient
//		int selectedIndex = view.getPatList().getSelectedIndex();
		
//		//ensure a patient was selected
//		if(selectedIndex == -1) {view.showDialogToUser("Select a Patient!"); return -1;}
		
		//book a doctor appointment
		if(appointmentType.compareTo("Doctor Appointment") == 0)
		{
			String department = view.getDepartmentDropDown().getItemAt(view.getDepartmentDropDown().getSelectedIndex()).toString();
			int doctor = view.getChooseDoc().getSelectedIndex();	//TODO test for empty doctor slot
			String selectAppointment = view.getChooseAppt().getItemAt(view.getChooseAppt().getSelectedIndex()).toString();
			
			model.storeDoctorApptInPatient(selectAppointment, this.model, department, doctor);
			view.showDialogToUser("Booked Doctor Appointment!");
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
			
			model.storeLabApptInPatient(year+"-"+month+"-"+day+" "+time, this.model);
			view.showDialogToUser("Booked Lab Appointment!");
			return -1;
		}
		
	}
	
	
	

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
		System.out.println("New Doc: "+newDoc);
		///??
		view.getChooseAppt().setModel( new DefaultComboBoxModel(model.getOpenSlots(newDoc) ));
	}
	
	
	/**
	 * try to update the patients information in the database and show the error message resulting
	 * 
	 * @return ease-of use early function exit flag
	 * @author Sajid C
	 */
	public int updateInfo() {

		//check that no field is empty
		for( JTextField j: view.getInfoFields() )
		{
			//if the textfield is empty
			if(j.getText().isEmpty() )
			{
				view.showDialogToUser("No Field should be empty!");
				return 0;
			}
				
		}


		//set model information
		model.setName(view.getNameText().getText());
		model.setAddress(view.getAddrText().getText());
		model.setPhoneNumber(view.getPhText().getText());
		model.setEmail(view.getEmailText().getText());
		model.setBlood(view.getBlood().getItemAt(view.getBlood().getSelectedIndex() ));
		model.setSex(view.getSex().getItemAt(view.getSex().getSelectedIndex() ));
		model.setBirthdayFromString(view.getBirthday());
		
		//do checks on the information and store it
		String response = model.verifyInfo();
		
		//display the response to the user
		view.showDialogToUser(response);
		
		return 0;
	
		
	}
	
	
	/**
	 * Update password in database and return a message for user to view
	 * 
	 * @return ease-of use early function exit flag
	 */
	public int changePass()
	{
		//get password from fields in view and set them in model
		//Note: getPassword does not throw an exception if it is empty
		model.setPassword(view.getPasswordInput().getPassword() );
		model.setPassword2(view.getPasswordInputConfirm().getPassword() );
		
		String response = model.updatePassword();
		
		view.showDialogToUser(response);
		
		
		return 0;
	}

}
