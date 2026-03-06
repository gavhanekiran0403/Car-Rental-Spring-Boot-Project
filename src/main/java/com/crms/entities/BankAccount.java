package com.crms.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "bank_accounts")
@Data
public class BankAccount {

	@Id
	private String bankAccId;
	private String accHolderName;
	private String bankName;
	private String accountNo;
	private String ifscNo;
}
