import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import javax.swing.JComboBox;
import java.awt.Color;

public class NewUserDialog extends JDialog {

			private final JPanel contentPanel = new JPanel();
			private JTextField nameInput;
			private JTextField usernameInput;
      private JTextField roleInput;
			private JPasswordField passwordInput;
			private JPasswordField passwordInputConfirm;
			private JButton cancelButton;
			private JButton okButton;
			private JComboBox<String> roleBox;

			/**
			 * Launch the application.
			 */
			public static void main(final String[] args) {
						try {
									final NewUserDialog dialog = new NewUserDialog(new Database());
									dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									dialog.setVisible(true);
						} catch (final Exception e) {
									e.printStackTrace();
						}
			}

			/**
			 * Create the dialog.
			 */
			public NewUserDialog(final Database dbase) {

						setSize(400, 230);
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
									final JLabel lblNewLabel = new JLabel("Name");
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
									final JLabel lblNewLabel_1 = new JLabel("Username");
									final GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
									gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
									gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
									gbc_lblNewLabel_1.gridx = 1;
									gbc_lblNewLabel_1.gridy = 2;
									contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
						}
						{
									usernameInput = new JTextField();
									final GridBagConstraints gbc_usernameInput = new GridBagConstraints();
									gbc_usernameInput.insets = new Insets(0, 0, 5, 0);
									gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
									gbc_usernameInput.gridx = 3;
									gbc_usernameInput.gridy = 2;
									contentPanel.add(usernameInput, gbc_usernameInput);
									usernameInput.setColumns(10);
						}


/*            {
									final JLabel roleLabel = new JLabel("Role");
									final GridBagConstraints gbc_roleLabel = new GridBagConstraints();
									gbc_roleLabel.anchor = GridBagConstraints.EAST;
									gbc_roleLabel.insets = new Insets(0, 0, 5, 5);
									gbc_roleLabel.gridx = 1;
									gbc_roleLabel.gridy = 3;
									contentPanel.add(roleLabel, gbc_roleLabel);
						}
						{
									roleInput = new JTextField();
									final GridBagConstraints gbc_roleInput = new GridBagConstraints();
									gbc_roleInput.insets = new Insets(0, 0, 5, 0);
									gbc_roleInput.fill = GridBagConstraints.HORIZONTAL;
									gbc_roleInput.gridx = 3;
									gbc_roleInput.gridy = 3;
									contentPanel.add(roleInput, gbc_roleInput);
									nameInput.setColumns(10);
						}*/


						JLabel lblNurses = new JLabel("Assigned nurses:");
						lblNurses.setHorizontalAlignment(SwingConstants.LEFT);

						GridBagConstraints gbc_lblNurses = new GridBagConstraints();
						gbc_lblNurses.insets = new Insets(0, 0, 5, 0);
						gbc_lblNurses.gridx = 1;
						gbc_lblNurses.gridy = 3;
						contentPanel.add(lblNurses, gbc_lblNurses);



						String[] petStrings = { "Doctor", "Nurse", "Patient", "Admin", "Authority" };
						roleBox = new JComboBox<String>();
						roleBox.setBackground(Color.WHITE);
						roleBox.setSelectedIndex(-1);
						for (int i =0; i < petStrings.length; i++) {
									roleBox.addItem(petStrings[i]);
						}
						GridBagConstraints gbc_nurseComboBox = new GridBagConstraints();
						gbc_nurseComboBox.fill = GridBagConstraints.HORIZONTAL;
						gbc_nurseComboBox.insets = new Insets(0, 0, 5, 0);
						gbc_nurseComboBox.gridx = 3;
						gbc_nurseComboBox.gridy = 3;

						contentPanel.add(roleBox, gbc_nurseComboBox);



						{
									final JLabel lblNewLabel_2 = new JLabel("Password");
									final GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
									gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
									gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
									gbc_lblNewLabel_2.gridx = 1;
									gbc_lblNewLabel_2.gridy = 4;
									contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
						}
						{
									passwordInput = new JPasswordField();
									final GridBagConstraints gbc_passwordInput = new GridBagConstraints();
									gbc_passwordInput.insets = new Insets(0, 0, 5, 0);
									gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
									gbc_passwordInput.gridx = 3;
									gbc_passwordInput.gridy = 4;
									contentPanel.add(passwordInput, gbc_passwordInput);
						}


