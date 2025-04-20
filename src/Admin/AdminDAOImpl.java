package Admin;

import DBConnection.DB;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AdminDAOImpl implements AdminDAO {

    private Connection conn;
    private ResultSet rs;

    public AdminDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllAdmin() {
        try {
            String sql = "SELECT admin_id as 'ID',username as 'Username', fname as 'First Name', "
                    + "mname as 'Middle Name', lname as 'Last Name', department as 'Department',"
                    + " position as 'Position' from admin";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveAdmin(AdminModel admin) {
        try {
            String sql = "Insert into admin values (0,?,sha1 (?),?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getDepartment());
            stmt.setString(4, admin.getPosition());
            stmt.setString(5, admin.getFname());
            stmt.setString(6, admin.getLname());
            stmt.setString(7, admin.getMname());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Admin Added!");
        } catch (MySQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Username already taken", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateAdmin(AdminModel admin) {
        try {
            String sql = "Update admin set username = ? , password = sha1(?) , "
                    + "fname = ? , mname = ? , lname = ?, department = ?, "
                    + "position = ? where admin_id = '" + admin.getAdminID() + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getUsername());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getFname());
            stmt.setString(4, admin.getMname());
            stmt.setString(5, admin.getLname());
            stmt.setString(6, admin.getDepartment());
            stmt.setString(7, admin.getPosition());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Admin Info Updated!");
        } catch (MySQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Username already taken", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteAdmin(String adminID) {
        try {
            String sql = "Delete from admin where admin_id = '" + adminID + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            //JOptionPane.showMessageDialog(null,"Subscriber Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
