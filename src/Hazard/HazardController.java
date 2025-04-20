package Hazard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class HazardController {

    HazardPanel hazp;
    HazardService service;

    public HazardController(HazardPanel hazp) {
        this.hazp = hazp;
        service = new HazardServiceImpl(hazp);
        this.hazp.allListener(new Action(), new PopUp(), new Mouse());

    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == hazp.addBtn) {
                service.addDisaster();
            }
            if (e.getSource() == hazp.okBtn) {
                service.saveHazard();
            }
            if (e.getSource() == hazp.editBtn) {
                service.editHazard();
            }
            if (e.getSource() == hazp.deleteBtn) {
                service.deleteHazard();
            }
            if (e.getSource() == hazp.cancelBtn) {
                hazp.addDialog.dispose();
            }
            if (e.getSource() == hazp.cancelBtn1) {
                hazp.editDialog.dispose();
            }
            if (e.getSource() == hazp.viewMenuItem) {
                service.viewHazard();
            }
            if (e.getSource() == hazp.editMenuItem) {
                service.editHazard();
            }
            if (e.getSource() == hazp.addMenuItem) {
                service.addDisaster();
            }
            if (e.getSource() == hazp.deleteMenuItem) {
                service.deleteHazard();
            }
            if (e.getSource() == hazp.getLocBtn) {
                service.openMapToGetLoc();
            }
            if (e.getSource() == hazp.getLocBtn1) {
                service.openMapToGetLoc();
            }
            if (e.getSource() == hazp.okBtn1) {
                service.updateHazard();
            }
            if (e.getSource() == hazp.viewInMapMenuItem) {
                service.viewInMap();
            }
        }
    }

    class Mouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.hazardMouse(e);
        }
    }

    class PopUp implements PopupMenuListener {

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.hazardPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }

    }
}
