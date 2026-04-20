package com.crms.dto;

import lombok.Data;

@Data
public class CarOwnerBankDto {

	private String ownerId;
	private String ownerName;
	private String phoneNo;
	private String email;
	private String address;
	private String aadharImage;
	
	private String bankAccountId;
	private String accountHolderName;
	private String bankName;
	private String accountNo;
	private String ifscNo;
}
