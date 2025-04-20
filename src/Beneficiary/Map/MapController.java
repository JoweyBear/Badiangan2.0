package Beneficiary.Map;

import Beneficiary.BenePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapController {

    BenePanel bp;
    MapPanel mpp;
    MapService service;

    public MapController(MapPanel mpp, BenePanel bp) {
        this.mpp = mpp;
        this.bp = bp;
        service = new MapServiceImpl(mpp, bp);
        this.mpp.allListener(new Action(), new Mouse());
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == mpp.saveBtn) {
                service.saveLoc();
            }
        }
    }

    class Mouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
}
