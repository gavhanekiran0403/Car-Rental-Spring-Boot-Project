package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.BankAccountDto;
import com.crms.service.BankAccountService;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    @PostMapping
    public BankAccountDto create(@RequestBody BankAccountDto dto) {
        return service.createAccount(dto);
    }

    @GetMapping("/{id}")
    public BankAccountDto getById(@PathVariable String id) {
        return service.getAccountById(id);
    }

    @GetMapping
    public List<BankAccountDto> getAll() {
        return service.getAllAccounts();
    }

    @PutMapping("/{id}")
    public BankAccountDto update(@PathVariable String id,
                                 @RequestBody BankAccountDto dto) {
        return service.updateAccount(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.deleteAccount(id);
        return "Bank Account deleted successfully";
    }
}