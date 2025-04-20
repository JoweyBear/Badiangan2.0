package LiveStock.Disposal;

import LiveStock.*;
import Util.*;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import net.proteanit.sql.DbUtils;

public class DisposalServiceImpl implements DisposalService {

    DisposalPanel dp;
    JPanel mp;
    DisposalDAO dao = new DisposalDAOImpl();

    public DisposalServiceImpl(DisposalPanel dp, JPanel mp) {
        this.dp = dp;
        this.mp = mp;

        displayAllDisposal();
    }

    @Override
    public void addDisposal() {
        JOptionPane.showMessageDialog(null, "Please add a disposal in Livestock Panel",
                "Add Disposal", JOptionPane.INFORMATION_MESSAGE);
        DisposalPanel dp = new DisposalPanel();
        LSPanel lsp = new LSPanel();
        new LSController(lsp);
        CardLayout cl = (CardLayout) (mp.getLayout());
        mp.add(lsp, "LivestockPanel");
        cl.show(mp, "LivestockPanel");
    }

    @Override
    public void clearFields() {
        dp.profitSpin.setValue(0);
        dp.dateDC.setDate(new Date());
        dp.remarksTA.setText("");
    }

    @Override
    public void deleteDisposal() {
        int dataRow = dp.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = dp.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(dp, "Would You Like to "
                    + "delete disposal: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteDisposal(id);
                displayAllDisposal();
            }
        } else {
            JOptionPane.showMessageDialog(dp, "Please select a disposal to delete.");
        }
    }

    @Override
    public void displayAllDisposal() {
        ResultSet rs = dao.getAllDisposal();
        dp.table.setModel(DbUtils.resultSetToTableModel(rs));
        new SearchModel(dp, dp.table, dp.searchTF, rs);
    }

    @Override
    public void updateDisposal() {
        DisposalModel dis = new DisposalModel();
        dis.setDisID(Integer.parseInt(dp.idLSLbl.getText()));
        dis.setLsID(Alter.getInt(dp.idLSCB));
        dis.setProfit(Alter.getDouble(dp.profitSpin));
        dis.setDate(((JTextField) dp.dateDC.getDateEditor().getUiComponent()).getText());
        dis.setRemarks(dp.remarksTA.getText());
        dao.updateDisposal(dis);
        dp.editDialog.dispose();
        displayAllDisposal();
    }

    @Override
    public void viewDisposal() {
        int dataRow = dp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(dp,
                    "Disposal ID: " + (dp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Crop ID: " + (dp.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Container: " + (dp.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Qty: " + (dp.table.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Profit: " + (dp.table.getValueAt(dataRow, 4).toString()) + "\n"
                    + "Disposal Date: " + (dp.table.getValueAt(dataRow, 5).toString()) + "\n"
                    + "Remarks: " + (dp.table.getValueAt(dataRow, 6).toString()),
                    "Disposal Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(dp, "Please select disposal to view.");
        }
    }

    @Override
    public void disMouse(MouseEvent e) {
        int r = dp.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < dp.table.getRowCount()) {
            dp.table.setRowSelectionInterval(r, r);
        } else {
            dp.table.clearSelection();
        }

        int rowindex = dp.table.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(dp, e.getX(), e.getY());
            dp.table.setComponentPopupMenu(dp.popUpMenu);
        }
    }

    @Override
    public void disPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = dp.table.rowAtPoint(SwingUtilities.
                    convertPoint(dp.popUpMenu, new Point(0, 0), dp.table));
            if (rowAtPoint > -1) {
                dp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

    @Override
    public void editDisposal() {
        int dataRow = dp.table.getSelectedRow();
        if (dataRow >= 0) {
            dp.idLSLbl.setText(dp.table.getValueAt(dataRow, 0).toString());
            dp.idLSCB.setModel(new DefaultComboBoxModel(dao.getLSID().toArray()));
            dp.idLSCB.setSelectedItem(Integer.parseInt(dp.table.getValueAt(dataRow, 1).toString()));
            dp.profitSpin.setValue(Double.parseDouble(dp.table.getValueAt(dataRow, 2).toString()));

            try {
                Date date;
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dp.table.getValueAt(dataRow, 3).toString());
                dp.dateDC.setDate(date); //dob
            } catch (ParseException ex) {
                Logger.getLogger(DisposalServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            dp.remarksTA.setText(dp.table.getValueAt(dataRow, 4).toString());
            dp.editDialog.setTitle("Edit Disposal");
            dp.editDialog.setModal(true);
            dp.editDialog.pack();
            dp.editDialog.setLocationRelativeTo(dp);
            dp.editDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(dp, "Please select a disposal to edit.");
        }
    }

}
