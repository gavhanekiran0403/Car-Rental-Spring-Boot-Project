package com.crms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.CarOwner;

public interface CarOwnerRepository extends MongoRepository<CarOwner, String> {

}
