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

public class DoctorFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn;

	/**
	 * Launch the application.
	 */
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
	public DoctorFrame(Database dbase, LoginFrame main, String user) {
		Doctor doc = (Doctor) dbase.getUsers().get(user);
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
		
		btnReturn = new JButton("Back");
		GridBagConstraints gbc_btnReturn = new GridBagConstraints();
		gbc_btnReturn.insets = new Insets(0, 0, 5, 5);
		gbc_btnReturn.gridx = 0;
		gbc_btnReturn.gridy = 0;
		contentPane.add(btnReturn, gbc_btnReturn);
		
		JLabel lblName = new JLabel(doc.getName());
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		contentPane.add(lblName, gbc_lblName);
	}

	public JButton getBackButton() {
		return btnReturn;
	}

}
