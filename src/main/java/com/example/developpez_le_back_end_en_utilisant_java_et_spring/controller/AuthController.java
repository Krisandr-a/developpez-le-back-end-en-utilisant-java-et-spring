package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.UserRepository;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @PostMapping("/login")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getUsername())) {
            return "Error: Username is already taken!";
        }
        // Create new user's account
        User newUser = new User(
                null, // id is auto-generated
                user.getUsername(),
                user.getName(),
                encoder.encode(user.getPassword()), // encode the raw password
                LocalDateTime.now(), // createdAt
                LocalDateTime.now(), // updatedAt
                null, // rentals (initialize as null or empty list if needed)
                null  // messages (initialize as null or empty list if needed)
        );
        userRepository.save(newUser);
        return "User registered successfully!";
    }
}
