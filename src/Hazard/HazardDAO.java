package Hazard;

import java.sql.ResultSet;

public interface HazardDAO {
    ResultSet getAllHazard();
    void saveHazard(HazardModel hzrd);
    void updatHazard(HazardModel hzrd);
    void deleteHazard(String id);
}
