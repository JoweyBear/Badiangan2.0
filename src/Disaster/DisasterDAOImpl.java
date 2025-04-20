package Disaster;

import DBConnection.DB;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DisasterDAOImpl implements DisasterDAO {

    private Connection conn;
    private ResultSet rs;

    public DisasterDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllDisasters() {
        try {
            String sql = "Select * from disaster";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DisasterDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveDisasters(DisasterModel dsstr) {
        try {
            String sql = "Insert into disaster values (0,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dsstr.getType());
            stmt.setString(2, dsstr.getName());
            stmt.setString(3, dsstr.getDate());
            stmt.setDouble(4, dsstr.getLatt());
            stmt.setDouble(5, dsstr.getLongt());
            stmt.setDouble(6, dsstr.getRad());
            stmt.setString(7, dsstr.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DisasterDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(DisasterDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateDisasters(DisasterModel dsstr) {
        try {
            String sql = "Update disaster set disaster.type = ? , disaster.name = ? , "
                    + "disaster.date = ? , disaster.lat = ? , disaster.long = ?, disaster.radius = ?, "
                    + "disaster.remarks = ? where disaster.dis_id = '" + dsstr.getDisasterID() + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dsstr.getType());
            stmt.setString(2, dsstr.getName());
            stmt.setString(3, dsstr.getDate());
            stmt.setDouble(4, dsstr.getLatt());
            stmt.setDouble(5, dsstr.getLongt());
            stmt.setDouble(6, dsstr.getRad());
            stmt.setString(7, dsstr.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DisasterDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(DisasterDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteDisasters(String id) {
        try {
            conn = DB.getConnection();
            String sql = "Delete from disaster where dis_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DisasterDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(DisasterDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
