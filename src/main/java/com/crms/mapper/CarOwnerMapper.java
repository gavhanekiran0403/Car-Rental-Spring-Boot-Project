package com.crms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crms.dto.CarOwnerDto;
import com.crms.entities.CarOwner;

@Component
public class CarOwnerMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CarOwner dtoToEntity(CarOwnerDto dto) {
        return modelMapper.map(dto, CarOwner.class);
    }

    public CarOwnerDto entityToDto(CarOwner entity) {
        return modelMapper.map(entity, CarOwnerDto.class);
    }
}