package Far;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import net.proteanit.sql.DbUtils;

public class FarServiceImpl implements FarService {

    FarPanel fp;
    FarDAO dao = new FarDAOImpl();

    public FarServiceImpl(FarPanel fp) {
        this.fp = fp;

        displayAllFar();

    }

    @Override
    public void addFar() {
        fp.addDialog.setTitle("Add FAR");
        fp.addDialog.setModal(true);
        fp.addDialog.pack();
        fp.addDialog.setLocationRelativeTo(null);
        fp.addDialog.setVisible(true);
        clearFields();
    }

    @Override
    public void clearFields() {
        JTextField text = (JTextField) fp.beneCBB.getEditor().getEditorComponent();
        text.setText("");
        JTextField text1 = (JTextField) fp.disCBB.getEditor().getEditorComponent();
        text1.setText("");
        fp.providerTF.setText("");
        fp.qtySpin.setValue(0);
        fp.costSpin.setValue(0);
        fp.dateDC.setDate(new Date());

        JTextField text2 = (JTextField) fp.beneCBB1.getEditor().getEditorComponent();
        text2.setText("");
        JTextField text3 = (JTextField) fp.disCBB1.getEditor().getEditorComponent();
        text3.setText("");
        fp.providerTF1.setText("");
        fp.qtySpin1.setValue(0);
        fp.costSpin1.setValue(0);
        fp.dateDC1.setDate(new Date());
    }

    @Override
    public void deleteFar() {
        int dataRow = fp.table.getSelectedRow();
        if (dataRow >= 0) {
            String id = fp.table.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(fp, "Would You Like to "
                    + "delete FAR: " + id + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteFar(id);
                displayAllFar();
            }
        } else {
            JOptionPane.showMessageDialog(fp, "Please select a FAR to delete.");
        }
    }

    @Override
    public void displayAllFar() {
        DefaultTableModel model = dao.getAllFar();
        fp.table.setModel(model);
        fp.table.getColumnModel().getColumn(0).setMinWidth(0);
        fp.table.getColumnModel().getColumn(0).setMaxWidth(50);
        fp.table.getColumnModel().getColumn(0).setPreferredWidth(25);
        new SearchDefaultModel(fp, fp.table, fp.searchTF, model);
    }

