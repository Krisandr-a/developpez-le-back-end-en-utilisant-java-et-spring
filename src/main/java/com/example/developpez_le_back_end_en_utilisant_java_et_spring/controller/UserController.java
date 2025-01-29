package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // test success via CLI
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // test success via CLI
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // test success via CLI
    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Test adding a new user
    /* @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    } */
}
