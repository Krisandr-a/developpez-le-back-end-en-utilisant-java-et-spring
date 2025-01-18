package com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Integer> {
}
