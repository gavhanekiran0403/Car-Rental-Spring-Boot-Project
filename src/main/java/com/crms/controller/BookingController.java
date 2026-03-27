package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.crms.dto.BookingDto;
import com.crms.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping
    public BookingDto createBooking(@RequestBody BookingDto dto) {
        return service.createBooking(dto);
    }

    @GetMapping("/{id}")
    public BookingDto getBooking(@PathVariable String id) {
        return service.getBookingById(id);
    }

    @GetMapping
    public List<BookingDto> getAllBookings() {
        return service.getAllBookings();
    }

    @PutMapping("/{id}")
    public BookingDto updateBooking(@PathVariable String id,
                                     @RequestBody BookingDto dto) {
        return service.updateBooking(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable String id) {
        service.deleteBooking(id);
        return "Booking deleted successfully";
    }
}