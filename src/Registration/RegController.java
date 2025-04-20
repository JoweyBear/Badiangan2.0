package Registration;

import Util.MyPrinter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class RegController {

    RegPanel rp;
    RegService service;

    public RegController(RegPanel rp) {
        this.rp = rp;
        service = new RegServiceImpl(rp);
        this.rp.allListener(new Action(), new PopUp(), new Mouse());

    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == rp.viewMenuItem) {
                service.viewReg();
            }
            if (e.getSource() == rp.deleteMenuItem) {
                service.deleteReg();
            }
            if (e.getSource() == rp.deleteBtn) {
                service.deleteReg();
            }
            if (e.getSource() == rp.printBtn) {
                MyPrinter.printNow(rp.table, "Registration");
            }
        }

    }

    class Mouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.regMouse(e);
        }
    }

    class PopUp implements PopupMenuListener {

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.regPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }

    }
}
