import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.JFrame;

public class LoginController {
	
	private LoginModel model;
	private LoginView view;
	
	
	
	
	//constructor
	public LoginController(LoginModel m, LoginView v)
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
		
	}

	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		view.getBtnLogin().addActionListener(e -> verifyCredentials() );
		view.getBtnLogin().addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e){
			}

			public void keyPressed(KeyEvent e){
				int keyCode = e.getKeyCode();
				switch(keyCode) {
					case KeyEvent.VK_ENTER:
						verifyCredentials();
						break;
				}
			}
			public void keyReleased(KeyEvent e){
			}

	});
	
	view.getBtnNewPatient().addActionListener(e -> newPatient() );
	view.getBtnNewPatient().addKeyListener(new KeyListener() {

		public void keyTyped(KeyEvent e){
		}

		public void keyPressed(KeyEvent e){
			int keyCode = e.getKeyCode();
			switch(keyCode) {
				case KeyEvent.VK_ENTER:
					newPatient();
					break;
			}
		}
		public void keyReleased(KeyEvent e){
		}

	});
}




	public void newPatient() {
		// TODO Auto-generated method stub
		model.openNewPatientDialog();
	}




	public void verifyCredentials() {
		// TODO Auto-generated method stub
		model.setUsername(view.getUsernameField().getText());		//set entered username into model
		model.setPassword(view.getPasswordField().getPassword());	//set entered password into model
		view.clearInputs();											//clear textfields after pressing button
		
		//validate credentials against database; return error message if there is a problem.
		if(model.validate() == false)
		{
			//Show error message in GUI
			view.loginError(model.getErrorMessage() );;
		}
		
	}

	
	

}
