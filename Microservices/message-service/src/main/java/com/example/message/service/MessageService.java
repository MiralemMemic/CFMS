package com.example.message.service;

import com.example.message.exception.ResourceNotFoundException;
import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }


    public ResponseEntity<String> createMessage(Message message){
        messageRepository.save(message);
        return ResponseEntity.ok("Message sent");
    }

    public List<Message> getMessagesSortedBySender(){
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC,"sender"));
    }

    public List<Message> getMessagesSortedByReceiver(){
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC,"receiver"));
    }

    public ResponseEntity<Message> getMessageById(long id){
        Message message = messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<HttpStatus> deleteMessage(long id){
        Message message = messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        messageRepository.delete(message);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage;
            if(fieldName.equals("receiver")) {
                errorMessage = "User can't be null or 0";
            }else{
                errorMessage = error.getDefaultMessage();
            }
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
