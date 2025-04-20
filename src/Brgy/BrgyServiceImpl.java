package Brgy;

import Util.Alter;
import Util.SearchModel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import net.proteanit.sql.DbUtils;

public class BrgyServiceImpl implements BrgyService {

    BrgyPanel brgyp;
    BrgyDAO dao = new BrgyDAOImpl();

    public BrgyServiceImpl(BrgyPanel brgyp) {
        this.brgyp = brgyp;
        displayAllBrgy();
    }

    @Override
    public void addBrgy() {
        brgyp.addDialog.setTitle("Add Brgy");
        brgyp.addDialog.setModal(true);
        brgyp.addDialog.pack();
        brgyp.addDialog.setLocationRelativeTo(null);
        brgyp.addDialog.setVisible(true);
        clearFields();
    }

    @Override
    public void clearFields() {
        brgyp.nameTF.setText("");
    }

    @Override
    public void deleteBrgy() {
        int dataRow = brgyp.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = brgyp.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(brgyp, "Would You Like to "
                    + "delete brgy: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteBrgy(id);
                displayAllBrgy();
            }
        } else {
            JOptionPane.showMessageDialog(brgyp, "Please select a disaster to delete.");
        }
    }

    @Override
    public void displayAllBrgy() {
        ResultSet rs = dao.getAllBrgy();
        brgyp.table.setModel(DbUtils.resultSetToTableModel(rs));
        new SearchModel(brgyp, brgyp.table, brgyp.searchTF, rs);
    }

    @Override
    public void editBrgy() {
        int dataRow = brgyp.table.getSelectedRow();
        if (dataRow >= 0) {
            brgyp.idLbl.setText(brgyp.table.getValueAt(dataRow, 0).toString());
            brgyp.nameTF1.setText(brgyp.table.getValueAt(dataRow, 1).toString());
            brgyp.editDialog.setTitle("Edit Brgy");
            brgyp.editDialog.setModal(true);
            brgyp.editDialog.pack();
            brgyp.editDialog.setLocationRelativeTo(brgyp);
            brgyp.editDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(brgyp, "Please select a site to edit.");
        }
    }

    @Override
    public void saveBrgy() {
        dao.saveBrgy(brgyp.nameTF.getText());
        brgyp.addDialog.dispose();
        displayAllBrgy();
    }

    @Override
    public void updateBrgy() {
        dao.updateBrgy(Alter.toInt(brgyp.idLbl.getText()),
                brgyp.nameTF1.getText());
        brgyp.editDialog.dispose();
        displayAllBrgy();
    }

    @Override
    public void viewBrgy() {
        int dataRow = brgyp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(brgyp,
                    "ID: " + (brgyp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Name: " + (brgyp.table.getValueAt(dataRow, 1).toString()),
                    "Disaster Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(brgyp, "Please select a brgy to edit.");
        }
    }

    @Override
    public void mouseAction(MouseEvent e) {
        int r = brgyp.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < brgyp.table.getRowCount()) {
            brgyp.table.setRowSelectionInterval(r, r);
        } else {
            brgyp.table.clearSelection();
        }

        int rowindex = brgyp.table.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(brgyp, e.getX(), e.getY());
            brgyp.table.setComponentPopupMenu(brgyp.popUpMenu);
        }
    }

    @Override
    public void popup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = brgyp.table.rowAtPoint(SwingUtilities.
                    convertPoint(brgyp.popUpMenu, new Point(0, 0), brgyp.table));
            if (rowAtPoint > -1) {
                brgyp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

}
