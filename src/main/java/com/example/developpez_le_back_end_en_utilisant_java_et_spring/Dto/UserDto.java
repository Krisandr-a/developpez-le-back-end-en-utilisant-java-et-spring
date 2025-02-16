package com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO providing the required user info")
public record UserDto(@Schema(description = "Unique identifier of the user")
                      Integer id,
                      @Schema(description = "User's full name", example = "Jane Doe")
                      String name,
                      @Schema(description = "User's email address", example = "user@example.com")
                      String email,
                      @Schema(description = "Timestamp when the rental was created")
                      @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                      @JsonProperty("created_at")
                      LocalDateTime createdAt,
                      @Schema(description = "Timestamp when the rental was last updated")
                      @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                      @JsonProperty("updated_at")
                      LocalDateTime updatedAt
) {}

