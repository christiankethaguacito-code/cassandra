package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;
import com.isulan.financial.model.Transaction;
import com.isulan.financial.model.User;
import com.isulan.financial.service.TransactionService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Transactions Panel for Desktop Application
 * Manages income and expense transactions
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
public class TransactionsPanel extends JPanel {

    private MainFrame mainFrame;
    private JTable transactionsTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton refreshButton;

    /**
     * Constructor - Creates the transactions panel
     * 
     * @param mainFrame Reference to main frame
     */
    public TransactionsPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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
        
        JLabel titleLabel = new JLabel("All Transactions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(102, 126, 234));
        topPanel.add(titleLabel, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Date", "Type", "Category", "Description", "Amount"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        transactionsTable = new JTable(tableModel);
        transactionsTable.setFont(new Font("Arial", Font.PLAIN, 12));
        transactionsTable.setRowHeight(25);
        transactionsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        transactionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setBackground(Color.WHITE);

        addButton = createButton("Add Transaction", new Color(102, 126, 234));
        addButton.addActionListener(e -> showAddTransactionDialog());

        editButton = createButton("Edit Transaction", new Color(108, 117, 125));
        editButton.addActionListener(e -> showEditTransactionDialog());
        editButton.setEnabled(false);

        deleteButton = createButton("Delete Transaction", new Color(220, 53, 69));
        deleteButton.addActionListener(e -> deleteTransaction());
        deleteButton.setEnabled(false);

        refreshButton = createButton("Refresh", new Color(40, 167, 69));
        refreshButton.addActionListener(e -> refreshData());

        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(refreshButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        // Table selection listener
        transactionsTable.getSelectionModel().addListSelectionListener(e -> {
            boolean hasSelection = transactionsTable.getSelectedRow() != -1;
            editButton.setEnabled(hasSelection);
            deleteButton.setEnabled(hasSelection);
        });
    }

    /**
     * Create a styled button
     * 
     * @param text Button text
     * @param color Button color
     * @return JButton
     */
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Refresh transactions data
     */
    public void refreshData() {
        User user = SpringContextUtil.getCurrentUser();
        TransactionService transactionService = SpringContextUtil.getTransactionService();

        List<Transaction> transactions = transactionService.getUserTransactions(user);

        // Clear table
        tableModel.setRowCount(0);

        // Add transactions to table
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Transaction transaction : transactions) {
            Object[] row = {
                transaction.getId(),
                transaction.getDate().format(formatter),
                transaction.getType(),
                transaction.getCategory(),
                transaction.getDescription(),
                String.format("₱%.2f", transaction.getAmount())
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Show dialog to add new transaction
     */
    private void showAddTransactionDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add Transaction", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Type
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Type:"), gbc);
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"INCOME", "EXPENSE"});
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(typeCombo, gbc);

        // Category
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Category:"), gbc);
        JTextField categoryField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(categoryField, gbc);

        // Description
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Description:"), gbc);
        JTextArea descriptionArea = new JTextArea(3, 20);
        JScrollPane descScrollPane = new JScrollPane(descriptionArea);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(descScrollPane, gbc);

        // Amount
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Amount:"), gbc);
        JTextField amountField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(amountField, gbc);

        // Date
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        JTextField dateField = new JTextField(LocalDate.now().toString(), 20);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(dateField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                String type = (String) typeCombo.getSelectedItem();
                String category = categoryField.getText();
                String description = descriptionArea.getText();
                Double amount = Double.parseDouble(amountField.getText());
                LocalDate date = LocalDate.parse(dateField.getText());

                User user = SpringContextUtil.getCurrentUser();
                TransactionService transactionService = SpringContextUtil.getTransactionService();
                transactionService.addTransaction(user, type, category, description, amount, date);

                JOptionPane.showMessageDialog(dialog, "Transaction added successfully!");
                dialog.dispose();
                refreshData();
                mainFrame.refreshData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    /**
     * Show dialog to edit selected transaction
     */
    private void showEditTransactionDialog() {
        int selectedRow = transactionsTable.getSelectedRow();
        if (selectedRow == -1) return;

        Long transactionId = (Long) tableModel.getValueAt(selectedRow, 0);
        
        // Get transaction details
        User user = SpringContextUtil.getCurrentUser();
        TransactionService transactionService = SpringContextUtil.getTransactionService();
        Transaction transaction = transactionService.getUserTransactions(user).stream()
                .filter(t -> t.getId().equals(transactionId))
                .findFirst()
                .orElse(null);

        if (transaction == null) {
            JOptionPane.showMessageDialog(this, "Transaction not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Transaction", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Type
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Type:"), gbc);
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"INCOME", "EXPENSE"});
        typeCombo.setSelectedItem(transaction.getType());
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(typeCombo, gbc);

        // Category
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Category:"), gbc);
        JTextField categoryField = new JTextField(transaction.getCategory(), 20);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(categoryField, gbc);

        // Description
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Description:"), gbc);
        JTextArea descriptionArea = new JTextArea(transaction.getDescription(), 3, 20);
        JScrollPane descScrollPane = new JScrollPane(descriptionArea);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(descScrollPane, gbc);

        // Amount
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Amount:"), gbc);
        JTextField amountField = new JTextField(transaction.getAmount().toString(), 20);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(amountField, gbc);

        // Date
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        JTextField dateField = new JTextField(transaction.getDate().toString(), 20);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(dateField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                String type = (String) typeCombo.getSelectedItem();
                String category = categoryField.getText();
                String description = descriptionArea.getText();
                Double amount = Double.parseDouble(amountField.getText());
                LocalDate date = LocalDate.parse(dateField.getText());

                transactionService.updateTransaction(transactionId, type, category, description, amount, date);

                JOptionPane.showMessageDialog(dialog, "Transaction updated successfully!");
                dialog.dispose();
                refreshData();
                mainFrame.refreshData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    /**
     * Delete selected transaction
     */
    private void deleteTransaction() {
        int selectedRow = transactionsTable.getSelectedRow();
        if (selectedRow == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this transaction?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Long transactionId = (Long) tableModel.getValueAt(selectedRow, 0);
            
            try {
                TransactionService transactionService = SpringContextUtil.getTransactionService();
                transactionService.deleteTransaction(transactionId);
                
                JOptionPane.showMessageDialog(this, "Transaction deleted successfully!");
                refreshData();
                mainFrame.refreshData();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
