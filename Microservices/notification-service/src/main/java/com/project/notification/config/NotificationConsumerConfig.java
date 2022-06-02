package com.project.notification.config;

import com.commondtos.event.MessageEvent;
import com.commondtos.event.MessageStatus;
import com.commondtos.event.NotificationEvent;

import com.project.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class NotificationConsumerConfig {
/*
    @Autowired
    private NotificationService notificationService;

    @Bean
    public Function<Flux<MessageEvent>, Flux<NotificationEvent>> notificationProcessor() {
        Function<Flux<MessageEvent>, Flux<NotificationEvent>> fluxFluxFunction = (Function<Flux<MessageEvent>, Flux<NotificationEvent>>) messageEventFlux -> messageEventFlux.flatMap(this::processNotification);
        return fluxFluxFunction;
    }

    private Mono<MessageEvent> processNotification(MessageEvent messageEvent) {

        if(MessageStatus.MESSAGE_CREATED.equals(messageEvent.getMessageStatus())){
            return  Mono.fromSupplier(()->this.notificationService.newMessageEvent(messageEvent));
        }else{
            return Mono.fromRunnable(()->this.notificationService.cancelMessageEvent(messageEvent));
        }
    }
    }*/

}
