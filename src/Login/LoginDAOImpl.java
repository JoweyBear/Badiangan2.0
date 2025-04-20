package Login;

import DBConnection.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class LoginDAOImpl implements LoginDAO {

    private Connection conn;
    private ResultSet rs;

    public LoginDAOImpl() {
        conn = DB.getConnection();
    }

    @Override
    public String[] loginAdmin(String un, String pw) {
        String[] name = new String[4];
//        Connection conn = DB.getConnection();
        try 
        {
            if (un != null && pw != null) 
            {
                String sql = "SELECT admin_id, position, department, "
                        + "CONCAT_WS(' ', fname, mname, lname) AS 'name' FROM admin "
                            + "where username='" + un + "' and password = sha1('"+pw+"')";
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) 
                {
                    //in this case enter when at least one result comes it means user is valid
                    name[0] = rs.getString("position");
                    name[1] = rs.getString("department");
                    name[2] = rs.getString("name");
                    name[3] = "" + rs.getInt("admin_id");
                }
                else
                {
                    name[0] = null;
                    name[1] = null;
                    name[2] = null;
                    name[3] = null;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty!","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException err) 
        {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        return name;
    }

}
