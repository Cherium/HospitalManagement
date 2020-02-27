

public class CreatePatientAccount extends CreateAccount {
	
	
			//constructor
			public CreatePatientAccount()
			{
				printEntry();								//print title screen
				currentDbase = Login.data.getPatientMap();	//set currentDatabase to Patient database
				
			}
			
			//TODO create a class in superclass to verify if username is in the database
			
			
			//print title
			public void printEntry()
			{
		        System.out.printf(
							  			"=========================%n"
							  		+   "   Create Patient Account %n"
							  		+   "=========================%n"
							  		);
			}
			
			//verify if both entered passwords are the same
			//precondition: the user entered a password twice, both of which have been stored in this classes variables
			//postcondition: return true if both passwords match, else return false
			public Boolean verifyPass()
			{
						if(pwd.compareTo(pwd2) == 0)
						{
							return true;
						}
						else
						{
							return false;
						}
			}
			
			//add the information to the database
			//precondition: user name and pass have been set as instance variables
			//postcondition: the username and pass are added to the patient database
			public void addToDatabase()
			{
				//this.currentDbase = Login.data.getPatientMap();				//set current database to patient database
				this.currentDbase.put(this.username, this.pwd);				//add the user to this database
				System.out.println("Account Created! Returning to Login Screen...\n");
			}
			
			
		
	
	
	
	

	
	

}
