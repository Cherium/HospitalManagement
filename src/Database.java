import java.util.HashMap;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;

/*Create a Databsae and import CSV into that database*/
public class Database {

	
		public HashMap<String, UserSuperClass> users;

		//Can later be expanded by the appropriate user that has rights to add a department
		private ArrayList<String> departmentList = new ArrayList<>
					(List.of("Cardiology", "Nephrology", "Neurology", "Receptionist", "ER"));
		
		
		
		
		
		
		//Constructor: provide filename to import when initializing database
		public Database(String filePath) {
			
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
											InputStream st = getClass().getClassLoader().getResourceAsStream("dbase/"+importUsername+"Avail.txt");
											sc = new Scanner(st);
											
											ArrayList<String> tempp = new ArrayList<>(5);
											while(sc.hasNextLine()) 
											{
												
												String time = sc.nextLine();			//read in entire line
												
												tempp.add(time);
											}
											
											
											//create a temporary doctor object using database information
											DoctorModel temp = new DoctorModel(importUsername, importPassword, importName
													, importDepartment, importAssignedNurseUsernames, importAssigPat);
											
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
										{
												//get Patient appt times
												InputStream st = getClass().getClassLoader().getResourceAsStream("dbase/"+importUsername+"Appt.txt");
													sc = new Scanner(st);
													
													ArrayList<String> tempp = new ArrayList<>(5);
													while(sc.hasNextLine()) 
													{
														
														String time = sc.nextLine();			//read in entire line
														String[] split2 = time.split("\t");		//regex split into arrow on tabs
														
														String docName = split2[0];
														String apptTime = split2[1];
														
														tempp.add(docName);
														tempp.add(apptTime);
													}
												
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
