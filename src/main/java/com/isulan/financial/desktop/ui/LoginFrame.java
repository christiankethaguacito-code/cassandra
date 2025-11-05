package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;
import com.isulan.financial.model.User;
import com.isulan.financial.service.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Login Frame for Desktop Application
 * Provides user authentication interface
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel statusLabel;

    /**
     * Constructor - Creates the login frame
     */
    public LoginFrame() {
        initComponents();
        SpringContextUtil.initializeContext();
    }

    /**
     * Initialize GUI components
     */
    private void initComponents() {
        setTitle("Login - Financial Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(102, 126, 234));

        // Title
        JLabel titleLabel = new JLabel("Financial Manager");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Isulan, Sultan Kudarat");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(passwordField, gbc);

        // Status label
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(102, 126, 234));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(this::loginButtonActionPerformed);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(108, 117, 125));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setFocusPainted(false);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.addActionListener(this::registerButtonActionPerformed);

        // Add components to main panel
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(statusLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(registerButton);

        add(mainPanel);

        // Enter key listener for password field
        passwordField.addActionListener(e -> loginButtonActionPerformed(null));
    }

    /**
     * Handle login button click
     * 
     * @param evt Action event
     */
    private void loginButtonActionPerformed(ActionEvent evt) {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter email and password");
            statusLabel.setForeground(Color.RED);
            return;
        }

        try {
            UserService userService = SpringContextUtil.getUserService();
            User user = userService.authenticateUser(email, password);
            
            SpringContextUtil.setCurrentUser(user);
            
            // Open main frame
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            
            // Close login frame
            this.dispose();
        } catch (IllegalArgumentException e) {
            statusLabel.setText("Invalid email or password");
            statusLabel.setForeground(Color.RED);
            passwordField.setText("");
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            statusLabel.setForeground(Color.RED);
            e.printStackTrace();
        }
    }

    /**
     * Handle register button click
     * 
     * @param evt Action event
     */
    private void registerButtonActionPerformed(ActionEvent evt) {
        RegisterFrame registerFrame = new RegisterFrame(this);
        registerFrame.setVisible(true);
        this.setVisible(false);
    }
}
