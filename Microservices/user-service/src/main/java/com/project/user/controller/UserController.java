package com.project.user.controller;


import com.project.user.exception.ResourceNotFoundException;
import com.project.user.model.User;
import com.project.user.repository.UserRepository;
import com.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // build create user REST API
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid User user) {
       return userService.createUser(user);
    }

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable  long id){
        return userService.getUserById(id);
    }

    // build update user REST API
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User userDetails) {
        return userService.updateUser(id,userDetails);
    }

    // build delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        return userService.deleteUser(id);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
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

}
