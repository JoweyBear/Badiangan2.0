package Disaster;

import Disaster.Map.*;
import Util.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import net.proteanit.sql.DbUtils;

public class DisasterServiceImpl implements DisasterService {

    DisasterPanel disp;
    DisasterDAO dao = new DisasterDAOImpl();

    public DisasterServiceImpl(DisasterPanel disp) {
        this.disp = disp;

        displayAllDisasters();
    }

    @Override
    public void addDisaster() {
        disp.addDialog.setTitle("Add Disaster");
        disp.addDialog.setModal(true);
        disp.addDialog.pack();
        disp.addDialog.setLocationRelativeTo(null);
        disp.addDialog.setVisible(true);
        clearFields();
    }

    @Override
    public void clearFields() {
        disp.typeCBB.setSelectedIndex(0);
        disp.nameTF.setText("");
        disp.dateDC.setDate(new Date());
        disp.latSpin.setValue(0);
        disp.longSpin.setValue(0);
        disp.radSpin.setValue(0);
        disp.remarksTA.setText("");
    }

    @Override
    public void deleteDisaster() {
        int dataRow = disp.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = disp.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(disp, "Would You Like to "
                    + "delete disaster: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteDisasters(id);
                displayAllDisasters();
            }
        } else {
            JOptionPane.showMessageDialog(disp, "Please select a disaster to delete.");
        }
    }

    @Override
    public void displayAllDisasters() {
        ResultSet rs = dao.getAllDisasters();
        disp.table.setModel(DbUtils.resultSetToTableModel(rs));
        new SearchModel(disp, disp.table, disp.searchTF, rs);
    }

    @Override
    public void openMapToGetLoc() {
        DisMapPanel mpp = new DisMapPanel();
        new DisMapController(mpp, disp);
    }

    @Override
    public void editDisaster() {
        int dataRow = disp.table.getSelectedRow();
        if (dataRow >= 0) {
            disp.idLbl.setText(disp.table.getValueAt(dataRow, 0).toString());
            disp.typeCBB1.setSelectedItem((disp.table.getValueAt(dataRow, 1).toString()));
            disp.nameTF1.setText(disp.table.getValueAt(dataRow, 2).toString());

            try {
                Date date;
                date = new SimpleDateFormat("yyyy-MM-dd").parse(disp.table.getValueAt(dataRow, 3).toString());
                disp.dateDC1.setDate(date);
            } catch (ParseException ex) {
                Logger.getLogger(DisasterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            disp.latSpin1.setValue(Double.parseDouble(disp.table.getValueAt(dataRow, 4).toString()));
            disp.longSpin1.setValue(Double.parseDouble(disp.table.getValueAt(dataRow, 5).toString()));
            disp.radSpin1.setValue(Double.parseDouble(disp.table.getValueAt(dataRow, 6).toString()));
            disp.remarksTA1.setText(disp.table.getValueAt(dataRow, 7).toString());
            disp.editDialog.setTitle("Edit Disaster");
            disp.editDialog.setModal(true);
            disp.editDialog.pack();
            disp.editDialog.setLocationRelativeTo(disp);
            disp.editDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(disp, "Please select a disaster to edit.");
        }
    }

    @Override
    public void saveDisaster() {
        DisasterModel dsstr = new DisasterModel();
        dsstr.setType(Alter.getString(disp.typeCBB));
        dsstr.setName(disp.nameTF.getText());
        dsstr.setDate(Alter.gatDate(disp.dateDC));
        dsstr.setLatt(Alter.getDouble(disp.latSpin));
        dsstr.setLongt(Alter.getDouble(disp.longSpin));
        dsstr.setRad(Alter.getDouble(disp.radSpin));
        dsstr.setRemarks(disp.remarksTA.getText());
        dao.saveDisasters(dsstr);
        disp.addDialog.dispose();
        displayAllDisasters();
    }

    @Override
    public void updateDisaster() {
        DisasterModel dsstr = new DisasterModel();
        dsstr.setDisasterID(Integer.parseInt(disp.idLbl.getText()));
        dsstr.setType(Alter.getString(disp.typeCBB));
        dsstr.setName(disp.nameTF.getText());
        dsstr.setDate(Alter.gatDate(disp.dateDC));
        dsstr.setLatt(Alter.getDouble(disp.latSpin));
        dsstr.setLongt(Alter.getDouble(disp.longSpin));
        dsstr.setRad(Alter.getDouble(disp.radSpin));
        dsstr.setRemarks(disp.remarksTA.getText());
        dao.updateDisasters(dsstr);
        disp.editDialog.dispose();
        displayAllDisasters();
    }

    @Override
    public void viewDisaster() {
        int dataRow = disp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(disp,
                    "ID: " + (disp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Type: " + (disp.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Name: " + (disp.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Date: " + (disp.table.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Latitude: " + (disp.table.getValueAt(dataRow, 4).toString()) + "\n"
                    + "Longitude: " + (disp.table.getValueAt(dataRow, 5).toString()) + "\n"
                    + "Radius: " + (disp.table.getValueAt(dataRow, 6).toString()) + "\n"
                    + "Remarks: " + (disp.table.getValueAt(dataRow, 7).toString()),
                    "Disaster Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(disp, "Please select a disaster to edit.");
        }
    }

    @Override
    public void viewInMap() {
        int dataRow = disp.table.getSelectedRow();
        if (dataRow >= 0) {
            double lt = (Double.parseDouble(disp.table.getValueAt(dataRow, 4).toString()));
            double lg = (Double.parseDouble(disp.table.getValueAt(dataRow, 5).toString()));
            double rad = (Double.parseDouble(disp.table.getValueAt(dataRow, 6).toString()));
            DisMapPanel mpp = new DisMapPanel();
            new DisMapController(mpp, disp, lt, lg, rad);
        } else {
            JOptionPane.showMessageDialog(disp, "Please select a disaster to view in map.");
        }
    }

    @Override
    public void disasterMouse(MouseEvent e) {
        int r = disp.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < disp.table.getRowCount()) {
            disp.table.setRowSelectionInterval(r, r);
        } else {
            disp.table.clearSelection();
        }

        int rowindex = disp.table.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(disp, e.getX(), e.getY());
            disp.table.setComponentPopupMenu(disp.popUpMenu);
        }
    }

    @Override
    public void disasterPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = disp.table.rowAtPoint(SwingUtilities.
                    convertPoint(disp.popUpMenu, new Point(0, 0), disp.table));
            if (rowAtPoint > -1) {
                disp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

}
