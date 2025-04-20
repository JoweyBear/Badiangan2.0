package Brgy;

import java.sql.ResultSet;

public interface BrgyDAO {

    ResultSet getAllBrgy();
    void saveBrgy(String name);
    void updateBrgy(int id, String name);
    void deleteBrgy(String id);
}
