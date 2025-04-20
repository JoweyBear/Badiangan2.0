package Registration;

import Util.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.proteanit.sql.DbUtils;

public class RegServiceImpl implements RegService {

    RegPanel rp;
    RegDAO dao = new RegDAOImpl();

    public RegServiceImpl(RegPanel rp) {
        this.rp = rp;

        displayAllReg();
    }

    @Override
    public void displayAllReg() {
        ResultSet rs = dao.getAllReg();
        rp.table.setModel(DbUtils.resultSetToTableModel(rs));
        new SearchModel(rp, rp.table, rp.searchTF, rs);
    }

    @Override
    public void deleteReg() {
        int dataRow = rp.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = rp.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(rp, "Would You Like to "
                    + "delete registration: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteReg(id);
                displayAllReg();
            }
        } else {
            JOptionPane.showMessageDialog(rp, "Please select a registration to delete.");
        }
    }

    @Override
    public void viewReg() {
        int dataRow = rp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(rp,
                    "ID: " + (rp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Admin: " + (rp.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Beneficiary: " + (rp.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Walkin Status: " + (rp.table.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Case: " + (rp.table.getValueAt(dataRow, 4).toString()) + "\n"
                    + "Date: " + (rp.table.getValueAt(dataRow, 5).toString()) + "\n",
                    "Registration Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(rp, "Please select a registration to view.");
        }
    }

    @Override
    public void regMouse(MouseEvent e) {
        int dataRow = rp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(rp,
                    "ID: " + (rp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Admin: " + (rp.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Beneficiary: " + (rp.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Walkin Status: " + (rp.table.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Case: " + (rp.table.getValueAt(dataRow, 4).toString()) + "\n"
                    + "Date: " + (rp.table.getValueAt(dataRow, 5).toString()) + "\n",
                    "Registration Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(rp, "Please select a registration to view.");
        }
    }

    @Override
    public void regPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = rp.table.rowAtPoint(SwingUtilities.
                    convertPoint(rp.popUpMenu, new Point(0, 0), rp.table));
            if (rowAtPoint > -1) {
                rp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

}
