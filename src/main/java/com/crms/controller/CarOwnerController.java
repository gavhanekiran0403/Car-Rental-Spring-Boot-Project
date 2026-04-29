package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.CarOwnerBankDto;
import com.crms.dto.CarOwnerDto;
import com.crms.payload.ApiResponse;
import com.crms.service.CarOwnerService;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {

    @Autowired
    private CarOwnerService service;

    // CREATE CAR OWNER
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<CarOwnerDto>> createOwner(
            @RequestPart("carOwner") CarOwnerDto carOwnerDto,
            @RequestPart("image") MultipartFile imageFile) {

        CarOwnerDto owner =
                service.createCarOwner(carOwnerDto, imageFile);

        ApiResponse<CarOwnerDto> response =
                new ApiResponse<>(
                        "Car owner created successfully",
                        true,
                        owner
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // ===============================
    // GET OWNER BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CarOwnerDto>> getOwner(
            @PathVariable String id) {

        CarOwnerDto owner =
                service.getCarOwnerById(id);

        ApiResponse<CarOwnerDto> response =
                new ApiResponse<>(
                        "Car owner fetched successfully",
                        true,
                        owner
                );

        return ResponseEntity.ok(response);
    }

    // GET ALL OWNERS
    @GetMapping
    public ResponseEntity<ApiResponse<List<CarOwnerDto>>> getAllOwners() {

        List<CarOwnerDto> owners =
                service.getAllCarOwners();

        ApiResponse<List<CarOwnerDto>> response =
                new ApiResponse<>(
                        "Car owners fetched successfully",
                        true,
                        owners
                );

        return ResponseEntity.ok(response);
    }

    // UPDATE OWNER
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<CarOwnerDto>> updateOwner(
            @PathVariable String id,
            @RequestPart("carOwner") CarOwnerDto carOwnerDto,
            @RequestPart(value = "image", required = false)
            MultipartFile imageFile) {

        CarOwnerDto updatedOwner =
                service.updateCarOwner(id, carOwnerDto, imageFile);

        ApiResponse<CarOwnerDto> response =
                new ApiResponse<>(
                        "Car owner updated successfully",
                        true,
                        updatedOwner
                );

        return ResponseEntity.ok(response);
    }

    // DELETE OWNER
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteOwner(
            @PathVariable String id) {

        service.deleteCarOwner(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        "Car owner deleted successfully",
                        true,
                        null
                );

        return ResponseEntity.ok(response);
    }

    // CREATE OWNER WITH BANK ACCOUNT
    @PostMapping(
            value = "/car-owner-with-bank",
            consumes = "multipart/form-data"
    )
    public ResponseEntity<ApiResponse<CarOwnerBankDto>> createOwnerWithBankAccount(
            @RequestPart("carOwner") CarOwnerBankDto carOwnerBankDto,
            @RequestPart("image") MultipartFile imageFile) {

        CarOwnerBankDto owner =
                service.createCarOwnerBank(
                        carOwnerBankDto,
                        imageFile
                );

        ApiResponse<CarOwnerBankDto> response =
                new ApiResponse<>(
                        "Car owner with bank account created successfully",
                        true,
                        owner
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}