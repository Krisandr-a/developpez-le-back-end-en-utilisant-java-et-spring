package com.example.developpez_le_back_end_en_utilisant_java_et_spring.service;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.AddMessageDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.Message;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.Rental;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.MessageRepository;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.RentalRepository;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public void addMessage(AddMessageDto addMessageDto) {
        User user = userRepository.findById(addMessageDto.user_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Rental rental = rentalRepository.findById(addMessageDto.rental_id())
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        Message message = new Message();
        message.setMessage(addMessageDto.message());
        message.setUser(user);
        message.setRental(rental);
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);
    }

}
