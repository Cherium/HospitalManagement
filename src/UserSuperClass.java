import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.*;

public class UserSuperClass {
	
			protected String name;
			protected String role;
			protected String username;
			protected char[] password;
			protected Schedule s = new Schedule();	//initialize s so now ALL model classes can use object 's' to get ease-of-use functions in Schedule class
			protected LocalDateTime[] availability = new LocalDateTime[14];
			
			
			public UserSuperClass()
			{
	
			}
		
			public UserSuperClass(String name, String username, char[] password) {
						this.name = name;
						this.username = username;
						this.password = password;
			}
		
			public UserSuperClass(String name, String username, String password) {
						this.name = name;
						this.username = username;
						this.password = password.toCharArray();
			}
			
			
			//takes in a 1D array of availability(size 14), and converts it to a LDT (size 14)
			public LocalDateTime[] arrayToLDTArray(ArrayList<String> availability)
			{
				LocalDateTime[] temp = new LocalDateTime[14];
				
				for(int i=0; i < availability.size(); i++)
				{
					temp[i] = LocalDateTime.parse(availability.get(i) );
				}
				
				return temp;
			}
			
			
			
			//returns the 'name' associated with an object in the database
			public String getObjectsName(String username)
			{
				
				return Main.dbase.get(username).name;
			}
			
			//convert a Unix Time (seconds) string to a LocalDateTime object
			public LocalDateTime secondsToDate(String numberOfSecondsString) {
				
				//convert string to long
				long timestampSeconds = Integer.parseInt(numberOfSecondsString);
				
				//convert long to LocalDate
				//https://stackoverflow.com/questions/44883432/long-timestamp-to-localdatetime
				LocalDateTime temp = LocalDateTime.ofEpochSecond(timestampSeconds, 0, ZoneOffset.UTC);

				return temp;
			}
			
			//convert a date string(uuuu/M/d) to a Unix Time (seconds) string
			//https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html
			public LocalDate dateStringToLocalDate(String dateString)
			{
				//convert string to formatted date object
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/M/d").withResolverStyle(ResolverStyle.STRICT);
				
				//convert date object to LocalDateTime object
				LocalDate temp = LocalDate.parse(dateString, formatter);
				
				//convert LocalDateTime object to unix timestamp string(seconds)
				return temp;
				
			}
			
			//convert LocalDateTime object to Unix Time seconds String. Can pass any date, or even date and time
			public String dateToSeconds(LocalDate date)
			{
				//https://www.concretepage.com/java/java-8/convert-between-java-localdatetime-epoch
				//https://www.concretepage.com/java/java-8/convert-between-java-localdate-epoch#Epoch-Seconds
				long timeInSeconds = date.toEpochSecond(LocalTime.NOON,ZoneOffset.UTC);
				return Long.toString(timeInSeconds);
			}
			

			
			
			
		
			public void setName(String name) {
						this.name = name;
			}
		
			public void setRole(String role) {
						this.role = role;
			}
		
			public void setUsername(String username) {
						this.username = username;
			}
		
			public void setPassword(char[] password) {
						this.password = password;
			}

			public void setAvailability(LocalDateTime[] availability) {
				this.availability = availability;
			}
		
			public String getName() {
						return name;
			}
		
			public String getRole() {
						return role;
			}
		
			public String getUsername() {
						return username;
			}
		
			public char[] getPassword() {
						return password;
			}

			public LocalDateTime[] getAvailability() {
				return availability;
			}
		
			public String toString() {
						StringBuilder bob = new StringBuilder();
						bob.append("Name: ");
						bob.append(getName());
						bob.append("\n");
						bob.append("Role: ");
						bob.append(getRole());
						bob.append("\n");
						//bob.append("Username: " + getUsername() + " Password: " + getPassword().toString());
						bob.append("\n");
						return bob.toString();
			}
}
