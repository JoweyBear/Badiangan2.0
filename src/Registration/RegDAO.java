package Registration;

import java.sql.ResultSet;

public interface RegDAO {
    ResultSet getAllReg();
    void saveReg(RegModel reg);
    void deleteReg(String id);
}
