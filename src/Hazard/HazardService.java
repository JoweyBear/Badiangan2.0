package Hazard;

import java.awt.event.MouseEvent;

public interface HazardService {
    void addDisaster();
    void clearFields();
    void deleteHazard();
    void displayAllHazard();
    void openMapToGetLoc();
    void editHazard();
    void saveHazard();
    void updateHazard();
    void viewHazard();
    void viewInMap();
    void hazardMouse(MouseEvent e);
    void hazardPopup();
}
