import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class ReceptionistView extends JFrame{

	private JButton btnReturn;
	
	private JLabel welcomeLabel;
	
	public ReceptionistView(String title)
	{
		//sets frame containers attributes
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		
		initializeGUI();
			
	}

	
	public void initializeGUI() 
	{

		//Main panel background
		JPanel contentPanel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
		
			
		btnReturn = new JButton("Sign Out");
			btnReturn.addActionListener(e -> setVisible(false) );
		
		welcomeLabel = new JLabel();
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
		
		contentPanel.add(btnReturn,"wrap 30px");
		contentPanel.add(welcomeLabel, "wrap");
		
		
		
		setVisible(true);
	}

	
	
/**Getters and Setters*/
	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}


	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}
}
