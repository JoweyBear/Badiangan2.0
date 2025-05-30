package Beneficiary;

import DBConnection.DB;
import Util.DeEncryption;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
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

public class BeneDAOImpl implements BeneDAO {

    private Connection conn;
    private ResultSet rs;
    DeEncryption de = new DeEncryption();

    public BeneDAOImpl() {
        conn = DB.getConnection();
    }

//    @Override
//    public ResultSet getAllBene() {
//        try {
//            String sql = "SELECT bene_id as 'ID',"
//                    + "fname as 'First Name', mname as 'Middle Name', "
//                    + "lname as 'Last Name', sex as 'Sex', dob as 'Date of Birth', "
//                    + "(select name from brgy where brgy_id = fk_brgy_id_beneficiary) as 'Brgy', "
//                    + "code as 'Code', fourps as '4Ps', ip as 'Indigent', hea as 'Highest Educ Att', "
//                    + "ethnicity as 'Ethnicity', net_income as 'Net Income', occ as 'Occupation', "
//                    + "health_condition as 'Health Condition', house_status as 'House Status',"
//                    + "house_condition as 'House Condition', contact_num as 'Contact #',"
//                    + "loc_lat as 'Latitude', loc_long as 'Longitude' "
//                    + "from beneficiary";
//            Statement stmt = conn.createStatement();
//            rs = stmt.executeQuery(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(BeneModel.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, ex);
//            return null;
//        }
//        return rs;
//    }
//
//    @Override
//    public ResultSet getAllFarmer() {
//        try {
//            String sql = "SELECT bene_id as 'ID',"
//                    + "fname as 'First Name', mname as 'Middle Name', "
//                    + "lname as 'Last Name', sex as 'Sex', dob as 'Date of Birth', "
//                    + "(select name from brgy where brgy_id = fk_brgy_id_beneficiary) as 'Brgy', "
//                    + "code as 'Code', fourps as '4Ps', ip as 'Indigent', hea as 'Highest Educ Att', "
//                    + "ethnicity as 'Ethnicity', net_income as 'Net Income', occ as 'Occupation', "
//                    + "health_condition as 'Health Condition', house_status as 'House Status',"
//                    + "house_condition as 'House Condition', contact_num as 'Contact #',"
//                    + "loc_lat as 'Latitude', loc_long as 'Longitude' "
//                    + "from beneficiary where occ = 'Farmer'";
//            Statement stmt = conn.createStatement();
//            rs = stmt.executeQuery(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(BeneModel.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, ex);
//            return null;
//        }
//        return rs;
//    }
    @Override
    public int getIDofLatestBene() {
        int bene_id = 0;
        String sql = "Select * from beneficiary ORDER BY bene_id DESC LIMIT 1";
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                bene_id = rs.getInt("bene_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return bene_id;
    }

    @Override
    public void saveBene(BeneModel bene) {
        try {
            String sql = "Insert into beneficiary values (?,?,?,?,?,?,"
                    + "(Select brgy_id from brgy where name = '" + bene.getBrgy() + "'),"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bene.getBeneID());
            stmt.setString(2, de.encrypt(bene.getFname()));
            stmt.setString(3, de.encrypt(bene.getMname()));
            stmt.setString(4, de.encrypt(bene.getLname()));
            stmt.setString(5, bene.getSex());
            stmt.setString(6, bene.getDob());
            stmt.setString(7, bene.getCode());
            stmt.setString(8, bene.getFourPs());
            stmt.setString(9, bene.getIndigency());
            stmt.setString(10, bene.getHea());
            stmt.setString(11, bene.getEthnicity());
            stmt.setDouble(12, bene.getNetIncome());
            stmt.setString(13, bene.getOcc());
            stmt.setString(14, de.encrypt(bene.getHealthCond()));
            stmt.setString(15, bene.getHouseStat());
            stmt.setString(16, bene.getHouseCond());
            stmt.setString(17, de.encrypt(bene.getContactNum()));
            stmt.setDouble(18, bene.getLocLat());
            stmt.setDouble(19, bene.getLocLong());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BeneModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(BeneModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public void updateBene(BeneModel bene) {
        try {
            String sql = "Update beneficiary set "
                    + "fname = ?, mname = ?, lname = ?, "
                    + "sex = ?, dob = ?, "
                    + "fk_brgy_id_beneficiary = (Select brgy_id from brgy where name = '" + bene.getBrgy() + "'), "
                    + "code = ?, fourps = ?, ip = ?, hea = ?, "
                    + "ethnicity = ?, net_income = ?, occ = ?, health_condition = ?, "
                    + "house_status = ?, house_condition = ?, contact_num = ?, "
                    + "loc_lat = ?, loc_long = ?"
                    + " where bene_id = " + bene.getBeneID() + "";
//            String sql = "Insert into beneficiary values (?,?,?,?,?,?,"
//                    + "(Select brgy_id from brgy where name = '"+brgy+"'),"
//                    + "?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, de.encrypt(bene.getFname()));
            stmt.setString(2, de.encrypt(bene.getMname()));
            stmt.setString(3, de.encrypt(bene.getLname()));
            stmt.setString(4, bene.getSex());
            stmt.setString(5, bene.getDob());
            stmt.setString(6, bene.getCode());
            stmt.setString(7, bene.getFourPs());
            stmt.setString(8, bene.getIndigency());
            stmt.setString(9, bene.getHea());
            stmt.setString(10, bene.getEthnicity());
            stmt.setDouble(11, bene.getNetIncome());
            stmt.setString(12, bene.getOcc());
            stmt.setString(13, de.encrypt(bene.getHealthCond()));
            stmt.setString(14, bene.getHouseStat());
            stmt.setString(15, bene.getHouseCond());
            stmt.setString(16, de.encrypt(bene.getContactNum()));
            stmt.setDouble(17, bene.getLocLat());
            stmt.setDouble(18, bene.getLocLong());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Beneficiary Updated!");
        } catch (SQLException ex) {
            Logger.getLogger(BeneModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(BeneModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public void deleteBene(String beneID) {
        try {
            String sql = "Delete from beneficiary where bene_id = '" + beneID + "'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            //JOptionPane.showMessageDialog(null,"Subscriber Deleted!");
        } catch (SQLException ex) {
            Logger.getLogger(BeneModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
//                Logger.getLogger(BeneModel.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    @Override
    public DefaultTableModel getAllFarmer() {
        try {
            String sql = "SELECT bene_id as 'ID',"
                    + "fname as 'First Name', mname as 'Middle Name', "
                    + "lname as 'Last Name', sex as 'Sex', dob as 'Date of Birth', "
                    + "(select name from brgy where brgy_id = fk_brgy_id_beneficiary) as 'Brgy', "
                    + "code as 'Code', fourps as '4Ps', ip as 'Indigent', hea as 'Highest Educ Att', "
                    + "ethnicity as 'Ethnicity', net_income as 'Net Income', occ as 'Occupation', "
                    + "health_condition as 'Health Condition', house_status as 'House Status',"
                    + "house_condition as 'House Condition', contact_num as 'Contact #',"
                    + "loc_lat as 'Latitude', loc_long as 'Longitude' "
                    + "from beneficiary where occ = 'Farmer'";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
            int columnCount = md.getColumnCount();

            // Build column headers
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(md.getColumnLabel(i));
            }

            // Build rows, decrypting on the fly
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    String col = md.getColumnLabel(i);
                    Object val = rs.getObject(i);
                    if (val != null && ("First Name".equals(col) || "Middle Name".equals(col) || "Last Name".equals(col) || "Health Condition".equals(col) || "Contact #".equals(col))) {
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
        Vector<String> columns = new Vector<>(List.of("ID", "First Name", "Middle Name", "Last Name", "Sex",
                "Date of Birth", "Brgy", "Code", "4Ps", "Indigent", "Highest Educ Att", "Ethnicity", "Net Income",
                "Occupation", "Health Condition", "House Status", "House Condition", "Contact #", "Latitude", "Longitude"));
        return new DefaultTableModel(new Vector<>(), columns);
    }

    @Override
    public DefaultTableModel getAllBene() {
        try {
            String sql = "SELECT bene_id as 'ID',"
                    + "fname as 'First Name', mname as 'Middle Name', "
                    + "lname as 'Last Name', sex as 'Sex', dob as 'Date of Birth', "
                    + "(select name from brgy where brgy_id = fk_brgy_id_beneficiary) as 'Brgy', "
                    + "code as 'Code', fourps as '4Ps', ip as 'Indigent', hea as 'Highest Educ Att', "
                    + "ethnicity as 'Ethnicity', net_income as 'Net Income', occ as 'Occupation', "
                    + "health_condition as 'Health Condition', house_status as 'House Status',"
                    + "house_condition as 'House Condition', contact_num as 'Contact #',"
                    + "loc_lat as 'Latitude', loc_long as 'Longitude' "
                    + "from beneficiary";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
            int columnCount = md.getColumnCount();

            // Build column headers
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(md.getColumnLabel(i));
            }

            // Build rows, decrypting on the fly
            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    String col = md.getColumnLabel(i);
                    Object val = rs.getObject(i);
                    if (val != null && ("First Name".equals(col) || "Middle Name".equals(col) || "Last Name".equals(col) || "Health Condition".equals(col) || "Contact #".equals(col))) {
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
        Vector<String> columns = new Vector<>(List.of("ID", "First Name", "Middle Name", "Last Name", "Sex",
                "Date of Birth", "Brgy", "Code", "4Ps", "Indigent", "Highest Educ Att", "Ethnicity", "Net Income",
                "Occupation", "Health Condition", "House Status", "House Condition", "Contact #", "Latitude", "Longitude"));
        return new DefaultTableModel(new Vector<>(), columns);
    }
}
