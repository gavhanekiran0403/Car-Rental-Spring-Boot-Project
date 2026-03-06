package com.crms.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "cars")
@Data
public class Car {

	@Id
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
}
