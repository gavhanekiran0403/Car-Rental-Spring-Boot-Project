package com.crms.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationDto {

	private String notificationId;
    private String message;
    private String isRead; 
    private Date createdAt;
    private String userId;
    private String bookingId;
}
