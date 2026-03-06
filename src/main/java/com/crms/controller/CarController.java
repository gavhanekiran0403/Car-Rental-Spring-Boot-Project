package com.crms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.CarDto;
import com.crms.service.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public CarDto createCar(@RequestBody CarDto carDto){
        return carService.createCar(carDto);
    }

    @PutMapping("/{carId}")
    public CarDto updateCar(@PathVariable String carId,
                            @RequestBody CarDto carDto){

        return carService.updateCar(carId, carDto);
    }

    @DeleteMapping("/{carId}")
    public String deleteCar(@PathVariable String carId){

        carService.deleteCar(carId);

        return "Car deleted successfully";
    }

    @GetMapping("/{carId}")
    public CarDto getCarById(@PathVariable String carId){

        return carService.getCarById(carId);
    }

    @GetMapping
    public Page<CarDto> getAllCars(Pageable pageable){

        return carService.getAllCars(pageable);
    }

    @GetMapping("/fuel/{fuelType}")
    public List<CarDto> searchByFuel(@PathVariable String fuelType){

        return carService.searchByFuel(fuelType);
    }

    @GetMapping("/seats/{seats}")
    public List<CarDto> searchBySeats(@PathVariable int seats){

        return carService.searchBySeats(seats);
    }
    
    @GetMapping("/status/{status}")
    public List<CarDto> searchByStatus(@PathVariable String status){

        return carService.searchByStatus(status);
    }

    @PostMapping("/{carId}/upload")
    public String uploadImage(@PathVariable String carId,
                              @RequestParam("file") MultipartFile file)
                              throws IOException{

        carService.uploadImage(carId, file);

        return "Image uploaded successfully";
    }
}