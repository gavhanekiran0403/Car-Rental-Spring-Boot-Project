package com.crms.dto;

import lombok.Data;

@Data
public class CarOwnerDto {

	private String ownerId;
	private String ownerName;
	private String phoneNo;
	private String email;
	private String address;
	private byte[] aadharImage;
	private String bankAccId;
}
