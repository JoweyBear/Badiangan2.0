package Crop.Harvest;

import Crop.*;
import Util.*;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import net.proteanit.sql.DbUtils;

public class HarvestServiceImpl implements HarvestService {

    HarvestPanel hp;
    JPanel mp;
    HarvestDAO dao = new HarvestDAOImpl();

    public HarvestServiceImpl(HarvestPanel hp, JPanel mp) {
        this.hp = hp;
        this.mp = mp;

        displayAllHarvest();
    }

    @Override
    public void addHarvest() {
        JOptionPane.showMessageDialog(null, "Please add a harvest in Crop/Tree Panel",
                "Add Harvest", JOptionPane.INFORMATION_MESSAGE);
        HarvestPanel hp = new HarvestPanel();
        CropPanel cp = new CropPanel();
        new CropController(cp);
        CardLayout cl = (CardLayout) (mp.getLayout());
        mp.add(cp, "CropPanel");
        cl.show(mp, "CropPanel");
    }

    @Override
    public void clearFields() {
        hp.formTF.setText("");
        hp.qtySpin.setValue(0);
        hp.profitSpin.setValue(0);
        hp.dateDC.setDate(new Date());
        hp.remarksTA.setText("");
    }

    @Override
    public void deleteHarvest() {
        int dataRow = hp.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = hp.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(hp, "Would You Like to "
                    + "delete harvest: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteHarvest(id);
                displayAllHarvest();
            }
        } else {
            JOptionPane.showMessageDialog(hp, "Please select a harvest to delete.");
        }
    }

    @Override
    public void displayAllHarvest() {
        ResultSet rs = dao.getAllHarvest();
        hp.table.setModel(DbUtils.resultSetToTableModel(rs));
        // this is to disable editing in the jtable
        for (Class c : Arrays.asList(Object.class, Number.class, Boolean.class)) {
            TableCellEditor ce = hp.table.getDefaultEditor(c);
            if (ce instanceof DefaultCellEditor) {
                ((DefaultCellEditor) ce).setClickCountToStart(Integer.MAX_VALUE);
            }
        }
        new SearchModel(hp, hp.table, hp.searchTF, rs);
    }

    @Override
    public void editHarvest() {
        int dataRow = hp.table.getSelectedRow();
        if (dataRow >= 0) {
            hp.idHLbl.setText(hp.table.getValueAt(dataRow, 0).toString());
            hp.idCropCB.setModel(new DefaultComboBoxModel(dao.getCropsID().toArray()));
            hp.idCropCB.setSelectedItem(Integer.parseInt(hp.table.getValueAt(dataRow, 1).toString()));
            hp.formTF.setText(hp.table.getValueAt(dataRow, 2).toString());
            hp.qtySpin.setValue(Integer.parseInt(hp.table.getValueAt(dataRow, 3).toString()));
            hp.profitSpin.setValue(Double.parseDouble(hp.table.getValueAt(dataRow, 4).toString()));

            try {
                Date date;
                date = new SimpleDateFormat("yyyy-MM-dd").parse(hp.table.getValueAt(dataRow, 5).toString());
                hp.dateDC.setDate(date); //dob
            } catch (ParseException ex) {
                Logger.getLogger(HarvestServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            hp.remarksTA.setText(hp.table.getValueAt(dataRow, 6).toString());
            hp.editDialog.setTitle("Edit Harvest");
            hp.editDialog.setModal(true);

            hp.editDialog.pack();
            hp.editDialog.setLocationRelativeTo(hp);
            hp.editDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(hp, "Please select harvest to edit.");
        }
    }

    @Override
    public void viewHarvest() {
        int dataRow = hp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(hp,
                    "Harvest ID: " + (hp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Crop ID: " + (hp.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Container: " + (hp.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Qty: " + (hp.table.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Profit: " + (hp.table.getValueAt(dataRow, 4).toString()) + "\n"
                    + "Harvest Date: " + (hp.table.getValueAt(dataRow, 5).toString()) + "\n"
                    + "Remarks: " + (hp.table.getValueAt(dataRow, 6).toString()),
                    "Harvest Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(hp, "Please select harvest to view.");
        }
    }

    @Override
    public void updateHarvest() {
        HarvestModel hrvst = new HarvestModel();
        hrvst.setHarvestID(Integer.parseInt(hp.idHLbl.getText()));
        hrvst.setCropID(Alter.getInt(hp.idCropCB));
        hrvst.setContainer(hp.formTF.getText());
        hrvst.setQty(Alter.getInt(hp.qtySpin));
        hrvst.setProfit(Alter.getDouble(hp.profitSpin));
        hrvst.setDate(((JTextField) hp.dateDC.getDateEditor().getUiComponent()).getText());
        hrvst.setRemarks(hp.remarksTA.getText());
        dao.updateHarvest(hrvst);
        hp.editDialog.dispose();
        displayAllHarvest();
    }

    @Override
    public void harvestMouse(MouseEvent e) {
        int r = hp.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < hp.table.getRowCount()) {
            hp.table.setRowSelectionInterval(r, r);
        } else {
            hp.table.clearSelection();
        }

        int rowindex = hp.table.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(hp, e.getX(), e.getY());
            hp.table.setComponentPopupMenu(hp.popUpMenu);
        }
    }

    @Override
    public void harvestPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = hp.table.rowAtPoint(SwingUtilities.
                    convertPoint(hp.popUpMenu, new Point(0, 0), hp.table));
            if (rowAtPoint > -1) {
                hp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

}
