import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.glass.events.MouseEvent;

public class LaunchGUI {

	private LoginFrame login;


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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		login = new LoginFrame();
		DoctorFrame doc = new DoctorFrame(login);
		doc.setExtendedState(JFrame.MAXIMIZED_BOTH);
		NurseFrame nurse = new NurseFrame(login);
		nurse.setExtendedState(JFrame.MAXIMIZED_BOTH);
		PatientFrame pat = new PatientFrame(login);
		pat.setExtendedState(JFrame.MAXIMIZED_BOTH);
		AdminFrame adm = new AdminFrame(login);
		adm.setExtendedState(JFrame.MAXIMIZED_BOTH);
		StatsFrame stat = new StatsFrame(login);
		stat.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
		
		login.getLoginButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usr = login.getUsername();
				if (usr.equals("doctor")) {
					doc.setVisible(true);
					login.setVisible(false);
				} else if (usr.equals("nurse")) {
					nurse.setVisible(true);
					login.setVisible(false);
				} else if (usr.equals("patient")) {
					pat.setVisible(true);
					login.setVisible(false);
				} else if (usr.equals("admin")) {
					adm.setVisible(true);
					login.setVisible(false);
				} else if (usr.equals("stats")) {
					stat.setVisible(true);
					login.setVisible(false);
				} else {
					//JOptionPane.showMessageDialog(login, "Invalid username or password");
				}
			}
		});
		
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
