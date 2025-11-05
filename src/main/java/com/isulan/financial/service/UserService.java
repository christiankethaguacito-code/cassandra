package com.isulan.financial.service;

import com.isulan.financial.model.User;
import com.isulan.financial.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service class for User-related business logic
 * Handles user authentication, registration, and profile management
 * 
 * @author Isulan Development Team
 * @version 1.0.0
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user with encrypted password
     * 
     * @param name User's full name
     * @param email User's email address
     * @param password User's plain text password (will be encrypted)
     * @return Registered user
     * @throws IllegalArgumentException if email already exists
     */
    public User registerUser(String name, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(name, email, hashedPassword);
        return userRepository.save(user);
    }

    /**
     * Authenticate user with email and password
     * 
     * @param email User's email
     * @param password User's plain text password
     * @return User if authentication successful
     * @throws IllegalArgumentException if credentials are invalid
     */
    public User authenticateUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        
        User user = userOpt.get();
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        
        return user;
    }

    /**
     * Find user by ID
     * 
     * @param id User ID
     * @return Optional containing user if found
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Find user by email
     * 
     * @param email User's email
     * @return Optional containing user if found
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Update user profile information
     * 
     * @param userId User ID
     * @param name New name
     * @param email New email
     * @return Updated user
     * @throws IllegalArgumentException if user not found or email already taken
     */
    public User updateProfile(Long userId, String name, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        // Check if email is being changed and if new email is already taken
        if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    /**
     * Update user password
     * 
     * @param userId User ID
     * @param currentPassword Current password for verification
     * @param newPassword New password to set
     * @return Updated user
     * @throws IllegalArgumentException if user not found or current password is incorrect
     */
    public User updatePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        if (!BCrypt.checkpw(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    /**
     * Update user profile picture
     * 
     * @param userId User ID
     * @param profilePicturePath Path to profile picture
     * @return Updated user
     */
    public User updateProfilePicture(Long userId, String profilePicturePath) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        user.setProfilePicture(profilePicturePath);
        return userRepository.save(user);
    }
}
