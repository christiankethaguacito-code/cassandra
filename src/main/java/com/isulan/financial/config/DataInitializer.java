package com.isulan.financial.config;

import com.isulan.financial.model.User;
import com.isulan.financial.service.TransactionService;
import com.isulan.financial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Data Initializer (Optional)
 * Creates sample data for testing purposes
 * Comment out @Component annotation to disable
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
// @Component  // Uncomment to enable sample data creation
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    /**
     * Initialize sample data on application startup
     * 
     * @param args Command line arguments
     */
    @Override
    public void run(String... args) {
        try {
            // Check if demo user already exists
            if (userService.findByEmail("demo@isulan.com").isPresent()) {
                System.out.println("Sample data already exists. Skipping initialization.");
                return;
            }

            System.out.println("Creating sample data...");

            // Create demo user
            User demoUser = userService.registerUser(
                "Demo User",
                "demo@isulan.com",
                "demo123"
            );

            // Add sample income transactions
            transactionService.addTransaction(demoUser, "INCOME", "Salary", 
                "Monthly salary payment", 25000.00, LocalDate.now().minusDays(30));
            
            transactionService.addTransaction(demoUser, "INCOME", "Freelance", 
                "Web development project", 8000.00, LocalDate.now().minusDays(15));
            
            transactionService.addTransaction(demoUser, "INCOME", "Business", 
                "Online sales", 5500.00, LocalDate.now().minusDays(10));

            // Add sample expense transactions
            transactionService.addTransaction(demoUser, "EXPENSE", "Food", 
                "Groceries shopping", 3500.00, LocalDate.now().minusDays(28));
            
            transactionService.addTransaction(demoUser, "EXPENSE", "Transportation", 
                "Jeepney and tricycle fares", 800.00, LocalDate.now().minusDays(25));
            
            transactionService.addTransaction(demoUser, "EXPENSE", "Utilities", 
                "Electricity bill", 2200.00, LocalDate.now().minusDays(20));
            
            transactionService.addTransaction(demoUser, "EXPENSE", "Entertainment", 
                "Movie and dinner", 1500.00, LocalDate.now().minusDays(18));
            
            transactionService.addTransaction(demoUser, "EXPENSE", "Food", 
                "Restaurant and snacks", 1200.00, LocalDate.now().minusDays(12));
            
            transactionService.addTransaction(demoUser, "EXPENSE", "Education", 
                "Books and supplies", 2500.00, LocalDate.now().minusDays(8));
            
            transactionService.addTransaction(demoUser, "EXPENSE", "Healthcare", 
                "Medical check-up", 1800.00, LocalDate.now().minusDays(5));

            System.out.println("========================================");
            System.out.println("Sample data created successfully!");
            System.out.println("Demo Account:");
            System.out.println("  Email: demo@isulan.com");
            System.out.println("  Password: demo123");
            System.out.println("========================================");

        } catch (Exception e) {
            System.err.println("Error creating sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
