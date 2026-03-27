package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.CarOwnerDto;
import com.crms.service.CarOwnerService;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {

    @Autowired
    private CarOwnerService service;

    @PostMapping
    public CarOwnerDto createOwner(@RequestBody CarOwnerDto dto) {
        return service.createCarOwner(dto);
    }

    @GetMapping("/{id}")
    public CarOwnerDto getOwner(@PathVariable String id) {
        return service.getCarOwnerById(id);
    }

    @GetMapping
    public List<CarOwnerDto> getAllOwners() {
        return service.getAllCarOwners();
    }

    @PutMapping("/{id}")
    public CarOwnerDto updateOwner(@PathVariable String id,
                                   @RequestBody CarOwnerDto dto) {
        return service.updateCarOwner(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteOwner(@PathVariable String id) {
        service.deleteCarOwner(id);
        return "Deleted successfully";
    }
}