package com.project.systemeventsver2.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name= "timestamp")
    private String time;

    @Column(name= "service_name")
    private String name;

    @Column(name= "type")
    private String type;

    @Column(name= "resource")
    private String resource;

    @Column(name= "response")
    private String response;

}
