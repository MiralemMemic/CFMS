package com.commondtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private String role;

}
