import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PatientController {
	
	private PatientModel model;
	private PatientView view;
	
	
	
	
	//constructor
	public PatientController(PatientModel m, PatientView v)
	{
		this.model = m;
		this.view = v;
		initView();
		initListeners();
	}

	
	
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView()
	{
		view.getWelcomeLabel().setText("Welcome, "+ model.getName() );
		view.getNameText().setText(model.getName() );
		view.getAddrText().setText(model.getAddress() );
		view.getPhText().setText(model.convertToPhoneNumber() );
		view.getEmailText().setText(model.getEmail() );
		view.getAmountDue().setText(model.convertToDollar() );
		view.getUsernameLabel().setText(model.getUsername() );
	}
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
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
		
		
		
		view.getSave().addActionListener(e -> updateInfo() );
		view.getChangePassword().addActionListener(e -> changePass() );
		
		
		
	}



	//try to update the patients information in the database and show the error message resulting
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
		
		//do checks on the information and store it
		String response = model.verifyInfo();
		
		//display the response to the user
		view.showDialogToUser(response);
		
		return 0;
	
		
	}
	
	public int changePass()
	{
		//Note: getPassword does not throw an exception if it is empty
		model.setPassword(view.getPasswordInput().getPassword() );
		model.setPassword2(view.getPasswordInputConfirm().getPassword() );
		
		String response = model.updatePassword();
		
		view.showDialogToUser(response);
		
		
		return 0;
	}

}
