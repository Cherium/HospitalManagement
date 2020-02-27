import java.util.Scanner;
import java.util.HashMap;

public class Login{
	
	public static Database data = new Database();		//Global var; Main Database; available everywhere; access with Login.data outside this class

	public static void main(String[]args){
		  
				String username, password;				//store the username text and the password entered by user
				int role;								//store the integer that user chooses to determine the role
				HashMap<String,String> currentMap;		//to make shallow copy of a database from Database class
				
				
				//initialize command line input scanner
				Scanner scan = new Scanner(System.in);
				
				//initialize database
				
				currentMap = Login.data.getPatientMap();
				
				
				currentMap.put("test", "pwd");
				System.out.println(data.getPatientMap().get("test") + "\n");
	
	
				Boolean exitFlag = false;				//to exit inner while loop
	
				while (true)
				{
									//Welcome screen state
						        System.out.println(
											  			"=================\n"
											  		+   "   Welcome Screen  \n"
											  		+   "=================\n"
											  		);	
						
						        System.out.println(
						        					"Select One of the following Options and press Enter to proceed:"
						        					+ " \n[1] Login \n[2] Create Patient Account"
						        					);
						        
						        int option = scan.nextInt();
						        scan.nextLine();
						        
						        //Login Screen State		           
						        if(option == 1)
						        {
						        	
								            			while(true)
								            			{
												            	//scan.nextLine();
												            	System.out.println("You selected Login. Please Enter a Username: ");
												            	username = scan.nextLine();
												            	System.out.printf("Please Enter a Password: %n");
												            	password = scan.nextLine();
												            	System.out.printf("Please choose from the following roles:%n"
												            					 + "[1]Patient%n"
												            					 + "[2]Doctor%n"
												            					 + "[3]Nurse%n"
												            					 + "[4]Admin%n"
												            					 + "[5]Receptionist%n"
												            					 + "[6]Hospital Head%n"
												            					 + "[7]Back%n");
												            	
												            	
												            	
												            	//go to 'selected role' screen, display text, and exit
												            	role = scan.nextInt();
												            	if(role == 7)
												            	{
												            				break;			//back to welcome screen
												            	}
												            	else if(role == 1)
												            	{
													                    System.out.printf(
													        						  			"=================%n"
													        						  		+   "   Patient Screen  %n"
													        						  		+   "=================%n"
													        						  		);	

														                  //TODO check username, pass, and role against database 
												            	}
												            	else if(role == 2)
												            	{
														                    System.out.printf(
														        						  			"=================%n"
														        						  		+   "   Doctor Screen  %n"
														        						  		+   "=================%n"
														        						  		);	
														                  //TODO check username, pass, and role against database 
												            	}
												            	else if(role == 3)
												            	{
														                    System.out.printf(
														        						  			"=================%n"
														        						  		+   "   Nurse Screen  %n"
														        						  		+   "=================%n"
														        						  		);	
														                  //TODO check username, pass, and role against database 
												            	}
												            	else if(role == 4)
												            	{
														                    System.out.printf(
														        						  			"=================%n"
														        						  		+   "   Admin Screen  %n"
														        						  		+   "=================%n"
														        						  		);	
														                  //TODO check username, pass, and role against database 
												            	}
												            	else if(role == 5)
												            	{
														                    System.out.printf(
														        						  			"=======================%n"
														        						  		+   "   Receptionist Screen  %n"
														        						  		+   "=======================%n"
														        						  		);	
														                  //TODO check username, pass, and role against database 
												            	}
												            	else if(role == 6)
												            	{
														                    System.out.printf(
														        						  			"========================%n"
														        						  		+   "   Hospital Head Screen  %n"
														        						  		+   "========================%n"
														        						  		);
														                   //TODO check username, pass, and role against database 
												
												            	}
												            	System.out.println("Exiting...");
												            	exitFlag = true;
												            	break;
								            			}//end while
								            			

						        }//end if
						        
						
						        //Create Patient Account State
						        if(option == 2)
						        {
						        	
						        			while(true)
						        			{
						        						CreatePatientAccount pat = new CreatePatientAccount();
						        						
						        						
						        						//prompt for username and set that username, or exit
										            	System.out.println("Please Enter a Username, or enter 2 to exit: ");
										            	username = scan.nextLine();
										            	pat.setUsername(username);
										            	
										            	//user selected quit
										            	if(username.compareTo("2") == 0)
										            	{
										            		exitFlag = true;
										            		break;		//exit program
										            	}
										            	
										            	//check if username is already in database
										            	//pat.getCurrentDbase is shallow copy of patientDatabase instance var of data Database
										            	if(pat.getCurrentDbase().containsKey(username))
										            	{
										            		System.out.printf("Username already exists, select a different one. %n");
										            		continue;
										            	}
										            	
										            	//prompt and set password
										            	System.out.printf("Please Enter a Password: %n");
										            	password = scan.nextLine();
										            	pat.setPwd(password);
										            	
										            	
										            	System.out.printf("Please Re-enter the Password: %n");
										            	String password2 = scan.nextLine();
										            	pat.setPwd2(password2);
										        		
										        		
										        		//password match check
										        		if( !pat.verifyPass() )
										        		{
										        			System.out.printf("Passwords Don't match, Try Again. %n");
										        			continue;
										        		}
										        		
										        		//updateDatabase
										        		pat.addToDatabase();
										        		
										        		//return to login screen
										        		break;
										        		
										        		
						        			}//endwhile2
								                    
						        }//end if2
						        
						        //exit program or go to previous/exit screen
								if(exitFlag == true)		
								{
										exitFlag = false;
										System.out.println("Exiting...");
										break;			//exit program
								}
								else
								{
									continue;			//back to welcome screen
								}
		            			
						            
						
						
				}//end while
	
	}//end main


}
