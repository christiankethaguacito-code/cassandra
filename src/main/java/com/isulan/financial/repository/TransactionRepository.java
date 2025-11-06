package com.isulan.financial.repository;

import com.isulan.financial.model.Transaction;
import com.isulan.financial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    
    List<Transaction> findByUser(User user);

    
    List<Transaction> findByUserAndType(User user, String type);

    
    List<Transaction> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    
    @Query("SELECT COALESCE(SUM(t.amount), 0.0) FROM Transaction t WHERE t.user = :user AND t.type = :type")
    Double sumAmountByUserAndType(@Param("user") User user, @Param("type") String type);

    
    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.user = :user AND t.type = :type GROUP BY t.category")
    List<Object[]> sumAmountByUserAndTypeGroupByCategory(@Param("user") User user, @Param("type") String type);
}
