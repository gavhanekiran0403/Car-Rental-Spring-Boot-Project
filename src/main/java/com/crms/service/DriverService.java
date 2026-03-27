package com.crms.service;

import java.util.List;

import com.crms.dto.DriverDto;

public interface DriverService {

    DriverDto createDriver(DriverDto driverDto);

    DriverDto getDriverById(String driverId);

    List<DriverDto> getAllDrivers();

    DriverDto updateDriver(String driverId, DriverDto driverDto);

    void deleteDriver(String driverId);
}