package com.project.user.controller;


import com.project.user.exception.ResourceNotFoundException;
import com.project.user.model.User;
import com.project.user.repository.UserRepository;
import com.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllEmployees(){
        return userService.getAllEmployees();
    }

    // build create user REST API
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody @Valid User employee) {
       return userService.createEmployee(employee);
    }

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable  long id){
        return userService.getEmployeeById(id);
    }

    // greeting
    @GetMapping("/greeting")
    public String getGreeting(){
        return "my.greeting: " + greetingMessage;
    }

    // build update user REST API
    @PutMapping("{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable long id,@RequestBody User userDetails) {
        return userService.updateEmployee(id,userDetails);
    }

    // build delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        return userService.deleteEmployee(id);

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
