package Disaster;

import java.awt.event.MouseEvent;

public interface DisasterService {

    void addDisaster();
    void clearFields();
    void deleteDisaster();
    void displayAllDisasters();
    void openMapToGetLoc();
    void editDisaster();
    void saveDisaster();
    void updateDisaster();
    void viewDisaster();
    void viewInMap();
    void disasterMouse(MouseEvent e);
    void disasterPopup();
}
