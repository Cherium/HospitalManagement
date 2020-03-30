import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;

import net.miginfocom.swing.MigLayout;


/**
 * Creates all the components that are needed to view the GUI for this role. Contains nothing from the controller or view class.
 * Does NOT interact with the associated model class. The controller interacts with this view class, but not the other way around (the view class
 * does not interact with the controller)
 * 
 * Contains listeners that DO NOT need interaction with the model (ex. a back button listener that closes the view does not need interaction
 * with the model.)
 * Remaining button/field listeners that DO need to interact withe the model are initialized in the controller class.
 * 
 * @author 
 *
 */
public class ReceptionistView extends JFrame {

	//Added for patient track
	private ArrayList<String> newPatient = new ArrayList<String>();

	private JFrame frame;

	private JButton btnReturn;
	private JButton btnAddTreatmentNotes;

	private JLabel welcomeLabel;

	private JTextArea patientInformation = new JTextArea(7, 90);
	private JTextArea pastTreatments;
	private JTextArea currentTreatment;

	private JPanel listPatientsPanel;
	private JPanel patientPanel;
	private JPanel scheduleContainer;
	private JPanel infoPanel;

	private JScrollPane scroll;
	private JList listPatients;
	
	public ReceptionistView(String title)
	{
		//sets frame containers attributes
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1000,700));
		setPreferredSize(new Dimension(1000, 700));
		setLocation(10, 10);

		initializeGUI();
		
			

	}


	public void initializeGUI() 
	{

		//Main panel background
		JPanel contentPanel = new JPanel(new MigLayout("") );		//initialize jpanel and set its layout
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	//set insets for the panel		
			add(contentPanel, BorderLayout.CENTER);					//add the panel as the container for the frame
		
		/*
			#################################################
			#	Back										#
			#	Name, M.D.						#
			#												#
			#################################################
		*/



			
		btnReturn = new JButton("Sign Out");
			btnReturn.addActionListener(e -> setVisible(false) );
		
		welcomeLabel = new JLabel();
			welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		
	



		contentPanel.add(btnReturn,"wrap 30px");
		contentPanel.add(welcomeLabel, "wrap");


		patientPanel = new JPanel();
			patientPanel.setBorder(new LineBorder(Color.CYAN));
			patientPanel.setAlignmentY(Component.TOP_ALIGNMENT);
			// patientPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			patientPanel.setLayout(new BorderLayout());
			patientPanel.setBackground(Color.WHITE);
			patientPanel.setVisible(true);
		contentPanel.add(patientPanel, "wrap");
		// Main issue right here



		
		
		JScrollPane scroll = new JScrollPane(listPatients, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setPreferredSize(new DimensionUIResource(200, 0));
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		

		//contentPanel.add(scroll, BorderLayout.WEST);
		//contentPanel.add(scroll, "wrap 2");
		initializeVariables();
		initializeButtons();
		
		setVisible(true);
	}

	
	///

	/**
	 * Set up the patients view in receptionist
	 */
	public void initializeVariables() {
		listPatientsPanel = new JPanel();
		listPatientsPanel.setLayout(new MigLayout("wrap 1"));
		listPatientsPanel.setBackground(Color.WHITE);
	
		
		
		listPatientsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		listPatientsPanel.revalidate();
		listPatientsPanel.repaint();

		JPanel selectedPatient = new JPanel(new MigLayout("wrap 1"));
		selectedPatient.setBorder(BorderFactory.createTitledBorder("Patient"));
		selectedPatient.setBackground(Color.WHITE);
		selectedPatient.add(new JLabel("Patient Information"));
		patientInformation.setText("Name\nAge\nSex\nBlood type\nAddress\nPhone\nEmail\n");
		patientInformation.setEnabled(false);
		selectedPatient.add(patientInformation);
		selectedPatient.add(new JLabel("Detailed Treatment History"));
		pastTreatments = new JTextArea(0,200);
		pastTreatments.setLineWrap(true);
		pastTreatments.setText("Past treatment histories and doctors who recommended treatment.\nTreatment 1: Rest\n\t\t- Doctor: First Last\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas pharetra tempus gravida. Vivamus mollis erat sed libero maximus tempor. Aliquam sed orci non sem fringilla gravida. Duis maximus vitae lacus ut scelerisque. Donec quis mauris eget sapien fringilla tempor ut id nunc. Maecenas a diam non neque ornare porta. Sed volutpat in urna et scelerisque. Nulla consequat justo mauris, in aliquet dolor rutrum eget. Vivamus mauris metus, vehicula nec efficitur ac, ullamcorper quis neque. Nulla augue nisi, porttitor quis nisl in, condimentum euismod nisi. Nunc et leo bibendum nisi ultrices sollicitudin vulputate vitae nisi. Cras nec purus vestibulum, vehicula magna a, pharetra est. Sed tristique, nisi nec suscipit sagittis, tellus dolor tempor ipsum, a eleifend magna purus et neque. Curabitur porta non nisl posuere bibendum. Nam sit amet neque quis enim vehicula scelerisque quis id massa.\nIn ut placerat est. Fusce eu scelerisque lorem. Fusce at mi maximus, condimentum erat et, semper lectus. Donec mollis aliquam nibh, et consequat metus pretium ultrices. Morbi blandit placerat orci. Aliquam erat volutpat. Aliquam id metus orci. Maecenas sagittis mollis nisl, eu ornare nulla lacinia a. Cras congue tristique neque, vitae hendrerit odio. Morbi convallis leo sit amet nisi elementum, in tempor augue bibendum.\nDuis sit amet tempor enim. Proin eleifend, metus vel sodales consectetur, quam magna pellentesque lacus, eget lacinia leo nisl eu nisi. Vivamus aliquam urna ut enim ultricies varius. Duis sed tempor libero, non aliquet sapien. Pellentesque accumsan semper efficitur. Vestibulum vel augue eget sapien tincidunt eleifend. Cras vel molestie metus. Vivamus consequat suscipit mauris, id eleifend mauris pharetra in. Nunc hendrerit augue ultrices egestas commodo. Donec vitae ex turpis. Aenean lectus sem, faucibus nec mollis sed, gravida nec ligula.\nVestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam tempus, neque sit amet convallis gravida, risus tellus euismod metus, et cursus augue dui quis urna. Praesent sed dolor volutpat, tincidunt lorem in, tempor sem. Vivamus at venenatis dui, et scelerisque sem. Duis massa orci, fermentum non lobortis blandit, scelerisque ut arcu. Etiam vel purus eu enim molestie interdum. Etiam dictum mi sit amet diam consectetur venenatis. Donec rutrum odio magna, vel elementum lectus pulvinar at. Proin vel sapien bibendum, viverra velit eget, dignissim elit. Praesent posuere consectetur tellus, et ullamcorper dolor malesuada sed. Etiam ac enim placerat, feugiat purus vitae, imperdiet diam. Mauris at tempor sapien. Pellentesque pellentesque ex sem, eget efficitur elit hendrerit ac. Curabitur imperdiet ac mi eu hendrerit. Nulla venenatis augue ac tristique accumsan. Quisque condimentum neque eget lobortis viverra.\nEtiam eu aliquam arcu, vel lobortis metus. Donec sit amet diam placerat, fringilla nunc vel, tristique sem. Suspendisse mattis scelerisque viverra. Nunc cursus sodales augue, ut molestie nunc aliquet vel. Sed elementum ornare ligula sed volutpat. Ut metus mauris, feugiat luctus nunc a, maximus accumsan libero. In vulputate lorem eros, ac feugiat justo condimentum a. Curabitur laoreet nulla feugiat est semper, ac hendrerit nulla pellentesque. Aenean ac porttitor metus. Maecenas fermentum rutrum ex, sed vestibulum velit convallis a. Aliquam quis lacus sem. Proin euismod porta ligula. Cras in neque posuere, hendrerit ipsum id, egestas sapien. Nulla accumsan, risus at tincidunt tristique, quam justo consequat nulla, non tempor velit justo non ipsum. Ut urna dui, semper suscipit nisl ullamcorper, tincidunt dictum ante.\nEtiam eu aliquam arcu, vel lobortis metus. Donec sit amet diam placerat, fringilla nunc vel, tristique sem. Suspendisse mattis scelerisque viverra. Nunc cursus sodales augue, ut molestie nunc aliquet vel. Sed elementum ornare ligula sed volutpat. Ut metus mauris, feugiat luctus nunc a, maximus accumsan libero. In vulputate lorem eros, ac feugiat justo condimentum a. Curabitur laoreet nulla feugiat est semper, ac hendrerit nulla pellentesque. Aenean ac porttitor metus. Maecenas fermentum rutrum ex, sed vestibulum velit convallis a. Aliquam quis lacus sem. Proin euismod porta ligula. Cras in neque posuere, hendrerit ipsum id, egestas sapien. Nulla accumsan, risus at tincidunt tristique, quam justo consequat nulla, non tempor velit justo non ipsum. Ut urna dui, semper suscipit nisl ullamcorper, tincidunt dictum ante.\nEtiam eu aliquam arcu, vel lobortis metus. Donec sit amet diam placerat, fringilla nunc vel, tristique sem. Suspendisse mattis scelerisque viverra. Nunc cursus sodales augue, ut molestie nunc aliquet vel. Sed elementum ornare ligula sed volutpat. Ut metus mauris, feugiat luctus nunc a, maximus accumsan libero. In vulputate lorem eros, ac feugiat justo condimentum a. Curabitur laoreet nulla feugiat est semper, ac hendrerit nulla pellentesque. Aenean ac porttitor metus. Maecenas fermentum rutrum ex, sed vestibulum velit convallis a. Aliquam quis lacus sem. Proin euismod porta ligula. Cras in neque posuere, hendrerit ipsum id, egestas sapien. Nulla accumsan, risus at tincidunt tristique, quam justo consequat nulla, non tempor velit justo non ipsum. Ut urna dui, semper suscipit nisl ullamcorper, tincidunt dictum ante.\n");
		pastTreatments.setEnabled(false);
		JScrollPane sp1 = new JScrollPane(pastTreatments);
		selectedPatient.add(sp1, "span 1 10, height 300");


		patientPanel.add(selectedPatient, BorderLayout.CENTER);




		//https://www.techiedelight.com/print-all-keys-and-values-map-java/
		HashMap<String, UserSuperClass> users = Main.dbaseClass.getUsers();
		Iterator<String> itr = users.keySet().iterator();

		while(itr.hasNext()){

			String a = itr.next();
			String b = Main.dbase.get(a).getRole();
			if(b.compareTo("patient") == 0){
				System.out.println(">"+Main.dbase.get(a).getName());
				System.out.println("## "+b+" ##");
				newPatient.add(Main.dbase.get(a).getName());
			}

		}
		Object[] temp = newPatient.toArray();
			//
		
			listPatients = new JList(temp);

			scroll = new JScrollPane(listPatients, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setLayout(new ScrollPaneLayout());
			scroll.setPreferredSize(new DimensionUIResource(200, 0));
			scroll.getVerticalScrollBar().setUnitIncrement(10);
	
			patientPanel.add(scroll, BorderLayout.WEST);
			
			///
		

		
	}


	public void initializeButtons(){
		JButton btnChange = new JButton("Make changes");
		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//btnSaveChanges.setVisible(true);
				//btnOwnSchedule.setVisible(true);
			}
		});
	}



	
