package com.crms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.
UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.crms.dto.LoginDto;

import com.crms.entities.User;

import com.crms.enums.Role;

import com.crms.exception.ResourceNotFoundException;

import com.crms.payload.ApiResponse;

import com.crms.repository.UserRepository;

import com.crms.security.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<Object> login(
            LoginDto loginDto
    ) {

        User user = userRepository

                .findByEmailOrPhoneNo(
                        loginDto.getUsername()
                )

                .orElseThrow(() ->

                        new ResourceNotFoundException(

                                "User not found with email or phone: "

                                        + loginDto.getUsername()
                        )
                );

        try {

            Authentication authentication =

                    authenticationManager.authenticate(

                            new UsernamePasswordAuthenticationToken(

                                    loginDto.getUsername(),

                                    loginDto.getPassword()
                            )
                    );

            UserDetails userDetails =

                    (UserDetails) authentication.getPrincipal();

            String token =

                    jwtService.generateToken(userDetails);

            // Single device login for USER

            if (user.getRole() == Role.USER
                    && user.isStatus()) {

                return new ApiResponse<>(

                        "User already logged in on another device",

                        false,

                        null
                );
            }

            // Update login status

            if (user.getRole() == Role.USER) {

                user.setStatus(true);

                userRepository.save(user);
            }

            Map<String, Object> response =
                    new HashMap<>();

            response.put("token", token);

            response.put("userId", user.getUserId());

            response.put("fullName", user.getFullName());

            response.put("email", user.getEmail());

            response.put("phoneNo", user.getPhoneNo());

            response.put("role", user.getRole());

            switch (user.getRole()) {

                case ADMIN:

                    return new ApiResponse<>(

                            "Admin login successful",

                            true,

                            response
                    );

                case USER:

                    return new ApiResponse<>(

                            "User login successful",

                            true,

                            response
                    );

                default:

                    return new ApiResponse<>(

                            "Login successful",

                            true,

                            response
                    );
            }

        } catch (BadCredentialsException e) {

            return new ApiResponse<>(

                    "Invalid username or password",

                    false,

                    null
            );
        }
    }

    @Override
    public ApiResponse<Object> logout(
            String userId
    ) {

        User user = userRepository.findById(userId)

                .orElseThrow(() ->

                        new ResourceNotFoundException(

                                "User not found with userId: "
                                        + userId
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