import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchGUI {

	private LoginFrame loginFrame;
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
		dbase = new Database();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new LoginFrame(dbase);
		loginFrame.getLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usr = loginFrame.getUsernameInput();
				char[] pwd = loginFrame.getPasswordInput();
				User logUser = dbase.getUser(usr, pwd);
				if (logUser == null) {
					loginFrame.loginError();
				} else {
					if (logUser.getRole().equals("doctor")) {
						docFrame = new DoctorFrame(dbase, loginFrame, usr);						
						docFrame.setVisible(true);
						loginFrame.setVisible(false);
						docFrame.getBackButton().addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								docFrame.setVisible(false);
								loginFrame.setVisible(true);
							}
							
						});;
					}
				}

			}
		});
	}

}
