package com.crms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.User;

public interface UserRepository extends MongoRepository<User, String>{

}
