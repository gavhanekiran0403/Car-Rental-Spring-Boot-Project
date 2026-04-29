package com.crms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.CarDto;

public interface CarService {

	CarDto createCar(CarDto carDto);

	CarDto updateCar(String carId, CarDto carDto);

	void deleteCar(String carId);

	CarDto getCarById(String carId);

	Page<CarDto> getAllCars(Pageable pageable);

	List<CarDto> searchByFuel(String fuelType);

	List<CarDto> searchBySeats(int seats);
	
	List<CarDto> searchByStatus(String status);

	void uploadImage(String carId, List<MultipartFile> imageFiles) throws IOException;
	
	void updateCarImages(String carId, List<MultipartFile> imageFiles);
}
