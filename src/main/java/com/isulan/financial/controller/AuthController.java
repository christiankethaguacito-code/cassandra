package com.isulan.financial.controller;

import com.isulan.financial.model.User;
import com.isulan.financial.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String logout,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam String confirmPassword,
                              Model model,
                              HttpSession session) {
        try {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("error", "Passwords do not match");
                return "register";
            }
            if (password.length() < 6) {
                model.addAttribute("error", "Password must be at least 6 characters");
                return "register";
            }
            User user = userService.registerUser(name, email, password);
            session.setAttribute("user", user);
            
            return "redirect:/dashboard";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @PostMapping("/perform-login")
    public String loginUser(@RequestParam String email,
                           @RequestParam String password,
                           HttpSession session,
                           Model model) {
        try {
            User user = userService.authenticateUser(email, password);
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
