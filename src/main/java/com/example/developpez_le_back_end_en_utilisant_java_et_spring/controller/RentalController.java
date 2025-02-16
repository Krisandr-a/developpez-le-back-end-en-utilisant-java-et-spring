package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.RentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.AddRentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UpdateRentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.service.AuthService;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Locations")
@SecurityRequirement(name = "bearerAuth")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private AuthService authService;

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


    @PostMapping(consumes = {"multipart/form-data"})
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Ajouter une nouvelle location")
    public ResponseEntity<Map<String, String>> createRental(
            @RequestParam("name") String name,
            @RequestParam("surface") BigDecimal surface,
            @RequestParam("price") BigDecimal price,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("description") String description) {

        // Get the authenticated user
        User user = authService.getAuthenticatedUser(); // Uses the AuthService from previous approach
        Integer ownerId = user.getId();

        // Create the rental DTO
        AddRentalDto rentalRequest = new AddRentalDto(name, surface, price, picture, description, ownerId);

        // Save rental
        rentalService.addRental(rentalRequest);

        return ResponseEntity.ok(Map.of("message", "Rental created!"));
    }



    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Mettre à jour une location via son identifiant")
    public ResponseEntity<Map<String, String>> updateRental(
            @PathVariable Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal surface,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) MultipartFile picture,
            @RequestParam(required = false) String description) {

        UpdateRentalDto rentalUpdateDto = new UpdateRentalDto(name, surface, price, picture, description);
        rentalService.updateRental(id, rentalUpdateDto);

        return ResponseEntity.ok(Map.of("message", "Rental updated!"));
    }
}
