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
    public ApiResponse<Object> login(LoginDto loginDto) {

        User user = userRepository
                .findByEmailOrPhoneNo(loginDto.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email or phone: "
                                        + loginDto.getUsername()
                        )
                );

        // Password check
        if (!user.getPassword().equals(loginDto.getPassword())) {
            return new ApiResponse<>(
                    "Invalid password",
                    false,
                    null
            );
        }

        // USER already logged in
        if (user.getRole() == Role.USER && user.isStatus()) {
            return new ApiResponse<>(
                    "User already logged in on another device",
                    false,
                    null
            );
        }

        // Update login status only for USER
        if (user.getRole() == Role.USER) {
            user.setStatus(true);
            userRepository.save(user);
        }

        // Role based response
        switch (user.getRole()) {

            case ADMIN:
                return new ApiResponse<>(
                        "Admin login successful",
                        true,
                        user
                );

            case USER:
                return new ApiResponse<>(
                        "User login successful",
                        true,
                        user
                );

            default:
                return new ApiResponse<>(
                        "Login successful",
                        true,
                        user
                );
        }
    }

    @Override
    public ApiResponse<Object> logout(String userId) {

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

        return new ApiResponse<>(
                "Logout successful",
                true,
                null
        );
    }
}