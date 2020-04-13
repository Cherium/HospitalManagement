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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
public class PatientView extends JFrame{
	
	private JPanel contentPanel;
	private JPanel infoPanel;
	private JPanel referralPanel = new JPanel();
	private DefaultListModel<String> model = new DefaultListModel<String>();
	
	private JLabel welcomeLabel;
	private JLabel usernameLabel;
	private JLabel amountDue;
	private JLabel age;


	private JButton btnReturn;
	private JButton save;
	private JButton changePassword;
	private JButton btnAddReferral;
	private JButton bookAptBtn;
	private JButton cancelApptBtn;
	private JButton btnSelectFile;
	private JButton btnUploadReferral;
	
	private JTextField nameText;
	private JTextField addrText;
	private JTextField phText;
	private JTextField emailText;
	private JTextField amountText;
	private JTextField fileName;
	
	private ArrayList<JTextField> infoFields = new ArrayList<>(5);	//add all JTextFields into an Array to add listeners later
	
	
	private JPasswordField passwordInput;
	private JPasswordField passwordInputConfirm;

	private JComboBox<String> blood, posNeg;
	private JComboBox<String> sex;
	private JComboBox<String> apptType;
	private JComboBox<String> departmentDropDown;
	private JComboBox<String> chooseAppt;
	private JComboBox<String> chooseDoc;
	private JComboBox<String> labTime;
	private JComboBox<String> year, month, day;
	private JComboBox<String> yearfw, monthfw, dayfw;
	private JComboBox<String> cancelAppt;
	
	private String[] referrals = {""};
	String[] times = {
			"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "08:00", "09:00", "10:00", "11:00", "12:00"
			, "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"
	};
	
	
	
	//Constructor
	public PatientView(String title)
	{
		//create frame container
		setTitle(title);
		setMinimumSize(new Dimension(1000, 700));
		//setPreferredSize(new Dimension(1000, 1000));
		setMaximumSize(new Dimension(1005, 1005));
		//setSize(700,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		initializeGUI();
	}

	
	
	

	
	/**
	 * initialize the panels and components that will go inside the frame
	 * 
	 * @author Sajid C
	 */
	public void initializeGUI() 
	{

//Main panel background
		contentPanel = new JPanel(new MigLayout("align 50% 50%") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			contentPanel.setBackground(new Color(231,239,255));
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
		
			btnReturn = new JButton("Sign Out");
				btnReturn.addActionListener(e -> setVisible(false) );
				btnReturn.setForeground(Color.WHITE);
				btnReturn.setBackground(new Color(154,50,50));

			welcomeLabel = new JLabel();
				welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
				welcomeLabel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 36));
				
