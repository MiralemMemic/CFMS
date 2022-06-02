package com.example.message.service;

import com.commondtos.dto.MessageRequestDto;
import com.commondtos.event.MessageEvent;
import com.commondtos.event.MessageStatus;
import com.example.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class MessageStatusPublisher {

    @Autowired
    private Sinks.Many<MessageEvent> messageSinks;

    public void publishMessageEvent(Message message, MessageStatus messageStatus) {

        MessageRequestDto messageRequestDto = new MessageRequestDto();
        messageRequestDto.setId(message.getId());
        messageRequestDto.setReceiver(message.getReceiver());
        messageRequestDto.setSender(message.getSender());
        messageRequestDto.setContent(message.getContent());

        MessageEvent messageEvent = new MessageEvent(messageRequestDto, messageStatus);
        messageSinks.tryEmitNext(messageEvent);
    }
}
