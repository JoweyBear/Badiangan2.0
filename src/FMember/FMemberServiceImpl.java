package FMember;

import Util.*;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import net.proteanit.sql.DbUtils;

public class FMemberServiceImpl implements FMemberService {

    FMemberPanel fmp;
    FMemberDAO dao = new FMemberDAOImpl();

    public FMemberServiceImpl(FMemberPanel fmp) {
        this.fmp = fmp;
        displayAllFM();
        initRBG();
    }

    @Override
    public void clearFields() {
        //clears all fields in addFMDialog
        JTextField text = (JTextField) fmp.addBeneFMCB.getEditor().getEditorComponent();
        text.setText("");
        fmp.fnameAddMemberTF.setText("");
        fmp.mnameAddMemberTF.setText("");
        fmp.lnameAddMemberTF.setText("");
        fmp.relAddMemberCB.setSelectedIndex(0);
        fmp.ageAddMemberSpin.setValue(0);
        fmp.maleAddMemberRB.setSelected(true);
        fmp.heaAddMemberCB.setSelectedIndex(0);
        fmp.occAddMemberTF.setText("");
        fmp.remarksAddMemberTA.setText("");

        //clears all fields in editFMDialog
        JTextField text1 = (JTextField) fmp.editBeneFMCB.getEditor().getEditorComponent();
        text1.setText("");
        fmp.fnameEditMemberTF.setText("");
        fmp.mnameEditMemberTF.setText("");
        fmp.lnameEditMemberTF.setText("");
        fmp.relEditMemberCB.setSelectedIndex(0);
        fmp.ageEditMemberSpin.setValue(0);
        fmp.maleEditMemberRB.setSelected(true);
        fmp.heaEditMemberCB.setSelectedIndex(0);
        fmp.occEditMemberTF.setText("");
        fmp.remarksEditMemberTA.setText("");
    }

    @Override
    public void displayAllFM() {
        //this is to load all the schedules in the database upon selecting the Event Scheduler in the menu bar
        DefaultTableModel model = dao.getAllFM();
        fmp.fmTable.setModel(model);
        fmp.fmTable.getColumnModel().getColumn(0).setMinWidth(0);
        fmp.fmTable.getColumnModel().getColumn(0).setMaxWidth(100);
        fmp.fmTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        new SearchDefaultModel(fmp, fmp.fmTable, fmp.searchTF, model);
    }

    @Override
    public void initRBG() {
        fmp.sexAddFMRB.add(fmp.maleAddMemberRB);
        fmp.sexAddFMRB.add(fmp.femaleAddMemberRB);
        fmp.maleAddMemberRB.setSelected(true);

        fmp.sexEditFMRB.add(fmp.maleEditMemberRB);
        fmp.sexEditFMRB.add(fmp.femaleEditMemberRB);
        fmp.maleEditMemberRB.setSelected(true);
    }

    @Override
    public void deleteFM() {
        int dataRow = fmp.fmTable.getSelectedRow();
        if (dataRow >= 0) {
            String fmID = fmp.fmTable.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(fmp, "Would You Like to "
                    + "Delete Family Member: " + fmID + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteFM(fmID);
                displayAllFM();
            }
        } else {
            JOptionPane.showMessageDialog(fmp, "Please select Family Member to delete.");
        }
    }

    @Override
    public void editFM() {
        int dataRow = fmp.fmTable.getSelectedRow();
        if (dataRow >= 0) {
            fmp.idEditFMLbl.setText(fmp.fmTable.getValueAt(dataRow, 0).toString());
            fmp.fnameEditMemberTF.setText(fmp.fmTable.getValueAt(dataRow, 2).toString());
            fmp.mnameEditMemberTF.setText(fmp.fmTable.getValueAt(dataRow, 3).toString());
            fmp.lnameEditMemberTF.setText(fmp.fmTable.getValueAt(dataRow, 4).toString());
            fmp.relEditMemberCB.setSelectedItem(fmp.fmTable.getValueAt(dataRow, 5).toString());
            fmp.ageEditMemberSpin.setValue(Integer.parseInt(fmp.fmTable.getValueAt(dataRow, 6).toString()));
            fmp.maleEditMemberRB.setSelected(true);
            fmp.heaEditMemberCB.setSelectedItem(fmp.fmTable.getValueAt(dataRow, 8).toString());
            fmp.occEditMemberTF.setText(fmp.fmTable.getValueAt(dataRow, 9).toString());
            fmp.remarksEditMemberTA.setText(fmp.fmTable.getValueAt(dataRow, 10).toString());
            fmp.editFMDialog.setTitle("Edit Family Member");
            fmp.editFMDialog.setModal(true);
            fmp.editFMDialog.pack();
            fmp.editFMDialog.setLocationRelativeTo(fmp);
            fmp.editFMDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(fmp, "Please select Family Member to edit.");
        }
    }

