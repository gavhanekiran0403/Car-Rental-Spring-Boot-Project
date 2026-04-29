package com.crms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.CarDto;
import com.crms.payload.ApiResponse;
import com.crms.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // CREATE CAR
    @PostMapping
    public ResponseEntity<ApiResponse<CarDto>> createCar(
            @RequestBody CarDto carDto) {

        CarDto createdCar = carService.createCar(carDto);

        ApiResponse<CarDto> response =
                new ApiResponse<>(
                        "Car created successfully",
                        true,
                        createdCar
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // UPDATE CAR
    @PutMapping("/{carId}")
    public ResponseEntity<ApiResponse<CarDto>> updateCar(
            @PathVariable String carId,
            @RequestBody CarDto carDto) {

        CarDto updatedCar =
                carService.updateCar(carId, carDto);

        ApiResponse<CarDto> response =
                new ApiResponse<>(
                        "Car updated successfully",
                        true,
                        updatedCar
                );

        return ResponseEntity.ok(response);
    }

    // DELETE CAR
    @DeleteMapping("/{carId}")
    public ResponseEntity<ApiResponse<Object>> deleteCar(
            @PathVariable String carId) {

        carService.deleteCar(carId);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        "Car deleted successfully",
                        true,
                        null
                );

        return ResponseEntity.ok(response);
    }

    // GET CAR BY ID
    @GetMapping("/{carId}")
    public ResponseEntity<ApiResponse<CarDto>> getCarById(
            @PathVariable String carId) {

        CarDto car = carService.getCarById(carId);

        ApiResponse<CarDto> response =
                new ApiResponse<>(
                        "Car fetched successfully",
                        true,
                        car
                );

        return ResponseEntity.ok(response);
    }

    // GET ALL CARS (PAGINATION)
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CarDto>>> getAllCars(
            Pageable pageable) {

        Page<CarDto> cars = carService.getAllCars(pageable);

        ApiResponse<Page<CarDto>> response =
                new ApiResponse<>(
                        "Cars fetched successfully",
                        true,
                        cars
                );

        return ResponseEntity.ok(response);
    }

    // SEARCH BY FUEL TYPE
    @GetMapping("/fuel/{fuelType}")
    public ResponseEntity<ApiResponse<List<CarDto>>> searchByFuel(
            @PathVariable String fuelType) {

        List<CarDto> cars =
                carService.searchByFuel(fuelType);

        ApiResponse<List<CarDto>> response =
                new ApiResponse<>(
                        "Cars fetched by fuel type",
                        true,
                        cars
                );

        return ResponseEntity.ok(response);
    }

    // SEARCH BY SEATS
    @GetMapping("/seats/{seats}")
    public ResponseEntity<ApiResponse<List<CarDto>>> searchBySeats(
            @PathVariable int seats) {

        List<CarDto> cars =
                carService.searchBySeats(seats);

        ApiResponse<List<CarDto>> response =
                new ApiResponse<>(
                        "Cars fetched by seats",
                        true,
                        cars
                );

        return ResponseEntity.ok(response);
    }

    // SEARCH BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<CarDto>>> searchByStatus(
            @PathVariable String status) {

        List<CarDto> cars =
                carService.searchByStatus(status);

        ApiResponse<List<CarDto>> response =
                new ApiResponse<>(
                        "Cars fetched by status",
                        true,
                        cars
                );

        return ResponseEntity.ok(response);
    }

    // UPLOAD CAR IMAGES
    @PostMapping("/upload/{carId}")
    public ResponseEntity<ApiResponse<Object>> uploadImage(
            @PathVariable String carId,
            @RequestParam("imageFiles") List<MultipartFile> imageFiles)
            throws IOException {

        carService.uploadImage(carId, imageFiles);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        "Images uploaded successfully",
                        true,
                        null
                );

        return ResponseEntity.ok(response);
    }

    // UPDATE CAR IMAGES
    @PutMapping(
            value = "/update-images/{carId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<Object>> updateCarImages(
            @PathVariable String carId,
            @RequestParam("imageFiles") List<MultipartFile> imageFiles) {

        carService.updateCarImages(carId, imageFiles);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        "Car images updated successfully",
                        true,
                        null
                );

        return ResponseEntity.ok(response);
    }
}