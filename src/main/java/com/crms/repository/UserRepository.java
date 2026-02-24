package com.crms.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.crms.entities.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	@Query("{ $or: [ { 'email' : ?0 }, { 'phoneNo' : ?0 } ] }")
	Optional<User> findByEmailOrPhoneNo(String username);
}
