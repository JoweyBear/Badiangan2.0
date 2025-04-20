package Hazard;

import DBConnection.DB;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class HazardDAOImpl implements HazardDAO {

    private Connection conn;
    private ResultSet rs;

    public HazardDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllHazard() {
        try {
            String sql = "Select haz_id as 'ID', description as 'Description', "
                    + "lt as 'Latitude', lg as 'Longitude' from hazard";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(HazardDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveHazard(HazardModel hzrd) {
        try {
            conn = DB.getConnection();
            String sql = "Insert into hazard values (0,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, hzrd.getDesc());
            stmt.setDouble(2, hzrd.getLatt());
            stmt.setDouble(3, hzrd.getLongt());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HazardModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(HazardModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updatHazard(HazardModel hzrd) {
        try {
            conn = DB.getConnection();
            String sql = "Update hazard set desc = ? , "
                    + "lt = ? , lg = ? where haz_id = '" + hzrd.getHazardID() + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, hzrd.getDesc());
            stmt.setDouble(2, hzrd.getLatt());
            stmt.setDouble(3, hzrd.getLongt());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HazardModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(HazardDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteHazard(String id) {
        try {
            String sql = "Delete from hazard where haz_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HazardModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(HazardDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
