package LiveStock;

import LiveStock.Disposal.*;
import Util.*;
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
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import net.proteanit.sql.DbUtils;

public class LSServiceImpl implements LSService {

    LSPanel lsp;
    LSDAO dao = new LSDAOImpl();
    DisposalDAO dDao = new DisposalDAOImpl();
    public LSServiceImpl(LSPanel lsp) {
        this.lsp = lsp;
        displayAllLS();
    }

    @Override
    public void addLS() {
        lsp.addDialog.setTitle("Add LS");
        lsp.addDialog.setModal(true);
        lsp.addDialog.pack();
        lsp.addDialog.setLocationRelativeTo(null);
        lsp.addDialog.setVisible(true);
        clearFields();
    }

    @Override
    public void clearFields() {
        JTextField text = (JTextField) lsp.beneCB.getEditor().getEditorComponent();
        text.setText("");
        lsp.lsTF.setText("");
        lsp.classificationTF.setText("");
        lsp.headsSpin.setValue(0);
        lsp.ageSpin.setValue(0);
        lsp.expDC.setDate(new Date());
        lsp.remarksTA.setText("");

        lsp.idLSLbl.setText("0");
        lsp.profitSpin.setValue(0);
        lsp.dateDC.setDate(new Date());
        lsp.remarksTA2.setText("");
    }

