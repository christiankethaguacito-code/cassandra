package com.isulan.financial.desktop.ui;

import com.isulan.financial.desktop.util.SpringContextUtil;
import com.isulan.financial.model.User;
import com.isulan.financial.service.TransactionService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class DashboardPanel extends JPanel {

    private MainFrame mainFrame;

    
    public DashboardPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        refreshData();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        welcomeLabel = new javax.swing.JLabel();
        summaryPanel = new javax.swing.JPanel();
        incomeCard = new javax.swing.JPanel();
        incomeTitleLabel = new javax.swing.JLabel();
        incomeValueLabel = new javax.swing.JLabel();
        expenseCard = new javax.swing.JPanel();
        expenseTitleLabel = new javax.swing.JLabel();
        expenseValueLabel = new javax.swing.JLabel();
        balanceCard = new javax.swing.JPanel();
        balanceTitleLabel = new javax.swing.JLabel();
        balanceValueLabel = new javax.swing.JLabel();
        chartPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(240, 242, 245));

        topPanel.setBackground(new java.awt.Color(240, 242, 245));
        topPanel.setOpaque(true);

        welcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(102, 126, 234));
        welcomeLabel.setText("💰 Welcome, User!");

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        summaryPanel.setBackground(new java.awt.Color(240, 242, 245));
        summaryPanel.setOpaque(true);

        incomeCard.setBackground(new java.awt.Color(76, 175, 80));
        incomeCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        addHoverEffect(incomeCard);

        incomeTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        incomeTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        incomeTitleLabel.setText("💰 Total Income");

        incomeValueLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        incomeValueLabel.setForeground(new java.awt.Color(255, 255, 255));
        incomeValueLabel.setText("₱0.00");

        javax.swing.GroupLayout incomeCardLayout = new javax.swing.GroupLayout(incomeCard);
        incomeCard.setLayout(incomeCardLayout);
        incomeCardLayout.setHorizontalGroup(
            incomeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(incomeTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(incomeValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        incomeCardLayout.setVerticalGroup(
            incomeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incomeCardLayout.createSequentialGroup()
                .addComponent(incomeTitleLabel)
                .addGap(10, 10, 10)
                .addComponent(incomeValueLabel)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        expenseCard.setBackground(new java.awt.Color(244, 67, 54));
        expenseCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        addHoverEffect(expenseCard);

        expenseTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        expenseTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        expenseTitleLabel.setText("💸 Total Expenses");

        expenseValueLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        expenseValueLabel.setForeground(new java.awt.Color(255, 255, 255));
        expenseValueLabel.setText("₱0.00");

        javax.swing.GroupLayout expenseCardLayout = new javax.swing.GroupLayout(expenseCard);
        expenseCard.setLayout(expenseCardLayout);
        expenseCardLayout.setHorizontalGroup(
            expenseCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(expenseTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(expenseValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        expenseCardLayout.setVerticalGroup(
            expenseCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expenseCardLayout.createSequentialGroup()
                .addComponent(expenseTitleLabel)
                .addGap(10, 10, 10)
                .addComponent(expenseValueLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        balanceCard.setBackground(new java.awt.Color(33, 150, 243));
        balanceCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        addHoverEffect(balanceCard);

        balanceTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        balanceTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        balanceTitleLabel.setText("💵 Current Balance");

        balanceValueLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        balanceValueLabel.setForeground(new java.awt.Color(255, 255, 255));
        balanceValueLabel.setText("₱0.00");

        javax.swing.GroupLayout balanceCardLayout = new javax.swing.GroupLayout(balanceCard);
        balanceCard.setLayout(balanceCardLayout);
        balanceCardLayout.setHorizontalGroup(
            balanceCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(balanceTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(balanceValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        balanceCardLayout.setVerticalGroup(
            balanceCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(balanceCardLayout.createSequentialGroup()
                .addComponent(balanceTitleLabel)
                .addGap(10, 10, 10)
                .addComponent(balanceValueLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout summaryPanelLayout = new javax.swing.GroupLayout(summaryPanel);
        summaryPanel.setLayout(summaryPanelLayout);
        summaryPanelLayout.setHorizontalGroup(
            summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(summaryPanelLayout.createSequentialGroup()
                .addComponent(incomeCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(expenseCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(balanceCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        summaryPanelLayout.setVerticalGroup(
            summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(incomeCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(expenseCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(balanceCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        chartPanel.setBackground(new java.awt.Color(240, 242, 245));
        chartPanel.setOpaque(true);

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(summaryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(summaryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private JLabel getCardValueLabel(JPanel card) {
        Component[] components = card.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                for (Component innerComp : panel.getComponents()) {
                    if (innerComp instanceof JLabel) {
                        return (JLabel) innerComp;
                    }
                }
            }
        }
        return new JLabel("₱0.00");
    }

    
    private JPanel createAnimatedSummaryCard(String title, String value, Color color1, Color color2) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(
                    0, 0, color1,
                    getWidth(), getHeight(), color2
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2d.setColor(new Color(0, 0, 0, 30));
                g2d.drawRoundRect(0, getHeight() - 5, getWidth(), 5, 25, 25);
                GradientPaint shine = new GradientPaint(
                    0, 0, new Color(255, 255, 255, 120),
                    0, getHeight() / 3, new Color(255, 255, 255, 0)
                );
                g2d.setPaint(shine);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight() / 3, 25, 25);
                
                g2d.dispose();
            }
        };
        
        card.setLayout(new BorderLayout(15, 15));
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        card.setPreferredSize(new Dimension(280, 180));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        JPanel valuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        valuePanel.setOpaque(false);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 40)); // Larger like web app
        valueLabel.setForeground(Color.WHITE);
        valuePanel.add(valueLabel);
        JLabel trendLabel = new JLabel("↑ This Month");
        trendLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        trendLabel.setForeground(new Color(255, 255, 255, 200));
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(trendLabel, BorderLayout.WEST);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valuePanel, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);
        card.setVisible(false);
        Timer slideInTimer = new Timer(100, null);
        slideInTimer.addActionListener(new ActionListener() {
            int alpha = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha < 255) {
                    alpha += 25;
                    card.setVisible(true);
                    card.repaint();
                } else {
                    slideInTimer.stop();
                }
            }
        });
        slideInTimer.start();

        return card;
    }

    
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

    
    public void refreshData() {
        User user = SpringContextUtil.getCurrentUser();
        TransactionService transactionService = SpringContextUtil.getTransactionService();
        Double totalIncome = transactionService.getTotalIncome(user);
        Double totalExpenses = transactionService.getTotalExpenses(user);
        Double balance = transactionService.getBalance(user);
        animateValue(incomeValueLabel, 0, totalIncome, 1500);
        animateValue(expenseValueLabel, 0, totalExpenses, 1500);
        animateValue(balanceValueLabel, 0, balance, 1500);
        updateCharts();
    }

    
    private void animateValue(JLabel label, double start, double end, int duration) {
        Timer timer = new Timer(20, null);
        final long startTime = System.currentTimeMillis();
        
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsed = System.currentTimeMillis() - startTime;
                double progress = Math.min(1.0, (double) elapsed / duration);
                double easedProgress = 1 - Math.pow(1 - progress, 4);
                double current = start + (end - start) * easedProgress;
                
                label.setText(String.format("₱%.2f", current));
                
                if (progress >= 1.0) {
                    timer.stop();
                    label.setText(String.format("₱%.2f", end));
                }
            }
        });
        
        timer.start();
    }

    
    private void updateCharts() {
        chartPanel.removeAll();

        User user = SpringContextUtil.getCurrentUser();
        TransactionService transactionService = SpringContextUtil.getTransactionService();
        Map<String, Double> expensesByCategory = transactionService.getExpensesByCategory(user);
        if (!expensesByCategory.isEmpty()) {
            DefaultPieDataset<String> expenseDataset = new DefaultPieDataset<>();
            expensesByCategory.forEach(expenseDataset::setValue);
            
            JFreeChart expenseChart = ChartFactory.createPieChart3D(
                    "💸 Expenses by Category",
                    expenseDataset,
                    true, true, false);
            expenseChart.setBackgroundPaint(Color.WHITE);
            expenseChart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 20));
            expenseChart.getTitle().setPaint(new Color(60, 60, 60));
            expenseChart.setBorderVisible(false);
            expenseChart.setPadding(new org.jfree.chart.ui.RectangleInsets(10, 10, 10, 10));
            org.jfree.chart.plot.PiePlot3D plot = (org.jfree.chart.plot.PiePlot3D) expenseChart.getPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setOutlineVisible(false);
            plot.setLabelFont(new Font("Segoe UI", Font.BOLD, 12));
            plot.setLabelPaint(Color.BLACK);
            plot.setDepthFactor(0.08); // Subtle 3D depth
            plot.setStartAngle(290);
            plot.setCircular(true);
            plot.setLabelGap(0.02);
            plot.setLabelBackgroundPaint(new Color(255, 255, 255, 230));
            plot.setLabelOutlinePaint(new Color(200, 200, 200));
            plot.setLabelShadowPaint(null);
            plot.setInteriorGap(0.04);
            plot.setSectionPaint("Food", new Color(255, 99, 132));         // Pink-red
            plot.setSectionPaint("Transport", new Color(54, 162, 235));    // Blue
            plot.setSectionPaint("Entertainment", new Color(255, 206, 86)); // Yellow
            plot.setSectionPaint("Shopping", new Color(75, 192, 192));     // Teal
            plot.setSectionPaint("Bills", new Color(153, 102, 255));       // Purple
            plot.setSectionPaint("Others", new Color(255, 159, 64));       // Orange
            plot.setShadowPaint(new Color(0, 0, 0, 50));
            JPanel chartCard = createChartCard(expenseChart);
            chartPanel.add(chartCard);
        } else {
            JPanel noDataPanel = createNoDataPanel("No expense data available");
            chartPanel.add(noDataPanel);
        }
        Map<String, Double> incomeByCategory = transactionService.getIncomeByCategory(user);
        if (!incomeByCategory.isEmpty()) {
            DefaultPieDataset<String> incomeDataset = new DefaultPieDataset<>();
            incomeByCategory.forEach(incomeDataset::setValue);
            
            JFreeChart incomeChart = ChartFactory.createPieChart3D(
                    "💰 Income by Category",
                    incomeDataset,
                    true, true, false);
            incomeChart.setBackgroundPaint(Color.WHITE);
            incomeChart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 20));
            incomeChart.getTitle().setPaint(new Color(60, 60, 60));
            incomeChart.setBorderVisible(false);
            incomeChart.setPadding(new org.jfree.chart.ui.RectangleInsets(10, 10, 10, 10));
            org.jfree.chart.plot.PiePlot3D plot = (org.jfree.chart.plot.PiePlot3D) incomeChart.getPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setOutlineVisible(false);
            plot.setLabelFont(new Font("Segoe UI", Font.BOLD, 12));
            plot.setLabelPaint(Color.BLACK);
            plot.setDepthFactor(0.08); // Subtle 3D depth
            plot.setStartAngle(290);
            plot.setCircular(true);
            plot.setLabelGap(0.02);
            plot.setLabelBackgroundPaint(new Color(255, 255, 255, 230));
            plot.setLabelOutlinePaint(new Color(200, 200, 200));
            plot.setLabelShadowPaint(null);
            plot.setInteriorGap(0.04);
            plot.setSectionPaint("Salary", new Color(75, 192, 192));      // Teal
            plot.setSectionPaint("Freelance", new Color(54, 162, 235));   // Blue
            plot.setSectionPaint("Investment", new Color(153, 102, 255)); // Purple
            plot.setSectionPaint("Gift", new Color(255, 206, 86));        // Yellow
            plot.setSectionPaint("Others", new Color(255, 159, 64));      // Orange
            plot.setShadowPaint(new Color(0, 0, 0, 50));
            JPanel chartCard = createChartCard(incomeChart);
            chartPanel.add(chartCard);
        } else {
            JPanel noDataPanel = createNoDataPanel("No income data available");
            chartPanel.add(noDataPanel);
        }

        chartPanel.revalidate();
        chartPanel.repaint();
    }
    
    
    private JPanel createChartCard(JFreeChart chart) {
        JPanel card = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, 20, 20);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 20, 20);
                
                g2d.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(null);
        chartPanel.setOpaque(false);
        
        card.add(chartPanel, BorderLayout.CENTER);
        return card;
    }

    
    private JPanel createNoDataPanel(String message) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        
        JLabel label = new JLabel(message);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(150, 150, 150));
        panel.add(label);
        
        return panel;
    }
    
    private void addHoverEffect(JPanel card) {
        Color originalColor = card.getBackground();
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(brighten(originalColor));
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(originalColor);
                card.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
    
    private Color brighten(Color color) {
        int r = Math.min(255, color.getRed() + 20);
        int g = Math.min(255, color.getGreen() + 20);
        int b = Math.min(255, color.getBlue() + 20);
        return new Color(r, g, b);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel balanceCard;
    private javax.swing.JLabel balanceTitleLabel;
    private javax.swing.JLabel balanceValueLabel;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JPanel expenseCard;
    private javax.swing.JLabel expenseTitleLabel;
    private javax.swing.JLabel expenseValueLabel;
    private javax.swing.JPanel incomeCard;
    private javax.swing.JLabel incomeTitleLabel;
    private javax.swing.JLabel incomeValueLabel;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
