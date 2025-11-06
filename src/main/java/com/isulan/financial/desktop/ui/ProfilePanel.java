package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;
import com.isulan.financial.model.User;
import com.isulan.financial.service.UserService;
import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {

    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;

    
    public ProfilePanel(MainFrame mainFrame) {
        initComponents();
        refreshData();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        profileCard = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        confirmPasswordLabel = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        statusLabel = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 242, 245));

        titlePanel.setBackground(new java.awt.Color(240, 242, 245));
        titlePanel.setOpaque(true);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(102, 126, 234));
        titleLabel.setText("👤 User Profile");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        profileCard.setBackground(new java.awt.Color(255, 255, 255));
        profileCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nameLabel.setText("Full Name:");

        nameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        emailLabel.setText("Email:");

        emailField.setEditable(false);
        emailField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailField.setForeground(new java.awt.Color(150, 150, 150));

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passwordLabel.setText("New Password (leave blank to keep current):");

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        confirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmPasswordLabel.setText("Confirm New Password:");

        confirmPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        statusLabel.setForeground(new java.awt.Color(255, 0, 0));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText(" ");

        javax.swing.GroupLayout profileCardLayout = new javax.swing.GroupLayout(profileCard);
        profileCard.setLayout(profileCardLayout);
        profileCardLayout.setHorizontalGroup(
            profileCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileCardLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(profileCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(emailLabel)
                    .addComponent(emailField)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField)
                    .addComponent(confirmPasswordLabel)
                    .addComponent(confirmPasswordField)
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        profileCardLayout.setVerticalGroup(
            profileCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileCardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(confirmPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(statusLabel)
                .addGap(30, 30, 30))
        );

        buttonPanel.setBackground(new java.awt.Color(255, 255, 255));
        buttonPanel.setOpaque(false);

        updateButton.setBackground(new java.awt.Color(102, 126, 234));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("💾 Update Profile");
        updateButton.setOpaque(true);
        updateButton.setBorderPainted(false);
        updateButton.setFocusPainted(false);

        buttonPanel.setBackground(new java.awt.Color(240, 242, 245));

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(profileCard, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(profileCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private JPanel createProfileSection() {
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBackground(Color.WHITE);
        section.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(102, 126, 234), 2),
                "Profile Information",
                0, 0,
                new Font("Arial", Font.BOLD, 16),
                new Color(102, 126, 234)
        ));
        section.setMaximumSize(new Dimension(600, 250));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        nameField = new JTextField(30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        emailField = new JTextField(30);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton updateButton = new JButton("Update Profile");
        updateButton.setBackground(new Color(102, 126, 234));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setFocusPainted(false);
        updateButton.addActionListener(e -> updateProfile());
        formPanel.add(updateButton, gbc);

        section.add(formPanel);
        return section;
    }

    
    private JPanel createPasswordSection() {
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBackground(Color.WHITE);
        section.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(102, 126, 234), 2),
                "Change Password",
                0, 0,
                new Font("Arial", Font.BOLD, 16),
                new Color(102, 126, 234)
        ));
        section.setMaximumSize(new Dimension(600, 300));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel currentPasswordLabel = new JLabel("Current Password:");
        currentPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(currentPasswordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        currentPasswordField = new JPasswordField(30);
        currentPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(currentPasswordField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(newPasswordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        newPasswordField = new JPasswordField(30);
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(newPasswordField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        confirmPasswordField = new JPasswordField(30);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(confirmPasswordField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(new Color(108, 117, 125));
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 14));
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.addActionListener(e -> changePassword());
        formPanel.add(changePasswordButton, gbc);

        section.add(formPanel);
        return section;
    }

    
    public void refreshData() {
        User user = SpringContextUtil.getCurrentUser();
        if (user != null) {
            UserService userService = SpringContextUtil.getUserService();
            user = userService.findById(user.getId()).orElse(user);
            SpringContextUtil.setCurrentUser(user);

            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
        }
    }

    
    private void updateProfile() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = SpringContextUtil.getCurrentUser();
            UserService userService = SpringContextUtil.getUserService();
            
            User updatedUser = userService.updateProfile(user.getId(), name, email);
            SpringContextUtil.setCurrentUser(updatedUser);
            
            JOptionPane.showMessageDialog(this,
                    "Profile updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            
            refreshData();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error updating profile: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void changePassword() {
        String currentPassword = new String(currentPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all password fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "New passwords do not match",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(this,
                    "Password must be at least 6 characters",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = SpringContextUtil.getCurrentUser();
            UserService userService = SpringContextUtil.getUserService();
            
            userService.updatePassword(user.getId(), currentPassword, newPassword);
            
            JOptionPane.showMessageDialog(this,
                    "Password changed successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            currentPasswordField.setText("");
            newPasswordField.setText("");
            confirmPasswordField.setText("");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error changing password: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPanel profileCard;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
