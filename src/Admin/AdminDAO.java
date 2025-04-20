package Admin;

import java.sql.ResultSet;

public interface AdminDAO {
    ResultSet getAllAdmin();
    void saveAdmin(AdminModel admin);
    void updateAdmin(AdminModel admin);
    void deleteAdmin(String adminID);
}
