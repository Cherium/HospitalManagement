import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LaunchGUI {

	private LoginFrame login;
	// private Database dbase;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaunchGUI window = new LaunchGUI();
					window.login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LaunchGUI() {
		// dbase = new Database("database.txt");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize the 6 main frames. 
		// Login frame.
		login = new LoginFrame();
		// Doctor frame.
		DoctorFrame doc = new DoctorFrame(login);
		doc.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// Nurse frame.
		NurseFrame nurse = new NurseFrame(login);
		nurse.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// Patient frame.
		PatientFrame pat = new PatientFrame(login);
		pat.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// Admin frame.
		AdminFrame adm = new AdminFrame(login);
		adm.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// Hospital authority frame, shows statistics.
		StatsFrame stat = new StatsFrame(login);
		stat.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
		// Get the login button from login frame and attach an action listener for 
		// login input. 
		login.getLoginButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Link databse to login authentication
				String role = login.getUsername();
				String usr = login.getUsername();
				String pwd = login.getPassword();
				// if (dbase.checkUserInSystem(usr, pwd)) {
				// 	User aUser = dbase.getUser(usr);
				// 	String role = aUser.getRole();
				// 	// set the below fields to visible based on the role of the user
					// move the if and else if statements here
				// } // move the else statement here

				if (role.equals("doctor")) {
					doc.setVisible(true);
					login.setVisible(false);
				} else if (role.equals("nurse")) {
					nurse.setVisible(true);
					login.setVisible(false);
				} else if (role.equals("patient")) {
					pat.setVisible(true);
					login.setVisible(false);
				} else if (role.equals("admin")) {
					adm.setVisible(true);
					login.setVisible(false);
				} else if (role.equals("stats")) {
					stat.setVisible(true);
					login.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(login, "Invalid username or password");
				}
			}
		});
		
		// These are the back button for each of the 5 user frames. 
		// Triggering would move control back to the login frame.
		doc.getReturnButton().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				doc.setVisible(false);
				login.setVisible(true);
			}
		});
		
		nurse.getReturnButton().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				nurse.setVisible(false);
				login.setVisible(true);
			}
		});
		
		pat.getReturnButton().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				pat.setVisible(false);
				login.setVisible(true);
			}
		});
		
		adm.getReturnButton().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				adm.setVisible(false);
				login.setVisible(true);
			}
		});
		
		stat.getReturnButton().addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				stat.setVisible(false);
				login.setVisible(true);
			}
		});
	}

}
