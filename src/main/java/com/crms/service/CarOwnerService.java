package com.crms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.CarOwnerBankDto;
import com.crms.dto.CarOwnerDto;

public interface CarOwnerService {

    CarOwnerDto createCarOwner(CarOwnerDto dto, MultipartFile imageFile);

    CarOwnerDto getCarOwnerById(String ownerId);

    List<CarOwnerDto> getAllCarOwners();

    CarOwnerDto updateCarOwner(String ownerId, CarOwnerDto dto, MultipartFile imageFile);

    void deleteCarOwner(String ownerId);
    
    CarOwnerBankDto createCarOwnerBank(CarOwnerBankDto carOwnerBankDto, MultipartFile imageFile);
}