package Far;

import DBConnection.DB;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FarDAOImpl implements FarDAO {

    public Connection conn;
    private ResultSet rs;

    public FarDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllFar() {
        try {
            String sql = "SELECT far.far_id as 'ID', "
                    + "CONCAT_WS(' ', beneficiary.fname, beneficiary.mname, beneficiary.lname) as 'Beneficiary', "
                    + "far.fk_dis_id_far as 'Disaster ID', "
                    + "far.during as 'During', "
                    + "far.date as 'Date', "
                    + "far.type as 'Type', "
                    + "far.qty as 'Quantity', "
                    + "far.cost as 'Cost', "
                    + "far.provider as 'Provider' "
                    + "from far, beneficiary where "
                    + "far.fk_bene_id_far = beneficiary.bene_id ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(FarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveFar(FarModel far) {
        try {
            conn = DB.getConnection();
            String sql = "Insert into far values (0,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, far.getBeneID());
            if (far.getDisID() == null) {
                stmt.setObject(2, far.getDisID());
            } else {
                stmt.setInt(2, (int) far.getDisID());
            }
            stmt.setString(3, far.getDuring());
            stmt.setString(4, far.getDate());
            stmt.setString(5, far.getType());
            stmt.setInt(6, far.getQty());
            stmt.setDouble(7, far.getCost());
            stmt.setString(8, far.getProvider());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(FarModel far) {
        try {
            String sql = "Update far set fk_bene_id_far = ? , far_raised = ? , "
                    + "classification = ? , heads = ? , age = ?, exp = ?, "
                    + "remarks = ? where ls_id = '" + far.getFarID() + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, far.getBeneID());
            stmt.setString(2, far.getFar_raised());
            stmt.setString(3, far.getClassi());
            stmt.setInt(4, far.getHeads());
            stmt.setInt(5, far.getAge());
            stmt.setString(6, far.getExp());
            stmt.setString(7, far.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteFar(String id) {
        try {
            String sql = "Delete from far where far_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(FarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
