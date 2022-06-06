package com.project.authservice.models;

public class UserResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
