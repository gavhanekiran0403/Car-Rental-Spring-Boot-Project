package com.crms.dto;

import lombok.Data;

@Data
public class BankAccountDto {

	private String bankAccountId;
	private String accountHolderName;
	private String bankName;
	private String accountNo;
	private String ifscNo;
}
