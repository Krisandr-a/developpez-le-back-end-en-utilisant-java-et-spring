package com.example.developpez_le_back_end_en_utilisant_java_et_spring.service;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.RentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.AddRentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UpdateRentalDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.Rental;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.RentalRepository;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Data
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    public List<RentalDto> getAllRentals() {
        Iterable<Rental> rentals = rentalRepository.findAll();
        return convertToDtoList((List<Rental>) rentals);
    }

    public RentalDto getRentalById(Integer id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
        return convertToDto(rental);
    }

    public RentalDto convertToDto(Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                "http://localhost:3001" + rental.getPicture(),
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }

    public List<RentalDto> convertToDtoList(List<Rental> rentals) {
        return rentals.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public void addRental(AddRentalDto rentalRequest) {
        User owner = userRepository.findById(rentalRequest.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        String filename = saveImage(rentalRequest.picture());

        Rental rental = new Rental();
        rental.setName(rentalRequest.name());
        rental.setSurface(rentalRequest.surface());
        rental.setPrice(rentalRequest.price());
        rental.setPicture(filename != null ? "/images/" + filename : null);
        rental.setDescription(rentalRequest.description());
        rental.setOwner(owner);
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());

        Rental savedRental = rentalRepository.save(rental);
        convertToDto(savedRental);
    }



    public void updateRental(Integer id, UpdateRentalDto rentalUpdateDto) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        if (rentalUpdateDto.name() != null) rental.setName(rentalUpdateDto.name());
        if (rentalUpdateDto.surface() != null) rental.setSurface(rentalUpdateDto.surface());
        if (rentalUpdateDto.price() != null) rental.setPrice(rentalUpdateDto.price());
        if (rentalUpdateDto.picture() != null && !rentalUpdateDto.picture().isEmpty()) {
            String filename = saveImage(rentalUpdateDto.picture());
            rental.setPicture("/images/" + filename);
        }
        if (rentalUpdateDto.description() != null) rental.setDescription(rentalUpdateDto.description());

        rental.setUpdatedAt(LocalDateTime.now());

        Rental updatedRental = rentalRepository.save(rental);
        convertToDto(updatedRental);
    }

    private String saveImage(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;

        try {
            File uploadPath = new File(UPLOAD_DIR);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + filename);
            Files.copy(file.getInputStream(), filePath);

            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the image", e);
        }

    }
}
