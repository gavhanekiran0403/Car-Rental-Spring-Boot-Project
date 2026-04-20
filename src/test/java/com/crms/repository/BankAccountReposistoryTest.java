package com.crms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;

import com.crms.entities.BankAccount;

@DataMongoTest
public class BankAccountReposistoryTest {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@BeforeEach
	void cleanDatabase() {
		bankAccountRepository.deleteAll();
	}
	
	@Test
	void testSaveBankAccount() {
		
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountHolderName("Kiran Dattu Gavhane");
		bankAccount.setBankName("State Bank of India");
		bankAccount.setAccountNo("99454596338");
		bankAccount.setIfscNo("SBIN0000338");
		
		BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
		
		assertNotNull(savedBankAccount);
		assertNotNull(savedBankAccount.getBankAccountId());
		assertEquals("Kiran Dattu Gavhane", savedBankAccount.getAccountHolderName());
	}
	
	@Test
	void testGetBankAccountById() {
		
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountHolderName("Kiran Dattu Gavhane");
		bankAccount.setBankName("State Bank of India");
		bankAccount.setAccountNo("99454596338");
		bankAccount.setIfscNo("SBIN0000338");
		
		BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
		
		Optional<BankAccount>  foundBankAccount = bankAccountRepository.findById(savedBankAccount.getBankAccountId());
		
		assertTrue(foundBankAccount.isPresent());
		assertNotNull(foundBankAccount);
		assertEquals("Kiran Dattu Gavhane", savedBankAccount.getAccountHolderName());
	}
	
	@Test
	void TestDeleteBankAccounts() {
		
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountHolderName("Kiran Dattu Gavhane");
		bankAccount.setBankName("State Bank of India");
		bankAccount.setAccountNo("99454596338");
		bankAccount.setIfscNo("SBIN0000338");
		
		BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
		
		bankAccountRepository.deleteById(savedBankAccount.getBankAccountId());
		
		Optional<BankAccount>  foundBankAccount = bankAccountRepository.findById(savedBankAccount.getBankAccountId());
		
		assertFalse(foundBankAccount.isPresent());
	}
	
	@Test				
	void TestGetAllBankAccounts() {
		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setAccountHolderName("Kiran Dattu Gavhane");
		bankAccount1.setBankName("State Bank of India");
		bankAccount1.setAccountNo("99454596338");
		bankAccount1.setIfscNo("SBIN0000338");
		
		BankAccount bankAccount2 = new BankAccount();
		bankAccount2.setAccountHolderName("Dipak Dattu Gavhane");
		bankAccount2.setBankName("State Bank of India");
		bankAccount2.setAccountNo("99454564773");
		bankAccount2.setIfscNo("SBIN0000773");
		
		bankAccountRepository.save(bankAccount1);
		bankAccountRepository.save(bankAccount2);
		
		long count = bankAccountRepository.count();
		
		assertEquals(2, count);
	}
}
