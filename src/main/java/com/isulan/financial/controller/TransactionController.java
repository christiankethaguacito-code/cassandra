package com.isulan.financial.controller;

import com.isulan.financial.model.Transaction;
import com.isulan.financial.model.User;
import com.isulan.financial.service.TransactionService;
import com.isulan.financial.service.ReceiptService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ReceiptService receiptService;

    
    @GetMapping
    public String showTransactions(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        List<Transaction> transactions = transactionService.getUserTransactions(user);
        model.addAttribute("user", user);
        model.addAttribute("transactions", transactions);

        return "transactions";
    }

    
    @GetMapping("/add")
    public String showAddTransactionForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "add-transaction";
    }

    
    @PostMapping("/add")
    public String addTransaction(@RequestParam String type,
                                @RequestParam String category,
                                @RequestParam String description,
                                @RequestParam Double amount,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        try {
            transactionService.addTransaction(user, type, category, description, amount, date);
            redirectAttributes.addFlashAttribute("success", "Transaction added successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add transaction: " + e.getMessage());
        }

        return "redirect:/transactions";
    }

    
    @GetMapping("/edit/{id}")
    public String showEditTransactionForm(@PathVariable Long id, 
                                         HttpSession session, 
                                         Model model) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        List<Transaction> transactions = transactionService.getUserTransactions(user);
        Transaction transaction = transactions.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (transaction == null) {
            return "redirect:/transactions";
        }

        model.addAttribute("user", user);
        model.addAttribute("transaction", transaction);
        return "edit-transaction";
    }

    
    @PostMapping("/edit/{id}")
    public String updateTransaction(@PathVariable Long id,
                                   @RequestParam String type,
                                   @RequestParam String category,
                                   @RequestParam String description,
                                   @RequestParam Double amount,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                   RedirectAttributes redirectAttributes) {
        try {
            transactionService.updateTransaction(id, type, category, description, amount, date);
            redirectAttributes.addFlashAttribute("success", "Transaction updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update transaction: " + e.getMessage());
        }

        return "redirect:/transactions";
    }

    
    @PostMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        try {
            transactionService.deleteTransaction(id);
            redirectAttributes.addFlashAttribute("success", "Transaction deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete transaction: " + e.getMessage());
        }

        return "redirect:/transactions";
    }

    
    @GetMapping("/receipt/{id}")
    public String showReceipt(@PathVariable Long id,
                             HttpSession session,
                             Model model) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        List<Transaction> transactions = transactionService.getUserTransactions(user);
        Transaction transaction = transactions.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (transaction == null) {
            return "redirect:/transactions";
        }
        String receiptNumber = "RCP-" + transaction.getId() + "-" + 
                System.currentTimeMillis();

        model.addAttribute("user", user);
        model.addAttribute("transaction", transaction);
        model.addAttribute("receiptNumber", receiptNumber);
        model.addAttribute("currentDate", java.time.LocalDateTime.now());

        return "receipt";
    }

    
    @GetMapping("/receipt/{id}/pdf")
    public ResponseEntity<byte[]> exportReceiptPDF(@PathVariable Long id,
                                                   HttpSession session) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        try {
            List<Transaction> transactions = transactionService.getUserTransactions(user);
            Transaction transaction = transactions.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (transaction == null) {
                return ResponseEntity.notFound().build();
            }

            byte[] pdfBytes = receiptService.generateReceiptPDF(transaction, user);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", 
                    "receipt-" + transaction.getId() + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
                    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
