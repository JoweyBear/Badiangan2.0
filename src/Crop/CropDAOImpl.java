package Crop;

import DBConnection.DB;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CropDAOImpl implements CropDAO {

    private Connection conn;
    private ResultSet rs;

    public CropDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllCrop() {
        try {
            String sql = "SELECT crop.crop_id as 'ID', "
                    + "CONCAT_WS(' ', beneficiary.fname, beneficiary.mname, beneficiary.lname) as 'Beneficiary', "
                    + "crop.crop as 'Crop', crop.area as 'Area', crop.variety as 'Variety', "
                    + "crop.classification as 'Classification', crop.exp as 'Exp Harvest Date', "
                    + "crop.remarks as 'Remarks', crop.status as 'Status' "
                    + "from crop, beneficiary where "
                    + "crop.fk_bene_id_crop = beneficiary.bene_id";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CropDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveCrop(CropModel crop) {
        try {
            String sql = "Insert into crop values (0,?,?,?,?,?,?,?,'Planted')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, crop.getBeneID());
            stmt.setString(2, crop.getCrop());
            stmt.setDouble(3, crop.getArea());
            stmt.setString(4, crop.getVariety());
            stmt.setString(5, crop.getClassification());
            stmt.setString(6, crop.getDate());
            stmt.setString(7, crop.getRemarks());
            stmt.execute();
        }catch (SQLException ex) {
            Logger.getLogger(CropModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(CropDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateCrop(CropModel crop) {
        try {
            String sql = "Update crop set fk_bene_id_crop = ? , crop = ? , "
                    + "area = ? , variety = ? , classification = ?, exp = ?, "
                    + "remarks = ? where crop_id = '"+crop.getCropID()+"'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, crop.getBeneID());
            stmt.setString(2, crop.getCrop());
            stmt.setDouble(3, crop.getArea());
            stmt.setString(4, crop.getVariety());
            stmt.setString(5, crop.getClassification());
            stmt.setString(6, crop.getDate());
            stmt.setString(7, crop.getRemarks());
            stmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(CropDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(CropDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteCrop(String id) {
        try {
            String sql = "Delete from crop where crop_id = '"+id+"'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            //JOptionPane.showMessageDialog(null,"Subscriber Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(CropModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(CropDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
