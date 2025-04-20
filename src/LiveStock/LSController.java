package LiveStock;

import Util.BeneCBBHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class LSController {
    
    LSPanel lsp;
    LSService service;
    
    public LSController(LSPanel lsp) {
        this.lsp = lsp;
        service = new LSServiceImpl(lsp);
        this.lsp.allListener(new LSAction(), new LSPopUp(), new LSMouse(),
                new BeneCBBHandler(lsp.beneCB), new BeneCBBHandler(lsp.beneCB1));
        
    }
    
    class LSAction implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == lsp.addBtn) {
                service.addLS();
            }
            if (e.getSource() == lsp.saveBtn) {
                service.saveLS();
            }
            if (e.getSource() == lsp.editBtn) {
                service.editLS();
            }
            if (e.getSource() == lsp.updateBtn) {
                service.updateLS();
            }
            if (e.getSource() == lsp.deleteBtn) {
                service.deleteLS();
            }
            if (e.getSource() == lsp.cancelBtn) {
                lsp.addDialog.dispose();
            }
            if (e.getSource() == lsp.cancelBtn1) {
                lsp.editDialog.dispose();
            }
            if (e.getSource() == lsp.viewMenuItem) {
                service.viewLS();
            }
            if (e.getSource() == lsp.editMenuItem) {
                service.editLS();
            }
            if (e.getSource() == lsp.addMenuItem) {
                service.addLS();
            }
            if (e.getSource() == lsp.deleteMenuItem) {
                service.deleteLS();
            }
            if (e.getSource() == lsp.disposeMenuItem) {
                service.addDisposal();
            }
            if (e.getSource() == lsp.okBtn) {
                service.saveDisposal();
            }
            if (e.getSource() == lsp.cancelBtn2) {
                lsp.disposalDialog.dispose();
            }
        }
        
    }
    
    class LSMouse extends MouseAdapter {
        
        @Override
        public void mouseReleased(MouseEvent e) {
            service.lsMouse(e);
        }
    }
    
    class LSPopUp implements PopupMenuListener {
        
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.lsPopup();
        }
        
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }
        
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
        
    }
}
