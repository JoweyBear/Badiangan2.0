package Disaster;

import java.sql.ResultSet;

public interface DisasterDAO {
    ResultSet getAllDisasters();
    void saveDisasters(DisasterModel dsstr);
    void updateDisasters(DisasterModel dsstr);
    void deleteDisasters(String id);
}
