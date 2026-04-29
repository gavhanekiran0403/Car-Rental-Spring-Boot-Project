package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.NotificationDto;
import com.crms.payload.ApiResponse;
import com.crms.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    // CREATE NOTIFICATION
    @PostMapping
    public ResponseEntity<ApiResponse<NotificationDto>> create(
            @RequestBody NotificationDto dto) {

        NotificationDto notification =
                service.createNotification(dto);

        ApiResponse<NotificationDto> response =
                new ApiResponse<>(
                        "Notification created successfully",
                        true,
                        notification
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET NOTIFICATION BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotificationDto>> getById(
            @PathVariable String id) {

        NotificationDto notification =
                service.getNotificationById(id);

        ApiResponse<NotificationDto> response =
                new ApiResponse<>(
                        "Notification fetched successfully",
                        true,
                        notification
                );

        return ResponseEntity.ok(response);
    }

    // GET ALL NOTIFICATIONS
    @GetMapping
    public ResponseEntity<ApiResponse<List<NotificationDto>>> getAll() {

        List<NotificationDto> notifications =
                service.getAllNotifications();

        ApiResponse<List<NotificationDto>> response =
                new ApiResponse<>(
                        "Notifications fetched successfully",
                        true,
                        notifications
                );

        return ResponseEntity.ok(response);
    }

    // GET NOTIFICATIONS BY USER ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<NotificationDto>>> getByUser(
            @PathVariable String userId) {

        List<NotificationDto> notifications =
                service.getNotificationsByUserId(userId);

        ApiResponse<List<NotificationDto>> response =
                new ApiResponse<>(
                        "User notifications fetched successfully",
                        true,
                        notifications
                );

        return ResponseEntity.ok(response);
    }

    // MARK AS READ
    @PutMapping("/read/{id}")
    public ResponseEntity<ApiResponse<NotificationDto>> markAsRead(
            @PathVariable String id) {

        NotificationDto notification =
                service.markAsRead(id);

        ApiResponse<NotificationDto> response =
                new ApiResponse<>(
                        "Notification marked as read",
                        true,
                        notification
                );

        return ResponseEntity.ok(response);
    }

    // DELETE NOTIFICATION
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(
            @PathVariable String id) {

        service.deleteNotification(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        "Notification deleted successfully",
                        true,
                        null
                );

        return ResponseEntity.ok(response);
    }
}