    @Override
    public void deleteLS() {
        int dataRow = lsp.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = lsp.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(lsp, "Would You Like to "
                    + "delete livestock: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteLS(id);
                displayAllLS();
            }
        } else {
            JOptionPane.showMessageDialog(lsp, "Please select a livestock to delete.");
        }
    }

    @Override
    public void displayAllLS() {
        ResultSet rs = dao.getAllLS();
        lsp.table.setModel(DbUtils.resultSetToTableModel(rs));
        // this is to disable editing in the jtable
        for (Class c : Arrays.asList(Object.class, Number.class, Boolean.class)) {
            TableCellEditor ce = lsp.table.getDefaultEditor(c);
            if (ce instanceof DefaultCellEditor) {
                ((DefaultCellEditor) ce).setClickCountToStart(Integer.MAX_VALUE);
            }
        }
        lsp.table.getColumnModel().getColumn(0).setMinWidth(0);
        lsp.table.getColumnModel().getColumn(0).setMaxWidth(50);
        lsp.table.getColumnModel().getColumn(0).setPreferredWidth(25);
        new SearchModel(lsp, lsp.table, lsp.searchTF, rs);
    }

    @Override
    public void editLS() {
        int dataRow = lsp.table.getSelectedRow();
        if (dataRow >= 0) {
            lsp.idLbl.setText(lsp.table.getValueAt(dataRow, 0).toString());
            lsp.lsTF1.setText(lsp.table.getValueAt(dataRow, 2).toString());
            lsp.classificationTF1.setText(lsp.table.getValueAt(dataRow, 3).toString());
            lsp.headsSpin1.setValue(Alter.toInt(lsp.table.getValueAt(dataRow, 4).toString()));
            lsp.ageSpin1.setValue(Alter.toInt(lsp.table.getValueAt(dataRow, 5).toString()));

            try {
                Date date;
                date = new SimpleDateFormat("yyyy-MM-dd").parse(lsp.table.getValueAt(dataRow, 6).toString());
                lsp.expDC1.setDate(date); //dob
            } catch (ParseException ex) {
                Logger.getLogger(LSServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            lsp.remarksTA1.setText(lsp.table.getValueAt(dataRow, 7).toString());
            lsp.editDialog.setTitle("Edit Livestock");
            lsp.editDialog.setModal(true);
            lsp.editDialog.pack();
            lsp.editDialog.setLocationRelativeTo(lsp);
            lsp.editDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(lsp, "Please select ls to edit.");
        }
    }

    @Override
    public void saveLS() {
        JTextField text = (JTextField) lsp.beneCB.getEditor().getEditorComponent();
        String str = text.getText();
        LSModel ls = new LSModel();
        ls.setBeneID(Integer.parseInt(str.substring(str.indexOf(":") + 1, str.indexOf(")"))));
        ls.setLs(lsp.lsTF.getText());
        ls.setClassi(lsp.classificationTF.getText());
        ls.setHeads(Alter.getInt(lsp.headsSpin));
        ls.setAge(Alter.getInt(lsp.ageSpin));
        ls.setExp(((JTextField) lsp.expDC.getDateEditor().getUiComponent()).getText());
        ls.setRemarks(lsp.remarksTA.getText());
        dao.saveLS(ls);
        lsp.addDialog.dispose();
        displayAllLS();
    }

    @Override
    public void updateLS() {
        JTextField text = (JTextField) lsp.beneCB1.getEditor().getEditorComponent();
        String str = text.getText();
        LSModel ls = new LSModel();
        ls.setLsID(Integer.parseInt(lsp.idLbl.getText()));
        ls.setBeneID(Integer.parseInt(str.substring(str.indexOf(":") + 1, str.indexOf(")"))));
        ls.setLs(lsp.lsTF1.getText());
        ls.setClassi(lsp.classificationTF1.getText());
        ls.setHeads(Alter.getInt(lsp.headsSpin1));
        ls.setAge(Alter.getInt(lsp.ageSpin1));
        ls.setExp(((JTextField) lsp.expDC1.getDateEditor().getUiComponent()).getText());
        ls.setRemarks(lsp.remarksTA1.getText());
        dao.updateLS(ls);
        lsp.editDialog.dispose();
        displayAllLS();
    }

    @Override
    public void viewLS() {
        int dataRow = lsp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(lsp,
                    "ID: " + (lsp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Beneficiary: " + (lsp.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Livestock Raised: " + (lsp.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Area: " + (lsp.table.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Variety: " + (lsp.table.getValueAt(dataRow, 4).toString()) + "\n"
                    + "Classification: " + (lsp.table.getValueAt(dataRow, 5).toString()) + "\n"
                    + "Exp Harvest Date: " + (lsp.table.getValueAt(dataRow, 6).toString()) + "\n"
                    + "Remarks: " + (lsp.table.getValueAt(dataRow, 7).toString()),
                    "LS Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(lsp, "Please select ls to edit.");
        }
    }

    @Override
    public void addDisposal() {
        int dataRow = lsp.table.getSelectedRow();
        if (lsp.table.getValueAt(dataRow, 8).toString().equals("Disposed")) {
            JOptionPane.showMessageDialog(null, "Livestock already Disposed!");
        } else {
            if (dataRow >= 0) {
                lsp.idLSLbl.setText(lsp.table.getValueAt(dataRow, 0).toString());
                lsp.disposalDialog.setTitle("Livestock Disposal");
                lsp.disposalDialog.setModal(true);
                lsp.disposalDialog.pack();
                lsp.disposalDialog.setLocationRelativeTo(null);
                lsp.disposalDialog.setVisible(true);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(lsp, "Please select a livestock to dispose.");
            }
        }
    }

    @Override
    public void saveDisposal() {
        DisposalModel dis = new DisposalModel();
        dis.setLsID(Integer.parseInt(lsp.idLSLbl.getText()));
        dis.setProfit(Alter.getDouble(lsp.profitSpin));
        dis.setDate(Alter.gatDate(lsp.dateDC));
        dis.setRemarks(lsp.remarksTA2.getText());
        dDao.saveDisposal(dis);
        
        dDao.updateLSDisposed(Integer.parseInt(lsp.idLSLbl.getText()));
        JOptionPane.showMessageDialog(null, "Livestock Disposed!");
        lsp.disposalDialog.dispose();
        displayAllLS();
    }

    @Override
    public void lsMouse(MouseEvent e) {
        int r = lsp.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < lsp.table.getRowCount()) {
            lsp.table.setRowSelectionInterval(r, r);
        } else {
            lsp.table.clearSelection();
        }

        int rowindex = lsp.table.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(lsp, e.getX(), e.getY());
            lsp.table.setComponentPopupMenu(lsp.popUpMenu);
        }
    }

    @Override
    public void lsPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = lsp.table.rowAtPoint(SwingUtilities.
                    convertPoint(lsp.popUpMenu, new Point(0, 0), lsp.table));
            if (rowAtPoint > -1) {
                lsp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

}
