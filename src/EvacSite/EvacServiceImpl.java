package EvacSite;

import EvacSite.Map.*;
import Util.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import net.proteanit.sql.DbUtils;

public class EvacServiceImpl implements EvacService {

    EvacPanel ep;
    EvacDAO dao = new EvacDAOImpl();

    public EvacServiceImpl(EvacPanel ep) {
        this.ep = ep;

        displayAllEvac();

    }

    @Override
    public void displayAllEvac() {
        ResultSet rs = dao.getAllEvac();
        ep.table.setModel(DbUtils.resultSetToTableModel(rs));
        new SearchModel(ep, ep.table, ep.searchTF, rs);
    }

    @Override
    public void addEvac() {
        ep.addDialog.setTitle("Add Evacuation Site");
        ep.addDialog.setModal(true);
        ep.addDialog.pack();
        ep.addDialog.setLocationRelativeTo(null);
        ep.addDialog.setVisible(true);
        clearFields();
    }

    @Override
    public void clearFields() {
        ep.nameTF.setText("");
        ep.longSpin.setValue(0);
        ep.latSpin.setValue(0);
    }

    @Override
    public void deleteEvac() {
        int dataRow = ep.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = ep.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(ep, "Would You Like to "
                    + "delete evacuation site: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteEvac(id);
                displayAllEvac();
            }
        } else {
            JOptionPane.showMessageDialog(ep, "Please select a disaster to delete.");
        }
    }

    @Override
    public void openMapToGetLoc() {
        MapPanel mpp = new MapPanel();
        new MapController(mpp, ep);
    }

    @Override
    public void openMapToEditLoc() {
        MapPanel mpp = new MapPanel();
        new MapController(mpp, ep);
    }

    @Override
    public void saveEvac() {
        EvacModel evac = new EvacModel();
        evac.setName(ep.nameTF.getText());
        evac.setLatt(Alter.getDouble(ep.latSpin));
        evac.setLongt(Alter.getDouble(ep.longSpin));
        dao.saveEvac(evac);
        ep.addDialog.dispose();
        displayAllEvac();
    }

    @Override
    public void updateEvac() {
        EvacModel evac = new EvacModel();
        evac.setEvacID(Alter.toInt(ep.idLbl.getText()));
        evac.setName(ep.nameTF.getText());
        evac.setLatt(Alter.getDouble(ep.latSpin));
        evac.setLongt(Alter.getDouble(ep.longSpin));
        dao.updateEvac(evac);
        ep.editDialog.dispose();
        displayAllEvac();
    }

    @Override
    public void viewEvac() {
        int dataRow = ep.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(ep,
                    "ID: " + (ep.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Name: " + (ep.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Latitude: " + (ep.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Longitude: " + (ep.table.getValueAt(dataRow, 3).toString()),
                    "Disaster Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(ep, "Please select a site to edit.");
        }
    }

    @Override
    public void evacMouse(MouseEvent e) {
        int r = ep.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < ep.table.getRowCount()) {
            ep.table.setRowSelectionInterval(r, r);
        } else {
            ep.table.clearSelection();
        }

        int rowindex = ep.table.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(ep, e.getX(), e.getY());
            ep.table.setComponentPopupMenu(ep.popUpMenu);
        }
    }

    @Override
    public void evacPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = ep.table.rowAtPoint(SwingUtilities.
                    convertPoint(ep.popUpMenu, new Point(0, 0), ep.table));
            if (rowAtPoint > -1) {
                ep.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

    @Override
    public void editEvac() {
        int dataRow = ep.table.getSelectedRow();
        if (dataRow >= 0) {
            ep.idLbl.setText(ep.table.getValueAt(dataRow, 0).toString());
            ep.nameTF1.setText(ep.table.getValueAt(dataRow, 1).toString());
            ep.longSpin1.setValue(Double.parseDouble(ep.table.getValueAt(dataRow, 2).toString()));
            ep.latSpin1.setValue(Double.parseDouble(ep.table.getValueAt(dataRow, 3).toString()));
            ep.editDialog.setTitle("Edit Evacuation Site");
            ep.editDialog.setModal(true);
            ep.editDialog.pack();
            ep.editDialog.setLocationRelativeTo(ep);
            ep.editDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(ep, "Please select a site to edit.");
        }
    }

}
