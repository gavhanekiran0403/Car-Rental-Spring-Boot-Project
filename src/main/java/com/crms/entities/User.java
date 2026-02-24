package com.crms.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.crms.enums.Role;

import lombok.Data;

@Document(collection = "users")
@Data
public class User {

	@Id
	private String userId;
    private String fullName;
    private String phoneNo;
    private String email;
    private String password;
    private Role role;
    private boolean status;
}
