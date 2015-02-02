import java.sql.*;

public class Validate
 {
 
     public static boolean checkUser(String name,String pass) 
     {
      boolean st =false;
      try{

	 //loading driver 
         Class.forName("com.mysql.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/RM","root","diktyawww14");
         PreparedStatement ps =con.prepareStatement
                             ("select * from user_table where name=? and pass=?");
         ps.setString(1, name);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
  }   
}
