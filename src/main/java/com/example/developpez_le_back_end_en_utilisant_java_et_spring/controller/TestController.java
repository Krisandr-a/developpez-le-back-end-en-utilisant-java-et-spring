package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }
    @GetMapping("/user")
    public String userAccess() {
        return "User Content.";
    }
}
