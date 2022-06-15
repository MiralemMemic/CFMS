//package com.example.message.config;
//
//import commondtos.event.NotificationEvent;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.function.Consumer;
//
//@Configuration
//public class EventConsumerConfig {
//
//    @Autowired
//    private MessageStatusUpdateHandler handler;
//
//    @Bean
//    public Consumer<NotificationEvent> notificationEventConsumer(){
//        return (message) -> handler.updateMessage(message.getNotificationRequestDto().getId(),mo -> {
//            mo.setNotificationStatus(message.getUserStatus());
//        } );
//    }
//}
