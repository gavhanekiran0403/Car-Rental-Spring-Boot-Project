package com.crms.service;

import com.crms.dto.LoginDto;
import com.crms.payload.ApiResponse;

public interface AuthService {

	public ApiResponse<Object> login(LoginDto loginDto);
	
	public ApiResponse<Object> logout(String userId);
}
