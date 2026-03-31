package com.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.dto.DriverDto;
import com.crms.entities.Driver;
import com.crms.mapper.DriverMapper;
import com.crms.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository repository;

    @Autowired
    private DriverMapper mapper;

    @Override
    public DriverDto createDriver(DriverDto dto) {
        Driver driver = mapper.dtoToEntity(dto);
        driver.setStatus("Available");
        Driver saved = repository.save(driver);
        return mapper.entityToDto(saved);
    }

    @Override
    public DriverDto getDriverById(String driverId) {
        Driver driver = repository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return mapper.entityToDto(driver);
    }

    @Override
    public List<DriverDto> getAllDrivers() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDto updateDriver(String driverId, DriverDto dto) {
        Driver existing = repository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        existing.setFullName(dto.getFullName());
        existing.setLicenseNo(dto.getLicenseNo());
        existing.setPhoneNo(dto.getPhoneNo());
        existing.setEmail(dto.getEmail());
        existing.setAddress(dto.getAddress());
        existing.setStatus(dto.getStatus());
        existing.setBankAccountId(dto.getBankAccountId());

        Driver updated = repository.save(existing);
        return mapper.entityToDto(updated);
    }

    @Override
    public void deleteDriver(String driverId) {
        repository.deleteById(driverId);
    }
}