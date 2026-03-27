package com.crms.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crms.dto.BankAccountDto;
import com.crms.entities.BankAccount;

@Component
public class BankAccountMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BankAccount dtoToEntity(BankAccountDto dto) {
        return modelMapper.map(dto, BankAccount.class);
    }

    public BankAccountDto entityToDto(BankAccount entity) {
        return modelMapper.map(entity, BankAccountDto.class);
    }
}