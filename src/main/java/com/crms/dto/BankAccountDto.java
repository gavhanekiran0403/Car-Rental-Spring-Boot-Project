package com.crms.dto;

import lombok.Data;

@Data
public class BankAccountDto {

	private String bankAccId;
	private String accHolderName;
	private String bankName;
	private String accountNo;
	private String ifscNo;
}
