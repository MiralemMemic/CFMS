package com.example.message.model;


import com.commondtos.event.MessageStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Message.class)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message =  "The receiver is required")
    @Min(1)
    @Column(name= "receiver_name")
    private long receiver;

    @Column(name = "sender_name")
    private long sender;

    @NotBlank(message =  "The message is required")
    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;
}
