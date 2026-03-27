package com.crms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crms.dto.NotificationDto;
import com.crms.entities.Notification;

@Component
public class NotificationMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Notification dtoToEntity(NotificationDto dto) {
        return modelMapper.map(dto, Notification.class);
    }

    public NotificationDto entityToDto(Notification entity) {
        return modelMapper.map(entity, NotificationDto.class);
    }
}