package Far;

import java.sql.ResultSet;

public interface FarDAO {
    ResultSet getAllFar();
    void saveFar(FarModel far);
    void update(FarModel far);
    void deleteFar(String id);

}
