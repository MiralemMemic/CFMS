//package com.example.message.config;
//
//import commondtos.event.MessageEvent;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Sinks;
//
//import java.util.function.Supplier;
//
//@Configuration
//public class MessagePublisherConfig {
//
//    @Bean
//    public Sinks.Many<MessageEvent> messageEventSinks() {
//        return Sinks.many().multicast().onBackpressureBuffer();
//    }
//
//    @Bean
//    public Supplier<Flux<MessageEvent>> messageSupplier(Sinks.Many<MessageEvent> sinks){
//        return sinks::asFlux;
//    }
//
//}
