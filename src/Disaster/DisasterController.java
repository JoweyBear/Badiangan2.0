package Disaster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class DisasterController {

    DisasterPanel disp;
    DisasterService service;

    public DisasterController(DisasterPanel disp) {
        this.disp = disp;
        service = new DisasterServiceImpl(disp);
        this.disp.allListener(new Action(), new PopUp(), new Mouse());
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == disp.addBtn) {
                service.addDisaster();
            }
            if (e.getSource() == disp.okBtn) {
                service.saveDisaster();
            }
            if (e.getSource() == disp.editBtn) {
                service.editDisaster();
            }
            if (e.getSource() == disp.deleteBtn) {
                service.deleteDisaster();
            }
            if (e.getSource() == disp.cancelBtn) {
                disp.addDialog.dispose();
            }
            if (e.getSource() == disp.cancelBtn1) {
                disp.editDialog.dispose();
            }
            if (e.getSource() == disp.viewMenuItem) {
                service.viewDisaster();
            }
            if (e.getSource() == disp.editMenuItem) {
                service.editDisaster();
            }
            if (e.getSource() == disp.addMenuItem) {
                service.addDisaster();
            }
            if (e.getSource() == disp.deleteMenuItem) {
                service.deleteDisaster();
            }
            if (e.getSource() == disp.getLocBtn) {
                service.openMapToGetLoc();
            }
            if (e.getSource() == disp.getLocBtn1) {
                service.openMapToGetLoc();
            }
            if (e.getSource() == disp.okBtn1) {
                service.updateDisaster();
            }
            if (e.getSource() == disp.viewInMapMenuItem) {
                service.viewInMap();
            }
        }
    }

    class Mouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.disasterMouse(e);
        }
    }

    class PopUp implements PopupMenuListener {

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.disasterPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }

    }
}
