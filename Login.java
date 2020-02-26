import java.util.Scanner;

public class Login{

      public static void main(String[]args){
    	  
            String username, password;
            int role;
            Boolean exitFlag = false;
            Scanner scan = new Scanner(System.in);


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
			            
			            
			            //Login Screen State		           
			            if(option == 1)
			            {
			            	
						            			while(true)
						            			{
										            	scan.nextLine();
										            	System.out.println("You selected Login. Please Enter a Username: ");
										            	username = scan.nextLine();
										            	System.out.printf("Your username is %s%n"
										            					+ "Please Enter a Password: %n", username);
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
						            			
						            			if(exitFlag == true)		
						            			{
							            				exitFlag = false;
							            				break;			//exit program
							            		}
						            			else
						            			{
						            				continue;			//back to welcome screen
						            			}
						            			
			            }//end if
			            

			            //Create Patient Account State
			            if(option == 2)
			            {
				                    System.out.printf(
				        						  			"========================%n"
				        						  		+   "   Create Patient Account   %n"
				        						  		+   "========================%n"
				        						  		);
				                    ////////////////////////////////////////
				                    //CREATE PATIENT ACCOUNT SCREEN CODE HERE
				                    ////////////////////////////////////////
				                    System.out.println("Exiting...");
				                    break;
			            }
			            


            }//end while

     }//end main



}
