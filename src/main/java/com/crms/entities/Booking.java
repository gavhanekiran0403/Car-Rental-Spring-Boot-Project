package com.crms.entities;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "bookings")
@Data
public class Booking {

	@Id
	private String bookingId;
	private String pickupLocation;
	private String dropLocation;
	private String pickupDate;
	private String pickupTime;
	private String returnDate;
	private String returnTime;
	private String drivingOption;
	private byte[] aadharCard;
	private double totalAmount;
	private String status;
	private Timestamp createdAt;
	private String userId;
	private String carId;
	private String driverId;
	
}
