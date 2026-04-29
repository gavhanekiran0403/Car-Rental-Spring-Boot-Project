package com.crms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.CarOwnerBankDto;
import com.crms.dto.CarOwnerDto;
import com.crms.entities.BankAccount;
import com.crms.entities.CarOwner;
import com.crms.mapper.CarOwnerMapper;
import com.crms.repository.BankAccountRepository;
import com.crms.repository.CarOwnerRepository;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {

    @Autowired
    private CarOwnerRepository repository;

    @Autowired
    private BankAccountRepository bankAccountRepository;
    
    @Autowired
    private CarOwnerMapper mapper;

    @Autowired
    private FileService fileService;

    @Override
    public CarOwnerDto createCarOwner(CarOwnerDto dto, MultipartFile imageFile) {

        try {
            String fileName = FileService.saveImage(imageFile);
            dto.setAadharImage(fileName);
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed");
        }

        CarOwner entity = mapper.dtoToEntity(dto);
        CarOwner saved = repository.save(entity);

        return mapper.entityToDto(saved);
    }

    @Override
    public CarOwnerDto getCarOwnerById(String ownerId) {
        CarOwner owner = repository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Car Owner not found"));

        return mapper.entityToDto(owner);
    }

    @Override
    public List<CarOwnerDto> getAllCarOwners() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarOwnerDto updateCarOwner(String ownerId, CarOwnerDto dto, MultipartFile imageFile) {

        CarOwner existing = repository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Car Owner not found"));

        existing.setOwnerName(dto.getOwnerName());
        existing.setPhoneNo(dto.getPhoneNo());
        existing.setEmail(dto.getEmail());
        existing.setAddress(dto.getAddress());
        existing.setBankAccountId(dto.getBankAccountId());

        try {
            if (imageFile != null && !imageFile.isEmpty()) {

                // delete old image
                fileService.deleteImage(existing.getAadharImage());

                // save new image
                String fileName = fileService.saveImage(imageFile);
                existing.setAadharImage(fileName);
            }

        } catch (Exception e) {
            throw new RuntimeException("Image update failed");
        }

        CarOwner updated = repository.save(existing);

        return mapper.entityToDto(updated);
    }

    @Override
    public void deleteCarOwner(String ownerId) {

        CarOwner existing = repository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Car Owner not found"));

        // delete image
        fileService.deleteImage(existing.getAadharImage());

        repository.deleteById(ownerId);
    }

	@Override
	public CarOwnerBankDto createCarOwnerBank(CarOwnerBankDto carOwnerBankDto, MultipartFile imageFile) {
		
		try {
            String fileName = fileService.saveImage(imageFile);
            carOwnerBankDto.setAadharImage(fileName);
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed");
        }
		
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountHolderName(carOwnerBankDto.getOwnerName());
		bankAccount.setBankName(carOwnerBankDto.getBankName());
		bankAccount.setAccountNo(carOwnerBankDto.getAccountNo());
		bankAccount.setIfscNo(carOwnerBankDto.getIfscNo());
		
		BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
		
		CarOwner carOwner = new CarOwner();
		carOwner.setOwnerName(carOwnerBankDto.getOwnerName());
		carOwner.setPhoneNo(carOwnerBankDto.getPhoneNo());
		carOwner.setEmail(carOwnerBankDto.getEmail());
		carOwner.setAddress(carOwnerBankDto.getAddress());
		carOwner.setAadharImage(carOwnerBankDto.getAadharImage());
		carOwner.setBankAccountId(savedBankAccount.getBankAccountId());
		
		CarOwner saved = repository.save(carOwner);
		
		carOwnerBankDto.setOwnerId(saved.getOwnerId());
		carOwnerBankDto.setBankAccountId(savedBankAccount.getBankAccountId());
		carOwnerBankDto.setAccountHolderName(savedBankAccount.getAccountHolderName());
		
		return carOwnerBankDto;
	}
}