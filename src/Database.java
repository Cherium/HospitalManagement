import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @author Sajid C
 * Create a Databsae and import CSV into that database
 */
public class Database {

		//main database accessed everywhere	
		public HashMap<String, UserSuperClass> users;

		//Can later be expanded by the appropriate user that has rights to add a department
		private ArrayList<String> departmentList = new ArrayList<>
					(List.of("Cardiology", "Nephrology", "Neurology", "Receptionist", "ER"));
		
		
		private ArrayList<DoctorModel> docsToLoad = new ArrayList<DoctorModel>();

		
		
		
		/**
		 * Constructor
		 * @author Sajid C
		 * @param filePath provided filename to import when initializing database
		 */
		public Database(String filePath) {
			
			users = new HashMap<String, UserSuperClass>();
			importDatabase(filePath);
		}

		
		
		
		
		/**
		 * import information from the provided file
		 * @author Sajid C
		 * @param filePath filename to import when initializing database
		 */
		public void importDatabase(String filePath) {

			
			
			/*
			 * https://stackoverflow.com/questions/1464291/how-to-really-read-text-file-from-classpath-in-java
			 * https://stackoverflow.com/questions/21023065/open-file-that-is-in-the-resource-folder-inside-jar?noredirect=1&lq=1
			 * https://stackoverflow.com/questions/24521830/how-to-convert-an-inputstream-to-a-scanner-java*/
			InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);
			
			
						Scanner sc = new Scanner(stream);
						sc.nextLine();								//ignore first line of headers
						
