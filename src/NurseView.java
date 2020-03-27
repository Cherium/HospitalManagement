import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class NurseView extends JFrame{
	
	private JPanel contentPanel;
	private JPanel infoPanel;private JPanel listPanel;
	
	
	private JLabel welcomeLabel;
	private JLabel usernameLabel;
	private JLabel amountDue;
	private JLabel ageLabel;
	private JLabel drsPatient;
	private JLabel patName;
	private JLabel age;
	private JLabel addr;
	private JLabel phone;
	private JLabel email;
	private JLabel birth;
	private JLabel blood;
	private JLabel sex;

	private JButton btnReturn;
	private JButton reqAvailChangeBtn;
	private JButton bookAptBtn;
	
	
	private JTextField nameText;
	private JTextField addrText;
	private JTextField phText;
	private JTextField emailText;
	private JTextField amountText;
	
	JList patList;
	JList schedList;
	
	private String sunS, sunE, monS, monE, tueS, tueE, wedS, wedE, thuS, thuE, friS, friE, satS, satE;
	
	
	
	

	
	public static void main(String[] args)
	{
		char[] a = {'a'};
		new NurseController(new NurseModel("userName", a, "Nursey"
				, "Nephrology", "doctor"), new NurseView("Hii"));
	}




	
	
	//Constructor, takes in its title from Login Model class
	public NurseView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setSize(700,700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		initializeGUI();
	}

	//TODO scrollpane for jlists; jlist for upcoming appointments of patient
	
	

	/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI() 
	{


		//Main panel background
				contentPanel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
					contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
					
					btnReturn = new JButton("Sign Out");
						btnReturn.addActionListener(e -> setVisible(false) );
					welcomeLabel = new JLabel();
						welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		//add scrolling to main container
		JScrollPane scroll = new JScrollPane(contentPanel
				, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll, BorderLayout.CENTER);					//add the panel as the container for the frame


		
		//inner panel
		listPanel= new JPanel(new MigLayout("") );
			listPanel.setBorder(BorderFactory.createTitledBorder("Select Patient"));
			
			JLabel assigD = new JLabel("Assigned Doctor: ");
			drsPatient = new JLabel();
			
			listPanel.add(assigD, "split");
			listPanel.add(drsPatient, "wrap 10");
			listPanel.add(new JLabel("Select a Patient:"), "wrap");
		
		
		
		
		
		
		//inner panel
		infoPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );
			infoPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));
			infoPanel.setPreferredSize(new Dimension(450, 200));
		
			//Labels
			
			JLabel nameLabel = new JLabel("Name:");
			JLabel addrLabel = new JLabel("Address:");
			JLabel phLabel = new JLabel("Phone Number:");
			JLabel emailLabel = new JLabel("Email:");
			JLabel amountDueLabel = new JLabel("Amount Owing:");
				amountDue = new JLabel();
			JLabel birthday = new JLabel("Date of birth:");
			JLabel bloodLabel = new JLabel("Blood Type:");
			JLabel sexLabel = new JLabel("Sex:");
			
			patName = new JLabel();
			addr = new JLabel();
			age = new JLabel();
			phone = new JLabel();
			email = new JLabel();
			birth = new JLabel();
			blood = new JLabel();
			sex = new JLabel();
			
			//add to inner panel
			infoPanel.add(nameLabel);
			infoPanel.add(patName, "wrap");
			infoPanel.add(birthday);
			infoPanel.add(birth, "wrap");
			infoPanel.add(sexLabel);
			infoPanel.add(sex, "wrap");
			infoPanel.add(bloodLabel);
			infoPanel.add(blood, "wrap");
			infoPanel.add(addrLabel);
			infoPanel.add(addr, "wrap");
			infoPanel.add(phLabel);
			infoPanel.add(phone, "wrap");
			infoPanel.add(emailLabel);
			infoPanel.add(email, "wrap");
		
		
		
		//inner panel
		JPanel bookPanel = new JPanel(new MigLayout("") );
			bookPanel.setBorder(BorderFactory.createTitledBorder("Book an appointment"));
			
			bookAptBtn = new JButton("Book Appointment");
			
			bookPanel.add(new JLabel("Type: ") );
			bookPanel.add(new JLabel("comboBox"), "gapleft 20" );
			bookPanel.add(new JLabel("Department: ") );
			bookPanel.add(new JLabel("comboBox"), "gapleft 20" );
			bookPanel.add(new JLabel("1st Ten Appts: "), "gapleft 40" );
			bookPanel.add(new JLabel("comboBox"), "gapleft 20" );
			bookPanel.add(bookAptBtn, "gapleft 20");
			
		
		
		//inner panel
		JPanel schedPanel = new JPanel(new MigLayout("") );
			schedPanel.setBorder(BorderFactory.createTitledBorder("Upcoming Shifts"));
			schedPanel.setPreferredSize(new Dimension(325, 200));

		
		
		
		//inner panel
		sunS = sunE = monS = monE = tueS = tueE = wedS = wedE = thuS = thuE = friS = friE = satS = satE = "time";
		JPanel availChangePanel = createAvailabilityChangePanel(sunS, sunE, monS, monE, tueS, tueE, wedS, wedE, thuS, thuE, friS, friE, satS, satE);
			reqAvailChangeBtn = new JButton("Send Request");
			availChangePanel.add(reqAvailChangeBtn, "span, align right");
		
	
			//add to inner panel
			//schedPanel.add(schedList, "wrap 20");
		
		
		
		
		
		
		
		
		
		
		
		
		//add to main panel
		contentPanel.add(btnReturn, "wrap");
		contentPanel.add(welcomeLabel, "wrap");
		contentPanel.add(listPanel, "sg b");
		contentPanel.add(infoPanel, "sg b, wrap");
		contentPanel.add(bookPanel, "span, growx");
		contentPanel.add(schedPanel, "sg b"/*, "span"*/);
		contentPanel.add(availChangePanel, " sg b");
		setVisible(true);
		
		
		
	}


	//set the lists for initiView
	public void setLists(String[] list)
	{
		patList = new JList(list);
		listPanel.add(patList, "growx");
	}

	//create scheduling panel, pass in names of JComboBox fields
	public JPanel createAvailabilityChangePanel(String sunS, String sunE, String monS, String monE, String tueS, String tueE
			,String wedS, String wedE, String thuS, String thuE, String friS, String friE, String satS, String satE)
	{
		JPanel availChangePanel = new JPanel(new MigLayout("wrap 3", "[align right] 20 [align right] 40 [grow, align left]") );
		availChangePanel.setPreferredSize(new Dimension(325, 200));
		availChangePanel.setBorder(BorderFactory.createTitledBorder("Request Availability Change"));
		
		availChangePanel.add(new JLabel("Start"), "span 2");
		availChangePanel.add(new JLabel("End"), " wrap");
		availChangePanel.add(new JLabel("Sunday:"));

		availChangePanel.add(new JLabel(sunS));

		availChangePanel.add(new JLabel(sunE));
		availChangePanel.add(new JLabel("Monday:"));

		availChangePanel.add(new JLabel(monS));

		availChangePanel.add(new JLabel(monE));
		availChangePanel.add(new JLabel("Tuesday:"));

		availChangePanel.add(new JLabel(tueS));

		availChangePanel.add(new JLabel(tueE));
		availChangePanel.add(new JLabel("Wednesday:"));

		availChangePanel.add(new JLabel(wedS));

		availChangePanel.add(new JLabel(wedE));
		availChangePanel.add(new JLabel("Thursday:"));

		availChangePanel.add(new JLabel(thuS));

		availChangePanel.add(new JLabel(thuE));
		availChangePanel.add(new JLabel("Friday:"));

		availChangePanel.add(new JLabel(friS));

		availChangePanel.add(new JLabel(friE));
		availChangePanel.add(new JLabel("Saturday:"));

		availChangePanel.add(new JLabel(satS));

		availChangePanel.add(new JLabel(satE), "wrap 20");
		
		return availChangePanel;
	}
	
	
	
	
	/**Getter and Setter Methods*/

	public JPanel getContentPanel() {
		return contentPanel;
	}





	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}





	public JPanel getInfoPanel() {
		return infoPanel;
	}





	public void setInfoPanel(JPanel infoPanel) {
		this.infoPanel = infoPanel;
	}





	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}





	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}





	public JLabel getUsernameLabel() {
		return usernameLabel;
	}





	public void setUsernameLabel(JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}





	public JLabel getAmountDue() {
		return amountDue;
	}





	public void setAmountDue(JLabel amountDue) {
		this.amountDue = amountDue;
	}





	public JLabel getAgeLabel() {
		return ageLabel;
	}





	public void setAgeLabel(JLabel ageLabel) {
		this.ageLabel = ageLabel;
	}


	public JLabel getAge() {
		return age;
	}





	public void setAge(JLabel age) {
		this.age = age;
	}





	public JLabel getAddr() {
		return addr;
	}





	public void setAddr(JLabel addr) {
		this.addr = addr;
	}





	public JLabel getPhone() {
		return phone;
	}





	public void setPhone(JLabel phone) {
		this.phone = phone;
	}





	public JLabel getEmail() {
		return email;
	}





	public void setEmail(JLabel email) {
		this.email = email;
	}





	public JLabel getBirth() {
		return birth;
	}





	public void setBirth(JLabel birth) {
		this.birth = birth;
	}





	public JLabel getBlood() {
		return blood;
	}





	public void setBlood(JLabel blood) {
		this.blood = blood;
	}





	public JLabel getSex() {
		return sex;
	}





	public void setSex(JLabel sex) {
		this.sex = sex;
	}





	public JButton getBtnReturn() {
		return btnReturn;
	}





	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}


	public JTextField getNameText() {
		return nameText;
	}





	public void setNameText(JTextField nameText) {
		this.nameText = nameText;
	}





	public JTextField getAddrText() {
		return addrText;
	}





	public void setAddrText(JTextField addrText) {
		this.addrText = addrText;
	}





	public JTextField getPhText() {
		return phText;
	}





	public void setPhText(JTextField phText) {
		this.phText = phText;
	}





	public JTextField getEmailText() {
		return emailText;
	}





	public void setEmailText(JTextField emailText) {
		this.emailText = emailText;
	}





	public JTextField getAmountText() {
		return amountText;
	}





	public void setAmountText(JTextField amountText) {
		this.amountText = amountText;
	}





	public JList getPatList() {
		return patList;
	}





	public void setPatList(JList patList) {
		this.patList = patList;
	}








	public JLabel getDrsPatient() {
		return drsPatient;
	}





	public void setDrsPatient(JLabel drsPatient) {
		this.drsPatient = drsPatient;
	}





	public JLabel getPatName() {
		return patName;
	}





	public void setPatName(JLabel patName) {
		this.patName = patName;
	}





	
	
	
	

	


	
	

	

}
