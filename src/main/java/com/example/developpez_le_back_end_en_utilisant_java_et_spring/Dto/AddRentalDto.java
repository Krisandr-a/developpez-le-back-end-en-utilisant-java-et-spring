package com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Schema(description = "DTO for creating a new rental")
public record AddRentalDto(
        @Schema(description = "Name of the rental", example = "Test House 1")
        String name,

        @Schema(description = "Surface area in square meters", example = "432")
        BigDecimal surface,

        @Schema(description = "Rental price per month", example = "300")
        BigDecimal price,

        @Schema(description = "Image of the rental")
        MultipartFile picture,

        @Schema(description = "Detailed description of the rental", example = "A beautiful house with great amenities.")
        String description,

        @Schema(description = "ID of the rental owner", example = "1")
        Integer ownerId
) {}

