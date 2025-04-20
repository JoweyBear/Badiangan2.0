package Brgy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class BrgyController {

    BrgyPanel brgyp;
    BrgyService service;

    public BrgyController(BrgyPanel brgyp) {
        this.brgyp = brgyp;
        service = new BrgyServiceImpl(brgyp);
        this.brgyp.allListener(new Action(), new PopUp(), new Mouse());
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == brgyp.addBtn) {
                service.addBrgy();
            }
            if (e.getSource() == brgyp.okBtn) {
                service.saveBrgy();
            }
            if (e.getSource() == brgyp.okBtn1) {
                service.updateBrgy();
            }
            if (e.getSource() == brgyp.editBtn) {
                service.editBrgy();
            }
            if (e.getSource() == brgyp.deleteBtn) {
                service.deleteBrgy();
            }
            if (e.getSource() == brgyp.cancelBtn) {
                brgyp.addDialog.dispose();
            }
            if (e.getSource() == brgyp.cancelBtn1) {
                brgyp.editDialog.dispose();
            }
            if (e.getSource() == brgyp.viewMenuItem) {
                service.viewBrgy();
            }
            if (e.getSource() == brgyp.editMenuItem) {
                service.editBrgy();
            }
            if (e.getSource() == brgyp.addMenuItem) {
                service.addBrgy();
            }
            if (e.getSource() == brgyp.deleteMenuItem) {
                service.deleteBrgy();
            }
        }
    
    }
        class Mouse extends MouseAdapter {

            @Override
            public void mouseReleased(MouseEvent e) {
                service.mouseAction(e);
            }
        }


        class PopUp implements PopupMenuListener {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                service.popup();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }

        }
}


    
