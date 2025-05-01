package FMember;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public interface FMemberDAO {
    DefaultTableModel getAllFM();
//    ResultSet getAllFM();
    void saveFM(FMemberModel fm);
    void update(FMemberModel fm);
    void deleteFM(String id);
}
