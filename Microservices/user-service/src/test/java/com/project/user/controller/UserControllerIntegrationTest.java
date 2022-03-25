package com.project.user.controller;

import com.project.user.model.User;
import com.project.user.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    UserController userController;

    @Autowired
    private MockMvc mockMvc;

 /*   @Test
    void getAllUsers() throws Exception {


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/v1/users");
        System.out.println(requestBuilder);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult);
        assertEquals(2, mvcResult.getResponse().getContentLengthLong());

        //UserController userController = new UserController();
        //List<User> users = userController.getAllEmployees();
        //assertEquals(2, users.size());
    } */


    @Test
    public void getAllUsers() throws Exception {
        String uri = "/api/v1/users";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length() > 0);
    }


 /*   @Test
    public void createUser() throws Exception {
        String uri = "/api/v1/users";
        User user = new User();
        user.setFirstName("Kemal");
        user.setLastName("Halilovic");
        user.setUsername("khalilovic");
        user.setPassword("kemal2022");
        user.setEmail("khalilovic@hotmail.com");
        user.setRole("warden");


        JSONObject json = new JSONObject(String.valueOf(user));
        //String inputJson = new JSONObject(user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(String.valueOf(json))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        //String content = mvcResult.getResponse().getContentAsString();
        //assertEquals(content, "Product is created successfully");
    }*/

}