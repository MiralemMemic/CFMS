package com.project.user.controller;


import com.project.user.VO.ResponseTemplateVO;
import com.project.user.exception.ResourceNotFoundException;
import com.project.user.model.Message;
import com.project.user.model.User;
import com.project.user.model.UserMessage;
import com.project.user.repository.UserRepository;
import com.project.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        log.info("UserConstroller - getAllUsers");
        return userService.getAllUsers();
    }

    // build create user REST API
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid User user) {
        log.info("UserConstroller - createUser");
        return userService.createUser(user);
    }

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable  long id){
        log.info("UserConstroller - getUserById");
        return userService.getUserById(id);
    }

    @GetMapping("/sent-messages/{id}")
    public ResponseEntity<UserMessage> getUsersSentMessages(@PathVariable  long id){
        log.info("UserConstroller - getUsersSentMessages");
        return userService.getUsersSentMessages(id);
    }

    @GetMapping("/received-messages/{id}")
    public ResponseEntity<UserMessage> getUsersReceivedMessages(@PathVariable  long id){
        log.info("UserConstroller - getUsersReceivedMessages");
        return userService.getUsersReceivedMessages(id);
    }

    // greeting
    @GetMapping("/greeting")
    public String getGreeting(){
        return "my.greeting: " + greetingMessage;
    }

    // build update user REST API
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User userDetails) {
        log.info("UserConstroller - updateUser");
        return userService.updateUser(id,userDetails);
    }

    // build delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        log.info("UserConstroller - deleteUser");
        return userService.deleteUser(id);
    }

    @GetMapping("worst")
    public List<User> getSortedUsers(){
        log.info("UserConstroller - getSortedUsers");
        return userService.getSorted();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return userService.handleValidationExceptions(ex);
    }

 /*   @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithMessages(@PathVariable("id") String id, Long userId) {
        return userService.getUserWithMessage(userId);
    }
*/

}


class HandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {
        return true;
    }
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {

    }
}