package com.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.dto.CarOwnerDto;
import com.crms.entities.CarOwner;
import com.crms.mapper.CarOwnerMapper;
import com.crms.repository.CarOwnerRepository;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {

    @Autowired
    private CarOwnerRepository repository;

    @Autowired
    private CarOwnerMapper mapper;

    @Override
    public CarOwnerDto createCarOwner(CarOwnerDto dto) {
        CarOwner entity = mapper.dtoToEntity(dto);
        CarOwner saved = repository.save(entity);
        return mapper.entityToDto(saved);
    }

    @Override
    public CarOwnerDto getCarOwnerById(String ownerId) {
        CarOwner owner = repository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Car Owner not found"));
        return mapper.entityToDto(owner);
    }

    @Override
    public List<CarOwnerDto> getAllCarOwners() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarOwnerDto updateCarOwner(String ownerId, CarOwnerDto dto) {
        CarOwner existing = repository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Car Owner not found"));

        existing.setOwnerName(dto.getOwnerName());
        existing.setPhoneNo(dto.getPhoneNo());
        existing.setEmail(dto.getEmail());
        existing.setAddress(dto.getAddress());
        existing.setAadharImage(dto.getAadharImage());
        existing.setBankAccId(dto.getBankAccId());

        CarOwner updated = repository.save(existing);
        return mapper.entityToDto(updated);
    }

    @Override
    public void deleteCarOwner(String ownerId) {
        repository.deleteById(ownerId);
    }
}