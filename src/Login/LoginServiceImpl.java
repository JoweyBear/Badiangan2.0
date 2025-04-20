package Login;

import Main.MainFrame;
import Util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginServiceImpl implements LoginService {

    String adminName;
    String adminPos;
    LoginFrame lf;
    MainFrame mf;
    int progVal = 0;
    boolean flag = true;
    LoginDAO dao = new LoginDAOImpl();

    public LoginServiceImpl(LoginFrame lf) {
        this.lf = lf;

    }

    @Override
    public void loginNow() {
        lf.loginBtn.setEnabled(false);
        String[] name = new String[4];
        name = dao.loginAdmin(lf.unTF.getText(), lf.pwPF.getText());
        if (name[0] != null && name[1] != null && name[2] != null && name[3] != null) {
            mf = new MainFrame();
            Timer t = new Timer(mf);
            t.setTime();
            mf.deptLbl.setText(name[1]);
            mf.fnameLbl.setText(name[2]);
            mf.adminIDTF.setText(name[3]);
            mf.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //setting up the user config based on the dept
            mf.setConfig();
            lf.setVisible(false);
            mf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Wrong username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        lf.loginBtn.setEnabled(true);
    }

}
