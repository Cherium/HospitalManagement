import java.util.Scanner;

public class Login{

      public static void main(String[]args){
            String username, password;
            Scanner scan = new Scanner(System.in);

            TempData tempD = new TempData();
            int whileC = 0;

            while (whileC == 0){

            System.out.println("=================");
            System.out.println("Login Screen");
            System.out.println("Please type your username and then your password");
            System.out.println("Example Username Bob, Password 123");
            System.out.println("=================");
            System.out.print("Username: ");
            username = scan.nextLine();
            System.out.print("Password: ");
            password = scan.nextLine();
            System.out.println("Your username is "+username+" and your password is "+password);


            String c = tempD.getPassword(username);
            System.out.println(c);
            int d = stringCompare(password, c);
            System.out.println("Difference: "+d);

            if (d == 0){
                  System.out.println("Welcome. We are logging you in "+username);
                  whileC = 1;
            }
            else{
                  System.out.println("Please check your username and password.");
                  System.out.println("You may have typed it incorrectly.");
                  System.out.println("Consider creating a new account.");
            }



            }

      }

      //https://www.geeksforgeeks.org/compare-two-strings-in-java/
      public static int stringCompare(String str1, String str2)
      {

          int l1 = str1.length();
          int l2 = str2.length();
          int lmin = Math.min(l1, l2);

          for (int i = 0; i < lmin; i++) {
              int str1_ch = (int)str1.charAt(i);
              int str2_ch = (int)str2.charAt(i);

              if (str1_ch != str2_ch) {
                  return str1_ch - str2_ch;
              }
          }

          // Edge case for strings like
          // String 1="Geeks" and String 2="Geeksforgeeks"
          if (l1 != l2) {
              return l1 - l2;
          }

          // If none of the above conditions is true,
          // it implies both the strings are equal
          else {
              return 0;
          }
      }


}
