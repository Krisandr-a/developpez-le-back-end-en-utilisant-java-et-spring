package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.RentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.AddRentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UpdateRentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@Tag(name = "Rentals", description = "Endpoints for managing rental properties")
@SecurityRequirement(name = "bearerAuth")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    @Operation(summary = "Get all rentals", description = "Fetches a list of all rental properties")
    public List<RentalDto> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a rental by ID", description = "Fetches rental details by ID")
    public RentalDto getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new rental", description = "Adds a new rental property")
    public RentalDto createRental(@RequestBody AddRentalDto rentalRequest) {
        return rentalService.addRental(rentalRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a rental", description = "Updates an existing rental by ID")
    public RentalDto updateRental(@PathVariable Integer id, @RequestBody UpdateRentalDto rentalUpdateDto) {
        return rentalService.updateRental(id, rentalUpdateDto);
    }
}
