package com.crms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.Booking;

public interface BookingRepository extends MongoRepository<Booking, String>{

	List<Booking> findByUserId(String userId);
}
