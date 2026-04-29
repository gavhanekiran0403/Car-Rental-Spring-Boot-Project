package com.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.LoginDto;
import com.crms.payload.ApiResponse;
import com.crms.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(
            @RequestBody LoginDto loginDto) {

        ApiResponse<Object> response = authService.login(loginDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    // LOGOUT
    @PostMapping("/logout/{userId}")
    public ResponseEntity<ApiResponse<Object>> logout(
            @PathVariable String userId) {

        ApiResponse<Object> response = authService.logout(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}