package LiveStock;

import DBConnection.DB;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LSDAOImpl implements LSDAO {

    private Connection conn;
    private ResultSet rs;

    public LSDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllLS() {
        try {
//            conn = DB.getConnection();
            //String sql = "Select * from livestock";
            String sql = "SELECT livestock.ls_id as 'ID', "
                    + "CONCAT_WS(' ', beneficiary.fname, beneficiary.mname, beneficiary.lname) as 'Beneficiary', "
                    + "livestock.livestock_raised as 'Livestock Raised', "
                    + "livestock.classification as 'Classification', livestock.heads as 'No. of Heads', "
                    + "livestock.age as 'Age in Months', livestock.exp as 'Exp Disposal Date', "
                    + "livestock.remarks as 'Remarks', livestock.status as 'Status' "
                    + "from livestock, beneficiary where "
                    + "livestock.fk_bene_id_livestock = beneficiary.bene_id";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(LSModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveLS(LSModel ls) {
        try {
//            conn = DB.getConnection();
            String sql = "Insert into livestock values (0,?,?,?,?,?,?,?,'Held')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ls.getBeneID());
            stmt.setString(2, ls.getLs());
            stmt.setString(3, ls.getClassi());
            stmt.setInt(4, ls.getHeads());
            stmt.setInt(5, ls.getAge());
            stmt.setString(6, ls.getExp());
            stmt.setString(7, ls.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LSModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(LSModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateLS(LSModel ls) {
        try {
            conn = DB.getConnection();
            String sql = "Update livestock set fk_bene_id_livestock = ? , livestock_raised = ? , "
                    + "classification = ? , heads = ? , age = ?, exp = ?, "
                    + "remarks = ? where ls_id = '" + ls.getLsID() + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ls.getBeneID());
            stmt.setString(2, ls.getLs());
            stmt.setString(3, ls.getClassi());
            stmt.setInt(4, ls.getHeads());
            stmt.setInt(5, ls.getAge());
            stmt.setString(6, ls.getExp());
            stmt.setString(7, ls.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LSModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(LSModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteLS(String id) {
        try {
//            conn = DB.getConnection();
            String sql = "Delete from livestock where ls_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            //JOptionPane.showMessageDialog(null,"Subscriber Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(LSModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(LSModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
