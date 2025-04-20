package Admin;

import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;

public interface AdminService {
    void clearFields();
    void save();
    void update();
    void delete();
    void editView();
    void openAddAdminDialog();
    void viewAdmin();
    void popupMenu(MouseEvent e);
    void popupVisible(PopupMenuEvent e);

}
