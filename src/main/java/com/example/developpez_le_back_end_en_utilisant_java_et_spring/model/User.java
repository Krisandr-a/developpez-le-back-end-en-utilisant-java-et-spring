package com.example.developpez_le_back_end_en_utilisant_java_et_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;


}
/*
CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);


 */