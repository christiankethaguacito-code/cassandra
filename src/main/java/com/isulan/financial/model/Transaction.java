package com.isulan.financial.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Transaction Entity representing financial transactions
 * Stores income and expense records for users
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String type; // "INCOME" or "EXPENSE"

    private String category;

    private String description;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate date;

    /**
     * Default constructor
     */
    public Transaction() {
    }

    /**
     * Constructor with all transaction details
     * 
     * @param user User who owns this transaction
     * @param type Type of transaction (INCOME or EXPENSE)
     * @param category Category of the transaction
     * @param description Description of the transaction
     * @param amount Amount of money
     * @param date Date of the transaction
     */
    public Transaction(User user, String type, String category, String description, 
                      Double amount, LocalDate date) {
        this.user = user;
        this.type = type;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
