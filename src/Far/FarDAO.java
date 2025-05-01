package Far;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public interface FarDAO {
//    ResultSet getAllFar();
    DefaultTableModel getAllFar();
    void saveFar(FarModel far);
    void update(FarModel far);
    void deleteFar(String id);

}
