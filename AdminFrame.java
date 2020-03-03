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
                    ////Adjust this. This might be too messy
                    ///Honestly I can just copy and paste but that might make this too messy
                    ///Nothing would pop up at all
                    ////As of right now it logs you out
                    ///*

                    main.setVisible(true);
                    main.clearInputs();
                    setVisible(false);

                    NewPatientDialog popupMenu = new NewPatientDialog(dbase);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setSize(400,200);
                    setLocationRelativeTo(null);

                    contentPane = new JPanel();
                    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        						setContentPane(contentPane);
        						GridBagLayout gbl_contentPane = new GridBagLayout();
        						gbl_contentPane.columnWidths = new int[]{10, 10, 0, 0, 0, 0};
        						gbl_contentPane.rowHeights = new int[]{10, 10, 0, 0, 0, 0, 0};
        						gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        						gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        						contentPane.setLayout(gbl_contentPane);

                    ///*

                    {
                          final JLabel lblNewLabel = new JLabel("Name");
        									final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        									gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        									gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        									gbc_lblNewLabel.gridx = 1;
        									gbc_lblNewLabel.gridy = 1;
        									contentPanel.add(lblNewLabel, gbc_lblNewLabel);
        						}
        						{
        									JTextField nameInput = new JTextField();
        									final GridBagConstraints gbc_nameInput = new GridBagConstraints();
        									gbc_nameInput.insets = new Insets(0, 0, 5, 0);
        									gbc_nameInput.fill = GridBagConstraints.HORIZONTAL;
        									gbc_nameInput.gridx = 3;
        									gbc_nameInput.gridy = 1;
        									contentPanel.add(nameInput, gbc_nameInput);
        									nameInput.setColumns(10);
        						}

                    {
        									final JLabel lblNewLabel_1 = new JLabel("Username");
        									final GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        									gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        									gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        									gbc_lblNewLabel_1.gridx = 1;
        									gbc_lblNewLabel_1.gridy = 2;
        									contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
        						}
        						{
        									JTextField usernameInput = new JTextField();
        									final GridBagConstraints gbc_usernameInput = new GridBagConstraints();
        									gbc_usernameInput.insets = new Insets(0, 0, 5, 0);
        									gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
        									gbc_usernameInput.gridx = 3;
        									gbc_usernameInput.gridy = 2;
        									contentPanel.add(usernameInput, gbc_usernameInput);
        									usernameInput.setColumns(10);
        						}
        						{
        									final JLabel lblNewLabel_2 = new JLabel("Password");
        									final GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        									gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        									gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        									gbc_lblNewLabel_2.gridx = 1;
        									gbc_lblNewLabel_2.gridy = 3;
        									contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
        						}
        						{
        									JPasswordField passwordInput = new JPasswordField();
        									final GridBagConstraints gbc_passwordInput = new GridBagConstraints();
        									gbc_passwordInput.insets = new Insets(0, 0, 5, 0);
        									gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
        									gbc_passwordInput.gridx = 3;
        									gbc_passwordInput.gridy = 3;
        									contentPanel.add(passwordInput, gbc_passwordInput);
        						}
        						{
        									final JLabel lblNewLabel_3 = new JLabel("Re-type password");
        									lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        									final GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        									gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        									gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
        									gbc_lblNewLabel_3.gridx = 1;
        									gbc_lblNewLabel_3.gridy = 4;
        									contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
        						}
        						{
        									JPasswordField passwordInputConfirm = new JPasswordField();
        									final GridBagConstraints gbc_passwordInputConfirm = new GridBagConstraints();
        									gbc_passwordInputConfirm.fill = GridBagConstraints.HORIZONTAL;
        									gbc_passwordInputConfirm.gridx = 3;
        									gbc_passwordInputConfirm.gridy = 4;
        									contentPanel.add(passwordInputConfirm, gbc_passwordInputConfirm);
        						}
                    //*/



                    ///*
                    JFrame frame = new JFrame();
        						frame.setSize(200,100);
        						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        						JOptionPane.showMessageDialog(frame, "Staff Account created");

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

                    JFrame frame = new JFrame();
        						frame.setSize(200,100);
        						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        						JOptionPane.showMessageDialog(frame, "Account Deleted");

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
