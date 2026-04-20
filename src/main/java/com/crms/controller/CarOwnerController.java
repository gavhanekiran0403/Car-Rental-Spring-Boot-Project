package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.CarOwnerBankDto;
import com.crms.dto.CarOwnerDto;
import com.crms.service.CarOwnerService;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {

    @Autowired
    private CarOwnerService service;

    @PostMapping(consumes = "multipart/form-data")
    public CarOwnerDto createOwner(
            @RequestPart("carOwner") CarOwnerDto carOwnerDto,
            @RequestPart("image") MultipartFile imageFile) {

        return service.createCarOwner(carOwnerDto, imageFile);
    }

    @GetMapping("/{id}")
    public CarOwnerDto getOwner(@PathVariable String id) {
        return service.getCarOwnerById(id);
    }

    @GetMapping
    public List<CarOwnerDto> getAllOwners() {
        return service.getAllCarOwners();
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public CarOwnerDto updateOwner(
            @PathVariable String id,
            @RequestPart("carOwner") CarOwnerDto carOwnerDto,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        return service.updateCarOwner(id, carOwnerDto, imageFile);
    }

    @DeleteMapping("/{id}")
    public String deleteOwner(@PathVariable String id) {
        service.deleteCarOwner(id);
        return "Deleted successfully";
    }
    
    @PostMapping(value = "/car-owner-with-bank", consumes = "multipart/form-data")
    public CarOwnerBankDto createOwnerWithBankAccount(
            @RequestPart("carOwner") CarOwnerBankDto carOwnerBankDto,
            @RequestPart("image") MultipartFile imageFile) {

        return service.createCarOwnerBank(carOwnerBankDto, imageFile);
    }
}