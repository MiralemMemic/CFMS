package com.example.message.controller;

import com.example.message.exception.ResourceNotFoundException;
import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message){
        return messageRepository.save(message);
    }

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable long id){
        Message message = messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<Message> deleteMessage(long id){
        Message message = messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        messageRepository.delete(message);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
