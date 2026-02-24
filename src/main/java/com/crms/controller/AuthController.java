package com.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crms.dto.LoginDto;
import com.crms.payload.ApiResponse;
import com.crms.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto) {
		ApiResponse response = authService.login(loginDto);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/logout/{userId}")
	public ResponseEntity<ApiResponse> logout(@PathVariable String userId) {
		authService.logout(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("logout successfully...!", true), HttpStatus.OK);
	}
}
