package Crop;

import java.sql.ResultSet;

public interface CropDAO {
    ResultSet getAllCrop();
    void saveCrop(CropModel crop);
    void updateCrop(CropModel crop);
    void deleteCrop(String id);
    
}
