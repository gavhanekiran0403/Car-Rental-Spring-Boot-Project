package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.UserDto;
import com.crms.payload.ApiResponse;
import com.crms.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> registerUser(
            @RequestBody UserDto userDto) {

        UserDto registeredUser =
                userService.registerUser(userDto);

        ApiResponse<UserDto> response =
                new ApiResponse<>(
                        "User registered successfully",
                        true,
                        registeredUser
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // UPDATE USER
    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @PathVariable String userId,
            @RequestBody UserDto userDto) {

        UserDto updatedUser =
                userService.updateUser(userId, userDto);

        ApiResponse<UserDto> response =
                new ApiResponse<>(
                        "User updated successfully",
                        true,
                        updatedUser
                );

        return ResponseEntity.ok(response);
    }

    // GET USER BY ID
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(
            @PathVariable String userId) {

        UserDto user =
                userService.getUserById(userId);

        ApiResponse<UserDto> response =
                new ApiResponse<>(
                        "User fetched successfully",
                        true,
                        user
                );

        return ResponseEntity.ok(response);
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {

        List<UserDto> users =
                userService.getAllUsers();

        ApiResponse<List<UserDto>> response =
                new ApiResponse<>(
                        "Users fetched successfully",
                        true,
                        users
                );

        return ResponseEntity.ok(response);
    }
}