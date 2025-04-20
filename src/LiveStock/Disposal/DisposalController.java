package LiveStock.Disposal;

import Util.MyPrinter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class DisposalController {
    
    DisposalPanel dp;
    JPanel mp;
    DisposalService service;
    
    public DisposalController(DisposalPanel dp, JPanel mp) {
        this.dp = dp;
        this.mp = mp;
        service = new DisposalServiceImpl(dp, mp);
        this.dp.allListener(new Action(), new PopUp(), new Mouse());
    }
    
    class Action implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == dp.addBtn) {
                service.addDisposal();
            }
            if (e.getSource() == dp.editBtn) {
                service.editDisposal();
            }
            if (e.getSource() == dp.okBtn) {
                service.updateDisposal();
            }
            if (e.getSource() == dp.deleteBtn) {
                service.deleteDisposal();
            }
            if (e.getSource() == dp.cancelBtn) {
                dp.editDialog.dispose();
            }
            if (e.getSource() == dp.viewMenuItem) {
                service.viewDisposal();
            }
            if (e.getSource() == dp.editMenuItem) {
                service.editDisposal();
            }
            if (e.getSource() == dp.addMenuItem) {
                service.addDisposal();
            }
            if (e.getSource() == dp.deleteMenuItem) {
                service.deleteDisposal();
            }
            if (e.getSource() == dp.printBtn) {
                MyPrinter.printNow(dp.table, "Livestock Disposal");
            }
        }
    }
    
    class Mouse extends MouseAdapter {
        
        @Override
        public void mouseReleased(MouseEvent e) {
            service.disMouse(e);
        }
    }
    
    class PopUp implements PopupMenuListener {
        
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.disPopup();
        }
        
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }
        
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
        
    }
}
