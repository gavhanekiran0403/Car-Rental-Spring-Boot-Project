package com.crms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crms.dto.CarDto;
import com.crms.entities.Car;

@Component
public class CarMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Car dtoToEntity(CarDto carDto){
		return modelMapper.map(carDto, Car.class);
	}
	
	public CarDto entityToDto(Car car){
		return modelMapper.map(car, CarDto.class);
	}
}
