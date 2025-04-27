package Crop.Harvest;

import DBConnection.DB;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class HarvestDAOImpl implements HarvestDAO {

    private Connection conn;
    private ResultSet rs;

    public HarvestDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllHarvest() {
        try {
            String sql = "SELECT crop_season.cs_id as 'ID', "
                    + "(SELECT CONCAT_WS(' ', beneficiary.fname, beneficiary.mname, beneficiary.lname)) as 'Beneficiary', "
                    + "crop_season.fk_crop_id_cs as 'Crop ID', "
                    + "crop_season.form as 'Container', "
                    + "crop_season.num as 'Quantity', "
                    + "crop_season.profit as 'Profit', "
                    + "crop_season.date as 'Harvest Date', "
                    + "crop_season.remarks as 'Remarks' "
                    + "from beneficiary, crop, crop_season where "
                    + "crop.fk_bene_id_crop = beneficiary.bene_id and "
                    + "crop.crop_id = crop_season.fk_crop_id_cs";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(HarvestDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveHarvest(HarvestModel hrvst) {
        try {
            String sql = "Insert into crop_season values (0,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, hrvst.getHarvestID());
            stmt.setString(2, hrvst.getCrop());
            stmt.setInt(3, hrvst.getQty());
            stmt.setDouble(4, hrvst.getProfit());
            stmt.setString(5, hrvst.getDate());
            stmt.setString(6, hrvst.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HarvestModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(HarvestModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public void updateHarvest(HarvestModel hrvst) {
        try {
            String sql = "Update crop_season set fk_crop_id_cs = ? , form = ? , "
                    + "num = ? , profit = ? , date = ?, "
                    + "remarks = ? where cs_id = '" + hrvst.getHarvestID() + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, hrvst.getCropID());
            stmt.setString(2, hrvst.getContainer());
            stmt.setInt(3, hrvst.getQty());
            stmt.setDouble(4, hrvst.getProfit());
            stmt.setString(5, hrvst.getDate());
            stmt.setString(6, hrvst.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HarvestModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
//                Logger.getLogger(HarvestModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public void updateCropHarvested(int id) {
        try {
            String sql = "Update crop set status = 'Harvested' "
                    + "where crop_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(HarvestDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
//                Logger.getLogger(HarvestDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public void deleteHarvest(String id) {
        try {
            String sql = "Delete from crop_season where cs_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            //JOptionPane.showMessageDialog(null,"Subscriber Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(HarvestModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(HarvestModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public ArrayList<Integer> getCropsID() {
        ArrayList<Integer> crop = new ArrayList<Integer>();
        try {
            String sql = "SELECT crop_id as 'ID' from crop";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                crop.add(rs.getInt("ID"));
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        return crop;
    }

}
