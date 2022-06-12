package com.project.authservice.services;

import com.project.authservice.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username je " + username);
        ResponseEntity<UserResponse> responseEntity = restTemplate.getForEntity("http://localhost:8082/api/v1/users/name/" + username, UserResponse.class);
        UserResponse userResponse = responseEntity.getBody();
        System.out.println("pronadjeni user je " + userResponse);
        return new User(userResponse.getUsername(), userResponse.getPassword(), new ArrayList<>());
    }
}
