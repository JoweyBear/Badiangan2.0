package Evacuation;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface EvacuationDAO {
    ResultSet getAllEvacuations();
    void saveEvacuation(EvacuationModel evac);
    void deleteExistingEvac(int dis_id);
    ArrayList<String> getEvacuationInfo(int dis_id);
    boolean isDisasterExist(int dis_id);
    int getNearestSite (int id, ArrayList<Integer> availableSites);
}
