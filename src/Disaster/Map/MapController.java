package Disaster.Map;

import Disaster.DisasterPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapController {

    MapPanel mpp;
    DisasterPanel dp;
    MapService service;

    public MapController(MapPanel mpp, DisasterPanel dp) {
        this.mpp = mpp;
        this.dp = dp;
        service = new MapServiceImpl(mpp, dp);
        this.mpp.allListener(new Action());
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == mpp.saveBtn) {
                service.saveLoc();
            }
        }

    }
}
