package com.project.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.user.exception.ResourceNotFoundException;
import com.project.user.model.User;
import com.project.user.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerIntegrationTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List All Posts When making GET request to endpoint - /api/users/")
    public void testGetAll() throws Exception {

        User bozaPub = new User();
        bozaPub.setFirstName("Boza");
        bozaPub.setLastName("Pub");
        bozaPub.setUsername("kockar");
        bozaPub.setPassword("karte123");
        bozaPub.setRole("warden");
        bozaPub.setEmail("boza@hotmail.com");

        User katrin = new User();
        katrin.setFirstName("Katrin");
        katrin.setLastName("Francuska");
        katrin.setUsername("stanicaPula");
        katrin.setPassword("dijon123");
        katrin.setRole("warden");
        katrin.setEmail("unknown@hotmail.com");

        List<User> userList = new ArrayList<User>();
        userList.add(katrin);
        userList.add(bozaPub);

        Mockito.when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8082/api/v1/users"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Katrin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("stanicaPula")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", Matchers.is("Boza")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].password", Matchers.is("karte123")));
    }

    @Test
    @DisplayName("Should Get when making Post request by id to endpoint - /api/users/")
    public void testGetID() throws Exception {

        User bozaPub = new User();
        bozaPub.setFirstName("Boza");
        bozaPub.setLastName("Pub");
        bozaPub.setUsername("kockar");
        bozaPub.setPassword("karte123");
        bozaPub.setRole("warden");
        bozaPub.setEmail("boza@hotmail.com");

        Mockito.when(userService.getUserById(0l)).thenReturn(ResponseEntity.ok(bozaPub));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/{id}", 0L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Boza")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("karte123")));
    }

    @Test
    @DisplayName("Should not Get when making Post request by id to endpoint - /api/users/")
    public void testGetID1() throws Exception {

        Mockito.when(userService.getUserById(0l)).thenThrow(new ResourceNotFoundException("User not exist with id: 0"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/0"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Should Delete when making Post request by id to endpoint - /api/users/")
    public void testDelete1() throws Exception {

        User katrin = new User();
        katrin.setFirstName("Katrin");
        katrin.setLastName("Francuska");
        katrin.setUsername("stanicaPula");
        katrin.setPassword("dijon123");
        katrin.setRole("warden");
        katrin.setEmail("unknown@hotmail.com");

        Mockito.when(userService.getUserById(0l)).thenReturn(ResponseEntity.ok(katrin));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/0"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    

    /*
    @Test
    @DisplayName("Should Update when making Post request by id to endpoint - /api/users/")
    public void testUpdate() throws Exception {

        User katrin = new User();
        katrin.setId(0);
        katrin.setFirstName("Katrin");
        katrin.setLastName("Francuska");
        katrin.setUsername("stanicaPula");
        katrin.setPassword("dijon123");
        katrin.setRole("warden");
        katrin.setEmail("unknown@hotmail.com");

        Mockito.when(userService.createUser(katrin)).thenReturn(ResponseEntity.ok("User added"));

        katrin.setLastName("Rovinj");

        Mockito.when(userService.updateUser(0l, katrin)).thenReturn(ResponseEntity.ok(katrin));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/{id}", 0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }*/


    @Test
    @DisplayName("Should Post  - /api/users/")
    public void testPost() throws Exception {

        User katrin = new User();
        katrin.setFirstName("Katrin");
        katrin.setLastName("Francuska");
        katrin.setUsername("stanicaPula");
        katrin.setPassword("dijon123");
        katrin.setRole("warden");
        katrin.setEmail("unknown@hotmail.com");

        Mockito.when(userService.createUser(katrin)).thenReturn(ResponseEntity.ok("User added"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(katrin);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}