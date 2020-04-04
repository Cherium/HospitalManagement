import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
 * @author Sajid C
 *
 */
public class NurseView extends JFrame{
	
	private JPanel contentPanel;
	private JPanel infoPanel;
	private JPanel listPanel;
	private JPanel schedPanel;
	
	
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
	private JLabel fileName;

	private JButton btnReturn;
	private JButton reqAvailChangeBtn;
	private JButton bookAptBtn;
	private JButton btnSelectFile;
	private JButton btnUploadReferral;
	
	
	private JTextField nameText;
	private JTextField addrText;
	private JTextField phText;
	private JTextField emailText;
	private JTextField amountText;
	
	JList patList;
	JTextArea schedList;
	
	JScrollPane patListScroll;
	JScrollPane schedListScroll;
	
	private JComboBox<String> apptType;
	private JComboBox<String> departmentDropDown;
	private JComboBox<String> chooseAppt;
	private JComboBox<String> chooseDoc;
	private JComboBox<String> labTime;
	private JComboBox<String> year, month, day;
	
	private JComboBox<String>[] availTimes = new JComboBox[14];	//stores al drop-down menues for availability change; index 0= sunday start // index 13= sat end
	
	String[] times = {
			"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "08:00", "09:00", "10:00", "11:00", "12:00"
			, "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"
	};
	

	
//	public static void main(String[] args)
//	{
//		char[] a = {'a'};
//		String[] b = {};
//		new NurseController(new NurseModel("userName", a, "Nursey"
//				, "Nephrology", "doctor", b), new NurseView("Hii"));
//	}




	
	
	/**
	 * constructor
	 * 
	 * @param title JFrame title
	 */
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
	
	

	/**
	 * initialize the panels and components that will go inside the frame
	 * @author Sajid C
	 */
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
					scroll.getVerticalScrollBar().setUnitIncrement(10);
					scroll.getHorizontalScrollBar().setUnitIncrement(10);
					add(scroll, BorderLayout.CENTER);					//add the panel as the container for the frame


		
		//inner panel
		listPanel= new JPanel(new MigLayout("") );
			listPanel.setBorder(BorderFactory.createTitledBorder("Select Patient"));
			
			JLabel assigD = new JLabel("Assigned Doctor: ");
			drsPatient = new JLabel();
				drsPatient.setBackground(Color.white);
			
			listPanel.add(assigD, "split");
			listPanel.add(drsPatient, "wrap 10");
			listPanel.add(new JLabel("Doctor's Patients:"), "wrap");
		
		
		
		
		
		
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
			bookPanel.setPreferredSize(new Dimension(900, 50));
			bookPanel.setMaximumSize(new Dimension(905, 125));
			bookAptBtn = new JButton("Book Appointment");
			
			apptType = new JComboBox<String>();
				apptType.addItem("Doctor Appointment");
				apptType.addItem("Lab Test");
				apptType.addActionListener(e -> disableLab() );
				
			departmentDropDown = new JComboBox<String>();
			chooseAppt = new JComboBox<String>();
				chooseAppt.setMaximumRowCount(10);
				
			chooseDoc = new JComboBox<String>();
			labTime = new JComboBox<String>(times);
				labTime.setEnabled(false);
			year = initYearCombo();
				year.setEnabled(false);
				year.addActionListener(e -> initDaysinBox());
			month = initMonthCombo();
				month.setEnabled(false);
				month.addActionListener(e -> initDaysinBox());
			day = initDayCombo();
				day.setEnabled(false);
				initDaysinBox();
				
			bookPanel.add(new JLabel("Type:"));
			bookPanel.add(apptType);
			
			bookPanel.add(new JLabel("Department:"), "gapleft 35, align right" );
			bookPanel.add(departmentDropDown, "growx, align left, sg a");
			
			bookPanel.add(new JLabel("Lab Date: "), "align right");
			bookPanel.add(year, "sg c, split");
			bookPanel.add(month, "sg c, split");
			bookPanel.add(day, "sg c, wrap");
			
			bookPanel.add(new JLabel("Select Doctor: "), "skip 2, align right");
			bookPanel.add(chooseDoc, "sg a, align left");
			
			bookPanel.add(new JLabel("Time: "), "align right");
			bookPanel.add(labTime, "sg c, wrap");
			
			bookPanel.add(new JLabel("Select Appointment: "), "skip 2 , align right");
			bookPanel.add(chooseAppt, "span 2, growx");
			
