package FMember;

import Beneficiary.BeneDAOImpl;
import DBConnection.DB;
import Util.DeEncryption;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FMemberDAOImpl implements FMemberDAO {

    private Connection conn;
    private ResultSet rs;
    DeEncryption de = new DeEncryption();

    public FMemberDAOImpl() {
        conn = DB.getConnection();
    }

//    @Override
//    public ResultSet getAllFM() {
//        try {
//            String sql = "SELECT fmember.fmem_id as 'ID', CONCAT_WS(' ', beneficiary.fname, "
//                    + "beneficiary.mname, beneficiary.lname ) as 'Beneficiary', "
//                    + "fmember.fname as 'First Name', fmember.mname as 'Middle Name', "
//                    + "fmember.lname as 'Last Name', fmember.rel_to_hod as 'Rel to HOD', "
//                    + "fmember.age as 'Age', fmember.sex as 'Sex', fmember.educ as 'HIghest Educ Att', "
//                    + "fmember.occ_skills as 'Occ Skills', "
//                    + "fmember.remarks as 'Remarks' from beneficiary , fmember where "
//                    + "fmember.fk_bene_id_member = beneficiary.bene_id";
//            Statement stmt = conn.createStatement();
//            rs = stmt.executeQuery(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(FMemberDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, ex);
//            return null;
//        }
//        return rs;
//    }
    @Override
    public void saveFM(FMemberModel fm) {
        try {
            conn = DB.getConnection();
            String sql = "Insert into fmember values (0,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, fm.getBeneID());
            stmt.setString(2, de.encrypt(fm.getFname()));
            stmt.setString(3, de.encrypt(fm.getMname()));
            stmt.setString(4, de.encrypt(fm.getLname()));
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
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public void update(FMemberModel fm) {
        try {
            conn = DB.getConnection();
            String sql = "Update fmember set fk_bene_id_member = ?, "
                    + "fname = ? , mname = ? , lname = ?, rel_to_hod = ?, "
                    + "age = ? , sex = ? , educ = ?, occ_skills = ?, "
                    + "remarks = ? where fmem_id = '" + fm.getFmID() + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, fm.getBeneID());
            stmt.setString(2, de.encrypt(fm.getFname()));
            stmt.setString(3, de.encrypt(fm.getMname()));
            stmt.setString(4, de.encrypt(fm.getLname()));
            stmt.setString(5, fm.getRel());
            stmt.setInt(6, fm.getAge());
            stmt.setString(7, fm.getSex());
            stmt.setString(8, fm.getEduc());
            stmt.setString(9, fm.getOcc());
            stmt.setString(10, fm.getRemarks());
            stmt.execute();
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Username already taken", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
//        finally
//        {
//            try {
//                conn.close();
//            } catch (SQLException ex) 
//            {
//                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
//                Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    @Override
    public void deleteFM(String id) {
        try {
            String sql = "Delete from fmember where fmem_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
//        finally
//        {
//            try {
//                conn.close();
//            } catch (SQLException ex) 
//            {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(FMemberModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    @Override
    public DefaultTableModel getAllFM() {
        try {
            String sql = "SELECT fmember.fmem_id as 'ID', "
                    + "beneficiary.fname as 'BeneFName', "
                    + "beneficiary.mname as 'BeneMName', "
                    + "beneficiary.lname as 'BeneLName', "
                    + "fmember.fname as 'First Name', fmember.mname as 'Middle Name', "
                    + "fmember.lname as 'Last Name', fmember.rel_to_hod as 'Rel to HOD', "
                    + "fmember.age as 'Age', fmember.sex as 'Sex', fmember.educ as 'Highest Educ Att', "
                    + "fmember.occ_skills as 'Occ Skills', fmember.remarks as 'Remarks' "
                    + "FROM beneficiary, fmember WHERE fmember.fk_bene_id_member = beneficiary.bene_id";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
            int columnCount = md.getColumnCount();

            Vector<String> columnNames = new Vector<>(List.of(
                    "ID", "Beneficiary", "First Name", "Middle Name", "Last Name",
                    "Rel to HOD", "Age", "Sex", "Highest Educ Att", "Occ Skills", "Remarks"
            ));

            Vector<Vector<Object>> data = new Vector<>();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();

                String bFName = de.decrypt(rs.getString("BeneFName"));
                String bMName = de.decrypt(rs.getString("BeneMName"));
                String bLName = de.decrypt(rs.getString("BeneLName"));
                String beneFullName = bFName + " "
                        + (bMName != null && !bMName.isEmpty() ? bMName + " " : "") + bLName;

                row.add(rs.getInt("ID"));
                row.add(beneFullName);

                for (int i = 1; i <= columnCount; i++) {
                    String col = md.getColumnLabel(i);

                    if ("ID".equals(col) || col.startsWith("Bene")) {
                        continue;
                    }

                    Object val = rs.getObject(i);

                    if (val != null && ("First Name".equals(col)
                            || "Middle Name".equals(col)
                            || "Last Name".equals(col))) {
                        val = de.decrypt(val.toString());
                    }

                    row.add(val);
                }

                data.add(row);
            }

            return new DefaultTableModel(data, columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(BeneDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        Vector<String> columns = new Vector<>(List.of(
                "ID", "Beneficiary", "First Name", "Middle Name", "Last Name",
                "Rel to HOD", "Age", "Sex", "Highest Educ Att", "Occ Skills", "Remarks"
        ));
        return new DefaultTableModel(new Vector<>(), columns);
    }

}
