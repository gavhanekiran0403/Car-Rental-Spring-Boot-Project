package com.crms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.Booking;

public interface BookingRepository extends MongoRepository<Booking, String>{

}
