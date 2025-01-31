package com.example.developpez_le_back_end_en_utilisant_java_et_spring.service;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.UserDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.model.User;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public List<UserDto> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll(); // Cast Iterable to List
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return convertToDto(user);
    }


    //Get all
    /*public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }*/

    //Create user
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /*public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    } */

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email " + email));
    }


}
