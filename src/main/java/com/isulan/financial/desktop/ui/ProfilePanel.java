package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;
import com.isulan.financial.model.User;
import com.isulan.financial.service.UserService;
import javax.swing.*;
import java.awt.*;

/**
 * Profile Panel for Desktop Application
 * Manages user profile and settings
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
public class ProfilePanel extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;

    /**
     * Constructor - Creates the profile panel
     * 
     * @param mainFrame Reference to main frame (for future use)
     */
    public ProfilePanel(MainFrame mainFrame) {
        initComponents();
        refreshData();
    }

    /**
     * Initialize GUI components
     */
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        // Top panel with title
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(102, 126, 234));
        topPanel.add(titleLabel, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // Profile Information Section
        JPanel profileSection = createProfileSection();
        contentPanel.add(profileSection);
        contentPanel.add(Box.createVerticalStrut(30));

        // Change Password Section
        JPanel passwordSection = createPasswordSection();
        contentPanel.add(passwordSection);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Create profile information section
     * 
     * @return JPanel for profile section
     */
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

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        nameField = new JTextField(30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameField, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        emailField = new JTextField(30);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(emailField, gbc);

        // Update button
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

    /**
     * Create password change section
     * 
     * @return JPanel for password section
     */
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

        // Current password
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel currentPasswordLabel = new JLabel("Current Password:");
        currentPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(currentPasswordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        currentPasswordField = new JPasswordField(30);
        currentPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(currentPasswordField, gbc);

        // New password
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(newPasswordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        newPasswordField = new JPasswordField(30);
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(newPasswordField, gbc);

        // Confirm password
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        confirmPasswordField = new JPasswordField(30);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(confirmPasswordField, gbc);

        // Change password button
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

    /**
     * Refresh profile data
     */
    public void refreshData() {
        User user = SpringContextUtil.getCurrentUser();
        if (user != null) {
            // Refresh user from database
            UserService userService = SpringContextUtil.getUserService();
            user = userService.findById(user.getId()).orElse(user);
            SpringContextUtil.setCurrentUser(user);

            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
        }
    }

    /**
     * Update profile information
     */
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

    /**
     * Change user password
     */
    private void changePassword() {
        String currentPassword = new String(currentPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Validation
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
            
            // Clear password fields
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
}
