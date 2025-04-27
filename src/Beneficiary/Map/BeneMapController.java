package Beneficiary.Map;

import Beneficiary.BenePanel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;

public class BeneMapController {

    BeneMapPanel mpp;
    BenePanel bp;
    BeneMapService service;

    public BeneMapController(BeneMapPanel mpp, BenePanel bp) {
        this.mpp = mpp;
        this.bp = bp;
        System.out.println("Controller got BeneMapPanel: " + this.mpp.hashCode());
        System.out.println("saveBtn inside mpp: " + this.mpp.saveBtn.hashCode());
        service = new BeneMapServiceImpl(mpp, bp);
        mpp.saveBtn.addActionListener(e -> {
            System.out.println("Save button clicked!");
            service.saveLoc();
        });
        service.showMap();

//        this.mpp.allListener(new Action(), new Mouse());
    }

    public BeneMapController(BeneMapPanel mpp, BenePanel bp, double locLat, double locLong) {
        this.mpp = mpp;
        this.bp = bp;
        System.out.println("Controller got BeneMapPanel: " + this.mpp.hashCode());
        System.out.println("saveBtn inside mpp: " + this.mpp.saveBtn.hashCode());
        service = new BeneMapServiceImpl(mpp, bp, locLat, locLong);
        mpp.saveBtn.addActionListener(e -> {
            System.out.println("Save button clicked!");
            service.saveLoc();
            ((Window)SwingUtilities.getWindowAncestor(mpp)).dispose();
        });
        service.showMap();

//        this.mpp.allListener(new Action(), new Mouse());
    }

//    class Action implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == mpp.saveBtn) {
//                service.saveLoc();
//                System.out.println("SaveBtn clicked");
//            }
////            System.out.println("Action performed"); // put this directly
////            service.saveLoc();
////            System.out.println("SaveBtn clicked");
////            System.out.println("ActionPerformed called from saveBtn: " + e.getSource().hashCode());
//
//        }
//    }
//    class Mouse extends MouseAdapter {
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//            // Mouse events if needed
//        }
//    }
}