						{
									final JLabel lblNewLabel_3 = new JLabel("Re-type password");
									lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
									final GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
									gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
									gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
									gbc_lblNewLabel_3.gridx = 1;
									gbc_lblNewLabel_3.gridy = 5;
									contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
						}
						{
									passwordInputConfirm = new JPasswordField();
									final GridBagConstraints gbc_passwordInputConfirm = new GridBagConstraints();
									gbc_passwordInputConfirm.fill = GridBagConstraints.HORIZONTAL;
									gbc_passwordInputConfirm.gridx = 3;
									gbc_passwordInputConfirm.gridy = 5;
									contentPanel.add(passwordInputConfirm, gbc_passwordInputConfirm);
						}


						{
									final JPanel buttonPane = new JPanel();
									buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
									getContentPane().add(buttonPane, BorderLayout.SOUTH);
									{
												okButton = new JButton("Create");
												okButton.addMouseListener(new MouseAdapter() {
															@Override
															public void mousePressed(final MouseEvent e) {
																		createAPatient(dbase);
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
																closeDialog();
													}
										});
										cancelButton.setActionCommand("Cancel");
										buttonPane.add(cancelButton);
									}
						}
			}


			// false = not valid password, true = valid password
			protected boolean checkPassword(final char[] testPwd){
						if (testPwd.length < 4){
									return false;
						}
						else {
									return true;
						}
			}

			////Records input values
			protected void createAPatient(final Database dbase) {
						final String name = nameInput.getText();
						final String username = usernameInput.getText();
            final String role = (String) roleBox.getSelectedItem();
						final char[] pwd = passwordInput.getPassword();
						final char[] pwdCheck = passwordInputConfirm.getPassword();

						////Create Account - Compares if the 2 passwords are equal
						////
						if (Arrays.equals(pwd, pwdCheck)) {
									////Creates the actual account. No error.

									if (checkPassword(pwd) == false) {
												JOptionPane.showMessageDialog(contentPanel, "Invalid password, password must be greater than 4 characters long.");
									} else if (username.length()==0){
												JOptionPane.showMessageDialog(contentPanel, "Username must not be blank");
									} else if (name.length()==0){
												JOptionPane.showMessageDialog(contentPanel, "Name must not be blank");
									}
									else{
												int a;
												a = role.compareTo("Doctor");
												if (a == 0){
															final Doctor newDoc = new Doctor(name, username, pwd, null, null);
															if (dbase.userExists(newDoc)){
																		JOptionPane.showMessageDialog(contentPanel, "Username taken.");
															}
															else{
																		dbase.addUser(newDoc);
																		closeDialog();
																		JOptionPane.showMessageDialog(null, "Doctor Account made succesfully!");
																	}
														}
												a = role.compareTo("Patient");
												if (a == 0){
															final Patient newPat = new Patient(name, username, pwd);
															if (dbase.userExists(newPat)){
																		JOptionPane.showMessageDialog(contentPanel, "Username taken.");
															}
															else{
																		dbase.addUser(newPat);
																		closeDialog();
																		JOptionPane.showMessageDialog(null, "Patient Account made succesfully!");
																	}
														}
												a = role.compareTo("Nurse");
												if (a == 0){
															final Nurse newNur = new Nurse(name, username, pwd, null, null);
															if (dbase.userExists(newNur)){
																		JOptionPane.showMessageDialog(contentPanel, "Username taken.");
															}
															else{
																		dbase.addUser(newNur);
																		closeDialog();
																		JOptionPane.showMessageDialog(null, "Nurse made succesfully!");
																	}
														}
												a = role.compareTo("Admin");
												if (a == 0){
															final Admin newAdm = new Admin(name, username, pwd);
															if (dbase.userExists(newAdm)){
																		JOptionPane.showMessageDialog(contentPanel, "Username taken.");
															}
															else{
																		dbase.addUser(newAdm);
																		closeDialog();
																		JOptionPane.showMessageDialog(null, "Admin Account made succesfully!");
																	}
														}
												a = role.compareTo("Authority");
												if (a == 0){
															final Authority newAut = new Authority(name, username, pwd);
															if (dbase.userExists(newAut)){
																		JOptionPane.showMessageDialog(contentPanel, "Username taken.");
															}
															else{
																		dbase.addUser(newAut);
																		closeDialog();
																		JOptionPane.showMessageDialog(null, "Authority Account made succesfully!");
																	}
														}
									}

								} else {
											////Passwords non-matching means none
											JOptionPane.showMessageDialog(contentPanel, "Passwords don't match.");
								}
			}


			public JButton getCancelButton() {
						return cancelButton;
			}

			public JButton getOkButton() {
						return okButton;
			}

			public void clearInputs() {
						nameInput.setText("");
						usernameInput.setText("");
						passwordInput.setText("");
						passwordInputConfirm.setText("");
			}

			public void closeDialog() {
						setVisible(false);
						clearInputs();
			}

}
