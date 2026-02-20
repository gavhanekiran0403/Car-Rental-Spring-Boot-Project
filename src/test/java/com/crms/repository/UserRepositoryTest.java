package com.crms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;

import com.crms.entities.User;
import com.crms.enums.Role;

@DataMongoTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void testSaveUser() {

		User user = new User();
		user.setFullName("Kiran Gavhane");
		user.setPhoneNo("9049226199");
		user.setEmail("gavhanekiran0403@gmail.com");
		user.setPassword("kiran@123");
		user.setRole(Role.USER);
		user.setStatus(false);
		
		User savedUser = userRepository.save(user);
		
		assertNotNull(savedUser);
		assertNotNull(savedUser.getUserId());
		assertEquals("Kiran Gavhane", savedUser.getFullName());
	}
}
