package com.project.notification;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.notification.controller.NotificationController;
import com.project.notification.exception.ResourceNotFoundException;
import com.project.notification.model.Notification;
import com.project.notification.repository.NotificationRepository;
import com.project.notification.service.NotificationService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NotificationController.class)
public class NotificationControllerTest {

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private NotificationRepository notificationRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("List all Notifications when making GET request")
    public void ListAllNotifications() throws Exception{

        Notification notification1 = new Notification();

        notification1.setId(1);
        notification1.setJail(2);
        notification1.setText("Kriv");

        Notification notification2 = new Notification();

        notification2.setId(2);
        notification2.setJail(2);
        notification2.setText("Kriv");

        Mockito.when(notificationService.getAllNotifications()).thenReturn(Arrays.asList(notification1,notification2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notifications"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @DisplayName("Get Notifications by id with GET")
    public void GetANotifications() throws Exception{

        Notification notification = new Notification();
        notification.setId(1);
        notification.setJail(2);
        notification.setText("Kriv");

        Mockito.when(notificationService.getNotificationById(1l)).thenReturn(ResponseEntity.ok(notification));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notifications/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.jail", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.text", Matchers.is("Kriv")));
    }

    @Test
    @DisplayName("Delete a Notification when making DELETE")
    public void DeleteNotification() throws Exception{

        Notification notification = new Notification();
        notification.setId(1);
        notification.setJail(2);
        notification.setText("Kriv");

        Mockito.when(notificationRepository.findById(1l)).thenReturn(Optional.of(notification));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/notifications/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void testErrorGet() throws Exception {

        Notification notification = new Notification();
        notification.setId(1);

        Mockito.when(notificationService.getNotificationById(1l)).thenThrow(new ResourceNotFoundException("Message not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/notifications/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    @DisplayName("Send error when notification information is invalid")
    public void testInvalidPost() throws Exception {


        Notification notification = new Notification();

        notification.setText("Kriv");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(notification);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/api/v1/notifications")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        this.mockMvc.perform(builder)
                .andExpect(status().is4xxClientError());
    }
    @Test
    @DisplayName("Should Post  - /api/notifications/")
    public void testPost() throws Exception {

        Notification notification = new Notification();
        notification.setId(1);
        notification.setJail(2);
        notification.setText("Kriv");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(notification);

        Mockito.when(notificationService.createNotification(notification)).thenReturn(ResponseEntity.ok("Message added"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/notifications")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}