						//import database, line by line
						while(sc.hasNextLine()) {
							
										String data = sc.nextLine();			//read in entire line
										String[] split = data.split("\t");		//regex split into arrow on tabs
										

								//read in values in each line
										String importRole						 = split[0];
										String importUsername					 = split[1];
										char[] importPassword					 = split[2].toCharArray();
										String importName						 = split[3];
										String importDepartment					 = split[4];
										String[] importAssignedNurseUsernames	 = split[5].split(",");
										String importAssignedDocUsername		 = split[6];
										String importAddress						= split[7];
										String importPhoneNumber			 			= split[8];
										String importEmail 								= split[9];
										float importAmountDue			 = Float.parseFloat(split[10]);
										String importDob								= split[11];
										String importBlood								=split[12];
										String importSex								=split[13];
										String[] importAssigPat							=split[14].split(",");
	
										
								//import fields into internal database
										
										
										//if the data to import pertains to a doctor
										if(importRole.compareTo("doctor") == 0)
										{
											//read in doctor availability
											InputStream wv = getClass().getClassLoader().getResourceAsStream("dbase/"+importUsername+"Avail.txt");

											Scanner fc = new Scanner(wv);
											
											ArrayList<String> importAvail = new ArrayList<>(5);
											while(fc.hasNextLine()) 
											{
												
												String time = fc.nextLine();			//read in entire line
												
												importAvail.add(time);
											}
											fc.close();
											//create a temporary doctor object using database information
											DoctorModel temp = new DoctorModel(importUsername, importPassword, importName
													, importDepartment, importAssignedNurseUsernames, importAssigPat, importAvail.toArray(new String[0]));
											
											//add that doctor to the internal database
											users.put(importUsername, temp);

											//add doctor to list to initialize appointments after importing
											docsToLoad.add(temp);
										}
										
										else if(importRole.compareTo("nurse")== 0)
										{

											//read in nurse availability
											InputStream wv = getClass().getClassLoader().getResourceAsStream("dbase/"+importUsername+"Avail.txt");

											Scanner fc = new Scanner(wv);
											
											ArrayList<String> importAvail = new ArrayList<>(5);
											while(fc.hasNextLine()) 
											{
												
												String time = fc.nextLine();			//read in entire line
												
												importAvail.add(time);
											}
											fc.close();
											
											NurseModel temp = new NurseModel(importUsername, importPassword, importName
											, importDepartment, importAssignedDocUsername, importAvail.toArray(new String[0]) );
											
											users.put(importUsername, temp);
										}
										
										else if(importRole.compareTo("patient")== 0)
										{
											//get Patient appt times
											InputStream st = getClass().getClassLoader().getResourceAsStream("dbase/"+importUsername+"Appt.txt");
											Scanner fc = new Scanner(st);
											
											ArrayList<String> tempp = new ArrayList<>(5);
											while(fc.hasNextLine()) 
											{
												
												String time = fc.nextLine();			//read in entire line
												String[] split2 = time.split("\t");		//regex split into arrow on tabs
												
												String docName = split2[0];
												String apptTime = split2[1];
												
												tempp.add(docName);
												tempp.add(apptTime);
											}
											fc.close();
											
											//get Patient record notes
											try {
												//https://stackoverflow.com/questions/3891375/how-to-read-a-text-file-resource-into-java-unit-test?noredirect=1&lq=1
												String record = new String(getClass().getClassLoader()
														.getResourceAsStream("dbase/"+importUsername+".txt").readAllBytes());
												
												//create a patient internally
												PatientModel temp = new PatientModel(importUsername, importPassword, importName
														, importAddress, importPhoneNumber, importEmail, importAmountDue
														, importDob, importBlood, importSex, record, tempp);
												
												users.put(importUsername, temp);
											} catch (IOException e) {
												System.out.println("Could not open file");
												e.printStackTrace();
											}
											
											
										}
										
										else if(importRole.compareTo("admin")== 0)
										{
											//read in admin availability
											InputStream wv = getClass().getClassLoader().getResourceAsStream("dbase/"+importUsername+"Avail.txt");

											Scanner fc = new Scanner(wv);
											
											ArrayList<String> importAvail = new ArrayList<>(5);
											while(fc.hasNextLine()) 
											{
												
												String time = fc.nextLine();			//read in entire line
												
												importAvail.add(time);
											}
											fc.close();
											
											
											
											AdminModel temp = new AdminModel(importUsername, importPassword, importName, importAvail.toArray(new String[0]));
											
											users.put(importUsername, temp);
										}
										
										else if(importRole.compareTo("authority")== 0)
										{
											//read in authority availability
											InputStream wv = getClass().getClassLoader().getResourceAsStream("dbase/"+importUsername+"Avail.txt");

											Scanner fc = new Scanner(wv);
											
											ArrayList<String> importAvail = new ArrayList<>(5);
											while(fc.hasNextLine()) 
											{
												
												String time = fc.nextLine();			//read in entire line
												
												importAvail.add(time);
											}
											fc.close();
											
											AuthorityModel temp = new AuthorityModel(importUsername, importPassword, importName, importAvail.toArray(new String[0]));
											
											users.put(importUsername, temp);
										}
										
										else if(importRole.compareTo("receptionist")== 0)
										{
											//read in receptionist availability
											InputStream wv = getClass().getClassLoader().getResourceAsStream("dbase/"+importUsername+"Avail.txt");

											Scanner fc = new Scanner(wv);
											
											ArrayList<String> importAvail = new ArrayList<>(5);
											while(fc.hasNextLine()) 
											{
												
												String time = fc.nextLine();			//read in entire line
												
												importAvail.add(time);
											}
											fc.close();
											
											ReceptionistModel temp = new ReceptionistModel(importUsername, importPassword, importName, importAvail.toArray(new String[0]));
											
											users.put(importUsername, temp);
										}
								}
						
								
						exportDbase();
						//close the scanner
						sc.close();
								
			
		}
		
		
		
		
		
		
		public void exportDbase()
		{
//			File tmp = new File(getClass().getResource("/temp/").getPath() );
//			tmp.mkdir();
//			//https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path/9658353
//			File dbase = new File(tmp, "dbasee.txt");
//			try {
//				dbase.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			for(Map.Entry<String, UserSuperClass> i: users.entrySet())
			{
				if(i.getValue().getRole().compareTo("doctor") == 0)
				{
					DoctorModel user = (DoctorModel) i.getValue();
					System.out.println(user.toStringDbase() );
				}
				
				else if(i.getValue().getRole().compareTo("nurse")== 0)
				{
					NurseModel user = (NurseModel) i.getValue();
					System.out.println(user.toStringDbase() );
				}
				
				else if(i.getValue().getRole().compareTo("patient")== 0)
				{
					PatientModel user = (PatientModel) i.getValue();
					System.out.println(user.toStringDbase() );
				}
				
				else if(i.getValue().getRole().compareTo("admin")== 0)
				{
					AdminModel user = (AdminModel) i.getValue();
					System.out.println(user.toStringDbase() );
				}
				
				else if(i.getValue().getRole().compareTo("authority")== 0)
				{
					AuthorityModel user = (AuthorityModel) i.getValue();
					System.out.println(user.toStringDbase() );
				}
				
				else if(i.getValue().getRole().compareTo("receptionist")== 0)
				{
					ReceptionistModel user = (ReceptionistModel) i.getValue();
					System.out.println(user.toStringDbase() );
				}
			}
		}
		
		
		
		
		

	/**Getters and Setters*/	
		public HashMap<String, UserSuperClass> getUsers() {
			return users;
		}

		public void setUsers(HashMap<String, UserSuperClass> users) {
			this.users = users;
		}

		public ArrayList<String> getDepartmentList() {
			return departmentList;
		}

		public void setDepartmentList(ArrayList<String> departmentList) {
			this.departmentList = departmentList;
		}
		

		public ArrayList<DoctorModel> getDocsToLoad() {
			return docsToLoad;
		}




		public void setDocsToLoad(ArrayList<DoctorModel> docsToLoad) {
			this.docsToLoad = docsToLoad;
		}

}
