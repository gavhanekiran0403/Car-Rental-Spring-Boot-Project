package com.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.dto.UserDto;
import com.crms.entities.User;
import com.crms.enums.Role;
import com.crms.exception.ResourceNotFoundException;
import com.crms.mapper.UserMapper;
import com.crms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = userMapper.dtoToEntity(userDto);
		user.setRole(Role.USER);
		user.setStatus(false);
		User savedUser = userRepository.save(user);
		return userMapper.entityToDto(savedUser);
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with userId : "+userId));
		
		user.setFullName(userDto.getFullName());
		user.setPhoneNo(userDto.getPhoneNo());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());

		User updatedUser = userRepository.save(user);
		return userMapper.entityToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with userId : "+userId));
		return userMapper.entityToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		return userRepository.findAll()
				.stream()
				.map(userMapper::entityToDto)
				.collect(Collectors.toList());
	}

}
