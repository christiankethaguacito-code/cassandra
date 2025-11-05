package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;
import com.isulan.financial.model.User;
import com.isulan.financial.service.TransactionService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Dashboard Panel for Desktop Application
 * Displays financial summary and charts
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
public class DashboardPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel totalIncomeLabel;
    private JLabel totalExpensesLabel;
    private JLabel balanceLabel;
    private JPanel chartPanel;

    /**
     * Constructor - Creates the dashboard panel
     * 
     * @param mainFrame Reference to main frame
     */
    public DashboardPanel(MainFrame mainFrame) {
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

        // Top panel with welcome message
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        
        User user = SpringContextUtil.getCurrentUser();
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(102, 126, 234));
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // Summary cards panel
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        summaryPanel.setBackground(Color.WHITE);

        // Income card
        JPanel incomeCard = createSummaryCard("Total Income", "₱0.00", new Color(102, 126, 234));
        totalIncomeLabel = (JLabel) ((JPanel) incomeCard.getComponent(1)).getComponent(0);
        summaryPanel.add(incomeCard);

        // Expense card
        JPanel expenseCard = createSummaryCard("Total Expenses", "₱0.00", new Color(240, 147, 251));
        totalExpensesLabel = (JLabel) ((JPanel) expenseCard.getComponent(1)).getComponent(0);
        summaryPanel.add(expenseCard);

        // Balance card
        JPanel balanceCard = createSummaryCard("Current Balance", "₱0.00", new Color(79, 172, 254));
        balanceLabel = (JLabel) ((JPanel) balanceCard.getComponent(1)).getComponent(0);
        summaryPanel.add(balanceCard);

        add(summaryPanel, BorderLayout.CENTER);

        // Charts panel
        chartPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setPreferredSize(new Dimension(0, 300));

        add(chartPanel, BorderLayout.SOUTH);

        // Quick actions
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        actionsPanel.setBackground(Color.WHITE);
        
        JButton addTransactionButton = new JButton("Add Transaction");
        addTransactionButton.setFont(new Font("Arial", Font.BOLD, 14));
        addTransactionButton.setBackground(new Color(102, 126, 234));
        addTransactionButton.setForeground(Color.WHITE);
        addTransactionButton.setFocusPainted(false);
        addTransactionButton.addActionListener(e -> mainFrame.switchToTransactionsTab());
        
        actionsPanel.add(addTransactionButton);
    }

    /**
     * Create a summary card
     * 
     * @param title Card title
     * @param value Initial value
     * @param color Card color
     * @return JPanel representing the card
     */
    private JPanel createSummaryCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel valuePanel = new JPanel();
        valuePanel.setBackground(color);
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setForeground(Color.WHITE);
        valuePanel.add(valueLabel);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valuePanel);

        return card;
    }

    /**
     * Refresh dashboard data
     */
    public void refreshData() {
        User user = SpringContextUtil.getCurrentUser();
        TransactionService transactionService = SpringContextUtil.getTransactionService();

        // Get financial summary
        Double totalIncome = transactionService.getTotalIncome(user);
        Double totalExpenses = transactionService.getTotalExpenses(user);
        Double balance = transactionService.getBalance(user);

        // Update labels
        totalIncomeLabel.setText(String.format("₱%.2f", totalIncome));
        totalExpensesLabel.setText(String.format("₱%.2f", totalExpenses));
        balanceLabel.setText(String.format("₱%.2f", balance));

        // Update charts
        updateCharts();
    }

    /**
     * Update pie charts
     */
    private void updateCharts() {
        chartPanel.removeAll();

        User user = SpringContextUtil.getCurrentUser();
        TransactionService transactionService = SpringContextUtil.getTransactionService();

        // Expenses chart
        Map<String, Double> expensesByCategory = transactionService.getExpensesByCategory(user);
        if (!expensesByCategory.isEmpty()) {
            DefaultPieDataset<String> expenseDataset = new DefaultPieDataset<>();
            expensesByCategory.forEach(expenseDataset::setValue);
            
            JFreeChart expenseChart = ChartFactory.createPieChart(
                    "Expenses by Category",
                    expenseDataset,
                    true, true, false);
            
            ChartPanel expenseChartPanel = new ChartPanel(expenseChart);
            chartPanel.add(expenseChartPanel);
        } else {
            JPanel noDataPanel = new JPanel();
            noDataPanel.add(new JLabel("No expense data available"));
            chartPanel.add(noDataPanel);
        }

        // Income chart
        Map<String, Double> incomeByCategory = transactionService.getIncomeByCategory(user);
        if (!incomeByCategory.isEmpty()) {
            DefaultPieDataset<String> incomeDataset = new DefaultPieDataset<>();
            incomeByCategory.forEach(incomeDataset::setValue);
            
            JFreeChart incomeChart = ChartFactory.createPieChart(
                    "Income by Category",
                    incomeDataset,
                    true, true, false);
            
            ChartPanel incomeChartPanel = new ChartPanel(incomeChart);
            chartPanel.add(incomeChartPanel);
        } else {
            JPanel noDataPanel = new JPanel();
            noDataPanel.add(new JLabel("No income data available"));
            chartPanel.add(noDataPanel);
        }

        chartPanel.revalidate();
        chartPanel.repaint();
    }
}
