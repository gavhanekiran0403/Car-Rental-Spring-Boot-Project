package com.crms.service;

import java.util.List;

import com.crms.dto.CarOwnerDto;

public interface CarOwnerService {

    CarOwnerDto createCarOwner(CarOwnerDto carOwnerDto);

    CarOwnerDto getCarOwnerById(String ownerId);

    List<CarOwnerDto> getAllCarOwners();

    CarOwnerDto updateCarOwner(String ownerId, CarOwnerDto carOwnerDto);

    void deleteCarOwner(String ownerId);
}