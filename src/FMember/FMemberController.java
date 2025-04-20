package FMember;

import Util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class FMemberController {

    FMemberPanel fmp;
    FMemberService service;

    public FMemberController(FMemberPanel fmp) {
        this.fmp = fmp;
        service = new FMemberServiceImpl(fmp);
        this.fmp.allListener(//Button Classes
                new OpenAddFMDialog(), new SaveFMClass(), new EditFMClass(),
                new UpdateFMClass(), new DeleteFMClass(), new CloseDialogClass(), 
                new CloseDialogClass(), new MyPopUpMenu(),
                
                //PopUpMenu Classes
                new MyPopUpMenu(), new ViewFMClass(), new EditFMClass(), 
                new OpenAddFMDialog(), new DeleteFMClass(), 
                
                //Auto-generate Beneficiary in ComboBox
                new BeneCBBHandler(fmp.addBeneFMCB),
                new BeneCBBHandler(fmp.editBeneFMCB)
        );
    }

    class CloseDialogClass extends WindowAdapter implements ActionListener {

        @Override
        public void windowClosing(WindowEvent e) {
            service.clearFields();
            fmp.addFMDialog.dispose();
            fmp.editFMDialog.dispose();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            service.clearFields();
            fmp.addFMDialog.dispose();
            fmp.editFMDialog.dispose();
        }
    }

    class DeleteFMClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.deleteFM();
        }
    }

    class DialogDisposal extends WindowAdapter {

    }

    class EditFMClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.editFM();
        }
    }

    class MyPopUpMenu extends MouseAdapter implements PopupMenuListener {

        @Override
        public void mouseReleased(MouseEvent e) {
            service.fmMouse(e);
        }

        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            service.fmPopup();
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {

        }
    }

    class OpenAddFMDialog implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.openDialog();
        }
    }

    class SaveFMClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.saveFM();
        }

    }

    class UpdateFMClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.updateFM();
        }
    }

    class ViewFMClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.viewFM();
        }

    }
}
