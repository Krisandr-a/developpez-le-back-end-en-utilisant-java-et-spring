package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UserDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UserLoginDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UserRegistrationDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.UserRepository;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String authenticateUser(@RequestBody UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.email(),
                        userLoginDto.password()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByEmail(userRegistrationDto.email())) {
            return "Error: Username is already taken!";
        }

        // Create new user's account
        User newUser = new User(
                null, // id is auto-generated
                userRegistrationDto.email(), // username is email
                userRegistrationDto.name(),
                encoder.encode(userRegistrationDto.password()), // encode the raw password
                LocalDateTime.now(), // createdAt
                LocalDateTime.now(), // updatedAt
                null, // rentals (initialize as null or empty list if needed)
                null  // messages (initialize as null or empty list if needed)
        );
        userRepository.save(newUser);
        return "User registered successfully!";
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")  // 🔹 Requires JWT authentication
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new RuntimeException("User not authenticated");
        }

        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert `User` to `UserDTO`
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
