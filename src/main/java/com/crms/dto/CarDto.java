package com.crms.dto;

import java.util.List;

import lombok.Data;

@Data
public class CarDto {

	private String carId;
	private String modelName;
	private String carNo;
	private String fuelType;
	private int seats;
	private double rent;
	private int carYearModel;
	private String insuranceNo;
	private String features;
	private String status;
	private List<byte[]> images;
	private String ownerId;
	private String ownerName;
}
