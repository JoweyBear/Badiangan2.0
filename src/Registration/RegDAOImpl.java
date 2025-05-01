package Registration;

import Beneficiary.BeneDAOImpl;
import DBConnection.DB;
import Util.DeEncryption;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class RegDAOImpl implements RegDAO {

    private Connection conn;
    private ResultSet rs;
    DeEncryption de = new DeEncryption();

    public RegDAOImpl() {
        conn = DB.getConnection();
    }

//    @Override
//    public ResultSet getAllReg() {
//        try {
////            conn = DB.getConnection();
//            //String sql = "Select * from registration";
//            String sql = "SELECT registration.reg_id as 'ID', "
//                    + "CONCAT_WS(' ', admin.fname, admin.mname, admin.lname) as 'Admin', "
//                    + "CONCAT_WS(' ', beneficiary.fname, beneficiary.mname, beneficiary.lname) as 'Beneficiary', "
//                    + "registration.walkin_status as 'Walk in Stat', "
//                    + "registration.case as 'Case', "
//                    + "registration.date as 'Reg Date' "
//                    + "from registration, beneficiary, admin where "
//                    + "registration.fk_bene_id_registration = beneficiary.bene_id "
//                    + "and registration.fk_admin_id_registration = admin.admin_id";
//            Statement stmt = conn.createStatement();
//            rs = stmt.executeQuery(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(RegModel.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, ex);
//            return null;
//        }
//        return rs;
//    }
    @Override
    public void saveReg(RegModel reg) {
        try {
//            conn = DB.getConnection();
            String sql = "Insert into registration values (0,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reg.getAdminID());
            stmt.setInt(2, reg.getBeneID());
            stmt.setString(3, reg.getStat());
            stmt.setString(4, reg.getCaseReg());
            stmt.setString(5, reg.getDate());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RegModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(RegModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public void deleteReg(String id) {
        try {
            conn = DB.getConnection();
            String sql = "Delete from registration where reg_id = '" + id + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            //JOptionPane.showMessageDialog(null,"Subscriber Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(RegModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(RegModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public DefaultTableModel getAllReg() {
        try {
            String sql = "SELECT registration.reg_id as 'ID', "
                    + "CONCAT_WS(' ', admin.fname, admin.mname, admin.lname) as 'Admin', "
                    + "beneficiary.fname as 'BeneFName', "
                    + "beneficiary.mname as 'BeneMName', "
                    + "beneficiary.lname as 'BeneLName', "                    
                    + "registration.walkin_status as 'Walk in Stat', "
                    + "registration.case as 'Case', "
                    + "registration.date as 'Reg Date' "
                    + "from registration, beneficiary, admin where "
                    + "registration.fk_bene_id_registration = beneficiary.bene_id "
                    + "and registration.fk_admin_id_registration = admin.admin_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
            int columnCount = md.getColumnCount();

            Vector<String> columnNames = new Vector<>(List.of(
                    "ID", "Admin", "Beneficiary",  "Walk in Stat", "Case",
                    "Reg Date"));

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
//
//                    if (val != null && ("First Name".equals(col)
//                            || "Middle Name".equals(col)
//                            || "Last Name".equals(col))) {
//                        val = de.decrypt(val.toString());
//                    }

                    row.add(val);
                }

                data.add(row);
            }

            return new DefaultTableModel(data, columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(BeneDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        Vector<String> columns = new Vector<>(List.of(
                    "ID", "Admin", "Beneficiary",  "Walk in Stat", "Case",
                    "Reg Date"));
        return new DefaultTableModel(new Vector<>(), columns);
    }

}
