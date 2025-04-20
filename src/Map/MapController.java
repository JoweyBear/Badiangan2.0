package Map;

import Main.MainFrame;
import Util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapController {

    MapPanel mpp;
    MainFrame mf;
    MapService service;

    public MapController(MapPanel mpp, MainFrame mf) {
        this.mpp = mpp;
        this.mf = mf;

        this.mpp.allListener(new Action(), new DisasterCBBHandler(mpp.disasterCBB));
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == mpp.viewBtn) {
                service.viewMap();
            }
            if (e.getSource() == mpp.evacBtn) {
                service.evacBtn();
            }
            if (e.getSource() == mpp.viewEvacBtn) {
                service.viewEvac();
            }
        }
    }

}
