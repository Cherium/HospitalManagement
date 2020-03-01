import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class DoctorFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn;
	private JLabel lblDepartment;
	private JLabel lblNurses;
	private JComboBox<String> comboBox;
	private JLabel lblSchedule;
	private JButton btnOwnSchedule;
	private JLabel lblSchedulePlaceholder;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// JFrame mframe = new JFrame();
	// mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// mframe.setSize(400,400);
	// DoctorFrame frame = new DoctorFrame(new Database(), new LoginFrame(new
	// Database()), "doctor");
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public DoctorFrame(Database dbase, StartScreen main, Doctor user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		btnReturn = new JButton("Back");
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

		JLabel lblName = new JLabel(user.getName());
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		contentPane.add(lblName, gbc_lblName);

		lblDepartment = new JLabel(user.getDepartment());
		lblDepartment.setFont(new Font("Sitka Small", Font.ITALIC, 24));
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 0);
		gbc_lblDepartment.gridx = 20;
		gbc_lblDepartment.gridy = 1;
		contentPane.add(lblDepartment, gbc_lblDepartment);

		lblNurses = new JLabel("Assigned nurses:");
		lblNurses.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNurses = new GridBagConstraints();
		gbc_lblNurses.anchor = GridBagConstraints.EAST;
		gbc_lblNurses.insets = new Insets(0, 0, 5, 5);
		gbc_lblNurses.gridx = 1;
		gbc_lblNurses.gridy = 3;
		contentPane.add(lblNurses, gbc_lblNurses);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(new Color(0, 0, 0));
		for (Nurse n : user.getNurses()){
			comboBox.addItem(n.getName());
		}
		comboBox.setSelectedIndex(-1);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblSchedulePlaceholder.setText(comboBox.getSelectedItem() + "'s Schedule");
				btnOwnSchedule.setVisible(true);
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		contentPane.add(comboBox, gbc_comboBox);
		
		lblSchedule = new JLabel("Schedule");
		lblSchedule.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblSchedule = new GridBagConstraints();
		gbc_lblSchedule.insets = new Insets(0, 0, 5, 5);
		gbc_lblSchedule.gridx = 1;
		gbc_lblSchedule.gridy = 5;
		contentPane.add(lblSchedule, gbc_lblSchedule);
		
		btnOwnSchedule = new JButton("See my schedule");
		btnOwnSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblSchedulePlaceholder.setText("Doctor's Schedule");
				btnOwnSchedule.setVisible(false);
			}
		});
		
		btnOwnSchedule.setVisible(false);
		GridBagConstraints gbc_btnOwnSchedule = new GridBagConstraints();
		gbc_btnOwnSchedule.insets = new Insets(0, 0, 5, 5);
		gbc_btnOwnSchedule.gridx = 2;
		gbc_btnOwnSchedule.gridy = 5;
		contentPane.add(btnOwnSchedule, gbc_btnOwnSchedule);
		
		lblSchedulePlaceholder = new JLabel("Doctor's Schedule");
		GridBagConstraints gbc_lblSchedulePlaceholder = new GridBagConstraints();
		gbc_lblSchedulePlaceholder.insets = new Insets(0, 0, 0, 5);
		gbc_lblSchedulePlaceholder.gridx = 1;
		gbc_lblSchedulePlaceholder.gridy = 6;
		contentPane.add(lblSchedulePlaceholder, gbc_lblSchedulePlaceholder);
		
	}


}
