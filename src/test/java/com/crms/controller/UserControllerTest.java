package com.crms.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crms.dto.UserDto;
import com.crms.enums.Role;
import com.crms.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    private UserDto getUserDto() {

        UserDto userDto = new UserDto();
        userDto.setUserId("1");
        userDto.setFullName("Kiran Gavhane");
        userDto.setPhoneNo("9049226199");
        userDto.setEmail("gavhanekiran0403@gmail.com");
        userDto.setPassword("kiran@123");
        userDto.setRole(Role.USER);
        userDto.setStatus(false);

        return userDto;
    }

    @Test
    void testRegisterUser() throws Exception {

        UserDto userDto = getUserDto();

        when(userService.registerUser(any(UserDto.class)))
                .thenReturn(userDto);

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value("Kiran Gavhane"));
    }

    @Test
    void testUpdateUser() throws Exception {

        UserDto userDto = getUserDto();

        when(userService.updateUser(anyString(), any(UserDto.class)))
                .thenReturn(userDto);

        mockMvc.perform(put("/users/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Kiran Gavhane"));
    }

    @Test
    void testGetUserById() throws Exception {

        UserDto userDto = getUserDto();

        when(userService.getUserById("1")).thenReturn(userDto);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Kiran Gavhane"));
    }

    @Test
    void testGetAllUsers() throws Exception {

        UserDto userDto = getUserDto();

        when(userService.getAllUsers()).thenReturn(Arrays.asList(userDto));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Kiran Gavhane"));
    }
}