import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ReceptionistController {

	private ReceptionistModel model;
	private ReceptionistView view;
	
	
	
	
	
	public ReceptionistController(ReceptionistModel model, ReceptionistView view) {

		this.model = model;
		this.view = view;
		initView();
		initListeners();
	}
	
	
	
	
	
	//initialize the elements that the GUI sees from the database 
	//	as soon as the view first opens for the user
	public void initView()
	{
		view.getWelcomeLabel().setText("Welcome Receptionist "+ model.getName() );

		/*
				//ArrayList<String> newPatient
				ArrayList<String> a = view.getPatientList();
				model.setScheduledPatientsUsernames(a);
				//*/

				


				//https://www.techiedelight.com/print-all-keys-and-values-map-java/
		HashMap<String, UserSuperClass> users = Main.dbaseClass.getUsers();
		Iterator<String> itr = users.keySet().iterator();
		ArrayList<String> newPatient = new ArrayList<String>();

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
		
		System.out.println("Anyone? "+temp.length);

		String[] patients = new String[newPatient.size()];

		for (int i = 0; i < temp.length; i++){
			patients[i] = newPatient.get(i);
		}


		/*
		for (String string : model.getScheduledPatientsUsernames()) {
			int index = model.getScheduledPatientsUsernames().indexOf(string);
			String a = Main.dbase.get(string).getName();
			System.out.println(a);
			patients[index] = a;///Issue is here?
		}*/
		view.setPatientList(patients);
		
		///


	}
	
	
	//initialize 'only' the listeners the GUI handles 'that
	//	need interaction with the model'
	public void initListeners() 
	{



			

		try{
				//listen for mouse clicks on patients names
				view.getListPatients().addMouseListener(new MouseAdapter() {
					
					
			
					public void mousePressed(MouseEvent a) {		
						 setUpPatientView();
						 
						 System.out.println("Ala");
					}
				});//*/
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Out of bounds error.");
			}



	}


		//set up patients in the center panels of 'View Patient'
		public void setUpPatientView() {
		
			//Index selected in GUI == index of patient in model 'scheduledPatientUsername' array
			//use it search hashmap for the patient
			int selectedIndex = view.getListPatients().getSelectedIndex();
			PatientModel pat = (PatientModel) Main.dbase.get(model.getScheduledPatientsUsernames().get(selectedIndex));
			
			//update patient label info in view
			view.getPatientInformation().setText(
					"Name:\t" + pat.getName() +
					"\nAge:\t" + pat.getAge() +
					"\nSex:\t" + pat.getSex() +
					"\nBlood Type:\t" + pat.getBlood() +
					"\nAddress:\t" + pat.getAddress() +
					"\nPhone:\t" + pat.getPhoneNumber() +
					"\nEmail:\t" + pat.getEmail() + "\n"
					);
			
			//update past history box in view
			view.getPastTreatmentBox().setText(pat.getRecordNotes() );
			
			
		}


}
