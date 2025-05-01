package Hazard;

import Hazard.Map.*;
import Util.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import net.proteanit.sql.DbUtils;

public class HazardServiceImpl implements HazardService {

    HazardPanel hazp;
    HazardDAO dao = new HazardDAOImpl();

    public HazardServiceImpl(HazardPanel hazp) {
        this.hazp = hazp;
        displayAllHazard();
    }

    @Override
    public void addDisaster() {
        hazp.addDialog.setTitle("Add Hazard");
        hazp.addDialog.setModal(true);
        hazp.addDialog.pack();
        hazp.addDialog.setLocationRelativeTo(null);
        hazp.addDialog.setVisible(true);
        clearFields();
    }

    @Override
    public void clearFields() {
        hazp.descTF.setText("");
        hazp.latSpin.setValue(0);
        hazp.longSpin.setValue(0);
    }

    @Override
    public void deleteHazard() {
        int dataRow = hazp.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = hazp.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(hazp, "Would You Like to "
                    + "delete hazard: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteHazard(id);
                displayAllHazard();
            }
        } else {
            JOptionPane.showMessageDialog(hazp, "Please select a hazard to delete.");
        }
    }

    @Override
    public void displayAllHazard() {
        ResultSet rs = dao.getAllHazard();
        hazp.table.setModel(DbUtils.resultSetToTableModel(rs));
        new SearchModel(hazp, hazp.table, hazp.searchTF, rs);
    }

    @Override
    public void openMapToGetLoc() {
        HazMapPanel mpp = new HazMapPanel();
        new HazMapController(mpp, hazp);
    }

    @Override
    public void editHazard() {
        int dataRow = hazp.table.getSelectedRow();
        if (dataRow >= 0) {
            hazp.idLbl.setText(hazp.table.getValueAt(dataRow, 0).toString());
            hazp.descTF1.setText(hazp.table.getValueAt(dataRow, 1).toString());
            hazp.latSpin1.setValue(Double.parseDouble(hazp.table.getValueAt(dataRow, 2).toString()));
            hazp.longSpin1.setValue(Double.parseDouble(hazp.table.getValueAt(dataRow, 3).toString()));
            hazp.editDialog.setTitle("Edit Hazard");
            hazp.editDialog.setModal(true);
            hazp.editDialog.pack();
            hazp.editDialog.setLocationRelativeTo(hazp);
            hazp.editDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(hazp, "Please select a hazard to edit.");
        }
    }

    @Override
    public void saveHazard() {
        HazardModel hzrd = new HazardModel();
        hzrd.setDesc(hazp.descTF.getText());
        hzrd.setLatt(Alter.getDouble(hazp.latSpin));
        hzrd.setLongt(Alter.getDouble(hazp.longSpin));
        dao.saveHazard(hzrd);
        hazp.addDialog.dispose();
        displayAllHazard();
    }

    @Override
    public void updateHazard() {
        HazardModel hzrd = new HazardModel();
        hzrd.setHazardID(Integer.parseInt(hazp.idLbl.getText()));
        hzrd.setDesc(hazp.descTF1.getText());
        hzrd.setLatt(Alter.getDouble(hazp.latSpin1));
        hzrd.setLongt(Alter.getDouble(hazp.longSpin1));
        dao.updatHazard(hzrd);
        hazp.editDialog.dispose();
        displayAllHazard();
    }

    @Override
    public void viewHazard() {
        int dataRow = hazp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(hazp,
                    "ID: " + (hazp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Description: " + (hazp.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Latitude: " + (hazp.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Longitude: " + (hazp.table.getValueAt(dataRow, 3).toString()),
                    "Hazard Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(hazp, "Please select a Hazard to view.");
        }
    }

    @Override
    public void viewInMap() {
        int dataRow = hazp.table.getSelectedRow();
        if (dataRow >= 0) {
            double lt = (Double.parseDouble(hazp.table.getValueAt(dataRow, 2).toString()));
            double lg = (Double.parseDouble(hazp.table.getValueAt(dataRow, 3).toString()));
            HazMapPanel mpp = new HazMapPanel();
            new HazMapController(mpp, hazp, lt, lg);
        } else {
            JOptionPane.showMessageDialog(hazp, "Please select a hazard to view in map.");
        }
    }

    @Override
    public void hazardMouse(MouseEvent e) {
        int r = hazp.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < hazp.table.getRowCount()) {
            hazp.table.setRowSelectionInterval(r, r);
        } else {
            hazp.table.clearSelection();
        }

        int rowindex = hazp.table.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(hazp, e.getX(), e.getY());
            hazp.table.setComponentPopupMenu(hazp.popUpMenu);
        }
    }

    @Override
    public void hazardPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = hazp.table.rowAtPoint(SwingUtilities.
                    convertPoint(hazp.popUpMenu, new Point(0, 0), hazp.table));
            if (rowAtPoint > -1) {
                hazp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }
}
