
public class CreateNewAdminController {
	
	private CreateNewAdminModel model;
	private CreateNewAdminView view;
	
	

	public CreateNewAdminController(CreateNewAdminModel model, CreateNewAdminView view) {

		this.model = model;
		this.view = view;
		initListeners();
	}
	
	

	/**
	 * initialize the listeners from the view class that need to interact with model
	 * and give functionality to these listeners once they 'hear' something
	 * 
	 * @author Sajid C
	 */
	public void initListeners() 
	{
		view.getCreateButton().addActionListener(e -> parseEntry() );		//handle okay button being pressed
		
	}


	public void parseEntry() {
		
		model.setName(view.getNameInput().getText());

		model.setUsername(view.getUsernameInput().getText());
		model.setPwd(view.getPasswordInput().getPassword());
		model.setPwd2(view.getPasswordInputConfirm().getPassword());
		
		
		
		//try to update database with the user entered information,
		//	or return an error message if cannot
		String response = model.storeInDatabase();
		if( response.compareTo("Account successfully created!") == 0 )
		{
            
			//exit after showing success dialog
			view.showDialogToUser(response);
			view.setVisible(false);
		}
		else
		{
			//pass error message to show user
			view.showDialogToUser(response);
			
		}

		
	}
	

	
}
