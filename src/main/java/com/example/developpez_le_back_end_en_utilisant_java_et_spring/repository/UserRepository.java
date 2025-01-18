package com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Integer> {
}
