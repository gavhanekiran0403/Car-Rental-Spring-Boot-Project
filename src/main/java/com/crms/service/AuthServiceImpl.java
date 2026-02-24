package com.crms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.dto.LoginDto;
import com.crms.entities.User;
import com.crms.exception.ResourceNotFoundException;
import com.crms.payload.ApiResponse;
import com.crms.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ApiResponse login(LoginDto loginDto) {
		
		User user = userRepository.findByEmailOrPhoneNo(loginDto.getUsername())
		.orElseThrow(() -> 
			new ResourceNotFoundException("User not found with email or phone : "+loginDto.getUsername())
		);
		
		if(!user.getPassword().equals(loginDto.getPassword())) {
			return new ApiResponse("Invalid Password", false);
		}
		
		if(Boolean.TRUE.equals(user.isStatus())) {
			return new ApiResponse("User already logged in on another device", false);
		}
		
		user.setStatus(true);
		
		userRepository.save(user);
		
		return new ApiResponse("Login successful", true);
	}

	@Override
	public void logout(String userId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with userId : "+userId));
		
		user.setStatus(false);
		userRepository.save(user);
	}
	

}
