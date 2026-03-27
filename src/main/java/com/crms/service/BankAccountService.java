package com.crms.service;

import java.util.List;

import com.crms.dto.BankAccountDto;

public interface BankAccountService {

    BankAccountDto createAccount(BankAccountDto dto);

    BankAccountDto getAccountById(String id);

    List<BankAccountDto> getAllAccounts();

    BankAccountDto updateAccount(String id, BankAccountDto dto);

    void deleteAccount(String id);
}