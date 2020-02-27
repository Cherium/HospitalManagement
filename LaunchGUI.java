import java.awt.EventQueue;

import javax.swing.JFrame;

public class LaunchGUI {

	private LoginFrame loginFrame;
	private Database dbase;

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
	}

}
