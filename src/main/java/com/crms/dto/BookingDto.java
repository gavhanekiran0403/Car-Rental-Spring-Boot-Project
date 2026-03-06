package com.crms.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BookingDto {

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
