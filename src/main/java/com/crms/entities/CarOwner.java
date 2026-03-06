package com.crms.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "car_owners")
@Data
public class CarOwner {

	@Id
	private String ownerId;
	private String ownerName;
	private String phoneNo;
	private String email;
	private String address;
	private byte[] aadharImage;
	private String bankAccId;
}
