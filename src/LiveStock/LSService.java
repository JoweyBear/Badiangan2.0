package LiveStock;

import java.awt.event.MouseEvent;

public interface LSService {
    void addLS();
    void clearFields();
    void deleteLS();
    void displayAllLS();
    void editLS();
    void saveLS();
    void updateLS();
    void viewLS();
    void addDisposal();
    void saveDisposal();
    void lsMouse(MouseEvent e);
    void lsPopup();
}
