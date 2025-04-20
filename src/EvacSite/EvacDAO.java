package EvacSite;

import java.sql.ResultSet;

public interface EvacDAO {
    ResultSet getAllEvac();
    void saveEvac(EvacModel evac);
    void updateEvac(EvacModel evac);
    void deleteEvac(String id);
}
