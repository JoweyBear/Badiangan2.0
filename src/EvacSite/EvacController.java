package EvacSite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class EvacController {

    EvacPanel ep;
    EvacService service;

    public EvacController(EvacPanel ep) {
        this.ep = ep;
        service = new EvacServiceImpl(ep);
        this.ep.allListener(new Action(), new PopUp(), new Mouse());
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ep.addBtn) {
                service.addEvac();
            }
            if (e.getSource() == ep.okBtn) {
                service.saveEvac();
            }
            if (e.getSource() == ep.okBtn1) {
                service.updateEvac();
            }
            if (e.getSource() == ep.editBtn) {
                service.editEvac();
            }
            if (e.getSource() == ep.deleteBtn) {
                service.deleteEvac();
            }
            if (e.getSource() == ep.cancelBtn) {
                ep.addDialog.dispose();
            }
            if (e.getSource() == ep.cancelBtn1) {
                ep.editDialog.dispose();
            }
            if (e.getSource() == ep.viewMenuItem) {
                service.viewEvac();
            }
            if (e.getSource() == ep.editMenuItem) {
                service.editEvac();
            }
            if (e.getSource() == ep.addMenuItem) {
                service.addEvac();
            }
            if (e.getSource() == ep.deleteMenuItem) {
                service.deleteEvac();
            }
            if (e.getSource() == ep.getLocBtn) {
                service.openMapToGetLoc();
            }
            if (e.getSource() == ep.getLocBtn1) {
                service.openMapToEditLoc();
            }
        }
    }

    class Mouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.evacMouse(e);
        }
    }

    class PopUp implements PopupMenuListener {

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.evacPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }

    }
}
