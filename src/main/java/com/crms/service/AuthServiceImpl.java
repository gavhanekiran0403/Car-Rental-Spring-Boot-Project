package com.crms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.dto.LoginDto;
import com.crms.entities.User;
import com.crms.enums.Role;
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
                    new ResourceNotFoundException(
                        "User not found with email or phone: " + loginDto.getUsername()
                    )
                );
        
        if (!user.getPassword().equals(loginDto.getPassword())) {
            return new ApiResponse("Invalid password", false);
        }
        
        if (user.getRole() == Role.USER && user.isStatus()) {
            return new ApiResponse("User already logged in on another device", false);
        }
        
        if (user.getRole() == Role.USER) {
            user.setStatus(true);
            userRepository.save(user);
        }
        
        // role-based response
        if (user.getRole() == Role.ADMIN) {
            return new ApiResponse("Admin login successful", true);
        }
        
        if (user.getRole() == Role.USER) {
            return new ApiResponse("User login successful", true);
        }
        
        return new ApiResponse("Login successful", true);
    }

    
    @Override
    public void logout(String userId) {
        
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User not found with userId: " + userId
                    )
                );
        
        if (user.getRole() == Role.USER) {
            user.setStatus(false);
            userRepository.save(user);
        }
    }

}