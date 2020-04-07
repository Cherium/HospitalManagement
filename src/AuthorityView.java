import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
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
 * Purpose of this is to set up Authority View
 * @author Neil M
 *
 */
public class AuthorityView extends JFrame{
	
	
	//Sign out button
	private JButton btnReturn;


	//Labels
	private JLabel welcomeL;

	private JLabel depNumTotalL;

	private JLabel regPatTotalL;

	private JLabel regDocTotalL;

	private JLabel regNurTotalL;

	private JLabel upcomeDayTotalL;

	private JLabel upcomeMonTotalL;

	private JLabel cardioCL;
	private JLabel neuroCL;
	private JLabel nephroCL;
	private JLabel recepCL;
	private JLabel erCL;

	private JLabel docToday;

	//Panels for JLabels
	private JPanel contentPanel;
	private JPanel statPanel;

	
	
	
	
	
	/**
	 * constructor for the portal screen
	 * 
	 * @param title JFrame title
	 */
	public AuthorityView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setSize(600,420);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		initializeGUI();
	}

	
	/**
	 * initialize the panels and components that will go inside the frame
	 * @author Neil M
	 * #TODO Organize the java labels in a different fashion. Change names
	 */
	public void initializeGUI(){
		//Welcome Panel set up		
		welcomeL = new JLabel();
			welcomeL.setFont(new Font("Tahoma", Font.PLAIN, 16));

		contentPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );		//initialize jpanel and set its layout
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
		add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
		
		//add scrolling to main container
		JScrollPane scroll = new JScrollPane(contentPanel
				, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.getVerticalScrollBar().setUnitIncrement(10);
			scroll.getHorizontalScrollBar().setUnitIncrement(10);
			add(scroll, BorderLayout.CENTER);					//add the panel as the container for the frame

		//Sign out panel
		btnReturn = new JButton("Sign Out");
			btnReturn.addActionListener(e -> setVisible(false) );


		//All other JLabels necessary
		depNumTotalL = new JLabel();
			depNumTotalL.setFont(new Font("Tahoma", Font.PLAIN, 13));		
		regPatTotalL = new JLabel();
			regPatTotalL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		regDocTotalL = new JLabel();
			regDocTotalL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		regNurTotalL = new JLabel();
			regNurTotalL.setFont(new Font("Tahoma", Font.PLAIN, 13));

		
		upcomeDayTotalL = new JLabel();
			upcomeDayTotalL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		upcomeMonTotalL = new JLabel();
			upcomeMonTotalL.setFont(new Font("Tahoma", Font.PLAIN, 13));


		
		//Compile non-Department stats JLabels in a single panel
		statPanel= new JPanel(new MigLayout("wrap 2", "[] 16 []") );
			statPanel.setBorder(BorderFactory.createTitledBorder("Statistics here"));




		
		statPanel.add(new JLabel("# of Departments: "));
		statPanel.add(depNumTotalL, "wrap");
	
		statPanel.add(new JLabel("# of Registered Patients: "));
		statPanel.add(regPatTotalL, "wrap");
		


		statPanel.add(new JLabel("# of Registered Doctors: "));
		statPanel.add(regDocTotalL, "wrap");

		statPanel.add(new JLabel("# of Registered Nurses: "));
		statPanel.add(regNurTotalL, "wrap");

		statPanel.add(new JLabel("# of Upcoming Scheduled appointments in 24 hours: "));
		statPanel.add(upcomeDayTotalL, "wrap");

		statPanel.add(new JLabel("# of Upcoming Scheduled appointments in 2 months: "));
		statPanel.add(upcomeMonTotalL, "wrap");



		docToday = new JLabel();
			docToday.setFont(new Font("Tahoma", Font.PLAIN, 13));

		//Required Doctor for today
		statPanel.add(new JLabel("# of Doctor needed for today: "));
		statPanel.add(docToday, "wrap");

		//Required Nurse for today, implement is possible
		//statPanel.add(new JLabel("# of Nurse needed for today: "), "wrap");




		cardioCL = new JLabel();
		cardioCL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		neuroCL = new JLabel();
		neuroCL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nephroCL = new JLabel();
		nephroCL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		recepCL = new JLabel();
		recepCL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		erCL = new JLabel();
		erCL.setFont(new Font("Tahoma", Font.PLAIN, 13));//*/



		statPanel.add(new JLabel ("# of Doctors in Cardio: "));
		statPanel.add(cardioCL, "wrap");
		statPanel.add(new JLabel ("# of Doctors in Nephrology: "));
		statPanel.add(nephroCL, "wrap");
		statPanel.add(new JLabel ("# of Doctors in Neurology: "));
		statPanel.add(neuroCL, "wrap");
		statPanel.add(new JLabel ("# of Doctors in Receptionist: "));
		statPanel.add(recepCL, "wrap");
		statPanel.add(new JLabel ("# of Doctors in ER: "));
		statPanel.add(erCL, "wrap");


		
		
		
		contentPanel.add(welcomeL, "center, wrap");
		contentPanel.add(btnReturn, "center, wrap");
		contentPanel.add(statPanel, BorderLayout.CENTER);
		setVisible(true);

				
		}


	/**Getter and Setter Methods*/	

	public JLabel getWelLabel() {
		return welcomeL;
	}

	public void setwelLabel(JLabel label) {
		welcomeL = label;
	}
	
	public JLabel getdepNumTotalL (){
		return depNumTotalL;
	}
	public void setdepNumTotalL (JLabel label){
		depNumTotalL = label;
	}

	public JLabel getregPatTotalL (){
		return regPatTotalL;
	}
	public void setregPatTotalL(JLabel label){
		regPatTotalL = label;
	}

	public JLabel getregDocTotalL (){
		return regDocTotalL;
	}
	public void setregDocTotalL(JLabel label){
		regDocTotalL = label;
	}

	public JLabel getregNurTotalL (){
		return regNurTotalL;
	}
	public void setregNurTotalL(JLabel label){
		regNurTotalL = label;
	}

	public JLabel getupcomeDayTotalL (){
		return upcomeDayTotalL;
	}
	public void setupcomeDayTotalL(JLabel label){
		upcomeDayTotalL = label;
	}

	public JLabel getupcomeMonTotalL (){
		return upcomeMonTotalL;
	}
	public void setupcomeMonTotalL(JLabel label){
		upcomeMonTotalL = label;
	}

	public JLabel getcardioCL(){
		return cardioCL;
	}

	public void setcardioCL(JLabel label){
		cardioCL = label;
	}

	public JLabel getneuroCL(){
		return neuroCL;
	}

	public void setneuroCL(JLabel label){
		neuroCL= label;
	}


	public JLabel getnephroCL(){
		return nephroCL;
	}

	public void setnephroCL(JLabel label){
		nephroCL = label;
	}


	public JLabel getrecepCL(){
		return recepCL;
	}

	public void setrecepCL(JLabel label){
		recepCL = label;
	}


	public JLabel geterCL(){
		return erCL;
	}

	public void seterCL(JLabel label){
		erCL = label;
	}

	public JLabel getdocToday(){
		return docToday;
	}

	public void setdocToday(JLabel label){
		docToday = label;
	}


	/*

	public JLabel get(){
		return ;
	}

	public void set(JLabel label){
		= label;
	}

	*/



}
