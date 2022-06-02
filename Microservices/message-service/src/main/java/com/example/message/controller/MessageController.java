package com.example.message.controller;

import com.commondtos.event.MessageStatus;
import com.example.message.exception.ResourceNotFoundException;
import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import com.example.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private static Logger log = LoggerFactory.getLogger(MessageController.class);


    @Autowired
    private MessageService messageService;


    @GetMapping(value = "/producer")
    public String producer(@RequestParam("id") long id, @RequestParam("receiver") long receiver,
                           @RequestParam("sender") long sender, @RequestParam("content") String content) {

        Message message = new Message();
        message.setId(id);
        message.setReceiver(receiver);
        message.setSender(sender);
        message.setContent(content);
        messageService.send(message);

        return "JMS Message sent to the RabbitMQ Successfully";
    }


    @GetMapping
    public List<Message> getAllMessages(){
        log.info("MessageController - getAllMessages");
        return messageService.getAllMessages();
    }

    @PostMapping
    public ResponseEntity<String> createMessage( @RequestBody @Valid Message message){
        log.info("MessageController - createMessage");
        message.setMessageStatus(MessageStatus.MESSAGE_CREATED);
        return messageService.createMessage(message);
    }


    @GetMapping("{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable long id){
        log.info("MessageController - getMessageById");
        return messageService.getMessageById(id);
    }

    @GetMapping("/sent/{id}")
    public ResponseEntity<List<Message>> getSentMessagesByUserId(@PathVariable long id){
        return messageService.getSentMessagesByUserId(id);
    }

    @GetMapping("/received/{id}")
    public ResponseEntity<List<Message>> getReceivedMessagesByUserId(@PathVariable long id){
        return messageService.getReceivedMessagesByUserId(id);
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
