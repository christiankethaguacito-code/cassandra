package com.isulan.financial.repository;

import com.isulan.financial.model.Transaction;
import com.isulan.financial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Transaction entity
 * Provides database access methods for transaction operations
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Find all transactions for a specific user
     * 
     * @param user User to find transactions for
     * @return List of transactions
     */
    List<Transaction> findByUser(User user);

    /**
     * Find transactions by user and type
     * 
     * @param user User to find transactions for
     * @param type Type of transaction (INCOME or EXPENSE)
     * @return List of transactions
     */
    List<Transaction> findByUserAndType(User user, String type);

    /**
     * Find transactions by user within date range
     * 
     * @param user User to find transactions for
     * @param startDate Start date of range
     * @param endDate End date of range
     * @return List of transactions
     */
    List<Transaction> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    /**
     * Calculate total amount by user and type
     * 
     * @param user User to calculate for
     * @param type Type of transaction
     * @return Total amount
     */
    @Query("SELECT COALESCE(SUM(t.amount), 0.0) FROM Transaction t WHERE t.user = :user AND t.type = :type")
    Double sumAmountByUserAndType(@Param("user") User user, @Param("type") String type);

    /**
     * Get transactions grouped by category for a user
     * 
     * @param user User to get data for
     * @param type Type of transaction
     * @return List of objects containing category and sum
     */
    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.user = :user AND t.type = :type GROUP BY t.category")
    List<Object[]> sumAmountByUserAndTypeGroupByCategory(@Param("user") User user, @Param("type") String type);
}
