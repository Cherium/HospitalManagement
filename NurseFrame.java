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

public class NurseFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrame mframe = new JFrame();
//					mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					mframe.setSize(400,400);
//					DoctorFrame frame = new DoctorFrame(new Database(), new LoginFrame(new Database()), "doctor");
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public NurseFrame(Database dbase, StartScreen main, Nurse user) {
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

				////Nurse Display
				////TODO Create an array display list for Doctor to be displayed for the day
				////Adjust positioning to make this better
				JLabel lblDoc = new JLabel("The doctor you are working with is: ");
				GridBagConstraints gbc_lblDoc = new GridBagConstraints();
				gbc_lblDoc.gridx = 1;
				gbc_lblDoc.gridy = 2;
				contentPane.add(lblDoc, gbc_lblDoc);

				/*
				////Added for testing but aslo for greeting
				JLabel lblMsg = new JLabel("Good morning");
				GridBagConstraints gbclblMsg = new GridBagConstraints();
				gbclblMsg.gridx = 1;
				gbclblMsg.gridy = 0;
				contentPane.add(lblMsg, gbclblMsg);
				//*/

	}


}
