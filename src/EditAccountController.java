import javax.swing.DefaultComboBoxModel;

public class EditAccountController {
	
	private EditAccountModel model;
	private EditAccountView view;
		
	//constructor
	public EditAccountController(EditAccountModel model, EditAccountView view) {
		// TODO Auto-generated constructor stub
		
		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView()
	{
		
	}
	
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{
		view.getEditButton().addActionListener(e -> parseEntry() );	
	}

	
	
	//handle the user entered input
	public void parseEntry() {
		
		model.setUsername(view.getUsernameInput().getText());
		
		String returnMessage = model.editAccount();
		if(returnMessage.compareTo("That Account does not exist!") == 0)
		{
			view.showDialogToUser(returnMessage);
		}
		else
		{
			//view.showDialogToUser(returnMessage);
			view.setVisible(false);
		}
	}

}