			bookPanel.add(bookAptBtn, "align right");
			
			
		JPanel referPanel = new JPanel(new MigLayout("hidemode 3"));
			referPanel.setBorder(BorderFactory.createTitledBorder("Upload Referral"));
			referPanel.setPreferredSize(new Dimension(900, 50));
			referPanel.setMaximumSize(new Dimension(905, 125));
			
			fileName = new JLabel();
			fileName.setVisible(false);

			btnSelectFile = new JButton("Select file");

			btnUploadReferral = new JButton("Upload");


			referPanel.add(new JLabel("Referral: "));
			referPanel.add(fileName);
			referPanel.add(btnSelectFile, "wrap");
			referPanel.add(btnUploadReferral);
			
			
	
		//inner panel
		schedPanel = new JPanel(new MigLayout("") );
			schedPanel.setBorder(BorderFactory.createTitledBorder("Upcoming Shifts"));
			schedPanel.setPreferredSize(new Dimension(325, 200));

			schedList = new JTextArea();
				schedList.setFont( new Font("monospaced", Font.PLAIN, 10) );	//https://stackoverflow.com/questions/40901128/how-would-i-fix-this-jtextarea-formatting-error
			//wrap availability box in scrollpane
			schedListScroll = new JScrollPane(schedList
					, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			schedListScroll.setPreferredSize(new Dimension(400, 200));
			
			schedPanel.add(schedListScroll, "sg d");
		
		
		//inner panel
		JPanel availChangePanel = createAvailabilityChangePanel();
			reqAvailChangeBtn = new JButton("Send Request");
			availChangePanel.add(reqAvailChangeBtn, "span, align right");
		
		
		
		
		
		
		
		
		
		
		//add to main panel
		contentPanel.add(btnReturn, "wrap");
		contentPanel.add(welcomeLabel, "wrap");
		contentPanel.add(listPanel, "sg b");
		contentPanel.add(infoPanel, "sg b, wrap");
		contentPanel.add(referPanel, "span, growx");
		contentPanel.add(bookPanel, "span, growx");
		contentPanel.add(schedPanel, "sg b"/*, "span"*/);
		contentPanel.add(availChangePanel, " sg b");
		setVisible(true);
		
		
		
	}

	/**
	 * pop up a message-dialog box with a message passed in 
	 * @author Jenny Z
	 * @param message message to show user
	 */
	public void showDialogToUser(String message)
	{
		JOptionPane.showMessageDialog(contentPanel, message);
	}	
	
	
	
