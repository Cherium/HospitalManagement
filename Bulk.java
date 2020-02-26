import java.util.Calendar;
import java.util.Scanner;


public class Bulk{
  Calendar cal = Calendar.getInstance();
  Scanner scan = new Scanner(System.in);
  LinkedList linkL = new LinkedList();



      public void choiceDisplay(){
            //System.out.println("");
            System.out.println("1 = Display Schedule / Text only");
            System.out.println("2 = Add own Schedule");
            System.out.println("0 = Log out");
            System.out.println("============================");
      }



      public void display(){
            linkL.printList(linkL);
      }


      public void add(){
            System.out.println("Input what month you're requesting.");
            System.out.println("Example 1 = January, 12 = December");
            System.out.print("Year: ");
            int year = scan.nextInt();
            System.out.print("Month: ");
            int month = scan.nextInt();
            System.out.print("Day: ");
            int day = scan.nextInt();
            System.out.println();

            String dateV = monthSelect(month-1);

            dateV =  dateV+" "+day+", "+year;
            linkL.insert(linkL, dateV);
      }



      public String monthSelect(int month){
        String dateV = "";
        switch (month){
          case 0:
                dateV = "January";
                break;
          case 1:
                dateV = "February";
                break;
          case 2:
                dateV = "March";
                break;
          case 3:
                dateV = "April";
                break;
          case 4:
                dateV = "May";
                break;
          case 5:
                dateV = "June";
                break;
          case 6:
                dateV = "July";
                break;
          case 7:
                dateV = "August";
                break;
          case 8:
                dateV = "September";
                break;
          case 9:
                dateV = "October";
                break;
          case 10:
                dateV = "November";
                break;
          case 11:
                dateV = "December";
                break;
        }
        return dateV;
      }


      public String display(int year, int month, int day){
            System.out.println("\nProper format:");
            String dateV = "";

            dateV = monthSelect(month);
            dateV = dateV+" "+day+", "+year;
            //System.out.println(dateV);
            //System.out.println("============================");
            return(dateV);
      }

      public void F3(int year, int month, int day){
            display(year, month, day);
      }


      public void F2(){
        System.out.println("Displaying Schedule");
        linkL.printList(linkL);
      }

      public void F1(){



          linkL.insert(linkL, "February 24, 2020");
          linkL.printList(linkL);
          System.out.println("");



          int day, month, year; //Need to add in time later on. Can likely input in schedule
          day = 1;
          month = cal.get(Calendar.MONTH);
          year = cal.get(Calendar.YEAR);
          String dateV;


          System.out.println("\n\nDefault");
          System.out.println("Year: " + year);
          System.out.println("Month: " + month);
          System.out.println("Month: " + day);

          dateV = display(year, month, day);
          linkL.insert(linkL, dateV);
          linkL.printList(linkL);
          //Default values are February 2020 as of right now. It accurately depicts date


          //Unrefined method. Might not need the date system
          cal.set(Calendar.MONTH, Calendar.MAY);
          cal.set(Calendar.YEAR, 2021);
          year = cal.get(Calendar.YEAR);
          month = cal.get(Calendar.MONTH);
          day = 25;
          dateV = display(year, month, day);
          linkL.insert(linkL, dateV);
          linkL.printList(linkL);



      }


}
