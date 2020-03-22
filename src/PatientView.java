import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class PatientView extends JFrame{
	
	private JPanel contentPanel;
	private JPanel infoPanel;
	
	private JLabel welcomeLabel;
	private JLabel usernameLabel;
	private JLabel amountDue;
	private JLabel age;


	private JButton btnReturn;
	private JButton save;
	private JButton changePassword;
	
	private JTextField nameText;
	private JTextField addrText;
	private JTextField phText;
	private JTextField emailText;
	private JTextField amountText;
	
	private ArrayList<JTextField> infoFields = new ArrayList<>(5);	//add all JTextFields into an Array to add listeners later
	
	
	private JPasswordField passwordInput;
	private JPasswordField passwordInputConfirm;

	private JComboBox<String> year, month, day;
	private JComboBox<String> blood, posNeg;
	private JComboBox<String> sex;
	
	
	
	//Constructor
	public PatientView(String title)
	{
		//create frame container
		setTitle(title);
		setSize(700,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		initializeGUI();
	}

	
	
	

	/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI() 
	{
		
//Main panel background
		contentPanel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
		
			btnReturn = new JButton("Sign Out");
				btnReturn.addActionListener(e -> setVisible(false) );

			welcomeLabel = new JLabel();
				welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));	

//Inner Panels
			
			infoPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );
				infoPanel.setBorder(BorderFactory.createTitledBorder("Patient Information"));
				
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
					year.addActionListener(e -> initDaysinBox());
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
					blood.addItem("O+"); blood.addItem("O+");

			// JPanel, for having the combo boxes in one line
			JPanel bdayP = new JPanel();
			bdayP.add(year);
			bdayP.add(month);
			bdayP.add(day);

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
					
				
			
					
					
			JPanel credentialPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );
				credentialPanel.setBorder(BorderFactory.createTitledBorder("Patient Credentials"));
			
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
			
		//Add components to inner panel
				credentialPanel.add(usernameTitleLabel);
				credentialPanel.add(usernameLabel, "wrap");
				credentialPanel.add(pwdLabel);
				credentialPanel.add(passwordInput, "wrap");
				credentialPanel.add(pwd2Label);
				credentialPanel.add(passwordInputConfirm, "wrap 20");
				
				credentialPanel.add(changePassword, "span, center, wrap");
			
				
					
				
		//Add components to main panel
			contentPanel.add(btnReturn, "wrap");
			contentPanel.add(welcomeLabel, "wrap");
			contentPanel.add(infoPanel);
			contentPanel.add(credentialPanel);
			

			
	setVisible(true);
	}


	//create box for year
	public JComboBox<String> initYearCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		for (int i = 0; i < 120; i++) {
			temp.addItem((LocalDate.now().getYear()-i)+"");
		}
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	//create box for month
	public JComboBox<String> initMonthCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		for (int i = 0; i < 12; i++) {
			temp.addItem((i+1)+"");
		}
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	//create box for day
	public JComboBox<String> initDayCombo() {
		JComboBox<String> temp = new JComboBox<String>();
		temp.setBackground(Color.WHITE);
		
		return temp;
	}
	
	//add days to day box
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
		
	}
	
	//return the entire birth-date as a LocalDateTime object//~~string uuuu-M-d~~
	public String getBirthday() {
		return (String) year.getSelectedItem() +"/"+(String) month.getSelectedItem() +"/"+(String) day.getSelectedItem();
	}
	

	
	
	
	
	
	//show dialog message to user
	public void showDialogToUser(String message)
	{
		JOptionPane.showMessageDialog(contentPanel, message);
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

	
	
	
	

	





	
	
	

	

}
