package com.isulan.financial.desktop;

import com.isulan.financial.desktop.ui.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main entry point for the Desktop Swing Application
 * Launches the financial management desktop GUI
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
public class DesktopApplication {

    /**
     * Main method to launch the desktop application
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch the login frame on the EDT
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
