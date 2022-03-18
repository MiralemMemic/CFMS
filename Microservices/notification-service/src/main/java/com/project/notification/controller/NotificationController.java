package com.project.notification.controller;

import com.project.notification.exception.ResourceNotFoundException;
import com.project.notification.model.Notification;
import com.project.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Notification createMessage(@RequestBody Notification message){
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
}
