package com.example.message.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotNull(message =  "The receiver is required")
    @Min(1)
    @Column(name= "receiver_name")
    private int receiver;

    @Column(name = "sender_name")
    private int sender;

    @NotBlank(message =  "The message is required")
    @Column(name = "content")
    private String content;
}
