package com.crms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crms.dto.DriverDto;
import com.crms.entities.Driver;

@Component
public class DriverMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Driver dtoToEntity(DriverDto dto) {
        return modelMapper.map(dto, Driver.class);
    }

    public DriverDto entityToDto(Driver entity) {
        return modelMapper.map(entity, DriverDto.class);
    }
}