package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UserDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Utilisateur")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Chercher un utilisateur via son id")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }


}
