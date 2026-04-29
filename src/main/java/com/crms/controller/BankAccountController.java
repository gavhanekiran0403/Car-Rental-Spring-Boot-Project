package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.BankAccountDto;
import com.crms.payload.ApiResponse;
import com.crms.service.BankAccountService;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    // CREATE BANK ACCOUNT
    @PostMapping
    public ResponseEntity<ApiResponse<BankAccountDto>> create(
            @RequestBody BankAccountDto dto) {

        BankAccountDto createdAccount = service.createAccount(dto);

        ApiResponse<BankAccountDto> response =
                new ApiResponse<>(
                        "Bank account created successfully",
                        true,
                        createdAccount
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET BANK ACCOUNT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BankAccountDto>> getById(
            @PathVariable String id) {

        BankAccountDto account = service.getAccountById(id);

        ApiResponse<BankAccountDto> response =
                new ApiResponse<>(
                        "Bank account fetched successfully",
                        true,
                        account
                );

        return ResponseEntity.ok(response);
    }

    // GET ALL BANK ACCOUNTS
    @GetMapping
    public ResponseEntity<ApiResponse<List<BankAccountDto>>> getAll() {

        List<BankAccountDto> accounts = service.getAllAccounts();

        ApiResponse<List<BankAccountDto>> response =
                new ApiResponse<>(
                        "Bank accounts fetched successfully",
                        true,
                        accounts
                );

        return ResponseEntity.ok(response);
    }

    // UPDATE BANK ACCOUNT
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BankAccountDto>> update(
            @PathVariable String id,
            @RequestBody BankAccountDto dto) {

        BankAccountDto updatedAccount =
                service.updateAccount(id, dto);

        ApiResponse<BankAccountDto> response =
                new ApiResponse<>(
                        "Bank account updated successfully",
                        true,
                        updatedAccount
                );

        return ResponseEntity.ok(response);
    }

    // DELETE BANK ACCOUNT
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(
            @PathVariable String id) {

        service.deleteAccount(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        "Bank account deleted successfully",
                        true,
                        null
                );

        return ResponseEntity.ok(response);
    }
}