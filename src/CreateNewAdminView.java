import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

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

/**
 * Creates all the components that are needed to view the GUI for this role.
 * Contains nothing from the controller or view class. Does NOT interact with
 * the associated model class. The controller interacts with this view class,
 * but not the other way around (the view class does not interact with the
 * controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back
 * button listener that closes the view does not need interaction with the
 * model.) Remaining button/field listeners that DO need to interact withe the
 * model are initialized in the controller class.
 */
public class CreateNewAdminView extends JDialog {
	/*
	 * Variables include one contentPane to put in content, name and username
	 * inputs, password inputs, two buttons for creating and cancelling the creation
	 * process.
	 */
	private JPanel contentPanel = new JPanel();

	private JTextField nameInput;
	private JTextField usernameInput;

	private JPasswordField passwordInput;
	private JPasswordField passwordInputConfirm;

	private JButton cancelButton;
	private JButton createButton;

	/**
	 * constructor
	 * 
	 * @author Jeremy Fan, Sajid C
	 * @param title title of the popup box
	 */
	public CreateNewAdminView(String title) {
		// sets frame containers attributes
		setTitle(title);
		setSize(370, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initializeGUI();

	}

	/**
	 * initialize the panels and components that will go inside the frame
	 * 
	 * @author Sajid C, Jeremy F
	 */
	public void initializeGUI() {
//Main panel background
		JPanel Panel = new JPanel(new MigLayout("")); // initialize jpanel and set its layout
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // set insets for the panel
		add(Panel, BorderLayout.CENTER); // add the panel as the container for the frame

//Inner Panels	
		JPanel contentPanel = new JPanel(new MigLayout("wrap 3", "[] 16 [] 8 []")); // miglayout with 3 columns, 16px
																					// space between the first 2
																					// columns, and 8px space between
																					// column 2 and 3

		// Labels
		JLabel name = new JLabel("Name:");
		JLabel username = new JLabel("Username:");
		JLabel pwd = new JLabel("Password:");
		JLabel pwd2 = new JLabel("Re-enter Password:");

		// TextFields
		nameInput = new JTextField();
		usernameInput = new JTextField();

		// PasswordFields
		passwordInput = new JPasswordField();
		passwordInputConfirm = new JPasswordField();

		// Add components to panel in order
		contentPanel.add(name, "right"); // add name label to the right side of the current column
		contentPanel.add(nameInput, "width 80:160:300, sg a, wrap"); // set width of textfield, set component group to
																		// a(optional) and wrap to the next row for the
																		// next component(username)

		contentPanel.add(username, "right");
		contentPanel.add(usernameInput, "sg a,wrap");

		contentPanel.add(pwd, "right");
		contentPanel.add(passwordInput, "sg a,wrap");

		contentPanel.add(pwd2, "right");
		contentPanel.add(passwordInputConfirm, "sg a,wrap");

		Panel.add(contentPanel, "wrap");

		JPanel buttonPanel = new JPanel(new MigLayout("wrap 2")); // set an inner panel with 2 columns for the buttons

		// Buttons
		createButton = new JButton("Create Account");

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			clearInputs();
			setVisible(false);
		});

		// Add components to panel in order
		buttonPanel.add(createButton, "gapleft 50"); // in the current column, place the component with a gap of 50px
														// from the left
		buttonPanel.add(cancelButton, ""); // place the next component in the second column
		Panel.add(buttonPanel); // add inner panel to the outer panel

		setVisible(true);
	}

	/**
	 * clear inputs in the panels
	 * 
	 * @author Sajid C
	 */
	public void clearInputs() {
		nameInput.setText("");
		usernameInput.setText("");
		passwordInput.setText("");
		passwordInputConfirm.setText("");
	}

	/**
	 * pop up a message-dialog box with a message passed in
	 * 
	 * @author Sajid C, Jenny Z
	 */
	public void showDialogToUser(String message) {
		JOptionPane.showMessageDialog(contentPanel, message);
	}

	/** Getter and Setter Methods */
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

}
