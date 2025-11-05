package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;
import com.isulan.financial.model.User;
import javax.swing.*;
import java.awt.*;

/**
 * Main Frame for Desktop Application
 * Contains tabs for Dashboard, Transactions, and Profile
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;
    private DashboardPanel dashboardPanel;
    private TransactionsPanel transactionsPanel;
    private ProfilePanel profilePanel;

    /**
     * Constructor - Creates the main application frame
     */
    public MainFrame() {
        initComponents();
        loadUserData();
    }

    /**
     * Initialize GUI components
     */
    private void initComponents() {
        User user = SpringContextUtil.getCurrentUser();
        
        setTitle("Financial Manager - " + user.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Create panels
        dashboardPanel = new DashboardPanel(this);
        transactionsPanel = new TransactionsPanel(this);
        profilePanel = new ProfilePanel(this);

        // Add tabs
        tabbedPane.addTab("📊 Dashboard", dashboardPanel);
        tabbedPane.addTab("💰 Transactions", transactionsPanel);
        tabbedPane.addTab("👤 Profile", profilePanel);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(e -> refreshData());
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(e -> logout());
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        
        fileMenu.add(refreshMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(logoutMenuItem);
        fileMenu.add(exitMenuItem);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutMenuItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);

        add(tabbedPane);
    }

    /**
     * Load user data and refresh all panels
     */
    private void loadUserData() {
        refreshData();
    }

    /**
     * Refresh all panel data
     */
    public void refreshData() {
        if (dashboardPanel != null) {
            dashboardPanel.refreshData();
        }
        if (transactionsPanel != null) {
            transactionsPanel.refreshData();
        }
        if (profilePanel != null) {
            profilePanel.refreshData();
        }
    }

    /**
     * Logout and return to login screen
     */
    private void logout() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            SpringContextUtil.clearCurrentUser();
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.dispose();
        }
    }

    /**
     * Show about dialog
     */
    private void showAbout() {
        JOptionPane.showMessageDialog(this,
                "Personal Financial Management System\n" +
                "Version 1.0.0\n\n" +
                "Developed for Isulan, Sultan Kudarat\n" +
                "© 2025 All Rights Reserved\n\n" +
                "Manage your finances with ease!",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Switch to transactions tab
     */
    public void switchToTransactionsTab() {
        tabbedPane.setSelectedIndex(1);
    }
}
