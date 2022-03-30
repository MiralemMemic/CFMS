package com.project.notification.service;

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

public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public Notification createNotification(Notification notification){

        return notificationRepository.save(notification);
    }

    public ResponseEntity<Notification> getNotificationById(long id){
        Notification message = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<HttpStatus> deleteNotification(long id){
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        notificationRepository.delete(notification);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
