package com.crms.dto;

import com.crms.enums.Role;

import lombok.Data;

@Data
public class RegisterRequest {

    private String fullName;

    private String phoneNo;

    private String email;

    private String password;

    private Role role;
}