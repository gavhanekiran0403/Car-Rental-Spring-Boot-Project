package com.crms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.Car;

public interface CarRepository extends MongoRepository<Car, String> {

	List<Car> findByFuelType(String fuelType);
	
	List<Car> findBySeats(int seats);
	
	List<Car> findByStatus(String status);
}
