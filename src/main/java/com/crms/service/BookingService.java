package com.crms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.BookingDto;

public interface BookingService {

    BookingDto createBooking(BookingDto dto, MultipartFile file);

    BookingDto getBookingById(String bookingId);

    List<BookingDto> getAllBookings();

    BookingDto updateBooking(String bookingId, BookingDto dto, MultipartFile file);

    void deleteBooking(String bookingId);
    
    BookingDto approveBooking(String bookingId);
    
    BookingDto cancelBooking(String bookingId);
    
    List<BookingDto> getBookingsByUserId(String userId);
}