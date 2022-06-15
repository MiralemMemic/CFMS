package com.example.message;

import com.example.message.controller.MessageController;
import com.example.message.exception.ResourceNotFoundException;
import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import com.example.message.service.MessageService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.xml.bind.ValidationException;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@WebMvcTest(controllers = MessageController.class)
public class MessageControllerTest {
/*
    @MockBean
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("List all Messages when making GET request")
    public void ListAllMessages() throws Exception{

        Message message1 = new Message();
        message1.setId(1);
        message1.setReceiver(1);
        message1.setSender(2);
        message1.setContent("Hello");


        Message message2 = new Message();
        message2.setId(2);
        message2.setReceiver(3);
        message2.setSender(4);
        message2.setContent("Goodbye");

        Mockito.when(messageService.getAllMessages()).thenReturn(Arrays.asList(message1,message2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/messages"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @DisplayName("Get message by id with GET")
    public void GetAMessage() throws Exception{

        Message message = new Message();
        message.setId(1);
        message.setReceiver(1);
        message.setSender(2);
        message.setContent("Hello");


        Mockito.when(messageService.getMessageById(1l)).thenReturn(ResponseEntity.ok(message));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/messages/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.receiver", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sender", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Hello")));
    }

    @Test
    @DisplayName("Delete a message when making DELETE")
    public void DeleteMessage() throws Exception{

        Message message = new Message();
        message.setId(1);
        message.setReceiver(1);
        message.setSender(2);
        message.setContent("Hello");

        Mockito.when(messageRepository.findById(1l)).thenReturn(Optional.of(message));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/messages/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void testErrorGet() throws Exception {

        Message message = new Message();
        message.setId(1);
        Mockito.when(messageService.getMessageById(1l)).thenThrow(new ResourceNotFoundException("Message not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/messages/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    @DisplayName("Send error when message information is invalid")
    public void testInvalidPost() throws Exception {


        Message message = new Message();
        message.setId(1);
        message.setSender(2);
        message.setContent("Hello");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(message);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/api/v1/messages")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        this.mockMvc.perform(builder)
                .andExpect(status().is4xxClientError());
    }
    @Test
    @DisplayName("Should Post  - /api/messages/")
    public void testPost() throws Exception {

        Message message = new Message();
        message.setId(1);
        message.setReceiver(1);
        message.setSender(2);
        message.setContent("Hello");

        Mockito.when(messageService.createMessage(message)).thenReturn(ResponseEntity.ok("Message added"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(message);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/messages")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

 */
}
