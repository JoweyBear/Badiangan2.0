package Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class AdminController {

    AdminPanel ap;
    AdminService service;

    public AdminController(AdminPanel ap) {
        this.ap = ap;
        this.ap.allListener(new OpenAddAdminDialog(),
                new SaveAdminClass(), new EditAdminClass(), new UpdateAdminClass(),
                new DeleteAdminClass(), new ResetFieldsClass(), new CloseDialogClass(),
                new MyPopUpMenu(), new MyPopUpMenu(),
                new ViewAdminClass(), new EditAdminClass(), new OpenAddAdminDialog(),
                new DeleteAdminClass());
        service = new AdminServiceImpl(ap);
    }

    class OpenAddAdminDialog implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.openAddAdminDialog();
        }
    }

    class SaveAdminClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.save();
        }
    }

    class EditAdminClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.editView();
        }

    }

    class UpdateAdminClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.update();
        }

    }

    class DeleteAdminClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.delete();
        }

    }

    class ResetFieldsClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.clearFields();
        }

    }

    class CloseDialogClass extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            service.clearFields();
        }
    }

    class MyPopUpMenu extends MouseAdapter implements PopupMenuListener {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.popupMenu(e);
        }

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.popupVisible(e);
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }

    class ViewAdminClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.viewAdmin();
        }

    }

}
