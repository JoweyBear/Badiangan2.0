package Util;

import DBConnection.DB;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BeneCBBHandler extends KeyAdapter {

    private final JComboBox<String> comboBox;
    private final List<String> list = new ArrayList<>();
    private boolean shouldHide;
    private final DeEncryption de = new DeEncryption();

    public BeneCBBHandler(JComboBox<String> combo) {
        super();
        this.comboBox = combo;
//    for (int i = 0; i < comboBox.getModel().getSize(); i++) {
//      list.add((String) comboBox.getItemAt(i));
//    }
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DB.getConnection();
            String sql = "SELECT bene_id, fname, mname, lname FROM beneficiary";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("bene_id");
                String fname = de.decrypt(rs.getString("fname"));
                String mname = de.decrypt(rs.getString("mname"));
                String lname = de.decrypt(rs.getString("lname"));

                String fullName = String.format(" (ID:%s) %s %s %s", id, fname, mname, lname);
                list.add(fullName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BeneCBBHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        EventQueue.invokeLater(() -> {
            String text = ((JTextField) e.getComponent()).getText();
            ComboBoxModel<String> m;
            if (text.isEmpty()) {
                String[] array = list.toArray(new String[list.size()]);
                m = new DefaultComboBoxModel<String>(array);
                setSuggestionModel(comboBox, m, "");
                comboBox.hidePopup();
            } else {
                m = getSuggestedModel(list, text);
                if (m.getSize() == 0 || shouldHide) {
                    comboBox.hidePopup();
                } else {
                    setSuggestionModel(comboBox, m, text);
                    comboBox.showPopup();
                }
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextField textField = (JTextField) e.getComponent();
        String text = textField.getText();
        shouldHide = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                for (String s : list) {
                    if (s.startsWith(text)) {
                        textField.setText(s);
                        return;
                    }
                }
                break;
            case KeyEvent.VK_ENTER:
                if (!list.contains(text)) {
                    //list.add(text);
                    Collections.sort(list);
                    //setSuggestionModel(comboBox, new DefaultComboBoxModel(list), text);
                    setSuggestionModel(comboBox, getSuggestedModel(list, text), text);
                }
                shouldHide = true;
                break;
            case KeyEvent.VK_ESCAPE:
                shouldHide = true;
                break;
            default:
                break;
        }
    }

    private static void setSuggestionModel(
            JComboBox<String> comboBox, ComboBoxModel<String> mdl, String str) {
        comboBox.setModel(mdl);
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(str);
    }

    private static ComboBoxModel<String> getSuggestedModel(List<String> list, String text) {
        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
        for (String s : list) {
            if (s.toLowerCase().contains(text.toLowerCase())) {
                m.addElement(s);
            }
        }
        return m;
    }
}
