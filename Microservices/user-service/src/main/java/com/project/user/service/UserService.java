package com.project.user.service;

import com.project.user.exception.ResourceNotFoundException;
import com.project.user.model.User;
import com.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // build create user REST API

    public ResponseEntity<String> createUser(User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User added");
    }

    // build get user by id REST API

    public ResponseEntity<User> getUserById(long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
        return ResponseEntity.ok(user);
    }

    // build update user REST API
    public ResponseEntity<User> updateUser(long id,User userDetails) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setUsername(userDetails.getUsername());
        updateUser.setPassword(userDetails.getPassword());
        updateUser.setRole(userDetails.getRole());

        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }

    // build delete user REST API
    public ResponseEntity<HttpStatus> deleteUser(long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        userRepository.delete(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }



    public void generateData() {
        User user = new User();
        user.setFirstName("Miralem");
        user.setLastName("Memic");
        user.setUsername("mmemic");
        user.setPassword("miralem2022");
        user.setEmail("mememic@hotmail.com");
        user.setRole("warden");
        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Kenan");
        user1.setLastName("Omic");
        user1.setUsername("komic");
        user1.setPassword("kenan2022");
        user1.setEmail("komic@hotmail.com");
        user1.setRole("warden");
        userRepository.save(user1);
    }

    public List<User> getSorted() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC,"firstName"));
    }
}
