package com.project.notification.service;

import com.commondtos.dto.MessageRequestDto;
import com.commondtos.dto.NotificationRequestDto;
import com.commondtos.event.MessageEvent;
import com.commondtos.event.NotificationEvent;
import com.commondtos.event.NotificationStatus;
import com.project.notification.exception.ResourceNotFoundException;
import com.project.notification.model.Notification;
import com.project.notification.model.NotificationTransaction;
import com.project.notification.model.NotifierMessage;
import com.project.notification.model.User;
import com.project.notification.repository.NotificationRepository;
import com.project.notification.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserTransactionRepository notificationTransactionRepository;

    @Autowired
    private RestTemplate restTemplate;


    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public ResponseEntity<String> createNotification(Notification notification){
        notificationRepository.save(notification);
        return ResponseEntity.ok("Notification sent");
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

    public ResponseEntity<NotifierMessage> whoNotified(long id){
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification does not exist with id:" + id));
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://user-service/api/v1/users/" + notification.getNotifier(), User.class);
        User notifier = responseEntity.getBody();
        NotifierMessage notifierMessage = new NotifierMessage(notification.getText(),notifier);
        return ResponseEntity.ok(notifierMessage);
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

    @Transactional
    public NotificationEvent newMessageEvent(MessageEvent messageEvent) {

        MessageRequestDto messageRequestDto = messageEvent.getMessageRequestDto();

        NotificationRequestDto notificationRequestDto = new NotificationRequestDto(messageRequestDto.getId(), messageRequestDto.getReceiver(),
                messageRequestDto.getContent(), messageRequestDto.getSender());

        if(messageRequestDto.getSender() != messageRequestDto.getReceiver()){
            notificationTransactionRepository.save(new NotificationTransaction(messageRequestDto.getSender(),messageRequestDto.getReceiver()));
            notificationRepository.save(new Notification(messageRequestDto.getId(),(int) messageRequestDto.getReceiver(),messageRequestDto.getContent(), messageRequestDto.getSender()));
            return new NotificationEvent(notificationRequestDto, NotificationStatus.NOTIFICATION_COMPLETED);
        }else{
            return new NotificationEvent(notificationRequestDto, NotificationStatus.NOTIFICATION_FAILED);
        }


   //     notificationRepository.findAll().stream().filter(n -> n.getJail() == messageRequestDto.getReceiver())
   //             .map

    }
    @Transactional
    public void cancelMessageEvent(MessageEvent messageEvent) {
        notificationTransactionRepository.findById((int)messageEvent.getMessageRequestDto().getSender())
                .ifPresent(notificationTransaction -> {
                    notificationTransactionRepository.delete(notificationTransaction);
                });
        notificationRepository.findById(messageEvent.getMessageRequestDto().getSender())
                .ifPresent(notification -> {
                    notificationRepository.delete(notification);
                });
    }
}
