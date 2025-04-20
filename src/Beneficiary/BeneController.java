package Beneficiary;

import Main.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class BeneController {
    
    BenePanel bp;
    MainFrame mf;
    BeneService service;
    
    public BeneController(BenePanel bp, MainFrame mf) {
        this.bp = bp;
        this.mf = mf;
        service = new BeneServiceImpl(bp, mf);
        this.bp.allListener(
                new Action(),
                //Bene Classes
                new BenePopUpMenu(),
                new BenePopUpMenu(),
                //Members Classes
                new MemberPopUpMenu(),
                new MemberPopUpMenu(),
                //Crop Classes
                new CropPopUpMenu(),
                new CropPopUpMenu(),
                //Livestock Classes
                new LivestockPopUpMenu(),
                new LivestockPopUpMenu(),
                //farmer Class
                new FarmerCHBClass()
        );
    }
    
    class Action implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            //Bene
            if (e.getSource() == bp.viewBeneMenuItem) {
                service.viewBene();
            }
            if (e.getSource() == bp.editBeneMenuItem) {
                service.clearEditBeneFields();
                service.editBene();
            }
            if (e.getSource() == bp.addBeneMenuItem) {
                service.clearAddBeneFields();
                service.openAddBeneDialog();
            }
            if (e.getSource() == bp.deleteBeneMenuItem) {
                service.deleteBene();
            }
            if (e.getSource() == bp.viewMapMenuItem) {
                service.viewBeneInMap();
            }
            if (e.getSource() == bp.addBeneBtn) {
                service.openAddBeneDialog();
            }
            if (e.getSource() == bp.saveBeneBtn) {
                service.saveBeneToDB();
            }
            if (e.getSource() == bp.cancelBeneBtn) {
                bp.addBeneDialog.dispose();
            }
            if (e.getSource() == bp.editBeneBtn) {
                service.editBene();
            }
            if (e.getSource() == bp.updateBeneBtn) {
                service.updateBene();
            }
            if (e.getSource() == bp.cancelBeneBtn1) {
                bp.editBeneDialog.dispose();
            }
            if (e.getSource() == bp.deleteBeneBtn) {
                service.deleteBene();
            }
            if (e.getSource() == bp.locBtn) {
                service.openMapToGetLoc();
            }
            if (e.getSource() == bp.locBtn1) {
                service.openMapToGetLoc();
            }
            if (e.getSource() == bp.walkinMenuItem) {
                service.openWalkinDialog();
            }
            if (e.getSource() == bp.okWalkinBtn) {
                service.saveWalkinToDB();
            }

            //Members
            if (e.getSource() == bp.viewMemberMenuItem) {
                service.viewMember();
            }
            if (e.getSource() == bp.editMemberMenuItem) {
                service.clearEditMemberFields();
                service.editMember();
            }
            if (e.getSource() == bp.addMemberMenuItem) {
                service.clearAddMemberFields();
                service.openAddMemberDialog();
            }
            if (e.getSource() == bp.deleteMemberMenuItem) {
                service.deleteMember();
            }
            if (e.getSource() == bp.okAddMemberBtn) {
                service.okAddMember();
            }
            if (e.getSource() == bp.cancelAddMemberBtn) {
                bp.addMemberDialog.dispose();
            }
            if (e.getSource() == bp.okEditMemberBtn) {
                service.okEditMember();
            }
            if (e.getSource() == bp.cancelEditMemberBtn) {
                bp.editMemberDialog.dispose();
            }

            //Crops
            if (e.getSource() == bp.viewCropMenuItem) {
                service.viewCrop();
            }
            if (e.getSource() == bp.editCropMenuItem) {
                service.clearEditCropFields();
                service.editCrop();
            }
            if (e.getSource() == bp.addCropMenuItem) {
                service.clearAddCropFields();
                service.openAddCropDialog();
            }
            if (e.getSource() == bp.deleteCropMenuItem) {
                service.deleteCrop();
            }
            if (e.getSource() == bp.okAddCropBtn) {
                service.okAddCrop();
            }
            if (e.getSource() == bp.cancelAddCropBtn) {
                bp.addCropDialog.dispose();
            }
            if (e.getSource() == bp.okEditCropBtn) {
                service.okEditCrop();
            }
            if (e.getSource() == bp.cancelEditCropBtn) {
                bp.editCropDialog.dispose();
            }

            //Livestock
            if (e.getSource() == bp.viewLSMenuItem) {
                service.viewLS();
            }
            if (e.getSource() == bp.editLSMenuItem) {
                service.clearEditLSFields();
                service.editLS();
            }
            if (e.getSource() == bp.addLSMenuItem) {
                service.clearAddLSFields();
                service.openAddLSDialog();
            }
            if (e.getSource() == bp.deleteLSMenuItem) {
                service.deleteLS();
            }
            if (e.getSource() == bp.okAddLSBtn) {
                service.okAddLS();
            }
            if (e.getSource() == bp.cancelAddLSBtn) {
                bp.addLSDialog.dispose();
            }
            if (e.getSource() == bp.okEditLSBtn) {
                service.okEditLS();
            }
            if (e.getSource() == bp.cancelEditLSBtn) {
                bp.editLSDialog.dispose();
            }
        }
    }
    
    class BenePopUpMenu extends MouseAdapter implements PopupMenuListener {
        
        @Override
        public void mouseReleased(MouseEvent e) {
            service.benePopUpMenu(e);
        }
        
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.benePopupVisible(e);
        }
        
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    class CropPopUpMenu extends MouseAdapter implements PopupMenuListener {
        
        @Override
        public void mouseReleased(MouseEvent e) {
            service.cropPopupMenu(e);
        }
        
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.cropPopupVisible(e);
        }
        
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    class FarmerCHBClass implements ItemListener {
        
        @Override
        public void itemStateChanged(ItemEvent e) {
            service.farmerStateChanged(e);
        }
    }
    
    class LivestockPopUpMenu extends MouseAdapter implements PopupMenuListener {
        
        @Override
        public void mouseReleased(MouseEvent e) {
            service.liveStockPopupMenu(e);
        }
        
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.liveStoclPopupVisible(e);
        }
        
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }
        
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
    }

    class MemberPopUpMenu extends MouseAdapter implements PopupMenuListener {
        
        @Override
        public void mouseReleased(MouseEvent e) {
            service.memberPopupMenu(e);
        }

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.memberPopupVisible(e);
        }
        
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }
        
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
        
    }
}
