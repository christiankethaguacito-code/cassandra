package com.isulan.financial.service;

import com.isulan.financial.model.Transaction;
import com.isulan.financial.model.User;
import com.isulan.financial.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for Transaction-related business logic
 * Handles income/expense tracking and financial calculations
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Add a new transaction
     * 
     * @param user User who owns the transaction
     * @param type Type of transaction (INCOME or EXPENSE)
     * @param category Category of the transaction
     * @param description Description of the transaction
     * @param amount Amount of money
     * @param date Date of the transaction
     * @return Created transaction
     */
    public Transaction addTransaction(User user, String type, String category, 
                                     String description, Double amount, LocalDate date) {
        Transaction transaction = new Transaction(user, type.toUpperCase(), 
                                                  category, description, amount, date);
        return transactionRepository.save(transaction);
    }

    /**
     * Get all transactions for a user
     * 
     * @param user User to get transactions for
     * @return List of transactions
     */
    public List<Transaction> getUserTransactions(User user) {
        return transactionRepository.findByUser(user);
    }

    /**
     * Get transactions by type for a user
     * 
     * @param user User to get transactions for
     * @param type Type of transaction (INCOME or EXPENSE)
     * @return List of transactions
     */
    public List<Transaction> getUserTransactionsByType(User user, String type) {
        return transactionRepository.findByUserAndType(user, type.toUpperCase());
    }

    /**
     * Get transactions within date range
     * 
     * @param user User to get transactions for
     * @param startDate Start date
     * @param endDate End date
     * @return List of transactions
     */
    public List<Transaction> getUserTransactionsByDateRange(User user, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByUserAndDateBetween(user, startDate, endDate);
    }

    /**
     * Update an existing transaction
     * 
     * @param transactionId Transaction ID
     * @param type New type
     * @param category New category
     * @param description New description
     * @param amount New amount
     * @param date New date
     * @return Updated transaction
     * @throws IllegalArgumentException if transaction not found
     */
    public Transaction updateTransaction(Long transactionId, String type, String category,
                                        String description, Double amount, LocalDate date) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        
        transaction.setType(type.toUpperCase());
        transaction.setCategory(category);
        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setDate(date);
        
        return transactionRepository.save(transaction);
    }

    /**
     * Delete a transaction
     * 
     * @param transactionId Transaction ID to delete
     * @throws IllegalArgumentException if transaction not found
     */
    public void deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        transactionRepository.deleteById(transactionId);
    }

    /**
     * Calculate total income for a user
     * 
     * @param user User to calculate for
     * @return Total income amount
     */
    public Double getTotalIncome(User user) {
        Double total = transactionRepository.sumAmountByUserAndType(user, "INCOME");
        return total != null ? total : 0.0;
    }

    /**
     * Calculate total expenses for a user
     * 
     * @param user User to calculate for
     * @return Total expense amount
     */
    public Double getTotalExpenses(User user) {
        Double total = transactionRepository.sumAmountByUserAndType(user, "EXPENSE");
        return total != null ? total : 0.0;
    }

    /**
     * Calculate current balance (income - expenses)
     * 
     * @param user User to calculate for
     * @return Current balance
     */
    public Double getBalance(User user) {
        return getTotalIncome(user) - getTotalExpenses(user);
    }

    /**
     * Get expense breakdown by category
     * 
     * @param user User to get data for
     * @return Map of category to total amount
     */
    public Map<String, Double> getExpensesByCategory(User user) {
        List<Object[]> results = transactionRepository
                .sumAmountByUserAndTypeGroupByCategory(user, "EXPENSE");
        
        Map<String, Double> categoryMap = new HashMap<>();
        for (Object[] result : results) {
            String category = (String) result[0];
            Double amount = (Double) result[1];
            categoryMap.put(category != null ? category : "Uncategorized", amount);
        }
        
        return categoryMap;
    }

    /**
     * Get income breakdown by category
     * 
     * @param user User to get data for
     * @return Map of category to total amount
     */
    public Map<String, Double> getIncomeByCategory(User user) {
        List<Object[]> results = transactionRepository
                .sumAmountByUserAndTypeGroupByCategory(user, "INCOME");
        
        Map<String, Double> categoryMap = new HashMap<>();
        for (Object[] result : results) {
            String category = (String) result[0];
            Double amount = (Double) result[1];
            categoryMap.put(category != null ? category : "Uncategorized", amount);
        }
        
        return categoryMap;
    }
}
