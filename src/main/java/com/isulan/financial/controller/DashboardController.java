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

@Controller
public class DashboardController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }
        user = userService.findById(user.getId()).orElse(user);
        session.setAttribute("user", user);
        Double totalIncome = transactionService.getTotalIncome(user);
        Double totalExpenses = transactionService.getTotalExpenses(user);
        Double balance = transactionService.getBalance(user);
        Map<String, Double> expensesByCategory = transactionService.getExpensesByCategory(user);
        Map<String, Double> incomeByCategory = transactionService.getIncomeByCategory(user);
        model.addAttribute("user", user);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("balance", balance);
        model.addAttribute("expensesByCategory", expensesByCategory);
        model.addAttribute("incomeByCategory", incomeByCategory);

        return "dashboard";
    }
}
