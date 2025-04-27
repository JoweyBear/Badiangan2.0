package Disaster.Map;

import Disaster.DisasterPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisMapController {

    DisMapPanel mpp;
    DisasterPanel dp;
    DisMapService service;

    public DisMapController(DisMapPanel mpp, DisasterPanel dp) {
        this.mpp = mpp;
        this.dp = dp;
        service = new DisMapServiceImpl(mpp, dp);
        this.mpp.allListener(new Action());
        service.showMap();
    }

    public DisMapController(DisMapPanel mpp, DisasterPanel dp, double lt, double lg, double rad) {
        this.mpp = mpp;
        this.dp = dp;
        service = new DisMapServiceImpl(mpp, dp, lt, lg, rad);
        this.mpp.allListener(new Action());
        service.showMap();
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
