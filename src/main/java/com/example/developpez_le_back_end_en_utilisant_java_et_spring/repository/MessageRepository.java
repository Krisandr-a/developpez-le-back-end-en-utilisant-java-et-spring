package com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
