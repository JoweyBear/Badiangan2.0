package EvacSite.Map;

import EvacSite.EvacPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvacMapController {

    EvacPanel ep;
    EvacMapPanel mpp;
    EvacMapService service;

    public EvacMapController(EvacMapPanel mpp, EvacPanel ep) {
        this.mpp = mpp;
        this.ep = ep;
        service = new EvacMapServiceImpl(mpp, ep);
        this.mpp.allListener(new Action());
        service.showMap();
    }

    public EvacMapController(EvacMapPanel mpp, EvacPanel ep, double locLat, double locLong) {
        this.mpp = mpp;
        this.ep = ep;
        service = new EvacMapServiceImpl(mpp, ep, locLat, locLong);
        this.mpp.allListener(new Action());
        service.showMap();
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
