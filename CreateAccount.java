import java.util.HashMap;

/*
 * Allows for the creation of an account
 */
public abstract class CreateAccount {
	
	
			protected String name;
			protected String role;
			protected String dept;
			protected String Address;
			protected String phone;
			
			protected HashMap<String,String> currentDbase;	//to create or delete users in the system; shallow copy of main database
			protected String username;
			protected String pwd;						//first entered password
			protected String pwd2;						//re-entered password when creating account
			
			
			
			//getter methods
			public HashMap<String, String> getCurrentDbase() {
				return currentDbase;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public void setPwd(String pwd) {
				this.pwd = pwd;
			}
			public void setPwd2(String pwd2) {
				this.pwd2 = pwd2;
			}
			

			//public abstract void addToDatabase();
			
			

}
