package Crop.Harvest;

import Util.MyPrinter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class HarvestController {

    HarvestPanel hp;
    JPanel mp;
    HarvestService service;

    public HarvestController(HarvestPanel hp, JPanel mp) {
        this.hp = hp;
        this.mp = mp;
        service = new HarvestServiceImpl(hp, mp);
        this.hp.allListener(new HAction(), new HPopUp(), new HMouse());
    }

    class HAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == hp.addBtn) {
                service.addHarvest();
            }
            if (e.getSource() == hp.editBtn) {
                service.editHarvest();
            }
            if (e.getSource() == hp.okBtn) {
                service.updateHarvest();
            }
            if (e.getSource() == hp.deleteBtn) {
                service.deleteHarvest();
            }
            if (e.getSource() == hp.cancelBtn) {
                hp.editDialog.dispose();
            }
            if (e.getSource() == hp.viewMenuItem) {
                service.viewHarvest();
            }
            if (e.getSource() == hp.editMenuItem) {
                service.editHarvest();
            }
            if (e.getSource() == hp.addMenuItem) {
                service.addHarvest();
            }
            if (e.getSource() == hp.deleteMenuItem) {
                service.deleteHarvest();
            }
            if (e.getSource() == hp.printBtn) {
                MyPrinter.printNow(hp.table, "Crop/Tree Harvest");
            }
        }
    }

    class HMouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.harvestMouse(e);
        }
    }

    class HPopUp implements PopupMenuListener {

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.harvestPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }

    }
}
