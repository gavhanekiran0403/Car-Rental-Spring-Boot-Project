package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.DriverBankDto;
import com.crms.dto.DriverDto;
import com.crms.payload.ApiResponse;
import com.crms.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService service;

    // CREATE DRIVER
    @PostMapping
    public ResponseEntity<ApiResponse<DriverDto>> createDriver(
            @RequestBody DriverDto dto) {

        DriverDto createdDriver = service.createDriver(dto);

        ApiResponse<DriverDto> response =
                new ApiResponse<>(
                        "Driver created successfully",
                        true,
                        createdDriver
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET DRIVER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDto>> getDriver(
            @PathVariable String id) {

        DriverDto driver = service.getDriverById(id);

        ApiResponse<DriverDto> response =
                new ApiResponse<>(
                        "Driver fetched successfully",
                        true,
                        driver
                );

        return ResponseEntity.ok(response);
    }

    // GET ALL DRIVERS
    @GetMapping
    public ResponseEntity<ApiResponse<List<DriverDto>>> getAllDrivers() {

        List<DriverDto> drivers = service.getAllDrivers();

        ApiResponse<List<DriverDto>> response =
                new ApiResponse<>(
                        "Drivers fetched successfully",
                        true,
                        drivers
                );

        return ResponseEntity.ok(response);
    }

    // UPDATE DRIVER
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DriverDto>> updateDriver(
            @PathVariable String id,
            @RequestBody DriverDto dto) {

        DriverDto updatedDriver = service.updateDriver(id, dto);

        ApiResponse<DriverDto> response =
                new ApiResponse<>(
                        "Driver updated successfully",
                        true,
                        updatedDriver
                );

        return ResponseEntity.ok(response);
    }

    // DELETE DRIVER
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteDriver(
            @PathVariable String id) {

        service.deleteDriver(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        "Driver deleted successfully",
                        true,
                        null
                );

        return ResponseEntity.ok(response);
    }

    // CREATE DRIVER WITH BANK
    @PostMapping("/driver-with-bank")
    public ResponseEntity<ApiResponse<DriverBankDto>> createDriverWithBank(
            @RequestBody DriverBankDto driverBankDto) {

        DriverBankDto savedData =
                service.createDriverWithBankAccount(driverBankDto);

        ApiResponse<DriverBankDto> response =
                new ApiResponse<>(
                        "Driver with bank account created successfully",
                        true,
                        savedData
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}