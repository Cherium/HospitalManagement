import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


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
public class CreateNewDoctorView  extends JDialog{
		/* Variables include one contentPane to put in content, 
		* name and username inputs, password inputs,
		* three buttons for creating, cancelling the creation process, and adding a nurse
		* two combo boxes for nurse dropdowns and department dropdowns 
		* and a box.
		*/

		private JPanel contentPanel = new JPanel();
		private JPanel schedPanel;

		private JTextField nameInput;
		private JTextField usernameInput;
		
		private JPasswordField passwordInput;
		private JPasswordField passwordInputConfirm;
		
		private JButton cancelButton;
		private JButton createButton;
		private JButton addNurse;

		JTextArea schedList;
		JScrollPane schedListScroll;


		
		private JComboBox<String> nurseDropDown;
		private JComboBox<String> departmentDropDown;
		
		private JTextArea box;

		private JComboBox<String> year, month, day;
	
	private JComboBox<String>[] availTimes = new JComboBox[14];	//stores al drop-down menues for availability change; index 0= sunday start // index 13= sat end
	
	String[] times = {
			"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "08:00", "09:00", "10:00", "11:00", "12:00"
			, "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"
	};
		
		
		
		
		/**
		 * constructor
		 * 
		 * @author Jeremy Fan, Sajid C
		 * @param title title of the popup box
		 */
		public CreateNewDoctorView(String title)
		{
			//sets frame containers attributes
			setTitle(title);
			setSize(370, 450);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			initializeGUI();
				
		}
		
		
		
		/**initialize the panels and components that will go inside the frame
		 * @author Sajid C, Jeremy F
		 */	
		public void initializeGUI()
		{
//Main panel background
			JPanel Panel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
			Panel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(Panel, BorderLayout.CENTER);					//add the panel as the container for the frame
			

			
//Inner Panels	
				JPanel contentPanel = new JPanel(new MigLayout("wrap 3", "[] 16 [] 8 []") );		//miglayout with 3 columns, 16px space between the first 2 columns, and 8px space between column 2 and 3
			
				//Labels
					JLabel name = new JLabel("Name:");
					JLabel username = new JLabel("Username:");
					JLabel department = new JLabel("Department:");
					JLabel assignedNurses = new JLabel("Assigned Nurses:");
					JLabel pwd = new JLabel("Password:");
					JLabel pwd2 = new JLabel("Re-enter Password:");
				
				//TextFields
					nameInput = new JTextField();
					usernameInput = new JTextField();
					
				//PasswordFields
					passwordInput = new JPasswordField();
					passwordInputConfirm = new JPasswordField();
					
				//Buttons
					addNurse = new JButton("add");
					
				//TextBox
					box = new JTextArea("List of nurses to be assigned: \n");
					
				//ComboBox drop down lists	
					nurseDropDown = new JComboBox<String>();
					departmentDropDown = new JComboBox<String>();
						departmentDropDown.insertItemAt("", 0);
					
				//Add components to panel in order	
					contentPanel.add(name, "right");								//add name label to the right side of the current column
					contentPanel.add(nameInput, "width 80:160:300, sg a, wrap");	//set width of textfield, set component group to a(optional) and wrap to the next row for the next component(username)
					
					contentPanel.add(username, "right");
					contentPanel.add(usernameInput, "sg a,wrap");
					
					contentPanel.add(department, "right");
					contentPanel.add(departmentDropDown, "sg a,wrap");
					
					contentPanel.add(assignedNurses, "right");
					contentPanel.add(nurseDropDown, "sg a");
					contentPanel.add(addNurse, "wrap");
					contentPanel.add(box, "span 3 2, center, height 50:150:, wrap");	//stretch the box across 3 horizontal columns and 2 vertical rows starting in the current row, set its height, and wrap to next row
					
					
					contentPanel.add(pwd, "right");
					contentPanel.add(passwordInput, "sg a,wrap");
					
					contentPanel.add(pwd2, "right");
					contentPanel.add(passwordInputConfirm, "sg a,wrap");
					
					Panel.add(contentPanel, "wrap");
				
			
				
			
			JPanel buttonPanel = new JPanel(new MigLayout("wrap 2") );			//set an inner panel with 2 columns for the buttons
				
			//Buttons			
				createButton = new JButton("Create Account");
				
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> {clearInputs(); 
				 									setVisible(false);} );		
				
			//Add components to panel in order		
				buttonPanel.add(createButton, "gapleft 50");					//in the current column, place the component with a gap of 50px from the left
				buttonPanel.add(cancelButton, "");								//place the next component in the second column
				Panel.add(buttonPanel);											//add inner panel to the outer panel
			//inner bottom panel
			schedPanel = new JPanel(new MigLayout("") );
			schedPanel.setBackground(new Color(255, 247, 231));
			schedPanel.setBorder(BorderFactory.createTitledBorder("Default Shifts"));
			schedPanel.setPreferredSize(new Dimension(325, 200));

			schedList = new JTextArea();
				schedList.setFont( new Font("monospaced", Font.PLAIN, 10) );	//https://stackoverflow.com/questions/40901128/how-would-i-fix-this-jtextarea-formatting-error
			//wrap availability box in scrollpane
			schedListScroll = new JScrollPane(schedList
					, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			schedListScroll.setPreferredSize(new Dimension(400, 200));
			
			schedPanel.add(schedListScroll);
				contentPanel.add(schedPanel, "sg b, growx");

				
			setVisible(true);
		}

		
	
		
		
		
		
		
		
		/**clear inputs in the panels
		 * @author Sajid C
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



		public JButton getCreateButton() {
			return createButton;
		}



		public void setCreateButton(JButton createButton) {
			this.createButton = createButton;
		}



		public JComboBox<String> getNurseDropDown() {
			return nurseDropDown;
		}



		public void setNurseDropDown(JComboBox<String> nurseDropDown) {
			this.nurseDropDown = nurseDropDown;
		}



		public JComboBox<String> getDepartmentDropDown() {
			return departmentDropDown;
		}



		public void setDepartmentDropDown(JComboBox<String> departmentDropDown) {
			this.departmentDropDown = departmentDropDown;
		}



		public JButton getAddNurse() {
			return addNurse;
		}



		public void setAddNurse(JButton addNurse) {
			this.addNurse = addNurse;
		}



		public JTextArea getBox() {
			return box;
		}



		public void setBox(JTextArea box) {
			this.box = box;
		}

		public JTextArea getSchedList() {
			return schedList;
		}
	
		public void setSchedList(JTextArea schedList) {
			this.schedList = schedList;
		}
		
		
		
		
		
		
		
		
}


