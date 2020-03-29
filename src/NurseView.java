import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.*/
public class NurseView extends JFrame{
	
	private JPanel panel;
	
	private JButton btn;
	
	private JLabel label;
	private JLabel label2;
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Constructor, takes in its title from Login Model class
	public NurseView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setSize(700,700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		initializeGUI();
	}

	
	
	

	/**initialize the panels and components that will go inside the frame*/
	public void initializeGUI() 
	{
		
		//set panel container(with a layout) inside the frame
		panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);						//set 'panel' as frames inner container
		GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{10, 10, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{10, 10, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};		
		panel.setLayout(gbl_panel);

		
		
		//create UI elements and add to panel
		GridBagConstraints c = new GridBagConstraints();
//LABELS		
		label = new JLabel("Username");
			c.insets = new Insets(0, 0, 5, 5);
			c.gridx = 3;
			c.gridy = 4;
			panel.add(label, c);
			
		label2 = new JLabel("Username");
			c.insets = new Insets(0, 0, 5, 5);
			c.gridx = 3;
			c.gridy = 5;
			panel.add(label2, c);

//BUTTONS			
		btn = new JButton("Sign Out");
			btn.addActionListener(e -> setVisible(false));
			c.insets = new Insets(0, 0, 5, 5);
			c.gridx = 2;
			c.gridy = 1;
			panel.add(btn, c);
			


		setVisible(true);
	}





	
	
	
	

	/**Getter and Setter Methods*/


	public JButton getBtn() {
		return btn;
	}






	public void setBtn(JButton btn) {
		this.btn = btn;
	}






	public JLabel getLabel() {
		return label;
	}






	public void setLabel(JLabel label) {
		this.label = label;
	}






	public JLabel getLabel2() {
		return label2;
	}






	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}	


	
	
	

	

}
