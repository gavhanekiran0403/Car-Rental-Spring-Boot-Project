package com.crms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crms.dto.BookingDto;
import com.crms.entities.Booking;

@Component
public class BookingMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Booking dtoToEntity(BookingDto dto) {
        return modelMapper.map(dto, Booking.class);
    }

    public BookingDto entityToDto(Booking entity) {
        return modelMapper.map(entity, BookingDto.class);
    }
}