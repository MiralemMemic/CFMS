package com.example.message.controller;

import com.example.message.exception.ResourceNotFoundException;
import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import com.example.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @PostMapping
    public ResponseEntity<String> createMessage( @RequestBody @Valid Message message){
        return messageService.createMessage(message);
    }


    @GetMapping("{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable long id){
        return messageService.getMessageById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMessage(@PathVariable long id){
        return messageService.deleteMessage(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return messageService.handleValidationExceptions(ex);
    }

}
