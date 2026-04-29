package com.crms.dto;

import java.util.Date;

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
	private String aadharCard;
	private double totalAmount;
	private String status;
	private Date createdAt;
	private String userId;
	private String carId;
	private String driverId;
}
