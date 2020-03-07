//Check Override
import java.awt.BorderLayout;
import java.awt.FlowLayout;

//For delete notification
import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class DeleteAccount extends JDialog{

  		private final JPanel contentPanel = new JPanel();

			private JTextField nameInput;
			private JButton cancelButton;
			private JButton okButton;


      public static void main(final String[] args) {
						try {
									//final NewPatientDialog dialog = new NewPatientDialog(new Database());
									//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									//dialog.setVisible(true);
                  Database dbase = new Database();
                  DeleteAccount deleter = new DeleteAccount(dbase);
                  deleter.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                  deleter.setVisible(true);

						} catch (final Exception e) {
									e.printStackTrace();
						}
			}


      public DeleteAccount(Database dbase){

            setVisible(true);
						setSize(400, 200);
						setLocationRelativeTo(null);
						getContentPane().setLayout(new BorderLayout());

						contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
						getContentPane().add(contentPanel, BorderLayout.CENTER);
						final GridBagLayout gbl_contentPanel = new GridBagLayout();
						gbl_contentPanel.columnWidths = new int[]{50, 0, 25, 100, 0};
						gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
						gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
						gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
						contentPanel.setLayout(gbl_contentPanel);
						{
									final JLabel lblNewLabel = new JLabel("Username");
									final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
									gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
									gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
									gbc_lblNewLabel.gridx = 1;
									gbc_lblNewLabel.gridy = 1;
									contentPanel.add(lblNewLabel, gbc_lblNewLabel);
						}
						{
									nameInput = new JTextField();
									final GridBagConstraints gbc_nameInput = new GridBagConstraints();
									gbc_nameInput.insets = new Insets(0, 0, 5, 0);
									gbc_nameInput.fill = GridBagConstraints.HORIZONTAL;
									gbc_nameInput.gridx = 3;
									gbc_nameInput.gridy = 1;
									contentPanel.add(nameInput, gbc_nameInput);
									nameInput.setColumns(10);
						}

						{
									final JPanel buttonPane = new JPanel();
									buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
									getContentPane().add(buttonPane, BorderLayout.SOUTH);
									{
												okButton = new JButton("Delete");
												okButton.addMouseListener(new MouseAdapter() {
      												@Override
      												public void mousePressed(final MouseEvent e) {
                                    setVisible(false);
                                    JFrame frame = new JFrame();
                        						frame.setSize(200,100);
                        						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    deleteAPatient(dbase);

      												}
												});
												okButton.setActionCommand("OK");
												buttonPane.add(okButton);
												getRootPane().setDefaultButton(okButton);
									}
									{
    										cancelButton = new JButton("Cancel");
    										cancelButton.addMouseListener(new MouseAdapter() {
												@Override
												public void mousePressed(final MouseEvent arg0) {
                              setVisible(false);
                  						nameInput.setText("");
												}
									});
    										cancelButton.setActionCommand("Cancel");
    										buttonPane.add(cancelButton);
									}
						}


      }

      protected void deleteAPatient(Database dbase){
            final String name = nameInput.getText();
            if (dbase.findUser(name)){
                  User u = dbase.getUser(name);
                  dbase.removeUser(u);
                  JOptionPane.showMessageDialog(contentPanel, "Account deleted");
            }
            else{
                  JOptionPane.showMessageDialog(contentPanel, "No Account detected.");
            }

      }

}
