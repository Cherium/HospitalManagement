import java.awt.Component;

import javax.swing.JFileChooser;
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
		
		view.getBtnAddReferral().addActionListener(e -> {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == 0) {
				System.out.println(model.getReferrals().size());
				String file = fc.getSelectedFile().getName();
				System.out.println(file);
				model.getReferrals().add(file);
				System.out.println(model.getReferrals().size());
				view.initializeGUI();
			}
		});

		
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