			//add scrolling to main container
			JScrollPane scroll = new JScrollPane(contentPanel
					, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scroll.getVerticalScrollBar().setUnitIncrement(10);
				scroll.getHorizontalScrollBar().setUnitIncrement(10);
				add(scroll, BorderLayout.CENTER);					//add the panel as the container for the frame


//Inner Panels
			
			infoPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );
				infoPanel.setMinimumSize(new Dimension(450, 50));	//extends jpanel in this sg to have 1/2 the width of booking panel
				infoPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));
				infoPanel.setBackground(new Color(255, 247, 231));
				
		//Labels
				JLabel nameLabel = new JLabel("Name:");
				JLabel agelabel = new JLabel("Age:");
					age = new JLabel();
				JLabel addrLabel = new JLabel("Address:");
				JLabel phLabel = new JLabel("Phone Number:");
				JLabel emailLabel = new JLabel("Email:");
				JLabel amountDueLabel = new JLabel("Amount Owing:");
					amountDue = new JLabel();
				JLabel birthday = new JLabel("Date of birth:");
				JLabel bloodLabel = new JLabel("Blood Type:");
				JLabel sexLabel = new JLabel("Sex:");

				
		//TextFields
				nameText = new JTextField();
					nameText.setColumns(20);
					infoFields.add(nameText);	//add all JTextFields into an Array to add listeners later
				addrText = new JTextField();
					addrText.setColumns(20);
					infoFields.add(addrText);
				phText = new JTextField();
					phText.setColumns(20);
					infoFields.add(phText);
				emailText = new JTextField();
					emailText.setColumns(20);
					infoFields.add(emailText);
					
		//Buttons
				save = new JButton("Save Changes");
		
		//Combo boxes
				year = initYearCombo();
					year.addActionListener(e -> initDaysinBox() );
				month = initMonthCombo();
					month.addActionListener(e -> initDaysinBox());
				day = initDayCombo();
					initDaysinBox();
				sex = new JComboBox<String>();
					sex.addItem("Male");
					sex.addItem("Female");
				blood = new JComboBox<String>();
					blood.addItem("A+"); blood.addItem("A-");
					blood.addItem("B+"); blood.addItem("B-");
					blood.addItem("AB+"); blood.addItem("AB-");
					blood.addItem("O+"); blood.addItem("O-");


		//Add components to inner panel
					infoPanel.add(nameLabel);
					infoPanel.add(nameText, "wrap");
					
					infoPanel.add(agelabel);
					infoPanel.add(age, "wrap");
					
					infoPanel.add(birthday);
					infoPanel.add(year, "sg b, split");	//split splits a current column into 2. Everything in 'sgroup b' will be the same size
					infoPanel.add(month, "sg b, split");
					infoPanel.add(day, "sg b, wrap");
					
					infoPanel.add(bloodLabel);
					infoPanel.add(blood, "wrap");
					
					infoPanel.add(sexLabel);
					infoPanel.add(sex, "wrap");
					
					infoPanel.add(addrLabel);
					infoPanel.add(addrText, "wrap");
					
					infoPanel.add(phLabel);
					infoPanel.add(phText, "wrap");
					
					infoPanel.add(emailLabel);
					infoPanel.add(emailText, "wrap");
					
					infoPanel.add(amountDueLabel);
					infoPanel.add(amountDue, "wrap 20");
					
					
					infoPanel.add(save, "span, center");
					
				
			
					
		//Inner Panel		
			JPanel credentialPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );
				credentialPanel.setBorder(BorderFactory.createTitledBorder(""));
				credentialPanel.setBackground(new Color(255, 247, 231));
			
		//Labels
				JLabel usernameTitleLabel = new JLabel("Username:");
					usernameLabel	= new JLabel();
				JLabel pwdLabel = new JLabel("New Password:");
				JLabel pwd2Label = new JLabel("Re-Enter Password:");
			
		//PasswordFields
				passwordInput = new JPasswordField(20);
				passwordInputConfirm = new JPasswordField(20);
			
		//Buttons
				changePassword = new JButton("Change Password");
				cancelApptBtn = new JButton("Cancel Appointment");
			
		//JComboBox
				cancelAppt = new JComboBox<String>();
				
		//Add components to inner panel
				credentialPanel.add(usernameTitleLabel);
				credentialPanel.add(usernameLabel, "wrap");
				credentialPanel.add(pwdLabel);
				credentialPanel.add(passwordInput, "wrap");
				credentialPanel.add(pwd2Label);
				credentialPanel.add(passwordInputConfirm, "wrap 20");			
				credentialPanel.add(changePassword, "span, center, wrap 60");
				
				credentialPanel.add(new JLabel("List of Upcoming Appointments:"), "align left, span, wrap 10");
				credentialPanel.add(cancelAppt, "span, gapleft 6, split");
				credentialPanel.add(cancelApptBtn);
				
			//inner panel
			JPanel bookPanel = new JPanel(new MigLayout("") );
				bookPanel.setBorder(BorderFactory.createTitledBorder("Book an appointment"));
				bookPanel.setPreferredSize(new Dimension(900, 50));
				bookPanel.setMaximumSize(new Dimension(905, 300));
				bookPanel.setBackground(new Color(255, 247, 231));
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
				yearfw = initYearComboForward();
					yearfw.setEnabled(false);
					yearfw.addActionListener(e -> initDaysinBoxfw());
				monthfw = initMonthCombo();
					monthfw.setEnabled(false);
					monthfw.addActionListener(e -> initDaysinBoxfw());
				dayfw = initDayCombo();
					dayfw.setEnabled(false);
					initDaysinBoxfw();
					
				fileName = new JTextField();		
				btnSelectFile = new JButton("Select file");
				btnUploadReferral = new JButton("Upload");
					
				bookPanel.add(new JLabel("Type:"));
				bookPanel.add(apptType);
				
				bookPanel.add(new JLabel("Department:"), "gapleft 35, align right" );
				bookPanel.add(departmentDropDown, "growx, align left, sg e");
				
				bookPanel.add(new JLabel("Lab Date: "), "gapleft 30, align right");
				bookPanel.add(yearfw, "sg c, split");
				bookPanel.add(monthfw, "sg c, split");
				bookPanel.add(dayfw, "sg c, span, pushx, wrap");	//span final column to edge of JPanel and push to extend column all the way to edge
				
				bookPanel.add(new JLabel("Select Doctor: "), "skip 2, align right");
				bookPanel.add(chooseDoc, "sg e, align left");
				
				bookPanel.add(new JLabel("Time: "), "align right");
				bookPanel.add(labTime, "sg c, wrap");
				
				bookPanel.add(new JLabel("Select Appointment: "), "skip 2 , align right");
				bookPanel.add(chooseAppt, "sg e, wrap");
				
				bookPanel.add(new JLabel("Referral:"), "skip 2, align right");
				bookPanel.add(fileName, "sg e, align left");
				bookPanel.add(btnSelectFile, "sg b, split, span 2, align left");
				bookPanel.add(btnUploadReferral, "sg b, wrap 20");
				
				
				bookPanel.add(bookAptBtn, "spanx, align right");
				
				
				
				
				
