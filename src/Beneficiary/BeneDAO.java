package Beneficiary;

import java.sql.ResultSet;

public interface BeneDAO {
    ResultSet getAllBene();
    ResultSet getAllFarmer();
    int getIDofLatestBene();
    void saveBene(BeneModel bene);
    void updateBene(BeneModel bene);
    void deleteBene(String beneID);
}
