package com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto;

import java.time.LocalDateTime;

public record UserDto(Integer id,
                      String name,
                      String email,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt
) {}

