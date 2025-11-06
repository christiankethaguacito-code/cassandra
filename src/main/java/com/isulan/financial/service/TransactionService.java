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

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction addTransaction(User user, String type, String category, 
                                     String description, Double amount, LocalDate date) {
        Transaction transaction = new Transaction(user, type.toUpperCase(), 
                                                  category, description, amount, date);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getUserTransactions(User user) {
        return transactionRepository.findByUser(user);
    }

    public List<Transaction> getUserTransactionsByType(User user, String type) {
        return transactionRepository.findByUserAndType(user, type.toUpperCase());
    }

    public List<Transaction> getUserTransactionsByDateRange(User user, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByUserAndDateBetween(user, startDate, endDate);
    }

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

    public void deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new IllegalArgumentException("Transaction not found");
        }
        transactionRepository.deleteById(transactionId);
    }

    public Double getTotalIncome(User user) {
        Double total = transactionRepository.sumAmountByUserAndType(user, "INCOME");
        return total != null ? total : 0.0;
    }

    public Double getTotalExpenses(User user) {
        Double total = transactionRepository.sumAmountByUserAndType(user, "EXPENSE");
        return total != null ? total : 0.0;
    }

    public Double getBalance(User user) {
        return getTotalIncome(user) - getTotalExpenses(user);
    }

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
