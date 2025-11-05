package com.isulan.financial.controller;

import com.isulan.financial.model.User;
import com.isulan.financial.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for user profile management
 * Handles profile viewing and updating
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    /**
     * Show user profile page
     * 
     * @param session HTTP session
     * @param model Model for view
     * @return View name
     */
    @GetMapping
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        // Refresh user data
        user = userService.findById(user.getId()).orElse(user);
        session.setAttribute("user", user);

        model.addAttribute("user", user);
        return "profile";
    }

    /**
     * Update user profile information
     * 
     * @param name New name
     * @param email New email
     * @param session HTTP session
     * @param redirectAttributes Redirect attributes for flash messages
     * @return Redirect to profile page
     */
    @PostMapping("/update")
    public String updateProfile(@RequestParam String name,
                               @RequestParam String email,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        try {
            User updatedUser = userService.updateProfile(user.getId(), name, email);
            session.setAttribute("user", updatedUser);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/profile";
    }

    /**
     * Update user password
     * 
     * @param currentPassword Current password for verification
     * @param newPassword New password
     * @param confirmPassword New password confirmation
     * @param session HTTP session
     * @param redirectAttributes Redirect attributes for flash messages
     * @return Redirect to profile page
     */
    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            return "redirect:/login";
        }

        try {
            // Validate new password
            if (!newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "New passwords do not match");
                return "redirect:/profile";
            }

            if (newPassword.length() < 6) {
                redirectAttributes.addFlashAttribute("error", "Password must be at least 6 characters");
                return "redirect:/profile";
            }

            userService.updatePassword(user.getId(), currentPassword, newPassword);
            redirectAttributes.addFlashAttribute("success", "Password changed successfully");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/profile";
    }
}
