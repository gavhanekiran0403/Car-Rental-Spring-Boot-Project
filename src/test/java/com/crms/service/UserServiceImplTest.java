package com.crms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crms.dto.UserDto;
import com.crms.entities.User;
import com.crms.enums.Role;
import com.crms.exception.ResourceNotFoundException;
import com.crms.mapper.UserMapper;
import com.crms.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;
	
    @BeforeEach
    void setup() {
    	
    	user = new User();
        user.setUserId("1");
        user.setFullName("Kiran Gavhane");
        user.setPhoneNo("9049226199");
        user.setEmail("gavhanekiran0403@gmail.com");
        user.setPassword("kiran@123");
        user.setRole(Role.USER);
        user.setStatus(false);

        userDto = new UserDto();
        userDto.setUserId("1");
        userDto.setFullName("Kiran Gavhane");
        userDto.setPhoneNo("9049226199");
        userDto.setEmail("gavhanekiran0403@gmail.com");
        userDto.setPassword("kiran@123");
        userDto.setRole(Role.USER);
        userDto.setStatus(false);
    }
    
    @Test
    void testRegisterUser() {
    	
    	when(userMapper.dtoToEntity(userDto)).thenReturn(user);
    	when(userRepository.save(any(User.class))).thenReturn(user);
    	when(userMapper.entityToDto(user)).thenReturn(userDto);
    	
    	UserDto savedUserDto = userService.registerUser(userDto);
    	
    	assertNotNull(savedUserDto);
    	assertEquals("Kiran Gavhane", savedUserDto.getFullName());
    	
    	verify(userRepository).save(any(User.class));
    }
    
    @Test
    void testUpdateUser() {
    	
    	when(userRepository.findById("1")).thenReturn(Optional.of(user));
    	when(userRepository.save(any(User.class))).thenReturn(user);
    	when(userMapper.entityToDto(user)).thenReturn(userDto);
    	
    	UserDto updatedUserDto = userService.updateUser("1", userDto);
    	
    	assertNotNull(updatedUserDto);
    	assertEquals("Kiran Gavhane", updatedUserDto.getFullName());
    	
    	verify(userRepository).save(user);
    }
    
    @Test
    void testGetUserById() {
    	
    	when(userRepository.findById("1")).thenReturn(Optional.of(user));
    	when(userMapper.entityToDto(user)).thenReturn(userDto);
    	
    	UserDto result = userService.getUserById("1");
    	
    	assertNotNull(result);
    	assertEquals("Kiran Gavhane", result.getFullName());
    }
    
    @Test
    void testGetUserById_NotFound() {
    	when(userRepository.findById("1")).thenReturn(Optional.empty());
    	
    	assertThrows(ResourceNotFoundException.class, () -> userService.getUserById("1"));
    }
    
    @Test
    void testGetAllUsers() {
    	when(userRepository.findAll()).thenReturn(Arrays.asList(user));
    	when(userMapper.entityToDto(user)).thenReturn(userDto);
    	
    	List<UserDto> users = userService.getAllUsers();
    	
    	assertEquals(1, users.size());
    	assertEquals("Kiran Gavhane", users.get(0).getFullName());
    }
}
