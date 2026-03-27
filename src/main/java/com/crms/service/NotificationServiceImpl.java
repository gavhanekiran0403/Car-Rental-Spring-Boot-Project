package com.crms.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.dto.NotificationDto;
import com.crms.entities.Notification;
import com.crms.mapper.NotificationMapper;
import com.crms.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private NotificationMapper mapper;

    @Override
    public NotificationDto createNotification(NotificationDto dto) {

        Notification notification = mapper.dtoToEntity(dto);

        notification.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        if (notification.getIsRead() == null) {
            notification.setIsRead("false");
        }

        Notification saved = repository.save(notification);
        return mapper.entityToDto(saved);
    }

    @Override
    public NotificationDto getNotificationById(String id) {
        Notification notification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        return mapper.entityToDto(notification);
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> getNotificationsByUserId(String userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto markAsRead(String id) {

        Notification notification = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setIsRead("true");

        Notification updated = repository.save(notification);
        return mapper.entityToDto(updated);
    }

    @Override
    public void deleteNotification(String id) {
        repository.deleteById(id);
    }
}