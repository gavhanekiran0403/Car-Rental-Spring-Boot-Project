package com.crms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;

import com.crms.entities.User;
import com.crms.enums.Role;

@DataMongoTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }
	
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
	
	@Test
	void testFindUserById() {
		
		User user = new User();
		user.setFullName("Kiran Gavhane");
		user.setPhoneNo("9049226199");
		user.setEmail("gavhanekiran0403@gmail.com");
		user.setPassword("kiran@123");
		user.setRole(Role.USER);
		user.setStatus(false);
		
		User savedUser = userRepository.save(user);
		
		Optional<User> foundUser = userRepository.findById(savedUser.getUserId());
		
		assertTrue(foundUser.isPresent());
		assertEquals("Kiran Gavhane", foundUser.get().getFullName());
	}
	
	 @Test
	 void testDeleteUser() {
		
		User user = new User();
		user.setFullName("Kiran Gavhane");
		user.setPhoneNo("9049226199");
		user.setEmail("gavhanekiran0403@gmail.com");
		user.setPassword("kiran@123");
		user.setRole(Role.USER);
		user.setStatus(false);
			
		User savedUser = userRepository.save(user);
		
		userRepository.deleteById(user.getUserId());
		
		Optional<User> foundUser = userRepository.findById(savedUser.getUserId());
		
		assertFalse(foundUser.isPresent());
	 }
	 
	 @Test
	 void testFindAllUsers() {
		
		User user1 = new User();
		user1.setFullName("Kiran Gavhane");
		user1.setPhoneNo("9049226199");
		user1.setEmail("gavhanekiran0403@gmail.com");
		user1.setPassword("kiran@123");
		user1.setRole(Role.USER);
		user1.setStatus(false);
		
		User user2 = new User();
		user2.setFullName("Shravani Gavhane");
		user2.setPhoneNo("9049226199");
		user2.setEmail("gavhaneshravani0403@gmail.com");
		user2.setPassword("shravani@123");
		user2.setRole(Role.USER);
		user2.setStatus(false);
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		long count = userRepository.count();
		
		assertEquals(2, count);
	 }
}
