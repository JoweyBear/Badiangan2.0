
package Brgy;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.event.PopupMenuListener;
import Util.MySpinner;

public class BrgyPanel extends javax.swing.JPanel {

    /**
     * Creates new form OwnerPanel
     */
    public BrgyPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addDialog = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        editDialog = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        nameTF1 = new javax.swing.JTextField();
        okBtn1 = new javax.swing.JButton();
        cancelBtn1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        idLbl = new javax.swing.JLabel();
        popUpMenu = new javax.swing.JPopupMenu();
        viewMenuItem = new javax.swing.JMenuItem();
        editMenuItem = new javax.swing.JMenuItem();
        addMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        searchTF = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();

        addDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addDialog.setTitle("Add Crop/Tree");
        addDialog.setResizable(false);

        jPanel6.setBackground(new java.awt.Color(69, 73, 84));

        jPanel7.setBackground(new java.awt.Color(69, 73, 84));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Brgy Info", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel48.setText("Name:");

        okBtn.setBackground(new java.awt.Color(51, 255, 255));
        okBtn.setText("OK");

        cancelBtn.setBackground(new java.awt.Color(51, 255, 255));
        cancelBtn.setText("Cancel");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(okBtn)
                .addGap(18, 18, 18)
                .addComponent(cancelBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout addDialogLayout = new javax.swing.GroupLayout(addDialog.getContentPane());
        addDialog.getContentPane().setLayout(addDialogLayout);
        addDialogLayout.setHorizontalGroup(
            addDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addDialogLayout.setVerticalGroup(
            addDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        editDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        editDialog.setTitle("Add Crop/Tree");
        editDialog.setResizable(false);

        jPanel8.setBackground(new java.awt.Color(69, 73, 84));

        jPanel9.setBackground(new java.awt.Color(69, 73, 84));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edit Brgy Info", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel49.setText("Name:");

        okBtn1.setBackground(new java.awt.Color(51, 255, 255));
        okBtn1.setText("OK");

        cancelBtn1.setBackground(new java.awt.Color(51, 255, 255));
        cancelBtn1.setText("Cancel");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID:");

        idLbl.setForeground(new java.awt.Color(255, 255, 255));
        idLbl.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTF1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addComponent(idLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(okBtn1)
                .addGap(18, 18, 18)
                .addComponent(cancelBtn1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okBtn1)
                    .addComponent(cancelBtn1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout editDialogLayout = new javax.swing.GroupLayout(editDialog.getContentPane());
        editDialog.getContentPane().setLayout(editDialogLayout);
        editDialogLayout.setHorizontalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        editDialogLayout.setVerticalGroup(
            editDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        viewMenuItem.setText("View");
        popUpMenu.add(viewMenuItem);

        editMenuItem.setText("Edit");
        popUpMenu.add(editMenuItem);

        addMenuItem.setText("Add");
        popUpMenu.add(addMenuItem);

        deleteMenuItem.setText("Delete");
        popUpMenu.add(deleteMenuItem);

        jPanel1.setBackground(new java.awt.Color(69, 73, 84));

        addBtn.setBackground(new java.awt.Color(51, 255, 255));
        addBtn.setText("Add Brgy");

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setEnabled(false);
        table.setGridColor(new java.awt.Color(0, 102, 102));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        editBtn.setBackground(new java.awt.Color(51, 255, 255));
        editBtn.setText("Edit Brgy");

        deleteBtn.setBackground(new java.awt.Color(51, 255, 255));
        deleteBtn.setText("Delete Brgy");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search");

        jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Barangay");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBtn)
                        .addGap(18, 18, 18)
                        .addComponent(editBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(deleteBtn)
                    .addComponent(addBtn)
                    .addComponent(editBtn)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
   
    public void allListener(ActionListener a, PopupMenuListener p, MouseListener m)
    {
        //Action Listeners
        addBtn.addActionListener(a);
        okBtn.addActionListener(a);
        okBtn1.addActionListener(a);
        editBtn.addActionListener(a);
        deleteBtn.addActionListener(a);
        cancelBtn.addActionListener(a);
        cancelBtn1.addActionListener(a);
        viewMenuItem.addActionListener(a);
        editMenuItem.addActionListener(a);
        addMenuItem.addActionListener(a);
        deleteMenuItem.addActionListener(a);

        
        //PopUpMenu Listener
        popUpMenu.addPopupMenuListener(p);
        
        //Mouse Listener
        table.addMouseListener(m);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addBtn;
    public javax.swing.JDialog addDialog;
    public javax.swing.JMenuItem addMenuItem;
    public javax.swing.JButton cancelBtn;
    public javax.swing.JButton cancelBtn1;
    public javax.swing.JButton deleteBtn;
    public javax.swing.JMenuItem deleteMenuItem;
    public javax.swing.JButton editBtn;
    public javax.swing.JDialog editDialog;
    public javax.swing.JMenuItem editMenuItem;
    public javax.swing.JLabel idLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField nameTF;
    public javax.swing.JTextField nameTF1;
    public javax.swing.JButton okBtn;
    public javax.swing.JButton okBtn1;
    public javax.swing.JPopupMenu popUpMenu;
    public javax.swing.JTextField searchTF;
    public javax.swing.JTable table;
    public javax.swing.JMenuItem viewMenuItem;
    // End of variables declaration//GEN-END:variables

}
