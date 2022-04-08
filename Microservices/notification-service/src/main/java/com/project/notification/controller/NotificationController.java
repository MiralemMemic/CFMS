package com.project.notification.controller;

import com.project.notification.exception.ResourceNotFoundException;
import com.project.notification.model.Notification;
import com.project.notification.model.NotifierMessage;
import com.project.notification.repository.NotificationRepository;
import com.project.notification.service.NotificationService;
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
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody @Valid Notification message){
        return notificationService.createNotification(message);
    }

    @GetMapping("{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable long id){
        return notificationService.getNotificationById(id);
    }
    @DeleteMapping ("{id}")
    public ResponseEntity<HttpStatus> deleteNotification(@PathVariable long id){
        return notificationService.deleteNotification(id);
    }

    @GetMapping("who-notified/{id}")
    public ResponseEntity<NotifierMessage> whoNotified(@PathVariable long id){
        return notificationService.whoNotified(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return notificationService.handleValidationExceptions(ex);
    }
}
