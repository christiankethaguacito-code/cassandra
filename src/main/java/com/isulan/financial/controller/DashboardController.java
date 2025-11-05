package com.isulan.financial.controller;

import com.isulan.financial.model.User;
import com.isulan.financial.service.TransactionService;
import com.isulan.financial.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for dashboard and main application views
 * Handles financial overview and analytics
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
@Controller
public class DashboardController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    /**
     * Show dashboard with financial overview
     * 
     * @param session HTTP session
     * @param model Model for view
     * @return View name
     */
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        // Refresh user data from database
        user = userService.findById(user.getId()).orElse(user);
        session.setAttribute("user", user);

        // Calculate financial summary
        Double totalIncome = transactionService.getTotalIncome(user);
        Double totalExpenses = transactionService.getTotalExpenses(user);
        Double balance = transactionService.getBalance(user);

        // Get category breakdowns
        Map<String, Double> expensesByCategory = transactionService.getExpensesByCategory(user);
        Map<String, Double> incomeByCategory = transactionService.getIncomeByCategory(user);

        // Add data to model
        model.addAttribute("user", user);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("balance", balance);
        model.addAttribute("expensesByCategory", expensesByCategory);
        model.addAttribute("incomeByCategory", incomeByCategory);

        return "dashboard";
    }
}
