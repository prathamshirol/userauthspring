package com.example.demouserauth.service;

import com.example.demouserauth.model.user;
import com.example.demouserauth.repository.userrepository;
import com.example.demouserauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userservice {

    @Autowired
    private userrepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;  // 👈 Added email service

    // Register user + send welcome email
    public user registerUser(user newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user savedUser = userRepository.save(newUser);

        // Send welcome email
        if (savedUser.getEmail() != null && !savedUser.getEmail().isEmpty()) {
            emailService.sendWelcomeEmail(savedUser.getEmail(), savedUser.getUsername());
        }

        return savedUser;
    }

    // Login user -> return JWT token
    public String loginUser(String username, String password) {
        user existingUser = userRepository.findByUsername(username);
        if (existingUser != null && passwordEncoder.matches(password, existingUser.getPassword())) {
            return jwtUtil.generateToken(username);
        }
        return null;
    }
}
