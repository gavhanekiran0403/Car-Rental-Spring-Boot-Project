package com.crms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crms.entities.BankAccount;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {

}