	/**
	 * enable/disable menues baed on chosen appointment type
	 * @author Sajid C
	 */
	public void disableLab() {
		
		if (apptType.getSelectedItem().equals("Lab Test")) {
			departmentDropDown.setEnabled(false);
			chooseAppt.setEnabled(false);
			chooseDoc.setEnabled(false);
			
			year.setEnabled(true);
			month.setEnabled(true);
			day.setEnabled(true);
			labTime.setEnabled(true);			
		} else {
			departmentDropDown.setEnabled(true);
			chooseAppt.setEnabled(true);
			chooseDoc.setEnabled(true);
			
			year.setEnabled(false);
			month.setEnabled(false);
			day.setEnabled(false);
			labTime.setEnabled(false);	
		}
	}

	
	/**
	 * set the patient list for initiView
	 * @author Sajid C
	 * @param list
	 */
	public void setPatientList(String[] list)
	{
		patList = new JList(list);
		
		//wrap lists in scrollpanes
		patListScroll = new JScrollPane(patList
				, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		patListScroll.setPreferredSize(new Dimension(200, 200));
		
		listPanel.add(patListScroll, "sg d");
	}
	

	
	/**
	 * create schedule change panel- sets menus to instance variables
	 * @author Sajid C
	 * @return availability panel containing all labels and time menus
	 */
	public JPanel createAvailabilityChangePanel()
	{
		String[] days = {"Sunday", "", "Monday","","Tuesday","","Wednesday","","Thursday","","Friday","","Saturday"};

		JPanel availChangePanel = new JPanel(new MigLayout("wrap 3", "[align right] 20 [align right] 40 [grow, align left]") );
		availChangePanel.setPreferredSize(new Dimension(325, 200));
		availChangePanel.setBorder(BorderFactory.createTitledBorder("Request Availability Change"));
		
		availChangePanel.add(new JLabel("Start"), "span 2");
		availChangePanel.add(new JLabel("End"), " wrap");
		
		for (int i = 0; i < this.availTimes.length; i=i+2) 
		{
			availChangePanel.add(new JLabel(days[i]) );
			availTimes[i] = new JComboBox(times);
			availTimes[i+1] = new JComboBox(times);
			availChangePanel.add(availTimes[i] );
			availChangePanel.add(availTimes[i+1] );
		}
		
		return availChangePanel;

		
	}

	
	/**
	 * create a view for year
	 * @author Sajid C
	 * @return drop down menu for years
	 */
	public JComboBox<String> initYearCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		for (int i = 0; i < 120; i++) {
			temp.addItem((LocalDate.now().getYear()-i)+"");
		}
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	/**
	 * 
	 * create box for month
	 * @author Sajid C
	 * @return dropdown menu of months in a year
	 */
	public JComboBox<String> initMonthCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		for (int i = 0; i < 12; i++) {
			temp.addItem((i+1)+"");
		}
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	/**
	 * create box for day
	 * @author Sajid C
	 * @return empty list that will store days
	 */
	public JComboBox<String> initDayCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	/**
	 * populate days menu with days based on current month selection
	 * @author Sajid C
	 */
	public void initDaysinBox()
	{//
		//https://www.youtube.com/watch?v=yylaqeWkPmM
		//https://stackoverflow.com/questions/33666456/java8-datetimeformatter-parse-date-with-both-single-digit-and-double-digit-day
		DateTimeFormatter df = DateTimeFormatter.ofPattern("uuuu/M/d")
				.withResolverStyle(ResolverStyle.STRICT);
		for (int i = 1; i <= 31 ; i++)
		{
			try
			{
				df.parse((String) year.getSelectedItem() +"/"+(String) month.getSelectedItem() 
					+"/"+ Integer.toString(i));
				day.addItem(Integer.toString(i));
			}
			catch(Exception e)
			{
				continue;
			}
		}
		/**Getter and Setter Methods*/
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






	public JLabel getPatName() {
		return patName;
	}






	public void setPatName(JLabel patName) {
		this.patName = patName;
	}






	public JLabel getAge() {
		return age;
	}






	public void setAge(JLabel age) {
		this.age = age;
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






	public JButton getReqAvailChangeBtn() {
		return reqAvailChangeBtn;
	}






	public void setReqAvailChangeBtn(JButton reqAvailChangeBtn) {
		this.reqAvailChangeBtn = reqAvailChangeBtn;
	}






	public JButton getBookAptBtn() {
		return bookAptBtn;
	}






	public void setBookAptBtn(JButton bookAptBtn) {
		this.bookAptBtn = bookAptBtn;
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






	public JComboBox getApptType() {
		return apptType;
	}






	public void setApptType(JComboBox apptType) {
		this.apptType = apptType;
	}






	public JComboBox<String> getDepartmentDropDown() {
		return departmentDropDown;
	}






	public void setDepartmentDropDown(JComboBox<String> departmentDropDown) {
		this.departmentDropDown = departmentDropDown;
	}






	public JComboBox getChooseAppt() {
		return chooseAppt;
	}






	public void setChooseAppt(JComboBox chooseAppt) {
		this.chooseAppt = chooseAppt;
	}






	public JComboBox getLabTime() {
		return labTime;
	}






	public void setLabTime(JComboBox labTime) {
		this.labTime = labTime;
	}






	public JComboBox<String> getYear() {
		return year;
	}






	public void setYear(JComboBox<String> year) {
		this.year = year;
	}






	public JComboBox<String> getMonth() {
		return month;
	}






	public void setMonth(JComboBox<String> month) {
		this.month = month;
	}






	public JComboBox<String> getDay() {
		return day;
	}






	public void setDay(JComboBox<String> day) {
		this.day = day;
	}






	public JComboBox<String>[] getAvailTimes() {
		return availTimes;
	}






	public void setAvailTimes(JComboBox<String>[] availTimes) {
		this.availTimes = availTimes;
	}






	public JLabel getDrsPatient() {
		return drsPatient;
	}






	public void setDrsPatient(JLabel drsPatient) {
		this.drsPatient = drsPatient;
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






	public JTextArea getSchedList() {
		return schedList;
	}






	public void setSchedList(JTextArea schedList) {
		this.schedList = schedList;
	}

	public JComboBox<String> getChooseDoc() {
		return chooseDoc;
	}

	public void setChooseDoc(JComboBox<String> chooseDoc) {
		this.chooseDoc = chooseDoc;
	}
	
	
	public JButton getBtnSelectFile() {
		return btnSelectFile;
	}

	public JLabel getFileName() {
		return fileName;
	}

	public JButton getBtnUploadReferral() {
		return btnUploadReferral;
	}
	


	

	


	
	

	

}
