package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.DriverBankDto;
import com.crms.dto.DriverDto;
import com.crms.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService service;

    @PostMapping
    public DriverDto createDriver(@RequestBody DriverDto dto) {
        return service.createDriver(dto);
    }

    @GetMapping("/{id}")
    public DriverDto getDriver(@PathVariable String id) {
        return service.getDriverById(id);
    }

    @GetMapping
    public List<DriverDto> getAllDrivers() {
        return service.getAllDrivers();
    }

    @PutMapping("/{id}")
    public DriverDto updateDriver(@PathVariable String id,
                                  @RequestBody DriverDto dto) {
        return service.updateDriver(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteDriver(@PathVariable String id) {
        service.deleteDriver(id);
        return "Driver deleted successfully";
    }
    
    @PostMapping("/driver-with-bank")
    public DriverBankDto createDriver(@RequestBody DriverBankDto driverBankDto) {
        return service.createDriverWithBankAccount(driverBankDto);
    }
}