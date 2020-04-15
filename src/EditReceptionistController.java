import javax.swing.DefaultComboBoxModel;

/**
 * MVC Model: Controller that deals with the edit receptionist functionality,
 * interacts with model and view to help edit receptionist.
 * 
 * @author Jeremy Fan
 */

public class EditReceptionistController {

	// Interacts with the model, personal information view, and view
	private EditReceptionistModel model;
	private EditReceptionistView view;
	private EditReceptionistPersonalInfoView personalInformationView;

	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with
	 * @param view  view that controller interacts with
	 */

	public EditReceptionistController(EditReceptionistModel model, EditReceptionistView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}

	/**
	 * constructor
	 * 
	 * @author Jeremy Fan
	 * 
	 * @param model model that controller interacts with
	 * @param view2 view that controller interacts with
	 */

	public EditReceptionistController(EditReceptionistModel model, EditReceptionistPersonalInfoView view2) {

		this.model = model;
		this.personalInformationView = view2;
		initView();
		initListeners2();
	}

	/*
	 * initialize the elements that the GUI sees from the database as soon as the
	 * view first opens for the user
	 */
	public void initView() {

	}

	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something In this
	 * case, add an action to the "edit schedule" and "edit information" button.
	 * 
	 * @author Sajid C, Jeremy F
	 */
	public void initListeners() {
		view.getEditScheduleButton().addActionListener(e -> parseEntry());
		view.getEditInformationButton().addActionListener(e -> parseEntryPersonalInfo());
	}

	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something In this
	 * case, add an action to the "confirm button".
	 * 
	 * @author Sajid C, Jeremy F
	 */
	public void initListeners2() {
		personalInformationView.getConfirmButton().addActionListener(e -> parseEntryPersonalInfoConfirmation());
	}

	/**
	 * For model, set the the name, username, password, and password confirmation
	 * based on the input for the frontend label
	 * 
	 * @author Sajid C, Jeremy F
	 */
	public void parseEntry() {

		model.setUsername(view.getUsernameInput().getText());

		int returnVal = model.editSchedule();
		if (returnVal == -1) {
			view.showDialogToUser("Account does not exist!");
		} else if (returnVal == 0) {
			view.showDialogToUser("Account is not a authority");
		} else {
			// view.showDialogToUser(returnMessage);
			view.setVisible(false);
		}

	}

	/**
	 * For model, set the username confirmation input for the frontend label
	 * 
	 * @author Sajid C, Jeremy F
	 */

	public void parseEntryPersonalInfo() {

		model.setUsername(view.getUsernameInput().getText());

		int returnVal = model.editPersonalInfo();
		if (returnVal == -1) {
			view.showDialogToUser("Account does not exist!");
		} else if (returnVal == 0) {
			view.showDialogToUser("Account is not a receptionist");
		} else {
			// view.showDialogToUser(returnMessage);
			view.setVisible(false);
		}

	}

	/**
	 * For model, set the name and password confirmation based on the input for the
	 * frontend label
	 * 
	 * @author Jeremy F
	 */

	public void parseEntryPersonalInfoConfirmation() {
		// the below happens once "Confirmed" button is clicked

		model.setUsername(personalInformationView.getUsernameInput().getText());
		String returnMessage = model.checkPersonalInfo();

		// get the department chosen and set it in model

		if (returnMessage.compareTo("That Account does not exist!") == 0) {
			personalInformationView.showDialogToUser(returnMessage);
		} else if (returnMessage.compareTo("This Account is not an Admin!") == 0) {
			personalInformationView.showDialogToUser(returnMessage);
		} else {
			String tempName = personalInformationView.getNameInput().getText();
			char[] tempPass = personalInformationView.getPassInput().getText().toCharArray();
			String tempPassString = personalInformationView.getPassInput().getText();

			if (tempPass.length < 4 && tempPassString.compareTo("") != 0) {
				personalInformationView.showDialogToUser("Password must be longer than 4 characters!");
			}

			else {
				UserSuperClass user = Main.dbase.get(model.getUsername());
				if (tempName.compareTo("") != 0) {
					user.setName(tempName);
					returnMessage = returnMessage + "\n" + "Name changed!";
				}
				if (tempPassString.compareTo("") != 0) {
					user.setPassword(tempPass);
					returnMessage = returnMessage + "\n" + "Password changed!";
				}
				personalInformationView.setVisible(false);
				personalInformationView.showDialogToUser(returnMessage);
			}

		}

	}

}
