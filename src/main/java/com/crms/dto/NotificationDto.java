package com.crms.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class NotificationDto {

	private String notificationId;
    private String message;
    private String isRead; 
    private Timestamp createdAt;
    private String userId;
    private String bookingId;
}
