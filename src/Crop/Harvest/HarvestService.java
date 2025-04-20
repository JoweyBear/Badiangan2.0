package Crop.Harvest;

import java.awt.event.MouseEvent;

public interface HarvestService {

    void addHarvest();
    void clearFields();
    void deleteHarvest();
    void displayAllHarvest();
    void editHarvest();
    void viewHarvest();
    void updateHarvest();
    void harvestMouse(MouseEvent e);
    void harvestPopup();
}
