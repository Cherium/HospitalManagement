import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;


////Added for account creation and deletion
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class AdminFrame extends JFrame {

    	private JPanel contentPane;
    	private JButton btnReturn;

      ////Added for Account creaiton and deletion
      private JButton crtAccount;
      private JButton dltAccount;
      private NewPatientDialog popupMenu;
      private StartScreen loginFrame;

      private final JPanel contentPanel = new JPanel();
      private DeleteAccount deleter;
      private NewPatientDialog creator;


//	/**
//	 * Launch the application.
//	 */

/*
	public static void main(String[] args) {
    		EventQueue.invokeLater(new Runnable() {
        			public void run() {
            				try {
                					JFrame mframe = new JFrame();
                					mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                					mframe.setSize(400,400);
                					DoctorFrame frame = new DoctorFrame(new Database(), new LoginFrame(new Database()), "doctor");
                					frame.setVisible(true);
            				} catch (Exception e) {
                					e.printStackTrace();
            				}
        			}
    		});
	}

	/**
	 * Create the frame.
	 */
	public AdminFrame(Database dbase, StartScreen main, Admin user) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(800,700);
				setExtendedState(JFrame.MAXIMIZED_BOTH);

				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				GridBagLayout gbl_contentPane = new GridBagLayout();
				gbl_contentPane.columnWidths = new int[]{30, 0, 0};
				gbl_contentPane.rowHeights = new int[]{30, 0, 0};
				gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				contentPane.setLayout(gbl_contentPane);


				//Simple log out button that goes back to the menu screen
				btnReturn = new JButton("Sign Out");
				btnReturn.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
										main.setVisible(true);
										main.clearInputs();
										setVisible(false);
							}
				});
				GridBagConstraints gbc_btnReturn = new GridBagConstraints();
				gbc_btnReturn.insets = new Insets(0, 0, 5, 5);
				gbc_btnReturn.gridx = 0;
				gbc_btnReturn.gridy = 0;
				contentPane.add(btnReturn, gbc_btnReturn);



				JLabel lblName = new JLabel("Hello "+user.getName());
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.gridx = 1;
				gbc_lblName.gridy = 1;
				contentPane.add(lblName, gbc_lblName);

				////Admin Display
				////TODO Adjust the buttons that will allow for creation and deletion of accounts
				JLabel lblPlaceholder = new JLabel("What would you like to do today?");
				GridBagConstraints gbcPlaceholder = new GridBagConstraints();
				gbcPlaceholder.gridx = 1;
				gbcPlaceholder.gridy = 2;
				contentPane.add(lblPlaceholder, gbcPlaceholder);


        ////Currently these two are placeholders till they can be successfully ustilized
        //TODO Ensure that the Create Account actually creates and the same for Delete Account
        crtAccount = new JButton("Create Account");
				crtAccount.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {


                    ////TODO Implement creation
                    ////You're going to have to utilize the class as it is

                    ///*
                    creator = new NewPatientDialog(dbase);
                    //creator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        						creator.setSize(400,200);
        						//creator.setLocationRelativeTo(null);
                    creator.setVisible(true);


                    //JFrame frame = new JFrame();
        						//frame.setSize(200,100);
        						//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        						//JOptionPane.showMessageDialog(frame, "Staff Account created");

                    //main.setVisible(false);
                    //main.clearInputs();
                    //setVisible(true);

                    //*/

							}
				});
        GridBagConstraints gbccrtAccount = new GridBagConstraints();
        gbccrtAccount.insets = new Insets(0, 0, 5, 5);
        gbccrtAccount.gridx = 3;
        gbccrtAccount.gridy = 2;


        dltAccount = new JButton("Delete Account");
        dltAccount.addMouseListener(new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
                    ////TODO Implement deletion

                    //JFrame frame = new JFrame();
        						//frame.setSize(200,100);
        						//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        						//JOptionPane.showMessageDialog(frame, "Account Deleted");

                    deleter = new DeleteAccount(dbase);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


              }
        });
        GridBagConstraints gbcdltAccount = new GridBagConstraints();
        gbcdltAccount.insets = new Insets(0, 0, 5, 5);
        gbcdltAccount.gridx = 3;
        gbcdltAccount.gridy = 3;

        contentPane.add(crtAccount, gbccrtAccount);
        contentPane.add(dltAccount, gbcdltAccount);

        ////TODO Make the distance between the GUI buttons smaller


	}


}
