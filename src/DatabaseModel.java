import java.util.HashMap;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;

/*Create a Databsae and import CSV into that database*/
public class DatabaseModel {

	
		public HashMap<String, UserSuperClass> users;

		//Can later be expanded by the appropriate user that has rights to add a department
		private ArrayList<String> departmentList = new ArrayList<>
					(List.of("Cardiology", "Nephrology", "Neurology", "Receptionist", "ER"));
		
		
		
		
		
		
		//Constructor: provide filename to import when initializing database
		public DatabaseModel(String filePath) {
			
			users = new HashMap<String, UserSuperClass>();
			importDatabase(filePath);
		}

		
		
		
		//import information from the provided file
		public void importDatabase(String filePath) {

			
			//TODO implement so a .jar can find file in a given build
			//1/File file = new File(filePath);
			
			/*
			 * https://stackoverflow.com/questions/1464291/how-to-really-read-text-file-from-classpath-in-java
			 * https://stackoverflow.com/questions/21023065/open-file-that-is-in-the-resource-folder-inside-jar?noredirect=1&lq=1
			 * https://stackoverflow.com/questions/24521830/how-to-convert-an-inputstream-to-a-scanner-java*/
			InputStream stream = getClass().getClassLoader().getResourceAsStream("dbase.txt");
			
			
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
										System.out.println(split[8]);
										String importPhoneNumber			 = split[8];
										String importEmail 								= split[9];
										float importAmountDue			 = Float.parseFloat(split[10]);
										
	
										
								//import fields into internal database
										
										
										//if the data to import pertains to a doctor
										if(importRole.compareTo("doctor") == 0)
										{
											System.out.println(importAssignedNurseUsernames[0]);
											//create a temporary doctor object using database information
											DoctorModel temp = new DoctorModel(importUsername, importPassword, importName
													, importDepartment, importAssignedNurseUsernames);
											
											//add that doctor to the internal database
											users.put(importUsername, temp);
										}
										
										else if(importRole.compareTo("nurse")== 0)
										{
											NurseModel temp = new NurseModel(importUsername, importPassword, importName
													, importDepartment, importAssignedDocUsername);
											
											users.put(importUsername, temp);
										}
										
										else if(importRole.compareTo("patient")== 0)
										{ System.out.println(importAmountDue);
											PatientModel temp = new PatientModel(importUsername, importPassword, importName
													, importAddress, importPhoneNumber, importEmail, importAmountDue);
											
											users.put(importUsername, temp);
										}
										
										else if(importRole.compareTo("admin")== 0)
										{
											AdminModel temp = new AdminModel(importUsername, importPassword, importName);
											
											users.put(importUsername, temp);
										}
										
										else if(importRole.compareTo("authority")== 0)
										{
											AuthorityModel temp = new AuthorityModel(importUsername, importPassword, importName);
											
											users.put(importUsername, temp);
										}
										
										else if(importRole.compareTo("receptionist")== 0)
										{
											ReceptionistModel temp = new ReceptionistModel(importUsername, importPassword, importName);
											
											users.put(importUsername, temp);
										}
								}
						
								
	
						//close the scanner
						sc.close();
								
								
								

			
			
			
			
			
			
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
		

}
