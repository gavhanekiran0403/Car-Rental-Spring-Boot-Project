package com.crms.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "notifications")
@Data
public class Notification {

	@Id
	private String notificationId;
    private String message;
    private String isRead; 
    private Date createdAt;
    private String userId;
    private String bookingId;
}
