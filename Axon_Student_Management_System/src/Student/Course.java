
package Student;
 /**Coded with love by SSEKAJJA WAVAMUNO ISAAC 
             * Email: ssekajjawavamuno@gmail.com
             * Contact +256 703933903, +256 773883705
             */

import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sun.util.logging.PlatformLogger;

public class Course {
    Home home = new Home();
      Connection con = MyConnection.getConection();
    PreparedStatement ps;
     //Get table max row
    public int getMax(){
        int id = 0;
        Statement st;
        try{
        st = con.createStatement();
        ResultSet re = st.executeQuery("select max(id) from course");
        }
        catch(Exception e)
        {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, e);
           // Logger.getLogger(Course.class.getName().log(Level.SEVERE, null,e));
        //JOptionPane.showMessageDialog(null, "Failed retrieving table connent"+e);
        }
        return id + 1;
    }
    public boolean getId(int id){
          try {
              ps = con.prepareStatement("select * from student where id = ?");
              ps.setInt(1, id);
              ResultSet rs = ps.executeQuery();
              if(rs.next()){
              home.jTextField25.setText(String.valueOf(rs.getInt(1)));
              return true;             
              }else{
                  JOptionPane.showMessageDialog(null, "Student id doesnot exist.");
              }
          } catch (SQLException ex) {
              Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
          }
          return false;
    }
    public int countSemester(int id){
    int total =0;
          try {
              ps = con.prepareStatement("select count(*) as 'total' from course where student_id = ?");
                ps.setInt(1, id);
              ResultSet rs = ps.executeQuery();
              while(rs.next()){
              total = rs.getInt(1);
              }
              if(total ==8){
              JOptionPane.showMessageDialog(null, "This student has completed all the courses.");
              return -1;
              }
          } catch (SQLException ex) {
              Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
          }
    return total;
    }
//Checking whether the student has alrady taken this sem or not
    public boolean isSemesterExist(int sid, int semesterNo){
    try{
    ps = con.prepareStatement("select * from course where student_id and semester = ?");
    ps.setInt(1, sid);
    ps.setInt(2, semesterNo);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
    return true;
    }
    }catch(SQLException ex){
    //Logger.getLogger(Student.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }
    //Checking whether the student has alrady taken this course or not
    public boolean isCourseExist(int id, String courseNo, String course){
    String sql = "select * from course where student_id = ? and " + courseNo + " = ?";
        try{
    ps = con.prepareStatement("select * from course where student_id and semester = ?");
    ps.setInt(1, id);
    ps.setString(2, courseNo);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
    return true;
    }
    }catch(SQLException ex){
    //Logger.getLogger(Student.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }
    //Inserting data into course table
    public void insert(int id, int sid, int semester, String course1, String course2,
            String course3, String course4, String course5)
    {
    String sql = "insert into course values(?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, sid);
            ps.setInt(3, semester);
            ps.setString(4, course1);
            ps.setString(5, course2);
            ps.setString(6, course3);
            ps.setString(7, course4);
            ps.setString(8, course5);
            
            
            if(ps.executeUpdate()>0){
            JOptionPane.showMessageDialog(null,"Course added successfully");
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Get all the courses from the database
     public void getCourseValue(JTable table, String searchValue){
    String sql = "Select * from course where concat(id,student_id,semester)like ? order by id desc";
   
    try{
         ps = con.prepareStatement(sql);
          ps.setString(1, "%"+searchValue+"%");
         ResultSet rs = ps.executeQuery();
         DefaultTableModel model = (DefaultTableModel)table.getModel();
   Object[] row;
   while(rs.next()){
   row = new Object[8];
   row[0] = rs.getInt(1);
   row[1] = rs.getString(2);
   row[2] = rs.getString(3);
   row[3] = rs.getString(4);
   row[4] = rs.getString(5);
   row[5] = rs.getString(6);
   row[6] = rs.getString(7);
   row[7] = rs.getString(8);
   
   model.addRow(row);
   }
    }
    catch(Exception x){
    /**
 * Coded with love By SSEKAJJA WAVAMUNO ISAAC 20/2/314/D/337
 */
    }
    }

}
