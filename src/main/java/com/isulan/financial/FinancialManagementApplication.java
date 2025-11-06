package com.isulan.financial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancialManagementApplication {

    
    public static void main(String[] args) {
        SpringApplication.run(FinancialManagementApplication.class, args);
        System.out.println("========================================");
        System.out.println("Financial Management System Started");
        System.out.println("Web Interface: http://localhost:8080");
        System.out.println("========================================");
    }
}
