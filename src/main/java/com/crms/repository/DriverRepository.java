package com.crms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.Driver;

public interface DriverRepository extends MongoRepository<Driver, String> {

}
