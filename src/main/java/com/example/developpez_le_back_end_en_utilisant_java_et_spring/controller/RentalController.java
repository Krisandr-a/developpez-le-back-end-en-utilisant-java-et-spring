package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.RentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.AddRentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UpdateRentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Locations")
@SecurityRequirement(name = "bearerAuth")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    @Operation(summary = "Récupérer une liste de toutes les locations")
    public Map<String, List<RentalDto>> getAllRentals() {
        List<RentalDto> rentals = rentalService.getAllRentals();
        return Map.of("rentals", rentals);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recherche d'une location par son identifiant")
    public RentalDto getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    @Operation(summary = "Ajouter une nouvelle location")
    public ResponseEntity<Map<String, String>> createRental(@RequestBody AddRentalDto rentalRequest) {
        rentalService.addRental(rentalRequest);
        return ResponseEntity.ok(Map.of("message", "Rental created!"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une location via son identifiant")
    public ResponseEntity<Map<String, String>> updateRental(@PathVariable Integer id, @RequestBody UpdateRentalDto rentalUpdateDto) {
        rentalService.updateRental(id, rentalUpdateDto); // Perform the update
        return ResponseEntity.ok(Map.of("message", "Rental updated!")); // Return custom message
    }

}
