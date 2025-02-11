package com.example.developpez_le_back_end_en_utilisant_java_et_spring.controller;

import com.example.developpez_le_back_end_en_utilisant_java_et_spring.Dto.AddMessageDto;
import com.example.developpez_le_back_end_en_utilisant_java_et_spring.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/messages")
@Tag(name = "Messages", description = "Endpoint pour ajouter des messages")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    @Operation(summary = "Create a new message", description = "Sends a message for a rental")
    public ResponseEntity<Map<String, String>> createMessage(@RequestBody AddMessageDto addMessageDto) {
        messageService.addMessage(addMessageDto);
        return ResponseEntity.ok(Map.of("message", "Message send with success"));
    }
}

