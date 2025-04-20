package LiveStock.Disposal;

import java.awt.event.MouseEvent;

public interface DisposalService {
    void addDisposal();
    void clearFields();
    void deleteDisposal();
    void editDisposal();
    void displayAllDisposal();
    void updateDisposal();
    void viewDisposal();
    void disMouse(MouseEvent e);
    void disPopup();
}