    @Override
    public void fmMouse(MouseEvent e) {
        int r = fmp.fmTable.rowAtPoint(e.getPoint());
        if (r >= 0 && r < fmp.fmTable.getRowCount()) {
            fmp.fmTable.setRowSelectionInterval(r, r);
        } else {
            fmp.fmTable.clearSelection();
        }

        int rowindex = fmp.fmTable.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(fmp, e.getX(), e.getY());
            fmp.fmTable.setComponentPopupMenu(fmp.fmPopUpMenu);
        }
    }

    @Override
    public void fmPopup() {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = fmp.fmTable.rowAtPoint(SwingUtilities.
                    convertPoint(fmp.fmPopUpMenu, new Point(0, 0), fmp.fmTable));
            if (rowAtPoint > -1) {
                fmp.fmTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

    @Override
    public void openDialog() {
        fmp.addFMDialog.setTitle("Add Family Member");
        fmp.addFMDialog.setModal(true);
        fmp.addFMDialog.pack();
        fmp.addFMDialog.setLocationRelativeTo(null);
        fmp.addFMDialog.setVisible(true);
    }

    @Override
    public void saveFM() {
        JTextField text = (JTextField) fmp.addBeneFMCB.getEditor().getEditorComponent();
        String str = text.getText();
        FMemberModel fm = new FMemberModel();
        fm.setBeneID(Integer.parseInt(str.substring(str.indexOf(":") + 1, str.indexOf(")"))));
        fm.setFname(fmp.fnameAddMemberTF.getText());
        fm.setMname(fmp.mnameAddMemberTF.getText());
        fm.setLname(fmp.lnameAddMemberTF.getText());
        fm.setRel(fmp.relAddMemberCB.getSelectedItem().toString());
        fm.setAge(Integer.parseInt(fmp.ageAddMemberSpin.getValue().toString()));
        fm.setSex(fmp.maleAddMemberRB.isSelected() ? "Male" : "Female");
        fm.setEduc(fmp.heaAddMemberCB.getSelectedItem().toString());
        fm.setOcc(fmp.occAddMemberTF.getText());
        fm.setRemarks(fmp.remarksAddMemberTA.getText());
        dao.saveFM(fm);
        fmp.addFMDialog.dispose();
        displayAllFM();
    }

    @Override
    public void updateFM() {
        JTextField text = (JTextField) fmp.editBeneFMCB.getEditor().getEditorComponent();
        String str = text.getText();
        FMemberModel fm = new FMemberModel();
        fm.setFmID(Integer.parseInt(fmp.idEditFMLbl.getText()));
        fm.setBeneID(Integer.parseInt(str.substring(str.indexOf(":") + 1, str.indexOf(")"))));
        fm.setFname(fmp.fnameAddMemberTF.getText());
        fm.setMname(fmp.mnameAddMemberTF.getText());
        fm.setLname(fmp.lnameAddMemberTF.getText());
        fm.setRel(fmp.relAddMemberCB.getSelectedItem().toString());
        fm.setAge(Integer.parseInt(fmp.ageAddMemberSpin.getValue().toString()));
        fm.setSex(fmp.maleAddMemberRB.isSelected() ? "Male" : "Female");
        fm.setEduc(fmp.heaAddMemberCB.getSelectedItem().toString());
        fm.setOcc(fmp.occAddMemberTF.getText());
        fm.setRemarks(fmp.remarksAddMemberTA.getText());
        dao.update(fm);
        fmp.editFMDialog.dispose();
        displayAllFM();
    }

    @Override
    public void viewFM() {
        int dataRow = fmp.fmTable.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(fmp,
                    "ID: " + (fmp.fmTable.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Beneficiary: " + (fmp.fmTable.getValueAt(dataRow, 1).toString()) + "\n"
                    + "First Name: " + (fmp.fmTable.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Middle Name: " + (fmp.fmTable.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Last Name: " + (fmp.fmTable.getValueAt(dataRow, 4).toString()) + "\n"
                    + "Rel to HOF: " + (fmp.fmTable.getValueAt(dataRow, 5).toString()) + "\n"
                    + "Age: " + (fmp.fmTable.getValueAt(dataRow, 6).toString()) + "\n"
                    + "Sex: " + (fmp.fmTable.getValueAt(dataRow, 7).toString()) + "\n"
                    + "Highest Ed Att: " + (fmp.fmTable.getValueAt(dataRow, 8).toString()) + "\n"
                    + "Occ Skills: " + (fmp.fmTable.getValueAt(dataRow, 9).toString()) + "\n"
                    + "Remarks: " + (fmp.fmTable.getValueAt(dataRow, 10).toString()),
                    "Family Member Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(fmp, "Please select Family Member to edit.");
        }
    }

}
