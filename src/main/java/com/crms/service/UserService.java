package com.crms.service;

import java.util.List;

import com.crms.dto.UserDto;

public interface UserService {

	public UserDto registerUser(UserDto userDto);
	public UserDto updateUser(String userId, UserDto userDto);
	public UserDto getUserById(String userId);
	public List<UserDto> getAllUsers();
}
