package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;
import com.isulan.financial.model.Transaction;
import com.isulan.financial.model.User;
import com.isulan.financial.service.TransactionService;
import com.isulan.financial.service.ReceiptService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Desktop;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionsPanel extends JPanel {

    private MainFrame mainFrame;
    private DefaultTableModel tableModel;
    private JButton refreshButton;

    
    public TransactionsPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        this.tableModel = (DefaultTableModel) transactionTable.getModel();
        transactionTable.getColumnModel().getColumn(0).setMinWidth(0);
        transactionTable.getColumnModel().getColumn(0).setMaxWidth(0);
        transactionTable.getColumnModel().getColumn(0).setWidth(0);
        addButton.addActionListener(e -> showAddTransactionDialog());
        editButton.addActionListener(e -> showEditTransactionDialog());
        deleteButton.addActionListener(e -> deleteTransaction());
        receiptButton.addActionListener(e -> generateReceipt());
        transactionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    showEditTransactionDialog();
                }
            }
        });
        
        refreshData();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        receiptButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        transactionTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(240, 242, 245));

        titlePanel.setBackground(new java.awt.Color(240, 242, 245));
        titlePanel.setOpaque(false);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(102, 126, 234));
        titleLabel.setText("💳 Transaction Management");

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

        buttonPanel.setBackground(new java.awt.Color(240, 242, 245));
        buttonPanel.setOpaque(true);

        addButton.setBackground(new java.awt.Color(102, 126, 234));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("➕ Add");
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(54, 162, 192));
        editButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("✏️ Edit");
        editButton.setOpaque(true);
        editButton.setBorderPainted(false);
        editButton.setFocusPainted(false);

        deleteButton.setBackground(new java.awt.Color(220, 59, 83));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("🗑️ Delete");
        deleteButton.setOpaque(true);
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);

        receiptButton.setBackground(new java.awt.Color(153, 102, 255));
        receiptButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        receiptButton.setForeground(new java.awt.Color(255, 255, 255));
        receiptButton.setText("🧾 Receipt");
        receiptButton.setOpaque(true);
        receiptButton.setBorderPainted(false);
        receiptButton.setFocusPainted(false);
        receiptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(receiptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(receiptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        transactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Type", "Category", "Description", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        transactionTable.setRowHeight(30);
        transactionTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(transactionTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void receiptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptButtonActionPerformed
    }//GEN-LAST:event_receiptButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonActionPerformed

    
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.GREEN);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        return button;
    }

    
    public void refreshData() {
        User user = SpringContextUtil.getCurrentUser();
        TransactionService transactionService = SpringContextUtil.getTransactionService();

        List<Transaction> transactions = transactionService.getUserTransactions(user);
        tableModel.setRowCount(0);
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

    
    private void showAddTransactionDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add Transaction", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Type:"), gbc);
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"INCOME", "EXPENSE"});
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(typeCombo, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Category:"), gbc);
        JTextField categoryField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(categoryField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Description:"), gbc);
        JTextArea descriptionArea = new JTextArea(3, 20);
        JScrollPane descScrollPane = new JScrollPane(descriptionArea);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(descScrollPane, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Amount:"), gbc);
        JTextField amountField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(amountField, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        JTextField dateField = new JTextField(LocalDate.now().toString(), 20);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(dateField, gbc);
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
                mainFrame.refreshAllPanels();
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

    
    private void showEditTransactionDialog() {
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow == -1) return;

        Long transactionId = (Long) tableModel.getValueAt(selectedRow, 0);
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
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Type:"), gbc);
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"INCOME", "EXPENSE"});
        typeCombo.setSelectedItem(transaction.getType());
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(typeCombo, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Category:"), gbc);
        JTextField categoryField = new JTextField(transaction.getCategory(), 20);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(categoryField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Description:"), gbc);
        JTextArea descriptionArea = new JTextArea(transaction.getDescription(), 3, 20);
        JScrollPane descScrollPane = new JScrollPane(descriptionArea);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(descScrollPane, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Amount:"), gbc);
        JTextField amountField = new JTextField(transaction.getAmount().toString(), 20);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(amountField, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        JTextField dateField = new JTextField(transaction.getDate().toString(), 20);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(dateField, gbc);
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
                mainFrame.refreshAllPanels();
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

    
    private void deleteTransaction() {
        int selectedRow = transactionTable.getSelectedRow();
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
                mainFrame.refreshAllPanels();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    private void generateReceipt() {
        int selectedRow = transactionTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a transaction first!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long transactionId = (Long) tableModel.getValueAt(selectedRow, 0);
        
        try {
            TransactionService transactionService = SpringContextUtil.getTransactionService();
            List<Transaction> allTransactions = transactionService.getUserTransactions(SpringContextUtil.getCurrentUser());
            Transaction transaction = allTransactions.stream()
                .filter(t -> t.getId().equals(transactionId))
                .findFirst()
                .orElseThrow(() -> new Exception("Transaction not found"));
            
            User user = SpringContextUtil.getCurrentUser();
            ReceiptService receiptService = SpringContextUtil.getBean(ReceiptService.class);
            
            byte[] pdfBytes = receiptService.generateReceiptPDF(transaction, user);
            String fileName = "receipt_" + transactionId + ".pdf";
            java.nio.file.Files.write(java.nio.file.Paths.get(fileName), pdfBytes);
            
            JOptionPane.showMessageDialog(this, 
                "Receipt generated successfully!\nSaved as: " + fileName, 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(new java.io.File(fileName));
            } catch (Exception e) {
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error generating receipt: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton receiptButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTable transactionTable;
    // End of variables declaration//GEN-END:variables
}
