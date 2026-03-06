package com.crms.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "drivers")
@Data
public class Driver {

	@Id
	private String driverId;
	private String fullName;
	private String licenseNo;
	private String phoneNo;
	private String email;
	private String address;
	private String status;
	private String bankAccId;
}
