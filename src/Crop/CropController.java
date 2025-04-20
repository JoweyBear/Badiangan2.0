package Crop;

import Util.BeneCBBHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class CropController {

    CropPanel cp;
    CropService service;

    public CropController(CropPanel cp) {
        this.cp = cp;
        service = new CropServiceImpl(cp);
        this.cp.allListener(new CCAction(), new CCPopUp(), new CCMouse(),
                new BeneCBBHandler(cp.beneCB), new BeneCBBHandler(cp.beneCB1));
    }

    class CCAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cp.addBtn) {
                service.addCrop();
            }
            if (e.getSource() == cp.saveBtn) {
                service.saveCrop();
            }
            if (e.getSource() == cp.editBtn) {
                service.editCrop();
            }
            if (e.getSource() == cp.updateBtn) {
                service.updateCrop();
            }
            if (e.getSource() == cp.deleteBtn) {
                service.deleteCrop();
            }
            if (e.getSource() == cp.cancelBtn) {
                cp.addDialog.dispose();
            }
            if (e.getSource() == cp.cancelBtn1) {
                cp.editDialog.dispose();
            }
            if (e.getSource() == cp.cancelHarvestBtn) {
                cp.harvestDialog.dispose();
            }
            if (e.getSource() == cp.viewMenuItem) {
                service.viewCrop();
            }
            if (e.getSource() == cp.editMenuItem) {
                service.editCrop();
            }
            if (e.getSource() == cp.addMenuItem) {
                service.addCrop();
            }
            if (e.getSource() == cp.deleteMenuItem) {
                service.deleteCrop();
            }
            if (e.getSource() == cp.harvestMenuItem) {
                service.harvestCrop();
            }
            if (e.getSource() == cp.cancelHarvestBtn) {
                cp.harvestDialog.dispose();
            }
            if (e.getSource() == cp.saveHarvestBtn) {
                service.saveHarvest();
            }
        }
    }

    class CCMouse extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.cropMouse(e);
        }
        }
    class CCPopUp implements PopupMenuListener{

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.cropPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
        
    }
    
    }
