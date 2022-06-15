package com.project.notification.config;

import com.project.notification.service.NotificationService;
import commondtos.event.MessageEvent;
import commondtos.event.MessageStatus;
import commondtos.event.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class NotificationConsumerConfig {

    @Autowired
    private NotificationService notificationService;

    @Bean
    public Function<Flux<MessageEvent>, Flux<NotificationEvent>> notificationProcessor() {
        return  messageEventFlux -> messageEventFlux.flatMap(this::processNotification);
    }

    private Mono<NotificationEvent> processNotification(MessageEvent messageEvent) {

        if(MessageStatus.MESSAGE_CREATED.equals(messageEvent.getMessageStatus())){
            return  Mono.fromSupplier(()->this.notificationService.newMessageEvent(messageEvent));
        }else{
            return Mono.fromRunnable(()->this.notificationService.cancelMessageEvent(messageEvent));
        }
    }
}

