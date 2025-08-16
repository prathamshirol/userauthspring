package com.example.demouserauth.controller;

import com.example.demouserauth.model.user;
import com.example.demouserauth.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class usercontroller {

    @Autowired
    private userservice userService;

    @PostMapping("/register")
    public String register(@RequestBody user newUser) {
        user savedUser = userService.registerUser(newUser);
        return "User registered successfully with ID: " + savedUser.getId();
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody user loginUser) {
        String token = userService.loginUser(loginUser.getUsername(), loginUser.getPassword());

        Map<String, Object> response = new HashMap<>();
        if (token != null) {
            response.put("message", "Login successful");
            response.put("token", token);
        } else {
            response.put("message", "Invalid username or password");
        }
        return response;
    }
}
