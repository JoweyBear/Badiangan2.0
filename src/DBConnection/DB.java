package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Settings.Settings;

public class DB {

    private static Connection conn;

    private static Connection createConnection() {
        try {
            //String userName = "root";  //user of the database
            String userName = Settings.userName;  //user of the database
            String password = Settings.password;  //password of the database
            //String url = "jdbc:mysql://localhost/badiangan";  //the database that will be used
            String url = Settings.url;
            Class.forName("com.mysql.cj.jdbc.Driver"); //the driver of the database
            conn = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            System.err.print(ex);
            JOptionPane.showMessageDialog(null, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return conn;
    }

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = createConnection();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
//        public static void main(String[] args) throws SQLException {
//        System.out.println(getConnection());
////        System.out.println(DriverManager.getDriver("jdbc:mysql://").getClass().getName());
//
//    }
}