//		//Inner Panel
//			
//			JPanel cancelPanel = new JPanel(new MigLayout("") );
//				cancelPanel.setBorder(BorderFactory.createTitledBorder("List of Upcoming Appointments"));
//				cancelPanel.setBackground(new Color(255, 247, 231));
//				
//				cancelAppt = new JComboBox<String>();
//				cancelApptBtn = new JButton("Cancel Appointment");
//				
//				cancelPanel.add(cancelAppt);
//				cancelPanel.add(cancelApptBtn, "gapleft 10");
				
				
				
		//Add components to main panel
			contentPanel.add(btnReturn, "wrap");
			contentPanel.add(welcomeLabel, "wrap");
			contentPanel.add(infoPanel);
			contentPanel.add(credentialPanel);
			contentPanel.add(infoPanel, "sg a");
			contentPanel.add(credentialPanel, "sg a, wrap");
			contentPanel.add(bookPanel, "span, wrap");
			//contentPanel.add(cancelPanel, "span, growx, wrap");
			

			
	setVisible(true);
	}



	/**
	 * Setup the list of referrals for patient. A button is added that allows the upload of a referral file.
	 */
//	public void setupReferralPanel() {
//		referralPanel = new JPanel(new MigLayout("wrap 1", "align left"));
//		referralPanel.setBorder(BorderFactory.createTitledBorder("Referrals"));
//		setupReferralModel();
//		JList<String> referralList = new JList<String> (model);
//		referralList.setPreferredSize(new Dimension(300,200));
//		btnAddReferral = new JButton("Upload referral");
//		referralPanel.add(btnAddReferral, "south");
//		referralPanel.add(referralList);
//	}

	/**
	 * Update the referral list in referral panel.
	 */
	public void setupReferralModel() {
		model.removeAllElements();
		for (String r : referrals) {
			model.addElement(r);
		}
	}

