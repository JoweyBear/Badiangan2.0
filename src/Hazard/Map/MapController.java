package Hazard.Map;

import Hazard.HazardPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapController {

    HazardPanel hazp;
    MapPanel mpp;
    MapService service;

    public MapController(MapPanel mpp, HazardPanel hazp) {
        this.mpp = mpp;
        this.hazp = hazp;
        service = new MapServiceImpl(mpp, hazp);
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
