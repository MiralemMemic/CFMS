package com.example.message.service;

import com.commondtos.dto.MessageRequestDto;
import com.commondtos.event.MessageEvent;
import com.commondtos.event.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class MessageStatusPublisher {

    @Autowired
    private Sinks.Many<MessageEvent> messageSinks;

    public void publishMessageEvent(MessageRequestDto messageRequestDto, MessageStatus messageStatus) {
        MessageEvent messageEvent = new MessageEvent(messageRequestDto, messageStatus);
        messageSinks.tryEmitNext(messageEvent);
    }
}
