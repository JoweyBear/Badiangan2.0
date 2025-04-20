package Crop.Harvest;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface HarvestDAO {

    ResultSet getAllHarvest();
    void saveHarvest(HarvestModel hrvst);
    void updateHarvest(HarvestModel hrvst);
    void updateCropHarvested(int id);
    void deleteHarvest(String id);
    ArrayList<Integer> getCropsID();
}
