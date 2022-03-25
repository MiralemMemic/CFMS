package com.project.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name can't be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name can't be empty")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email needs to be format name.lastname@email.com")
    @NotBlank(message = "Email can not be empty")
    @Column(name = "email")
    private String email;

    @Size(min=5, max=15, message="Username must be between 5 and 15 characters")
    @Column(name = "username")
    private String username;


    @Size(min=5, max=15, message="Password must be between 5 and 15 characters")
    @Column(name = "password")
    private String password;


    @Column(name = "role")
    private String role;
}