import javax.swing.DefaultComboBoxModel;

/**
 * Controller for this MVC construct Handles all interaction between the
 * associated model class and the view class.
 * 
 * @author Sajid C
 *
 */
public class DeleteAccountController {

	private DeleteAccountModel model;
	private DeleteAccountView view;

	/**
	 * Constructor- sets references to associated view and model of this MVC
	 * construct
	 * 
	 * @author Sajid C
	 * @param model the associated model with this controller
	 * @param view  the associated view with this controller
	 */
	public DeleteAccountController(DeleteAccountModel model, DeleteAccountView view) {
		// TODO Auto-generated constructor stub

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}

	/**
	 * initialize the elements that the GUI sees from the database as soon as the
	 * view first opens for the user.
	 * 
	 * @ author Sajid C
	 */
	public void initView() {

	}

	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() {
		view.getDeleteButton().addActionListener(e -> parseEntry());
	}

	/**
	 * handle the user entered input and provide a debug message to user
	 * 
	 * @author Sajid C
	 */
	public void parseEntry() {

		// send user input to model
		model.setUsername(view.getUsernameInput().getText());

		// handle the input and show user message
		String returnMessage = model.deleteAccount();
		if (returnMessage.compareTo("That Account does not exist!") == 0) {
			view.showDialogToUser(returnMessage);
		} else {
			view.showDialogToUser(returnMessage);
			view.setVisible(false);
		}
	}

}
