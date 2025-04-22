package FMember;

import DBConnection.DB;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FMemberDAOImpl implements FMemberDAO {

    private Connection conn;
    private ResultSet rs;

    public FMemberDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public ResultSet getAllFM() {
        try {
            String sql = "SELECT fmember.fmem_id as 'ID', CONCAT_WS(' ', beneficiary.fname, "
                    + "beneficiary.mname, beneficiary.lname ) as 'Beneficiary', "
                    + "fmember.fname as 'First Name', fmember.mname as 'Middle Name', "
                    + "fmember.lname as 'Last Name', fmember.rel_to_hod as 'Rel to HOD', "
                    + "fmember.age as 'Age', fmember.sex as 'Sex', fmember.educ as 'HIghest Educ Att', "
                    + "fmember.occ_skills as 'Occ Skills', "
                    + "fmember.remarks as 'Remarks' from beneficiary , fmember where "
                    + "fmember.fk_bene_id_member = beneficiary.bene_id";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(FMemberDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }

    @Override
    public void saveFM(FMemberModel fm) {
        try {
            conn = DB.getConnection();
            String sql = "Insert into fmember values (0,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, fm.getBeneID());
            stmt.setString(2, fm.getFname());
            stmt.setString(3, fm.getMname());
            stmt.setString(4, fm.getLname());
            stmt.setString(5, fm.getRel());
            stmt.setInt(6, fm.getAge());
            stmt.setString(7, fm.getSex());
            stmt.setString(8, fm.getEduc());
            stmt.setString(9, fm.getOcc());
            stmt.setString(10, fm.getRemarks());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(FMemberModel fm) {
        try {
            conn = DB.getConnection();
            String sql = "Update fmember set fk_bene_id_member = ?, "
                    + "fname = ? , mname = ? , lname = ?, rel_to_hod = ?, "
                    + "age = ? , sex = ? , educ = ?, occ_skills = ?, "
                    + "remarks = ? where fmem_id = '"+fm.getFmID()+"'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, fm.getBeneID());
            stmt.setString(2, fm.getFname());
            stmt.setString(3, fm.getMname());
            stmt.setString(4, fm.getLname());
            stmt.setString(5, fm.getRel());
            stmt.setInt(6, fm.getAge());
            stmt.setString(7, fm.getSex());
            stmt.setString(8, fm.getEduc());
            stmt.setString(9, fm.getOcc());
            stmt.setString(10, fm.getRemarks());
            stmt.execute();
        } catch(SQLIntegrityConstraintViolationException ex)
        {
            JOptionPane.showMessageDialog(null, "Username already taken", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (SQLException ex) {
            Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteFM(String id) {
        try {
            String sql = "Delete from fmember where fmem_id = '"+id+"'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
