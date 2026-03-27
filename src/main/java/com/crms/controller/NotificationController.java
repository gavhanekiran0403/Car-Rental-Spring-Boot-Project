package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.NotificationDto;
import com.crms.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping
    public NotificationDto create(@RequestBody NotificationDto dto) {
        return service.createNotification(dto);
    }

    @GetMapping("/{id}")
    public NotificationDto getById(@PathVariable String id) {
        return service.getNotificationById(id);
    }

    @GetMapping
    public List<NotificationDto> getAll() {
        return service.getAllNotifications();
    }

    @GetMapping("/user/{userId}")
    public List<NotificationDto> getByUser(@PathVariable String userId) {
        return service.getNotificationsByUserId(userId);
    }

    @PutMapping("/read/{id}")
    public NotificationDto markAsRead(@PathVariable String id) {
        return service.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.deleteNotification(id);
        return "Notification deleted successfully";
    }
}