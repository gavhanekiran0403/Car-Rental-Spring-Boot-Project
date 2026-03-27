package com.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.dto.BankAccountDto;
import com.crms.entities.BankAccount;
import com.crms.mapper.BankAccountMapper;
import com.crms.repository.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Autowired
    private BankAccountMapper mapper;

    @Override
    public BankAccountDto createAccount(BankAccountDto dto) {
        BankAccount account = mapper.dtoToEntity(dto);
        BankAccount saved = repository.save(account);
        return mapper.entityToDto(saved);
    }

    @Override
    public BankAccountDto getAccountById(String id) {
        BankAccount account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank Account not found"));
        return mapper.entityToDto(account);
    }

    @Override
    public List<BankAccountDto> getAllAccounts() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountDto updateAccount(String id, BankAccountDto dto) {
        BankAccount existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank Account not found"));

        existing.setAccountHolderName(dto.getAccountHolderName());
        existing.setBankName(dto.getBankName());
        existing.setAccountNo(dto.getAccountNo());
        existing.setIfscNo(dto.getIfscNo());

        BankAccount updated = repository.save(existing);
        return mapper.entityToDto(updated);
    }

    @Override
    public void deleteAccount(String id) {
        repository.deleteById(id);
    }
}