import java.util.HashMap;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;
import java.util.Scanner;
import java.util.ArrayList;

/*Create a Databsae and import CSV into that database*/
public class DatabaseModel {

	
		public HashMap<String, UserModel> users;

		
		
		
		/*provide filename to import when initializing database*/
		public DatabaseModel(String filePath) {
			
			users = new HashMap<String, UserModel>();
			importDatabase(filePath);
		}

		
		
		
		
		public void importDatabase(String filePath) {
			// TODO Auto-generated method stub
			//String executionPath = System.getProperty("user.dir"+"\\database.csv");
			//System.out.print("Executing at =>"+executionPath.replace("\\", "/"));
			
			//TODO implement so a .jar can find file in a given build
			File file = new File(filePath);
			
			try {
						Scanner sc = new Scanner(file);
						sc.next();								//ignore first line of headers
						while(sc.hasNextLine()) {
							
									String data = sc.nextLine();			//read in entire line
									String[] split = data.split("\t");	//regex split into arrow on tabs
									
									/*
									Import each value of line into database.
									i: [0]role [1]username [2]password [3]name [4]department [5]assignedNurses usernames
										[6]Doctors key(username), 
									*/
									
									
//TODO expand as we determine more information for each role					
									//if the data to import pertains to a doctor
									if(split[0].compareTo("doctor") == 0)
									{
										String[] nurseList = split[5].split(",");
										
										//check if doctor already exists from a Nurse potentially creating him already
										if(users.containsKey(split[1]))
										{
											//this means the nurse already created a doctor object and set its: username, assignedNurses; set the rest
											DoctorModel d = (DoctorModel) users.get(split[1]);
											d.setName(split[3]);
											d.setPassword(split[2].toCharArray());
											d.setDepartment(split[4]);
											d.setRole(split[0]);
											
											//add any nurses to assignedNurses that aren't already added	
											for (String i: nurseList)
											{
												//if the import list nurse is already in the database
												if(users.containsKey(i))
												{
													int flag = 0;
													for (int j = 0; j < d.getNurses().size(); j++) 
													{
														if(d.getNurses().get(j).getUsername()
																.compareTo(i) == 0)
														{
															flag = 1;
															break;
														}
													}
													if(flag == 0)
													{
														//nurse not found in list; add nurse
														d.getNurses().add((NurseModel)users.get(i));
													}
													else
													{
														//nurse found in list, check next 'i'
													}
												}
												else
												{
													//else the import list nurse is not in the database, create that nurse object in dbase, add it to assignedNurses
													//can only add Nurse username, assignedDoc
													users.put(i,new NurseModel(i, d));
													d.getNurses().add((NurseModel)users.get(i));
													
												}
											}
										}
										else
										{
											//the doctor does not already exist in the database
											//his assigned nurses still might exist
											
											//create doctor from text file info
											DoctorModel z = new DoctorModel(split[3],split[1]
													,split[2].toCharArray(),split[4]);
											
											//get list of the doctors nurses that are not in the database
											ArrayList<NurseModel> temp = new ArrayList<NurseModel>(5);
											for (String i: nurseList)
											{
												
												//only make a new nurse, if one doesn't already exist
												if(users.containsKey(i) )
												{
													//nurse already in database, find it and add it to list in dbase constructor
													temp.add(((NurseModel)users.get(i)));
													
												}
												else
												{
													//add new nurse into database temp (username, assignedDoctor)-- only username and assignedDoc at this point in time
													users.put(i, new NurseModel(i,z));
													//add that newly added nurse to the doctors assignedNurse list
													temp.add(((NurseModel)users.get(i)));
												}
											}
											
											z.setNurses(temp);		//set temp nurse list as doctors actual assignedNurse list
											users.put(split[1], z);	//add new completed doctor to database
											
											
										}

										//Add Doctor to the HashMap database (String username, Object Doctor)
										//users.put(split[1], new DoctorModel(split[3],split[1]
										//		,split[2].toCharArray(),split[4],temp));
										continue;
									}
									
									//import a nurse
									else if(split[0].compareTo("nurse")== 0)
									{
										String doctorUsername = split[6];
										
										//check if Nurse already exists from a Doctor potentially creating them already
										if(users.containsKey(split[1]))
										{
											//this means a doctor already created part of this nurse (username, assignedDoc)
											//finish setting this nurse
											NurseModel n = (NurseModel) users.get(split[1]);
											n.setName(split[3]);
											n.setPassword(split[2].toCharArray());
											n.setRole(split[0]);
							//TODO set department here if Nurse is given that instance var

											users.put(split[1], n);	//reassign the value for that nurse username with the updated details
										}
										else
										{
											//nurse not in database, add everything
											//assumption: doc will always be in database of newly created Nurse
											users.put(split[1], new NurseModel(split[3],split[1],split[2].toCharArray()
													,split[4], ((DoctorModel) users.get(split[6])) ));											
										}

									}
									
									//import a patient
									else if(split[0].compareTo("patient")== 0)
									{
										users.put(split[1], new PatientModel( split[3],split[1],split[2].toCharArray() ));
									}
									
									else if(split[0].compareTo("admin")== 0)
									{
										users.put(split[1], new AdminModel(split[3],split[1],split[2].toCharArray() ));
									}
									
									else if(split[0].compareTo("authority")== 0)
									{
										users.put(split[1], new AuthorityModel(split[3],split[1],split[2].toCharArray() ));
									}
									
								}
						
								
						
						
						sc.close();
								
								
								
			} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
			}
			
			
			
			
			
			
		}

		public HashMap<String, UserModel> getUsers() {
			return users;
		}

		public void setUsers(HashMap<String, UserModel> users) {
			this.users = users;
		}
		

}
