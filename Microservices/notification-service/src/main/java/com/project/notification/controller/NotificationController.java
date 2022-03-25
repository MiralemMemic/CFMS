package com.project.notification.controller;

import com.project.notification.exception.ResourceNotFoundException;
import com.project.notification.model.Notification;
import com.project.notification.repository.NotificationRepository;
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
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> getAllMessages(){
        return notificationRepository.findAll();
    }

    @PostMapping
    public Notification createMessage(@RequestBody @Valid Notification message){

        return notificationRepository.save(message);
    }

    @GetMapping("{id}")
    public ResponseEntity<Notification> getMessageById(@PathVariable long id){
        Notification message = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<Notification> deleteMessage(long id){
        Notification message = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        notificationRepository.delete(message);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
