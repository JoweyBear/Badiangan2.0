package Crop;

import java.awt.event.MouseEvent;

public interface CropService {
    void addCrop();
    void clearFields();
    void deleteCrop();
    void displayAllCrops();
    void editCrop();
    void harvestCrop();
    void saveCrop();
    void saveHarvest();
    void updateCrop();
    void viewCrop();
    void cropMouse(MouseEvent e);
    void cropPopup();
}
