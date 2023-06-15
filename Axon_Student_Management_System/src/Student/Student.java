package Student;

import java.sql.Connection;
import db.MyConnection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Student {

    Connection con = MyConnection.getConection();
    PreparedStatement ps;

    //Get table max row
    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet re = st.executeQuery("select max(id) from student");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed retrieving table connent" + e);
        }finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);                            
            }
        }
        return id + 1;
    }

    //Inserting data into student table
    public void insert(int id, String sname, String date, String gender, String email,
            String phone, String father, String mother, String nationality, String address, String imagePath) {
        String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, sname);
            ps.setString(3, date);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, father);
            ps.setString(8, mother);
            ps.setString(9, nationality);
            ps.setString(10, address);
            ps.setString(11, imagePath);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New student added successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);                            
            }
        }
    }
    //Inserting updatedata into student table
    /**Coded with love by SSEKAJJA WAVAMUNO ISAAC  
             * Email: ssekajjawavamuno@gmail.com
             * Contact +256 703933903, +256 773883705
             */

    public void update(int id, String sname, String date, String gender, String email,
            String phone, String father, String mother, String nationality, String address, String imagePath) {
        String sql = "update student set id=?,name=?,date_of_birth=?,gender=?,email=?,phone=?,"
                + "father_name=?,mother_name=?,nationality=?,address=?,imagePath=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, sname);
            ps.setString(3, date);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, father);
            ps.setString(8, mother);
            ps.setString(9, nationality);
            ps.setString(10, address);
            ps.setString(11, imagePath);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Student updated successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);                            
            }
        }
    }

    //Cecking student email address if alraedy exixts
    public boolean isEmailExist(String email) throws Exception {
        try {
            ps = con.prepareStatement("select * from student where email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);                            
            }
        }
        return false;
    }
    //Cecking student  id if alraedy exixts

    public boolean isIdExist(int id) throws Exception {
        try {
            ps = con.prepareStatement("select * from student where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);                            
            }
        }
        return false;
    }
    /**Coded with love by SSEKAJJA WAVAMUNO ISAAC 20/2/314/D/337 
             * Email: ssekajjawavamuno@gmail.com
             * Contact +256 703933903, +256 773883705
             */

    //Get all students
    public void getStudentValue(JTable table, String searchValue)throws Exception {
        String sql = "Select * from student where concat(id,name,email,contact)like ? order by id desc";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[11];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                model.addRow(row);
            }
        } catch (Exception x) {
        }finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);                            
            }
        }
    }
    //student data delete
    public void delete(int id){
    int Yesor = JOptionPane.showConfirmDialog(null, "Course and score records will also be deleted", "Confirm deletion",JOptionPane.OK_CANCEL_OPTION, 0);
if(Yesor == JOptionPane.OK_OPTION){
        try {
            ps = con.prepareStatement("delete from student where id =?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                            JOptionPane.showMessageDialog(null, "Student data deleted successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
                ps.close();
            } catch (Exception ex) {
                            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);                            
            }
        }
}
    }
}
