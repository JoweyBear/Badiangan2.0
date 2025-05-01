package Registration;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public interface RegDAO {
    DefaultTableModel getAllReg();
//    ResultSet getAllReg();
    void saveReg(RegModel reg);
    void deleteReg(String id);
}
