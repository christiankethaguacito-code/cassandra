package com.isulan.financial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class for Personal Financial Management System
 * This application provides both web-based and desktop interfaces
 * for managing personal finances including income, expenses, and reports.
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 * @since 2025-11-05
 */
@SpringBootApplication
public class FinancialManagementApplication {

    /**
     * Main entry point for the Spring Boot application
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(FinancialManagementApplication.class, args);
        System.out.println("========================================");
        System.out.println("Financial Management System Started");
        System.out.println("Web Interface: http://localhost:8080");
        System.out.println("========================================");
    }
}
