package com.crms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crms.dto.UserDto;
import com.crms.entities.User;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public User dtoToEntity(UserDto userDto){
		return modelMapper.map(userDto, User.class);
	}
	
	public UserDto entityToDto(User user){
		return modelMapper.map(user, UserDto.class);
	}
}
