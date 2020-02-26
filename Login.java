import java.util.Scanner;

public class Login{

      public static void main(String[]args){
            String username, password;
            Scanner scan = new Scanner(System.in);

            System.out.println("=================");
            System.out.println("Login Screen");
            System.out.println("=================");
            System.out.print("Username: ");
            username = scan.nextLine();
            System.out.print("Password: ");
            password = scan.nextLine();
            System.out.println("Your username is "+username+" and your password is "+password);


      }
}
