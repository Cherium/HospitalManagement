import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.
 * 
 * @author Sajid C
 *
 */
public class AuthorityView extends JFrame{
	
	
	private JButton btnReturn;

	
	private JLabel welcomeLabel;


	private JLabel departBaseLabel;
		private JLabel departValLabel;
	private JLabel patientTotalLabel;
		private JLabel patientValueLabel;

	private JLabel regDoctorTotalLabel;
		private JLabel regDoctorValLabel;
	private JLabel regNurseTotalLabel;
		private JLabel regNurseValLabel;
		
	private JLabel appointLabel;
		private JLabel totalAppointLabel;
	private JLabel appDayLabel;
		private JLabel actualAppDayLabel;

	private JLabel totalAppointDocLabel;
	private JLabel totalAppointNurLabel;
	

	
	private JPanel contentPanel;
	private JPanel statPanel;

	
	private int depVal;
	private int patientVal;
	private int docVal;
	private int nurVal;
	
	
	
	
	
	
	/**
	 * constructor
	 * 
	 * @param title JFrame title
	 */
	public AuthorityView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setSize(470,360);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		initializeGUI();
	}

	
	/**
	 * initialize the panels and components that will go inside the frame
	 * @author 
	 */
	public void initializeGUI() {
		// TODO Auto-generated method stub

		welcomeLabel = new JLabel();
			welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		contentPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame



		btnReturn = new JButton("Sign Out");
			btnReturn.addActionListener(e -> setVisible(false) );




		departBaseLabel = new JLabel("# of Departments: ");
			departBaseLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		departValLabel = new JLabel(""+depVal);
			departValLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));



		patientTotalLabel = new JLabel("# of Registered Patients: ");
			patientTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
		patientValueLabel = new JLabel(""+patientVal);
			patientValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));



		regDoctorTotalLabel = new JLabel("# of Registered Doctors: ");
			regDoctorTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		regDoctorValLabel = new JLabel(""+docVal);
			regDoctorValLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));




		regNurseTotalLabel = new JLabel("# of Registered Nurses: ");
			regNurseTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		regNurseValLabel = new JLabel(""+nurVal);
		regNurseValLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		
		appDayLabel = new JLabel("# of Upcoming Scheduled appointments in 24 hours: ");
			appDayLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		actualAppDayLabel = new JLabel("");
			actualAppDayLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		appointLabel = new JLabel("# of Upcoming Scheduled appointments in 2 months: ");
			appointLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		totalAppointLabel = new JLabel("");
			totalAppointLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		totalAppointDocLabel = new JLabel("# of Doctors today: ");
			totalAppointDocLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		totalAppointNurLabel = new JLabel("# of Nurses today: ");
			totalAppointNurLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

			

		



		statPanel= new JPanel(new MigLayout("wrap 2", "[] 16 []") );
			statPanel.setBorder(BorderFactory.createTitledBorder("Statistics here"));



		
		statPanel.add(departBaseLabel);
		statPanel.add(departValLabel, "wrap");
	
		statPanel.add(patientTotalLabel);
		statPanel.add(patientValueLabel, "wrap");
		


		statPanel.add(regDoctorTotalLabel);
		statPanel.add(regDoctorValLabel, "wrap");

		statPanel.add(regNurseTotalLabel);
		statPanel.add(regNurseValLabel, "wrap");

		statPanel.add(appDayLabel);
		statPanel.add(actualAppDayLabel, "wrap");

		statPanel.add(appointLabel);
		statPanel.add(totalAppointLabel, "wrap");

		statPanel.add(totalAppointDocLabel, "wrap");
		statPanel.add(totalAppointNurLabel, "wrap");

		
		

		contentPanel.add(welcomeLabel, "center, wrap");
		contentPanel.add(btnReturn, "center, wrap");
		contentPanel.add(statPanel, BorderLayout.CENTER);

		setVisible(true);
	}
	
	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}

	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}
	
	public JLabel getDepLabel(){
		return departValLabel; 
		
	}

	public void setDepLabel(JLabel label){
		departValLabel = label;
	}

	public JLabel getPatientLabel(){
		return patientValueLabel; 
	}

	public void setPatientLabel(JLabel label){
		patientValueLabel = label;
	}

	public JLabel getDoctorLabel(){
		return regDoctorValLabel; 
	}

	public void setDoctorLabel(JLabel label){
		regDoctorValLabel = label;
	}

	public JLabel getNurseLabel(){
		return regNurseValLabel; 
	}

	public void setNurseLabel(JLabel label){
		regNurseValLabel = label;
	}

	public JLabel getTotalAppointLabel(){
		return totalAppointLabel;
	}

	public void setTotalAppointLabel(JLabel label){
		totalAppointLabel = label;
	}
	
	public JLabel getActualAppDayLabel(){
		return actualAppDayLabel;
	}

	public void setActualAppDayLabel(JLabel label){
		actualAppDayLabel = label;
	}

}
