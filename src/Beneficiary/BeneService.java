package Beneficiary;

import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import javax.swing.event.PopupMenuEvent;

public interface BeneService {
    void clearAddBeneFields();
    void clearAddCropFields();
    void clearAddLSFields();
    void clearAddMemberFields();
    void clearEditBeneFields();
    void clearEditCropFields();
    void clearEditLSFields();
    void clearEditMemberFields();
    void deleteBene();
    void deleteCrop();
    void deleteLS();
    void deleteMember();
    void displayAllBene();
    void editBene();
    void editCrop();
    void editLS();
    void editMember();
    void initJDateChooser();
    void initRBG();
    void okAddCrop();
    void okAddLS();
    void okAddMember();
    void okEditCrop();
    void okEditLS();
    void okEditMember();
    void openAddBeneDialog();
    void openAddCropDialog();
    void openAddLSDialog();
    void openAddMemberDialog();
    void openMapToGetLoc();
    void saveBeneToDB();
    void saveCropsToDB();
    void saveFMemberToDB();
    void saveLSToDB();
    void viewBeneInMap();
    void editBeneLoc();
    void updateBene();
    void viewBene();
    void viewCrop();
    void viewLS();
    void viewMember();
    void openWalkinDialog();
    void saveWalkinToDB();
    
    void benePopUpMenu(MouseEvent e);
    void benePopupVisible(PopupMenuEvent e);
    
    void cropPopupMenu(MouseEvent e);
    void cropPopupVisible(PopupMenuEvent e);
    
    void farmerStateChanged(ItemEvent e);
    void liveStockPopupMenu(MouseEvent e);
    void liveStockPopupVisible(PopupMenuEvent e);
    void memberPopupMenu(MouseEvent e);
    void memberPopupVisible(PopupMenuEvent e);
    
    
}