/**Getters and Setters*/
	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}


	public void setWelcomeLabel(JLabel welcomeLabel) {
		this.welcomeLabel = welcomeLabel;
	}


	public JPanel getListPatientsPanels() {
		return listPatientsPanel;
	}

	public void setListPatientsPanel(JPanel p) {
		this.listPatientsPanel = p;
	}


	///
	public JList getListPatients() {
		return listPatients;
	}


	public void setListPatients(JList listPatients) {
		this.listPatients = listPatients;
	}



	public void setPatientList(String[] patients)
	{
		
		listPatients = new JList(patients);

		scroll = new JScrollPane(listPatients, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setPreferredSize(new DimensionUIResource(200, 0));
		scroll.getVerticalScrollBar().setUnitIncrement(10);

		patientPanel.add(scroll, BorderLayout.WEST);
		
		
		//patientPanel.add(scroll, BorderLayout.WEST);
		///Major issue is this one right here
	}
	///

	public JPanel getPatientPanel() {
		return patientPanel;
	}

	public void setPatientPanel(JPanel patPanel) {
		this.patientPanel = patPanel;
	}

	public JTextArea getPatientInformation() {
		return patientInformation;
	}

	public void setPatientInformation(JTextArea pi) {
		this.patientInformation = pi;
	}

	public JTextArea getPastTreatmentBox() {
		return pastTreatments;
	}

	public void setPastTreatmentBox(JTextArea x) {
		this.pastTreatments = x;
	}

	public ArrayList<String> getPatientList(){
		return newPatient;
	}


}
