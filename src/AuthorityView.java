import javax.swing.JFrame;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class AuthorityView extends JFrame{
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Constructor, takes in its title from Login Model class
	public AuthorityView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setSize(700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		initializeGUI();
	}

	
/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
