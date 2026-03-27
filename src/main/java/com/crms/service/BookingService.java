package com.crms.service;

import java.util.List;

import com.crms.dto.BookingDto;

public interface BookingService {

    BookingDto createBooking(BookingDto dto);

    BookingDto getBookingById(String bookingId);

    List<BookingDto> getAllBookings();

    BookingDto updateBooking(String bookingId, BookingDto dto);

    void deleteBooking(String bookingId);
}