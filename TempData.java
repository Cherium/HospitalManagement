import java.util.HashMap;

public class TempData{
      HashMap<String, String> dataBase;



      public TempData(){
            dataBase = new HashMap<String, String>();
            dataBase.put("Adam", "0055");
            dataBase.put("Bob", "123");
      }

      public static void main(String[]args){


      }

      public String getPassword(String username){
            String temp = dataBase.get(username);
            System.out.println("Username found: "+temp);
            if (temp == null)
                return "1";
            else{
                return temp;
            }
      }

}
