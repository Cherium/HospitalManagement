import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

	private JComboBox departmentDropDown;
	
	private JLabel welcomeLabel;
	private JLabel departBaseLabel;
	private JLabel patientTotalLabel;
	private JLabel regDoctorTotalLabel;
	private JLabel regNurseTotalLabel;
	private JLabel totalAppointDepLabel;
	private JLabel totalAppointDocLabel;//Per doctor
	
	private JPanel contentPanel;
	private JPanel statPanel;
	
	
	
	
	
	
	
	
	/**
	 * constructor
	 * 
	 * @param title JFrame title
	 */
	public AuthorityView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setSize(600,500);
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




		departBaseLabel = new JLabel();
			departBaseLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		patientTotalLabel = new JLabel();
			patientTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		regDoctorTotalLabel = new JLabel("# of Registered Doctors: ");
			regDoctorTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		regNurseTotalLabel = new JLabel("# of Registered Nurses: ");
			regNurseTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
		totalAppointDocLabel = new JLabel("# of Scheduled appointments for Specific Doctor: TBA");
			totalAppointDocLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		totalAppointDepLabel = new JLabel("# of Scheduled appointments for Department: 0");
			totalAppointDepLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		statPanel= new JPanel(new MigLayout("wrap 2", "[] 16 []") );
			statPanel.setBorder(BorderFactory.createTitledBorder("Statistics here"));


		//Scroll bar
		departmentDropDown = new JComboBox<String>();
		departmentDropDown.insertItemAt("", 0);

		
		statPanel.add(departBaseLabel, "wrap");
		statPanel.add(patientTotalLabel, "wrap");
		statPanel.add(regDoctorTotalLabel, "wrap");
		statPanel.add(regNurseTotalLabel, "wrap");
		statPanel.add(totalAppointDocLabel, "wrap");
		statPanel.add(totalAppointDepLabel, "wrap");

		contentPanel.add(welcomeLabel, "center, wrap");
		contentPanel.add(btnReturn, "center, wrap");
		contentPanel.add(statPanel, "center, wrap");
		//contentPanel.add(departmentDropDown); //Temporarily locked

		setVisible(true);
	}
	
	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}

	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}
	
	public JLabel getDepNumLabel(){
		return departBaseLabel; 
	}

	public void setDepNumLabel(JLabel departBaseLabel){
		this.departBaseLabel = departBaseLabel;
	}

	public JLabel getPatientTotalLabel(){
		return patientTotalLabel; 
	}

	public void setPatientTotalLabel(JLabel label){
		patientTotalLabel = label;
	}

	public JLabel getDoctorTotalLabel(){
		return regDoctorTotalLabel; 
	}

	public void setDoctorTotalLabel(JLabel label){
		regDoctorTotalLabel = label;
	}

	public JLabel getNurseTotalLabel(){
		return regNurseTotalLabel; 
	}

	public void setNurseTotalLabel(JLabel label){
		regNurseTotalLabel = label;
	}


	

}
