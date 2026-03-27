package com.crms.service;

import java.util.List;

import com.crms.dto.NotificationDto;

public interface NotificationService {

    NotificationDto createNotification(NotificationDto dto);

    NotificationDto getNotificationById(String id);

    List<NotificationDto> getAllNotifications();

    List<NotificationDto> getNotificationsByUserId(String userId);

    NotificationDto markAsRead(String id);

    void deleteNotification(String id);
}