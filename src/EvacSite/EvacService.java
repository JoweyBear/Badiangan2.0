package EvacSite;

import java.awt.event.MouseEvent;

public interface EvacService {
    void displayAllEvac();
    void addEvac();
    void clearFields();
    void deleteEvac();
    void openMapToGetLoc();
    void openMapToEditLoc();
    void editEvac();
    void saveEvac();
    void updateEvac();
    void viewEvac();
    void evacMouse(MouseEvent e);
    void evacPopup();
}
