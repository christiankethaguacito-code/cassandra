package com.isulan.financial.config;

import com.isulan.financial.model.User;
import com.isulan.financial.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom authentication success handler
 * Stores user object in session after successful login
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                       HttpServletResponse response,
                                       Authentication authentication) throws IOException, ServletException {
        // Get the authenticated user's email
        String email = authentication.getName();
        
        // Load full user object from database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Store user in session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        // Redirect to dashboard
        response.sendRedirect("/dashboard");
    }
}
