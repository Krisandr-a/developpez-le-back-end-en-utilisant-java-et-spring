package com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for creating a message")
public record AddMessageDto(
        @Schema(description = "Message content", example = "mon message")
        String message,

        @Schema(description = "ID of the user associated with the message", example = "1")
        Integer user_id,

        @Schema(description = "ID of the rental associated with the message", example = "1")
        Integer rental_id
) {}
