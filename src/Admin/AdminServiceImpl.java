package Admin;

import Util.SearchModel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import net.proteanit.sql.DbUtils;

public class AdminServiceImpl implements AdminService {

    AdminPanel ap;
    AdminDAO dao = new AdminDAOImpl();

    public AdminServiceImpl(AdminPanel ap) {
        this.ap = ap;
        
        displayAllAdmin();
    }

    @Override
    public void clearFields() {
        ap.unTF.setText("");
        ap.fNameTF.setText("");
        ap.mNameTF.setText("");
        ap.lNameTF.setText("");
        ap.departmentCB.setSelectedIndex(0);
        ap.positionCB.setSelectedIndex(0);
        ap.jPasswordField1.setText("");
        ap.jPasswordField2.setText("");

        //clears all fields in editAdminDialog
        ap.unTF1.setText("");
        ap.fNameTF1.setText("");
        ap.mNameTF1.setText("");
        ap.lNameTF1.setText("");
        ap.departmentCB1.setSelectedIndex(0);
        ap.positionCB1.setSelectedIndex(0);
        ap.jPasswordField3.setText("");
        ap.jPasswordField4.setText("");
    }

    void displayAllAdmin() {
        //this is to load all the schedules in the database upon selecting the Event Scheduler in the menu bar
        ResultSet rs = dao.getAllAdmin();
        ap.adminTable.setModel(DbUtils.resultSetToTableModel(rs));
        new SearchModel(ap, ap.adminTable, ap.searchTF, rs);
    }

    @Override
    public void delete() {
        int dataRow = ap.adminTable.getSelectedRow();
        if (dataRow >= 0) {
            String adminID = ap.adminTable.getValueAt(dataRow, 0).toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(ap, "Would You Like to "
                    + "Delete Admin: " + adminID + "?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                dao.deleteAdmin(adminID);
                displayAllAdmin();
            }
        } else {
            JOptionPane.showMessageDialog(ap, "Please select admin to delete.");
        }
    }

    @Override
    public void editView() {
        int dataRow = ap.adminTable.getSelectedRow();
        if (dataRow >= 0) {
            ap.oldIdTF.setText(ap.adminTable.getValueAt(dataRow, 0).toString());
            ap.unTF1.setText(ap.adminTable.getValueAt(dataRow, 1).toString());
            ap.fNameTF1.setText(ap.adminTable.getValueAt(dataRow, 2).toString());
            ap.mNameTF1.setText(ap.adminTable.getValueAt(dataRow, 3).toString());
            ap.lNameTF1.setText(ap.adminTable.getValueAt(dataRow, 4).toString());
            ap.departmentCB1.setSelectedItem(ap.adminTable.getValueAt(dataRow, 5).toString());
            ap.positionCB1.setSelectedItem(ap.adminTable.getValueAt(dataRow, 6).toString());
            ap.editAdminDialog.setTitle("Edit Admin");
            ap.editAdminDialog.setModal(true);
            ap.editAdminDialog.pack();
            ap.editAdminDialog.setLocationRelativeTo(ap);
            ap.editAdminDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(ap, "Please select admin to edit.");
        }
    }

    @Override
    public void openAddAdminDialog() {
        ap.addAdminDialog.setTitle("Add Admin");
        ap.addAdminDialog.setModal(true);
        ap.addAdminDialog.pack();
        ap.addAdminDialog.setLocationRelativeTo(ap);
        ap.addAdminDialog.setVisible(true);
    }

    @Override
    public void viewAdmin() {
        int dataRow = ap.adminTable.getSelectedRow();
        if (dataRow >= 0) {
            JOptionPane.showMessageDialog(ap,
                    "Admin ID: " + (ap.adminTable.getValueAt(dataRow, 0).toString()) + "\n"
                    + "Username: " + (ap.adminTable.getValueAt(dataRow, 1).toString()) + "\n"
                    + "First Name: " + (ap.adminTable.getValueAt(dataRow, 2).toString()) + "\n"
                    + "Middle Name: " + (ap.adminTable.getValueAt(dataRow, 3).toString()) + "\n"
                    + "Last Name: " + (ap.adminTable.getValueAt(dataRow, 4).toString()) + "\n"
                    + "Department: " + (ap.adminTable.getValueAt(dataRow, 5).toString()) + "\n"
                    + "Position: " + (ap.adminTable.getValueAt(dataRow, 6).toString()),
                    "Admin Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(ap, "Please select admin to edit.");
        }
    }

    @Override
    public void popupMenu(MouseEvent e) {
        System.out.println();
        int r = ap.adminTable.rowAtPoint(e.getPoint());
        if (r >= 0 && r < ap.adminTable.getRowCount()) {
            ap.adminTable.setRowSelectionInterval(r, r);
        } else {
            ap.adminTable.clearSelection();
        }

        int rowindex = ap.adminTable.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.show(ap, e.getX(), e.getY());
            ap.adminTable.setComponentPopupMenu(ap.popupmenu);
        }
    }

    @Override
    public void popupVisible(PopupMenuEvent e) {
        SwingUtilities.invokeLater(() -> {
            int rowAtPoint = ap.adminTable.rowAtPoint(SwingUtilities.
                    convertPoint(ap.popupmenu, new Point(0, 0), ap.adminTable));
            if (rowAtPoint > -1) {
                ap.adminTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
            }
        });
    }

    @Override
    public void save() {
        if (ap.jPasswordField1.getText().equals(ap.jPasswordField2.getText())) {
            AdminModel admin = new AdminModel();
            admin.setUsername(ap.unTF.getText());
            admin.setPassword(ap.jPasswordField1.getText());
            admin.setFname(ap.fNameTF.getText());
            admin.setMname(ap.mNameTF.getText());
            admin.setLname(ap.lNameTF.getText());
            admin.setDepartment(ap.departmentCB.getSelectedItem().toString());
            admin.setPosition(ap.positionCB.getSelectedItem().toString());
            dao.saveAdmin(admin);
            displayAllAdmin();
        } else {
            JOptionPane.showMessageDialog(ap, "Password Mismatch", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void update() {
        if (ap.jPasswordField3.getText().equals(ap.jPasswordField4.getText())) {
            AdminModel admin = new AdminModel();
            admin.setOldID(ap.oldIdTF.getText());
            admin.setUsername(ap.unTF1.getText());
            admin.setPassword(ap.jPasswordField3.getText());
            admin.setFname(ap.fNameTF1.getText());
            admin.setMname(ap.mNameTF1.getText());
            admin.setLname(ap.lNameTF1.getText());
            admin.setDepartment(ap.departmentCB1.getSelectedItem().toString());
            admin.setPosition(ap.positionCB1.getSelectedItem().toString());
            dao.updateAdmin(admin);
            displayAllAdmin();
        } else {
            JOptionPane.showMessageDialog(ap, "Password Mismatch", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
