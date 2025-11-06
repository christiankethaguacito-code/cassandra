package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;

public class MainFrame extends javax.swing.JFrame {

    private DashboardPanel dashboardPanel;
    private TransactionsPanel transactionsPanel;
    private ProfilePanel profilePanel;

    
    public MainFrame() {
        initComponents();
        initializePanels();
        setLocationRelativeTo(null);
    }

    
    private void initializePanels() {
        dashboardPanel = new DashboardPanel(this);
        transactionsPanel = new TransactionsPanel(this);
        profilePanel = new ProfilePanel(this);

        tabbedPane.addTab("📊 Dashboard", dashboardPanel);
        tabbedPane.addTab("💳 Transactions", transactionsPanel);
        tabbedPane.addTab("👤 Profile", profilePanel);
        dashboardPanel.refreshData();
    }

    
    public void switchToTransactionsTab() {
        tabbedPane.setSelectedIndex(1);
    }

    
    public void refreshAllPanels() {
        dashboardPanel.refreshData();
        transactionsPanel.refreshData();
        profilePanel.refreshData();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        logoutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Financial Management System");

        getContentPane().setBackground(new java.awt.Color(240, 242, 245));

        tabbedPane.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tabbedPane.setBackground(new java.awt.Color(240, 242, 245));

        fileMenu.setText("File");

        logoutMenuItem.setText("Logout");
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(logoutMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuItemActionPerformed
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            SpringContextUtil.setCurrentUser(null);
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logoutMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to exit?",
            "Confirm Exit",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
