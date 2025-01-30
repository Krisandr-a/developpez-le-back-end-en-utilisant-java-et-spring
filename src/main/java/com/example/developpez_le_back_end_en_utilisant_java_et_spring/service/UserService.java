package com.example.developpez_le_back_end_en_utilisant_java_et_spring.service;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //Get all
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Create user
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email " + email));
    }


}
