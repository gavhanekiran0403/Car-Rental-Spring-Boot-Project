package com.crms.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crms.dto.BookingDto;
import com.crms.entities.Booking;
import com.crms.mapper.BookingMapper;
import com.crms.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private BookingMapper mapper;

    @Override
    public BookingDto createBooking(BookingDto dto) {

        Booking booking = mapper.dtoToEntity(dto);

        // Set created time automatically
        booking.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Default status
        if (booking.getStatus() == null) {
            booking.setStatus("PENDING");
        }

        Booking saved = repository.save(booking);
        return mapper.entityToDto(saved);
    }

    @Override
    public BookingDto getBookingById(String bookingId) {
        Booking booking = repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapper.entityToDto(booking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto updateBooking(String bookingId, BookingDto dto) {

        Booking existing = repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        existing.setPickupLocation(dto.getPickupLocation());
        existing.setDropLocation(dto.getDropLocation());
        existing.setPickupDate(dto.getPickupDate());
        existing.setPickupTime(dto.getPickupTime());
        existing.setReturnDate(dto.getReturnDate());
        existing.setReturnTime(dto.getReturnTime());
        existing.setDrivingOption(dto.getDrivingOption());
        existing.setAadharCard(dto.getAadharCard());
        existing.setTotalAmount(dto.getTotalAmount());
        existing.setStatus(dto.getStatus());
        existing.setUserId(dto.getUserId());
        existing.setCarId(dto.getCarId());
        existing.setDriverId(dto.getDriverId());

        Booking updated = repository.save(existing);
        return mapper.entityToDto(updated);
    }

    @Override
    public void deleteBooking(String bookingId) {
        repository.deleteById(bookingId);
    }
}