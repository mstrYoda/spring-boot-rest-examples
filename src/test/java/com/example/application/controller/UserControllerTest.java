package com.example.application.controller;

import com.example.application.request.CreateUserRequest;
import com.example.domain.user.User;
import com.example.infrastructure.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserRepository userRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("test user");

        User user = new User();
        user.setName(request.getName());

        when(userRepository.save(any(User.class))).thenReturn(user  );

        mockMvc.perform(post("/users")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    public void when_getUser_with_id_should_return_user() throws Exception {
        User user = new User();
        user.setName("test user");

        when(userRepository.getOne(any(int.class))).thenReturn(user);

        mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(user.getName()));
    }

    @Test
    public void listUsers() throws Exception {
        User user = new User();
        user.setName("test user");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]name").value(user.getName()));
    }

    @Test
    public void updateUser() {
    }
}