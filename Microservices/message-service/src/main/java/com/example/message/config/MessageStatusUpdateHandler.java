package com.example.message.config;

import commondtos.event.MessageStatus;
import commondtos.event.NotificationStatus;
import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import com.example.message.service.MessageStatusPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Configuration
public class MessageStatusUpdateHandler {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private MessageStatusPublisher publisher;

    @Transactional
    public void updateMessage(long id, Consumer<Message> consumer){
        repository.findById(id).ifPresent(consumer.andThen(this::updateMessage));
    }

    private void updateMessage(Message message) {
        boolean isComplete = NotificationStatus.NOTIFICATION_COMPLETED.equals(message.getNotificationStatus());
        MessageStatus messageStatus = isComplete ? MessageStatus.MESSAGE_COMPLETED : MessageStatus.MESSAGE_CANCELLED;
        message.setMessageStatus(messageStatus);
        if(!isComplete){
            publisher.publishMessageEvent(message,messageStatus);
        }
    }




}
