/** Coded with love by SSEKAJJA WAVAMUNO ISAAC
 * Email: ssekajjawavamuno@gmail.com
 * Contact +256 703933903, +256 773883705
 */
package db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class MyConnection {

    private static final String username = "root";
    private static final String password = "";
    private static final String dataConn = "jdbc:mysql//localhost/student_management";
    private static Connection con = null;

    
       public static Connection getConection(){
         try{
      Class.forName("com.mysql.cj.jdbc.Driver"); 
      con =  DriverManager.getConnection(dataConn,username,password);     
      }
      catch(Exception e)
      {
          e.printStackTrace(); 
      }   
     finally {
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());            
        }
}
        return con;
}  
}

    
   