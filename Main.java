//// Note. This is a prototype. Ignore this.
//
//https://itstillworks.com/create-calendar-java-7307425.html
//
//
//
//


import java.util.Calendar;
import java.util.Scanner;
import java.util.Random;

public class Main{
    //Calendar cal = Calendar.getInstance();
    //new scanner(System.in);

    public static void main (String[]args){
          Bulk bulk = new Bulk();

          System.out.println("Hello.\n\n");
          Scanner scan = new Scanner(System.in);
          Random rand = new Random();

          int choice = -1;
          while (choice != 0){


          System.out.println("============================");
          System.out.println("What choice would you like?");
          System.out.println("Your choices: ");
          bulk.choiceDisplay();
          System.out.println("============================");
          choice = scan.nextInt();
          System.out.println("\n\nYour choice is "+choice);
          System.out.println("============================");

          if(choice ==1){
                bulk.display();
          }
          if(choice ==2){
                bulk.add();
          }


          else{}

          //choice = 1;
        }

          //bulk.choiceDisplay();
          //bulk.F2();
          //bulk.F1();




    }

}
