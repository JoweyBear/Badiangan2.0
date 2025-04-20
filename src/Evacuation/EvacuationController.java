package Evacuation;

import Util.MyPrinter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class EvacuationController {

    EvacuationPanel evacp;
    EvacuationService service;

    public EvacuationController(EvacuationPanel evacp) {
        this.evacp = evacp;
        service = new EvacuationServiceImpl(evacp);
        this.evacp.allListener(new Action(), new PopUp(), new Mouse());
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == evacp.printBtn) {
                MyPrinter.printNow(evacp.table, "Evacuation");
            }
            if (e.getSource() == evacp.viewMenuItem) {
                service.displayEvacInfo();
            }
        }
    }

    class Mouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.evacuationMouse(e);
        }
    }

    class PopUp implements PopupMenuListener {

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.evacuationPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }

    }
}
