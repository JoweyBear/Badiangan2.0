package LiveStock.Disposal;

import DBConnection.DB;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DisposalDAOImpl implements DisposalDAO {

    private Connection conn;
    private ResultSet rs;

    public DisposalDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllDisposal() {
        try {
//            conn = DB.getConnection();
            String sql = "SELECT ls_season.ls_season_id as 'ID', "
                    + "(SELECT CONCAT_WS(' ', beneficiary.fname, beneficiary.mname, beneficiary.lname)) as 'Beneficiary', "
                    + "ls_season.fk_ls_id_ls_season as 'Livestock ID', "
                    + "ls_season.profit as 'Profit', "
                    + "ls_season.date as 'Disposal Date', "
                    + "ls_season.remarks as 'Remarks' "
                    + "from beneficiary, livestock, ls_season where "
                    + "livestock.fk_bene_id_livestock = beneficiary.bene_id and "
                    + "livestock.ls_id = ls_season.fk_ls_id_ls_season";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DisposalModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveDisposal(DisposalModel dis) {
        try {
//            conn = DB.getConnection();
            String sql = "Insert into ls_season values (0,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dis.getLsID());
            stmt.setDouble(2, dis.getProfit());
            stmt.setString(3, dis.getDate());
            stmt.setString(4, dis.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DisposalModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(DisposalModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateDisposal(DisposalModel dis) {
        try {
//            conn = DB.getConnection();
            String sql = "Update ls_season set fk_ls_id_ls_season = ?,"
                    + " profit = ? , date = ?, "
                    + "remarks = ? where ls_season_id = '" + dis.getDisID() + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dis.getLsID());
            stmt.setDouble(2, dis.getProfit());
            stmt.setString(3, dis.getDate());
            stmt.setString(4, dis.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DisposalModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(DisposalModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteDisposal(String id) {
        try {
//            conn = DB.getConnection();
            String sql = "Delete from ls_season where ls_season_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            //JOptionPane.showMessageDialog(null,"Subscriber Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(DisposalModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(DisposalModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Integer> getLSID() {
        ArrayList<Integer> ls = new ArrayList<Integer>();
//        Connection conn = DB.getConnection();
        try {
            String sql = "SELECT ls_id as 'ID' from livestock";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ls.add(rs.getInt("ID"));
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        return ls;
    }

    @Override
    public void updateLSDisposed(int id) {
        try {
//            conn = DB.getConnection();
            String sql = "Update livestock set status = 'Disposed' "
                    + "where ls_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DisposalDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(DisposalDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
