package com.isulan.financial.desktop;

import com.isulan.financial.desktop.ui.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DesktopApplication {

    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
