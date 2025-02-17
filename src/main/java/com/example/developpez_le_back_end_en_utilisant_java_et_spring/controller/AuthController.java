package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UserDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UserLoginDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UserRegistrationDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.UserRepository;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.security.JwtUtil;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "S'inscrire et se connecter")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @Autowired
    AuthService authService;


    @PostMapping("/login")
    @Operation(summary = "Se connecter")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.email(),
                        userLoginDto.password()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    @Operation(summary = "S'inscrire")
    public String registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByEmail(userRegistrationDto.email())) {
            return "Error: Username is already taken!";
        }

        // Create new user's account
        User newUser = new User(
                null, // id is auto-generated
                userRegistrationDto.email(), // username is email
                userRegistrationDto.name(),
                encoder.encode(userRegistrationDto.password()),
                LocalDateTime.now(), // createdAt
                LocalDateTime.now(), // updatedAt
                null, // rentals (initialize as null or empty list if needed)
                null  // messages (initialize as null or empty list if needed)
        );
        userRepository.save(newUser);
        return "User registered successfully!";
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Récupérer les informations sur l'utilisateur actuel")
    public UserDto getCurrentUser() {
        User user = authService.getAuthenticatedUser();
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
