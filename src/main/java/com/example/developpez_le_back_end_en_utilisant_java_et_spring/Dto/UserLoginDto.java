package com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserLoginDto(
        @Schema(description = "User's email address", example = "user@example.com")
        String email,

        @Schema(description = "User's password", example = "password123")
        String password
) {}