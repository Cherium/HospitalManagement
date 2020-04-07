import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
public class CreateNewPatientView  extends JDialog{
	
	private JPanel contentPanel;
	
	private JTextField nameInput;
	private JTextField usernameInput;
	private JTextField address;
	private JTextField phone;
	private JTextField email;
	
	private JPasswordField passwordInput;
	private JPasswordField passwordInputConfirm;
	
	private JButton cancelButton;
	private JButton createButton;

	private JComboBox<String> year, month, day;
	private JComboBox<String> blood, posNeg;
	private JComboBox<String> sex;
		
		
		
		
		
		
		
		
		
		
		
		/**
		 * constructor
		 * 
		 * @param title JFrame title
		 */
		public CreateNewPatientView(String title)
		{
			//sets frame containers attributes
			setTitle(title);
			setSize(370, 450);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			initializeGUI();
				
		}
		
		
		/**
		 * initialize the panels and components that will go inside the frame
		 * @author Sajid C
		 */
		public void initializeGUI()
		{
	
			//Main panel background
			contentPanel = new JPanel(new MigLayout("wrap 2", "[align right] 16 [align left]") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
			
		
			//Labels
				JLabel nameLab = new JLabel("Name:");
				JLabel birthday = new JLabel("Date of birth:");
				JLabel bloodLabel = new JLabel("Blood Type:");
				JLabel sexLabel = new JLabel("Sex:");
				JLabel usernameLab = new JLabel("Username:");
				JLabel addressLab = new JLabel("Address:");
				JLabel PhoneNumLab = new JLabel("Phone #:");
				JLabel emailLab = new JLabel("Email:");
				JLabel pwd = new JLabel("Password:");
				JLabel pwd2 = new JLabel("Re-enter Password:");
				
			
				
			//TextFields
				nameInput = new JTextField(20);
				usernameInput = new JTextField(20);
				address = new JTextField(20);
				phone = new JTextField(20);
				email = new JTextField(20);
				
			//PasswordFields
				passwordInput = new JPasswordField(20);
				passwordInputConfirm = new JPasswordField(20);
				
			//Buttons
				createButton = new JButton("Create Account");
				cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(e -> {setVisible(false);} );	
					
			//ComboBoxes
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
				
					
					
				

				
			//Add to main panel
			contentPanel.add(nameLab);
			contentPanel.add(nameInput, "wrap");
			
			contentPanel.add(birthday);
			contentPanel.add(year, "sg b, split");	//split splits a current column into 2. Everything in 'sgroup b' will be the same size
			contentPanel.add(month, "sg b, split");
			contentPanel.add(day, "sg b, wrap");
			
			contentPanel.add(bloodLabel);
			contentPanel.add(blood, "wrap");
			
			contentPanel.add(sexLabel);
			contentPanel.add(sex, "wrap");
			
			contentPanel.add(addressLab);
			contentPanel.add(address, "wrap");
			contentPanel.add(PhoneNumLab);
			contentPanel.add(phone, "wrap");
			contentPanel.add(emailLab);
			contentPanel.add(email, "wrap");
			contentPanel.add(usernameLab);
			contentPanel.add(usernameInput, "wrap");
			contentPanel.add(pwd);
			contentPanel.add(passwordInput, "wrap");
			contentPanel.add(pwd2);
			contentPanel.add(passwordInputConfirm, "wrap 10");
			
			contentPanel.add(createButton, "sg a");
			contentPanel.add(cancelButton, "sg a");
			
			
				
				
				
				
				
			setVisible(true);
		}

		
		
		
		
		
		/**
		 * clear textfield iputs
		 * 
		 */
		public void clearInputs() 
		{
			nameInput.setText("");
			usernameInput.setText("");
			passwordInput.setText("");
			passwordInputConfirm.setText("");		
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
			
		}
		
		/**
		 * return the entire birth-date as a LocalDateTime object//~~string uuuu-M-d~~
		 * @author Jenny Z, Sajid C
		 * @return birthdate of user as a string
		 */
		public String getBirthday() {
			return (String) year.getSelectedItem() +"-"+(String) month.getSelectedItem() +"-"+(String) day.getSelectedItem();
		}
		
		
		
		
		
		
		
		
		

		
/**Getter and Setter Methods*/		
		public JTextField getNameInput() {
			return nameInput;
		}



		public void setNameInput(JTextField nameInput) {
			this.nameInput = nameInput;
		}



		public JTextField getUsernameInput() {
			return usernameInput;
		}



		public void setUsernameInput(JTextField usernameInput) {
			this.usernameInput = usernameInput;
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



		public JButton getCancelButton() {
			return cancelButton;
		}



		public void setCancelButton(JButton cancelButton) {
			this.cancelButton = cancelButton;
		}


		public JPanel getContentPanel() {
			return contentPanel;
		}


		public void setContentPanel(JPanel contentPanel) {
			this.contentPanel = contentPanel;
		}


		public JTextField getAddress() {
			return address;
		}


		public void setAddress(JTextField address) {
			this.address = address;
		}


		public JTextField getPhone() {
			return phone;
		}


		public void setPhone(JTextField phone) {
			this.phone = phone;
		}


		public JTextField getEmail() {
			return email;
		}


		public void setEmail(JTextField email) {
			this.email = email;
		}


		public JButton getCreateButton() {
			return createButton;
		}


		public void setCreateButton(JButton createButton) {
			this.createButton = createButton;
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

		
		
		
		
}