    @Override
    public void editFar() {
        clearFields();
        int dataRow = fp.table.getSelectedRow();
        if (dataRow >= 0) {
            fp.idLbl.setText(fp.table.getValueAt(dataRow, 0).toString());
            fp.beneCBB1.setSelectedItem(fp.table.getValueAt(dataRow, 1).toString());
            fp.disCBB1.setSelectedItem(fp.table.getValueAt(dataRow, 3).toString());
            try {
                Date date;
                date = new SimpleDateFormat("yyyy-MM-dd").parse(fp.table.getValueAt(dataRow, 4).toString());
                fp.dateDC1.setDate(date); //dob
            } catch (ParseException ex) {
                Logger.getLogger(FarServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            fp.typeCBB1.setSelectedItem(fp.table.getValueAt(dataRow, 5).toString());
            fp.qtySpin1.setValue(Integer.parseInt(fp.table.getValueAt(dataRow, 6).toString()));
            fp.costSpin1.setValue(Double.parseDouble(fp.table.getValueAt(dataRow, 7).toString()));
            fp.providerTF1.setText(fp.table.getValueAt(dataRow, 8).toString());
            fp.editDialog.setTitle("Edit FAR");
            fp.okBtn1.setVisible(false);
            fp.editDialog.setModal(true);
            fp.editDialog.pack();
            fp.editDialog.setLocationRelativeTo(null);
            fp.editDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(fp, "Please select FAR to edit.");
        }
    }

    @Override
    public void saveFar() {
        Object dis_id = null;
        JTextField text = (JTextField) fp.beneCBB.getEditor().getEditorComponent();
        String str = text.getText();
        JTextField text1 = (JTextField) fp.disCBB.getEditor().getEditorComponent();
        String str1 = text1.getText();
        if (str1.contains(":")) {
            dis_id = Integer.parseInt(str1.substring(str1.indexOf(":") + 1, str1.indexOf(")")));
            str1 = str1.substring(str1.indexOf(")") + 2);
        }
        FarModel far = new FarModel();
        far.setBeneID(Integer.parseInt(str.substring(str.indexOf(":") + 1, str.indexOf(")"))));
        far.setDisID(dis_id);
        far.setDuring(str1);
        far.setDate(Alter.gatDate(fp.dateDC));
        far.setType(Alter.getString(fp.typeCBB));
        far.setQty(Alter.getInt(fp.qtySpin));
        far.setCost(Alter.getDouble(fp.costSpin));
        far.setProvider(fp.providerTF.getText());
        dao.saveFar(far);
        fp.addDialog.dispose();
        displayAllFar();
    }

    @Override
    public void updateFar() {
        Object dis_id = null;
        JTextField text = (JTextField) fp.beneCBB1.getEditor().getEditorComponent();
        String str = text.getText();
        JTextField text1 = (JTextField) fp.disCBB1.getEditor().getEditorComponent();
        String str1 = text1.getText();
        if (str1.contains(":")) {
            dis_id = Integer.parseInt(str1.substring(str1.indexOf(":") + 1, str1.indexOf(")")));
            str1 = str1.substring(str1.indexOf(")") + 2);
        }
        FarModel far = new FarModel();
        far.setFarID(Integer.parseInt(fp.idLbl.getText()));
        far.setBeneID(Integer.parseInt(str.substring(str.indexOf(":") + 1, str.indexOf(")"))));
        far.setDisID(dis_id);
        far.setDuring(str1);
        far.setDate(Alter.gatDate(fp.dateDC1));
        far.setType(Alter.getString(fp.typeCBB1));
        far.setQty(Alter.getInt(fp.qtySpin1));
        far.setCost(Alter.getDouble(fp.costSpin1));
        far.setProvider(fp.providerTF1.getText());
        dao.update(far);
        fp.editDialog.dispose();
        displayAllFar();
    }

    @Override
    public void viewFar() {
        int dataRow = fp.table.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(fp,
                    "ID: " + (fp.table.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Beneficiary: " + (fp.table.getValueAt(dataRow, 1).toString()) + "\n"
                    + "Disaster ID: " + (fp.table.getValueAt(dataRow, 2) == null ? "" : fp.table.getValueAt(dataRow, 2).toString()) + "\n"
                    + "During: " + (fp.table.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Date: " + (fp.table.getValueAt(dataRow, 4).toString()) + "\n"
                    + "type: " + (fp.table.getValueAt(dataRow, 5).toString()) + "\n"
                    + "Quantity: " + (fp.table.getValueAt(dataRow, 6).toString()) + "\n"
                    + "Cost: " + (fp.table.getValueAt(dataRow, 7).toString()) + "\n"
                    + "Provider: " + (fp.table.getValueAt(dataRow, 8).toString()),
                    "FAR Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(fp, "Please select FAR to edit.");
        }
    }

    @Override
    public void farMouse(MouseEvent e) {

        int r = fp.table.rowAtPoint(e.getPoint());
        if (r >= 0 && r < fp.table.getRowCount()) {
            fp.table.setRowSelectionInterval(r, r);
        } else {
            fp.table.clearSelection();
        }

        int rowindex = fp.table.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(fp, e.getX(), e.getY());
            fp.table.setComponentPopupMenu(fp.popUpMenu);
        }
    }

    @Override
    public void farPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = fp.table.rowAtPoint(SwingUtilities.
                    convertPoint(fp.popUpMenu, new Point(0, 0), fp.table));
            if (rowAtPoint > -1) {
                fp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

}
