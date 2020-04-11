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
public class AdminView extends JFrame {
	
	private JPanel contentPanel;
	private JPanel schedPanel;

	
	private JButton btnReturn;
    ////Added for Account creaiton and deletion
    private JButton crtAccount;
	private JButton delAccount;
	private JButton editAccount;
    private JButton crtDepartment;
	private JButton delDepartment;
	private JButton reqAvailChangeBtn;

	
    
	private JLabel welcomeLabel;
	JTextArea schedList;
	JScrollPane schedListScroll;

    
    private JTextField createDeptText;
    
    
    private JComboBox<String> rolesDropDown;
	
	private JComboBox<String> year, month, day;
	
	private JComboBox<String>[] availTimes = new JComboBox[14];	//stores al drop-down menues for availability change; index 0= sunday start // index 13= sat end
	
	String[] times = {
			"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "08:00", "09:00", "10:00", "11:00", "12:00"
			, "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"
	};
	

    
    
    
    
    
    
    
	/**
	 * constructor
	 * 
	 * @param title JFrame title
	 */
	public AdminView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(1000, 800));
		setLocationRelativeTo(null);
		
		initializeGUI();
			
	}
	
	
	/**
	 * initialize the panels and components that will go inside the frame
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
				btnReturn.setBackground(new Color(154,50,50));
				btnReturn.setForeground(Color.WHITE);
				
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
	
			JPanel accountPanel = new JPanel(new MigLayout("wrap 2", "[] 16 []") );
				accountPanel.setBorder(BorderFactory.createTitledBorder("Account Management"));
				accountPanel.setBackground(new Color(255, 247, 231));
			
	//Labels
				JLabel chooseRole = new JLabel("Choose Role to Manage:");
				rolesDropDown = new JComboBox<String>();
					rolesDropDown.addItem("Patient");
					rolesDropDown.addItem("Doctor");
					rolesDropDown.addItem("Admin");
					rolesDropDown.addItem("Hospital Authority");
					rolesDropDown.addItem("Nurse");
					rolesDropDown.addItem("Receptionist");
					
	//Buttons			
				crtAccount = new JButton("Create Account");
				delAccount = new JButton("Delete Account");
				editAccount = new JButton("Edit Account");
				
	//Add components to inner panel		
				accountPanel.add(chooseRole, "right");
				accountPanel.add(rolesDropDown, "wrap");
				accountPanel.add(crtAccount);
				accountPanel.add(delAccount);
				accountPanel.add(editAccount);
	
	//inner bottom panel
	schedPanel = new JPanel(new MigLayout("") );
	schedPanel.setBackground(new Color(255, 247, 231));
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
	availChangePanel.setBackground(new Color(255, 247, 231));
	reqAvailChangeBtn = new JButton("Send Request");
	availChangePanel.add(reqAvailChangeBtn, "span, align right");
				
				
				
				
			JPanel deptPanel = new JPanel(new MigLayout("wrap 2", "[] 16 []") );
				deptPanel.setBackground(new Color(255, 247, 231));
				deptPanel.setBorder(BorderFactory.createTitledBorder("Department Management"));
			
	//Labels
				JLabel enterDept = new JLabel("Enter Name of Department to Create:");
				//JLabel removeDept = new JLabel("Choose Department to Remove:");	
				
	//TextFields
				createDeptText = new JTextField();
					createDeptText.setColumns(10);
				
	//ComboBoxes
				//deptDropDown = new JComboBox<String>();
				//deptDropDown.addItem("Patient");
				
	//Buttons			
				crtDepartment = new JButton("Create Department");
				//delDepartment = new JButton("Delete Department");
	//Add components to inner panel
				deptPanel.add(enterDept, "span, wrap");
				deptPanel.add(createDeptText, "sg a, gapleft 30");		//sg a= textfield and combobox will be same size
				deptPanel.add(crtDepartment, "wrap");
	
			
			
		
		
			

//Add inner panel to main container, in order
		
		contentPanel.add(btnReturn,"wrap 30px");
		contentPanel.add(welcomeLabel, "wrap");
		//contentPanel.add(accountPanel, "grow, wrap");	
		contentPanel.add(accountPanel, "wrap");			//print label, wrap to the next row which will be 200 pixels lower
		contentPanel.add(deptPanel, "wrap");
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

	public JButton getCrtAccount() {
		return crtAccount;
	}



	public void setCrtAccount(JButton crtAccount) {
		this.crtAccount = crtAccount;
	}



	public JButton getDltAccount() {
		return delAccount;
	}



	public void setDltAccount(JButton dltAccount) {
		this.delAccount = dltAccount;
	}



	public JButton getEditAccount() {
		return editAccount;
	}



	public void setEditAccount(JButton editAccount) {
		this.editAccount = editAccount;
	}



	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}



	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}



	public JComboBox<String> getRolesDropDown() {
		return rolesDropDown;
	}



	public void setRolesDropDown(JComboBox<String> rolesDropDown) {
		this.rolesDropDown = rolesDropDown;
	}



	public JButton getDelAccount() {
		return delAccount;
	}



	public void setDelAccount(JButton delAccount) {
		this.delAccount = delAccount;
	}


	public JButton getCrtDepartment() {
		return crtDepartment;
	}


	public void setCrtDepartment(JButton crtDepartment) {
		this.crtDepartment = crtDepartment;
	}


	public JButton getDelDepartment() {
		return delDepartment;
	}


	public void setDelDepartment(JButton delDepartment) {
		this.delDepartment = delDepartment;
	}


	public JTextField getCreateDeptText() {
		return createDeptText;
	}


	public void setCreateDeptText(JTextField createDeptText) {
		this.createDeptText = createDeptText;
	}

	public JButton getReqAvailChangeBtn() {
		return reqAvailChangeBtn;
	}

	public void setReqAvailChangeBtn(JButton reqAvailChangeBtn) {
		this.reqAvailChangeBtn = reqAvailChangeBtn;
	}

	public JComboBox<String>[] getAvailTimes() {
		return availTimes;
	}

	public void setAvailTimes(JComboBox<String>[] availTimes) {
		this.availTimes = availTimes;
	}
	public JTextArea getSchedList() {
		return schedList;
	}

	public void setSchedList(JTextArea schedList) {
		this.schedList = schedList;
	}

}
