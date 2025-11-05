package com.isulan.financial.controller;

import com.isulan.financial.model.User;
import com.isulan.financial.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication-related operations
 * Handles login, registration, and logout
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * Show home/welcome page
     * 
     * @return View name
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Show login page
     * 
     * @param error Error parameter from failed login
     * @param logout Logout parameter
     * @param model Model for view
     * @return View name
     */
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "login";
    }

    /**
     * Show registration page
     * 
     * @return View name
     */
    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    /**
     * Process user registration
     * 
     * @param name User's name
     * @param email User's email
     * @param password User's password
     * @param confirmPassword Password confirmation
     * @param model Model for view
     * @param session HTTP session
     * @return Redirect to dashboard or back to registration
     */
    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam String confirmPassword,
                              Model model,
                              HttpSession session) {
        try {
            // Validate password match
            if (!password.equals(confirmPassword)) {
                model.addAttribute("error", "Passwords do not match");
                return "register";
            }

            // Validate password strength
            if (password.length() < 6) {
                model.addAttribute("error", "Password must be at least 6 characters");
                return "register";
            }

            // Register user
            User user = userService.registerUser(name, email, password);
            
            // Auto-login after registration
            session.setAttribute("user", user);
            
            return "redirect:/dashboard";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    /**
     * Process user login
     * 
     * @param email User's email
     * @param password User's password
     * @param session HTTP session
     * @param model Model for view
     * @return Redirect to dashboard or back to login
     */
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

    /**
     * Process user logout
     * 
     * @param session HTTP session
     * @return Redirect to login page
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
