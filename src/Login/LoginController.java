package Login;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginController {

    LoginFrame lf;
    LoginService service;
    
        public LoginController(LoginFrame lf)
    { 
        this.lf = lf;
        service =  new LoginServiceImpl(lf);
        this.lf.loginBtn.addActionListener((ActionEvent e) -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                service.loginNow();
            });
        });
        
        this.lf.pwPF.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.submit(() -> {
                        service.loginNow();
                    });
                }
            }
        });
    }
}
