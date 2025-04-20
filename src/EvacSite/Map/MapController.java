package EvacSite.Map;

import EvacSite.EvacPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapController {

    EvacPanel ep;
    MapPanel mpp;
    MapService service;

    public MapController(MapPanel mpp, EvacPanel ep) {
        this.mpp = mpp;
        this.ep = ep;
        service = new MapServiceImpl(mpp, ep);
        this.mpp.allListener(new Action());
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == mpp.saveBtn) {
                service.saveLoc();
            }
            if (e.getSource() == mpp.saveBtn) {
                service.saveLoc();
            }
        }

    }
}