/**
 * enable/disable menues baed on chosen appointment type
 * 
 * @author Sajid C
 */
	public void disableLab() {
		
		if (apptType.getSelectedItem().equals("Lab Test")) {
			departmentDropDown.setEnabled(false);
			chooseAppt.setEnabled(false);
			chooseDoc.setEnabled(false);
			
			yearfw.setEnabled(true);
			monthfw.setEnabled(true);
			dayfw.setEnabled(true);
			labTime.setEnabled(true);			
		} else {
			departmentDropDown.setEnabled(true);
			chooseAppt.setEnabled(true);
			chooseDoc.setEnabled(true);
			
			yearfw.setEnabled(false);
			monthfw.setEnabled(false);
			dayfw.setEnabled(false);
			labTime.setEnabled(false);	
		}
	}

	
	/**
	 * create a view for year
	 * @author Sajid C
	 * @return drop down menu for years
	 */
	public JComboBox<String> initYearCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		for (int i = 0; i < 120; i++) {
			temp.addItem(Integer.toString(LocalDate.now().getYear()-i) );
		}
		return temp;
	}
	
	
	/**
	 * Calculates the set of years encompassing the next 3 months
	 * @return set of years encompassing the next 3 months in a JComboBox
	 */
	public JComboBox<String> initYearComboForward()
	{
		LocalDate date3MonthsAway = LocalDate.now().plusMonths(3);
		LocalDate now = LocalDate.now();
		
		JComboBox<String> temp = new JComboBox<String>();
		temp.addItem(Integer.toString(now.getYear()) );
		while(now.isBefore(date3MonthsAway) )
		{
			if(now.getYear() == date3MonthsAway.getYear() )
				now = now.plusDays(1);
			else
			{
				temp.addItem(Integer.toString(now.getYear()) );
				now = now.plusDays(1);
			}
		}		
		return temp;
	}
	
	/**
	 * Check that selected lab date is in the future
	 * @return true if selected lab date is in the future
	 */
	public boolean isFutureDate()
	{
		//current values
		int yr = LocalDate.now().getYear();
		int mn = LocalDate.now().getMonthValue();
		int dy = LocalDate.now().getDayOfMonth();
		
		//selected values
		int selYr = Integer.parseInt(yearfw.getItemAt(yearfw.getSelectedIndex()).toString() );
		int selMn = Integer.parseInt(monthfw.getItemAt(monthfw.getSelectedIndex()).toString() );
		int selDay = Integer.parseInt(dayfw.getItemAt(dayfw.getSelectedIndex()).toString() );
		
		if(selMn == mn && selDay > dy)
			return true;
		else if(selMn == mn && selDay <= dy)
			return false;
		else if(selMn < mn)
			return false;
		else
			return true;
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
		
		day.removeAllItems();
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
		
		
		
	}
	
	
	/**
	 * populate days menu with days based on current month selection
	 * @author Sajid C
	 */
	public void initDaysinBoxfw()
	{//
		
		dayfw.removeAllItems();
		//https://www.youtube.com/watch?v=yylaqeWkPmM
		//https://stackoverflow.com/questions/33666456/java8-datetimeformatter-parse-date-with-both-single-digit-and-double-digit-day
		DateTimeFormatter df = DateTimeFormatter.ofPattern("uuuu/M/d")
				.withResolverStyle(ResolverStyle.STRICT);
		for (int i = 1; i <= 31 ; i++)
		{
			try
			{
				df.parse((String) yearfw.getSelectedItem() +"/"+(String) monthfw.getSelectedItem() 
					+"/"+ Integer.toString(i));
				dayfw.addItem(Integer.toString(i));
			}
			catch(Exception e)
			{
				continue;
			}
		}
		
		
		
	}
	
	
	/**
	 * return the entire birth-date as a LocalDateTime object//~~string uuuu-M-d~~
	 * @author Jeeny Z, Sajid C
	 * @return birthdate of user as a string
	 */
	public String getBirthday() {
		return (String) year.getSelectedItem() +"-"+(String) month.getSelectedItem() +"-"+(String) day.getSelectedItem();
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
	 * Shows a yes/no dialog to the user
	 * @author Sajid C
	 * @return true if user selects yes, false otherwise
	 */
	public boolean showOptionPane()
	{
		//https://stackoverflow.com/questions/8396870/joptionpane-yes-or-no-window
		int reply = JOptionPane.showConfirmDialog(contentPanel,
			    "Cancelling this appointment will result in a $25 fine.\n "
			    + "Are you sure you want to cancel?",
			    "Confirm Cancellation",
			    JOptionPane.YES_NO_OPTION);
		
		if (reply == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}
	
	
	
	
	/**Getter and Setter Methods*/	

	public JPanel getContentPanel() {
		return contentPanel;
	}





	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
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





	public JButton getBtnReturn() {
		return btnReturn;
	}





	public void setBtnReturn(JButton btnReturn) {
		this.btnReturn = btnReturn;
	}





	public JButton getSave() {
		return save;
	}





	public void setSave(JButton save) {
		this.save = save;
	}





	public JButton getChangePassword() {
		return changePassword;
	}





	public void setChangePassword(JButton changePassword) {
		this.changePassword = changePassword;
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





	public JPasswordField getPasswordInput() {
		return passwordInput;
	}





	public void setPasswordInput(JPasswordField passwordInput) {
		this.passwordInput = passwordInput;
	}





	public JPasswordField getPasswordInputConfirm() {
		return passwordInputConfirm;
	}





	public void setPasswordInputConfirm(JPasswordField passwordInputConfirm) {
		this.passwordInputConfirm = passwordInputConfirm;
	}





	public JPanel getInfoPanel() {
		return infoPanel;
	}





	public void setInfoPanel(JPanel infoPanel) {
		this.infoPanel = infoPanel;
	}





	public ArrayList<JTextField> getInfoFields() {
		return infoFields;
	}





	public void setInfoFields(ArrayList<JTextField> infoFields) {
		this.infoFields = infoFields;
	}


	public JComboBox<String> getYearCombo() {
		return year;
	}

	public void setYearCombo(JComboBox<String> y) {
		this.year = y;
	}

	public JComboBox<String> getMonthCombo() {
		return month;
	}

	public void setMonthCombo(JComboBox<String> m) {
		this.month = m;
	}

	public JComboBox<String> getDayCombo() {
		return day;
	}

	public void setDayCombo(JComboBox<String> d) {
		this.day = d;
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





	public JComboBox<String> getBlood() {
		return blood;
	}





	public void setBlood(JComboBox<String> blood) {
		this.blood = blood;
	}





	public JComboBox<String> getPosNeg() {
		return posNeg;
	}





	public void setPosNeg(JComboBox<String> posNeg) {
		this.posNeg = posNeg;
	}





	public JComboBox<String> getSex() {
		return sex;
	}





	public void setSex(JComboBox<String> sex) {
		this.sex = sex;
	}





	public JLabel getAge() {
		return age;
	}





	public void setAge(JLabel age) {
		this.age = age;
	}






	public JButton getBookAptBtn() {
		return bookAptBtn;
	}






	public void setBookAptBtn(JButton bookAptBtn) {
		this.bookAptBtn = bookAptBtn;
	}






	public JComboBox<String> getApptType() {
		return apptType;
	}






	public void setApptType(JComboBox<String> apptType) {
		this.apptType = apptType;
	}






	public JComboBox<String> getDepartmentDropDown() {
		return departmentDropDown;
	}






	public void setDepartmentDropDown(JComboBox<String> departmentDropDown) {
		this.departmentDropDown = departmentDropDown;
	}






	public JComboBox<String> getChooseAppt() {
		return chooseAppt;
	}






	public void setChooseAppt(JComboBox<String> chooseAppt) {
		this.chooseAppt = chooseAppt;
	}






	public JComboBox<String> getChooseDoc() {
		return chooseDoc;
	}






	public void setChooseDoc(JComboBox<String> chooseDoc) {
		this.chooseDoc = chooseDoc;
	}






	public JComboBox<String> getLabTime() {
		return labTime;
	}






	public void setLabTime(JComboBox<String> labTime) {
		this.labTime = labTime;
	}






	public JButton getCancelApptBtn() {
		return cancelApptBtn;
	}






	public void setCancelApptBtn(JButton cancelApptBtn) {
		this.cancelApptBtn = cancelApptBtn;
	}






	public JComboBox<String> getCancelAppt() {
		return cancelAppt;
	}






	public void setCancelAppt(JComboBox<String> cancelAppt) {
		this.cancelAppt = cancelAppt;
	}

	
	
	public void setReferrals(ArrayList<String> s) {
		this.referrals = s.toArray(new String[0]);
	}
	
	public JButton getBtnAddReferral() {
		return btnAddReferral;
	}






	public JButton getBtnSelectFile() {
		return btnSelectFile;
	}






	public void setBtnSelectFile(JButton btnSelectFile) {
		this.btnSelectFile = btnSelectFile;
	}






	public JButton getBtnUploadReferral() {
		return btnUploadReferral;
	}






	public void setBtnUploadReferral(JButton btnUploadReferral) {
		this.btnUploadReferral = btnUploadReferral;
	}






	public JTextField getFileName() {
		return fileName;
	}






	public void setFileName(JTextField fileName) {
		this.fileName = fileName;
	}






	public JComboBox<String> getYearfw() {
		return yearfw;
	}






	public void setYearfw(JComboBox<String> yearfw) {
		this.yearfw = yearfw;
	}






	public JComboBox<String> getMonthfw() {
		return monthfw;
	}






	public void setMonthfw(JComboBox<String> monthfw) {
		this.monthfw = monthfw;
	}






	public JComboBox<String> getDayfw() {
		return dayfw;
	}






	public void setDayfw(JComboBox<String> dayfw) {
		this.dayfw = dayfw;
	}
	



	
	
	

	

}
