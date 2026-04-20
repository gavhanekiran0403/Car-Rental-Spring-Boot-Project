package com.crms.dto;

import lombok.Data;

@Data
public class DriverBankDto {

	private String driverId;
	private String fullName;
	private String licenseNo;
	private String phoneNo;
	private String email;
	private String address;
	private String status;
	
	private String bankAccountId;
	private String accountHolderName;
	private String bankName;
	private String accountNo;
	private String ifscNo;
}
