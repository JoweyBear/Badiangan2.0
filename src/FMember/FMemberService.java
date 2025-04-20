package FMember;

import java.awt.event.MouseEvent;

public interface FMemberService {
    void clearFields();
    void displayAllFM();
    void initRBG();
    void deleteFM();
    void editFM();
    void fmMouse(MouseEvent e);
    void fmPopup();
    void openDialog();
    void saveFM();
    void updateFM();
    void viewFM();
}
