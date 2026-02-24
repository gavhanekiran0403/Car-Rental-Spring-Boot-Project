package com.crms.service;

import com.crms.dto.LoginDto;
import com.crms.payload.ApiResponse;

public interface AuthService {

	public ApiResponse login(LoginDto loginDto);
	
	public void logout(String userId);
}
