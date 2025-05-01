package Far;

import Util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class FarController {

    FarPanel fp;
    FarService service;

    public FarController(FarPanel fp) {
        this.fp = fp;
        service = new FarServiceImpl(fp);
        this.fp.allListener(new Action(), new PopUp(), new Mouse(),
                new BeneCBBHandler(fp.beneCBB),
                new DisasterCBBHandler(fp.disCBB),
                new BeneCBBHandler(fp.beneCBB1),
                new DisasterCBBHandler(fp.disCBB1));
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == fp.addBtn) {
                service.addFar();
            }
            if (e.getSource() == fp.okBtn) {
                service.saveFar();
            }
            if (e.getSource() == fp.okBtn1) {
                service.updateFar();
            }
            if (e.getSource() == fp.editBtn) {
                service.editFar();
            }
            if (e.getSource() == fp.deleteBtn) {
                service.deleteFar();
            }
            if (e.getSource() == fp.cancelBtn) {
                fp.addDialog.dispose();
            }
            if (e.getSource() == fp.cancelBtn1) {
                fp.editDialog.dispose();
            }
            if (e.getSource() == fp.viewMenuItem) {
                service.viewFar();
            }
            if (e.getSource() == fp.editMenuItem) {
                service.editFar();
            }
            if (e.getSource() == fp.addMenuItem) {
                service.addFar();
            }
            if (e.getSource() == fp.deleteMenuItem) {
                service.deleteFar();
            }
            if (e.getSource() == fp.printBtn) {
                MyPrinter.printNow(fp.table, "Family Assistance Record");
            }
        }
    }

    class Mouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.farMouse(e);
        }
    }

    class PopUp implements PopupMenuListener {

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.farPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }

    }
}
