package com.crms.entities;

import com.crms.enums.Role;

import lombok.Data;

@Data
public class User {

	private int userId;
    private String fullName;
    private String phoneNo;
    private String email;
    private String password;
    private Role role;
    private String status;
}
