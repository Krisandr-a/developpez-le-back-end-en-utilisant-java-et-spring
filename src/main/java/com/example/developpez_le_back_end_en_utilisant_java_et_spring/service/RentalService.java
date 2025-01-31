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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;


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
                rental.getPicture(),
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }

    public List<RentalDto> convertToDtoList(List<Rental> rentals) {
        return rentals.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public RentalDto addRental(AddRentalDto rentalRequest) {
        User owner = userRepository.findById(rentalRequest.ownerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Rental rental = new Rental();
        rental.setName(rentalRequest.name());
        rental.setSurface(rentalRequest.surface());
        rental.setPrice(rentalRequest.price());
        rental.setPicture(rentalRequest.picture());
        rental.setDescription(rentalRequest.description());
        rental.setOwner(owner);
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());

        Rental savedRental = rentalRepository.save(rental);
        return convertToDto(savedRental);
    }

    public RentalDto updateRental(Integer id, UpdateRentalDto rentalUpdateDto) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        if (rentalUpdateDto.name() != null) rental.setName(rentalUpdateDto.name());
        if (rentalUpdateDto.surface() != null) rental.setSurface(rentalUpdateDto.surface());
        if (rentalUpdateDto.price() != null) rental.setPrice(rentalUpdateDto.price());
        if (rentalUpdateDto.picture() != null) rental.setPicture(rentalUpdateDto.picture());
        if (rentalUpdateDto.description() != null) rental.setDescription(rentalUpdateDto.description());

        rental.setUpdatedAt(LocalDateTime.now());

        Rental updatedRental = rentalRepository.save(rental);
        return convertToDto(updatedRental);
    }

}
