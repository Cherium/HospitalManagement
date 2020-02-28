import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchGUI {

	private StartScreen loginFrame;
	private Database dbase;
	private DoctorFrame docFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaunchGUI window = new LaunchGUI();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * Create the application.
	 */
	public LaunchGUI() {
		// Populate database with users
		dbase = new Database();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize a LoginFrame with database
		loginFrame = new StartScreen(dbase);
		// Attach an action event to the login button in the login frame. This is
		// to give the control of the application to the LaunchGUI. 
		loginFrame.getLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The button has been clicked!
				// Get the inputs from the login page.
				String usr = loginFrame.getUsernameInput();
				char[] pwd = loginFrame.getPasswordInput();
				// Check if there is a user corresponding to the login credentials.
				User logUser = dbase.getUser(usr, pwd);
				if (logUser == null) {
					// There is no user with these login credentials.
					// Tell login frame to give an error message.
					loginFrame.loginError();
				} else {
					// There is a user!
					String roleFrame = logUser.getRole();
					if (roleFrame.equals("doctor")) {
						// The user is a Doctor
						// Initialize the doctor frame with the user's credentials
						docFrame = new DoctorFrame(dbase, loginFrame, usr);
						// Show the doctor frame						
						docFrame.setVisible(true);
						// Hide the login frame
						loginFrame.setVisible(false);
						// Attach an action event to the back button in the doctor frame.
						// This gives control back to the LaunchGUI. 
						docFrame.getBackButton().addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// Hide the doctor frame.
								docFrame.setVisible(false);
								// Show the login frame.
								loginFrame.setVisible(true);
							}
							
						});;
					}
				}

			}
		});
	}

}
