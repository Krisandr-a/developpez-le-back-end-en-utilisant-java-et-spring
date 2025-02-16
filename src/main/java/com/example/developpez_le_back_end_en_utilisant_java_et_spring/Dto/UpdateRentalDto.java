package com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Schema(description = "DTO for updating a rental")
public record UpdateRentalDto(
        @Schema(description = "Name of the rental", example = "Updated Test House")
        String name,

        @Schema(description = "Surface area in square meters", example = "500")
        BigDecimal surface,

        @Schema(description = "Rental price per month", example = "350")
        BigDecimal price,

        @Schema(description = "Image of the rental")
        MultipartFile picture,

        @Schema(description = "Detailed description of the rental", example = "Updated description...")
        String description
) {}
