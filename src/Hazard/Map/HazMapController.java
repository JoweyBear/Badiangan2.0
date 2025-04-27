package Hazard.Map;

import Hazard.HazardPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HazMapController {

    HazardPanel hazp;
    HazMapPanel mpp;
    HazMapService service;

    public HazMapController(HazMapPanel mpp, HazardPanel hazp) {
        this.mpp = mpp;
        this.hazp = hazp;
        service = new HazMapServiceImpl(mpp, hazp);
        this.mpp.allListener(new Action());
        service.showMap();
    }

    public HazMapController(HazMapPanel mpp, HazardPanel hazp, double locLat, double locLong) {
        this.mpp = mpp;
        this.hazp = hazp;
        service = new HazMapServiceImpl(mpp, hazp, locLat, locLong);
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
