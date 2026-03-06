package com.crms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.CarDto;
import com.crms.entities.Car;
import com.crms.entities.CarOwner;
import com.crms.exception.ResourceNotFoundException;
import com.crms.mapper.CarMapper;
import com.crms.repository.CarOwnerRepository;
import com.crms.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;
    
    @Autowired
    private CarOwnerRepository carOwnerRepository;
    
    @Override
    public CarDto createCar(CarDto carDto) {

        Car car = carMapper.dtoToEntity(carDto);

        car.setStatus("AVAILABLE");

        Car savedCar = carRepository.save(car);
        
     // fetch owner
        CarOwner carOwner = carOwnerRepository.findById(car.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        carDto.setOwnerName(carOwner.getOwnerName());

        return carMapper.entityToDto(savedCar);
    }

    @Override
    public CarDto updateCar(String carId, CarDto carDto) {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));

        car.setModelName(carDto.getModelName());
        car.setCarNo(carDto.getCarNo());
        car.setFuelType(carDto.getFuelType());
        car.setSeats(carDto.getSeats());
        car.setRent(carDto.getRent());
        car.setCarYearModel(carDto.getCarYearModel());
        car.setInsuranceNo(carDto.getInsuranceNo());
        car.setFeatures(carDto.getFeatures());
        car.setOwnerId(carDto.getOwnerId());

        Car updatedCar = carRepository.save(car);

        return carMapper.entityToDto(updatedCar);
    }

    @Override
    public void deleteCar(String carId) {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));

        carRepository.delete(car);
    }

    @Override
    public CarDto getCarById(String carId) {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));

        return carMapper.entityToDto(car);
    }

    @Override
    public Page<CarDto> getAllCars(Pageable pageable) {

        Page<Car> cars = carRepository.findAll(pageable);

        return cars.map(carMapper::entityToDto);
    }

    @Override
    public List<CarDto> searchByFuel(String fuelType) {

        List<Car> cars = carRepository.findByFuelType(fuelType);

        List<CarDto> carDtos = new ArrayList<>();

        for(Car car : cars){
            carDtos.add(carMapper.entityToDto(car));
        }

        return carDtos;
    }

    @Override
    public List<CarDto> searchBySeats(int seats) {

        List<Car> cars = carRepository.findBySeats(seats);

        List<CarDto> carDtos = new ArrayList<>();

        for(Car car : cars){
            carDtos.add(carMapper.entityToDto(car));
        }

        return carDtos;
    }
    
    @Override
    public List<CarDto> searchByStatus(String status){

        List<Car> cars = carRepository.findByStatus(status);

        return cars.stream()
                .map(carMapper::entityToDto)
                .toList();
    }

    @Override
    public void uploadImage(String carId, MultipartFile file) throws IOException {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found"));

        if(car.getImages() == null){
            car.setImages(new ArrayList<>());
        }

        car.getImages().add(file.getBytes());

        carRepository.save(car);
    }
}