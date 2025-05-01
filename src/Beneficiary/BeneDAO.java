package Beneficiary;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public interface BeneDAO {
//    ResultSet getAllBene();
//    ResultSet getAllFarmer();
    DefaultTableModel getAllBene();
    DefaultTableModel getAllFarmer();
    int getIDofLatestBene();
    void saveBene(BeneModel bene);
    void updateBene(BeneModel bene);
    void deleteBene(String beneID);
}
