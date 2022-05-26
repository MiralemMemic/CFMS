package com.commondtos.dto;

import com.commondtos.event.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDto {

    private long id;

    private long receiver;

    private long sender;

    private String content;

    private MessageStatus messageStatus;

}
