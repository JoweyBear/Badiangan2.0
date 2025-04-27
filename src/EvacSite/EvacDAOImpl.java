package EvacSite;

import DBConnection.DB;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EvacDAOImpl implements EvacDAO {

    private Connection conn;
    private ResultSet rs;

    public EvacDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllEvac() {
        try {
            String sql = "Select evac.evac_id as 'ID', name as 'Name', "
                    + "evac.lat as 'Latitude', evac.long as 'Longitude' from evac";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EvacDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveEvac(EvacModel evac) {
        try {
            String sql = "Insert into evac values (0,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, evac.getName());
            stmt.setDouble(2, evac.getLatt());
            stmt.setDouble(3, evac.getLongt());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EvacModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(EvacModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public void updateEvac(EvacModel evac) {
        try {
            String sql = "Update evac set evac.name = ? , evac.lat = ?, "
                    + "evac.long = ? where evac.evac_id = '"+evac.getEvacID()+"'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, evac.getName());
            stmt.setDouble(2, evac.getLatt());
            stmt.setDouble(3, evac.getLongt());
            stmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(EvacDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
//        finally
//        {
//            try {
//                conn.close();
//            } catch (SQLException ex) 
//            {
//                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
//                Logger.getLogger(EvacDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    @Override
    public void deleteEvac(String id) {
        try {
            String sql = "Delete from evac where evac_id = '"+id+"'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EvacModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
//        finally
//        {
//            try {
//                conn.close();
//            } catch (SQLException ex)
//            {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(EvacModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

}
