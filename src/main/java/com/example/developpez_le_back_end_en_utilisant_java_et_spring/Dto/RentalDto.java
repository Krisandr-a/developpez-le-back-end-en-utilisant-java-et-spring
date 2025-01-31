package com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO representing a rental property")
public record RentalDto(
        @Schema(description = "Unique identifier of the rental", example = "1")
        Integer id,

        @Schema(description = "Name of the rental property", example = "test house 1")
        String name,

        @Schema(description = "Surface area of the property in square meters", example = "432")
        BigDecimal surface,

        @Schema(description = "Rental price per month", example = "300")
        BigDecimal price,

        @Schema(description = "URL of the property picture", example = "https://example.com/image.jpg")
        String picture,

        @Schema(description = "Detailed description of the rental property", example = "Lorem ipsum dolor sit amet...")
        String description,

        @Schema(description = "ID of the owner", example = "1")
        Integer ownerId,

        @Schema(description = "Timestamp when the rental was created", example = "2012-12-02T00:00:00")
        LocalDateTime createdAt,

        @Schema(description = "Timestamp when the rental was last updated", example = "2014-12-02T00:00:00")
        LocalDateTime updatedAt
) {}

