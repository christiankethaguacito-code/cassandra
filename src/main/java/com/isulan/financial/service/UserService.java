package com.isulan.financial.service;

import com.isulan.financial.model.User;
import com.isulan.financial.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    
    public User registerUser(String name, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(name, email, hashedPassword);
        return userRepository.save(user);
    }

    
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

    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    
    public User updateProfile(Long userId, String name, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    
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

    
    public User updateProfilePicture(Long userId, String profilePicturePath) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        user.setProfilePicture(profilePicturePath);
        return userRepository.save(user);
    }
}
