package com.crms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.BookingDto;
import com.crms.payload.ApiResponse;
import com.crms.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    // CREATE BOOKING
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<BookingDto>> createBooking(
            @RequestPart("booking") BookingDto dto,
            @RequestPart("image") MultipartFile file) {

        BookingDto booking = service.createBooking(dto, file);

        ApiResponse<BookingDto> response =
                new ApiResponse<>(
                        "Booking created successfully",
                        true,
                        booking
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET BOOKING BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingDto>> getBooking(
            @PathVariable String id) {

        BookingDto booking = service.getBookingById(id);

        ApiResponse<BookingDto> response =
                new ApiResponse<>(
                        "Booking fetched successfully",
                        true,
                        booking
                );

        return ResponseEntity.ok(response);
    }

    // GET ALL BOOKINGS
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingDto>>> getAllBookings() {

        List<BookingDto> bookings = service.getAllBookings();

        ApiResponse<List<BookingDto>> response =
                new ApiResponse<>(
                        "Bookings fetched successfully",
                        true,
                        bookings
                );

        return ResponseEntity.ok(response);
    }

    // UPDATE BOOKING
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<BookingDto>> updateBooking(
            @PathVariable String id,
            @RequestPart("booking") BookingDto dto,
            @RequestPart("image") MultipartFile file) {

        BookingDto updatedBooking =
                service.updateBooking(id, dto, file);

        ApiResponse<BookingDto> response =
                new ApiResponse<>(
                        "Booking updated successfully",
                        true,
                        updatedBooking
                );

        return ResponseEntity.ok(response);
    }

    // DELETE BOOKING
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteBooking(
            @PathVariable String id) {

        service.deleteBooking(id);

        ApiResponse<Object> response =
                new ApiResponse<>(
                        "Booking deleted successfully",
                        true,
                        null
                );

        return ResponseEntity.ok(response);
    }

    // APPROVE BOOKING
    @PutMapping("/approve/{bookingId}")
    public ResponseEntity<ApiResponse<BookingDto>> approveBooking(
            @PathVariable String bookingId) {

        BookingDto booking =
                service.approveBooking(bookingId);

        ApiResponse<BookingDto> response =
                new ApiResponse<>(
                        "Booking approved successfully",
                        true,
                        booking
                );

        return ResponseEntity.ok(response);
    }

    // CANCEL BOOKING
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<ApiResponse<BookingDto>> cancelBooking(
            @PathVariable String bookingId) {

        BookingDto booking =
                service.cancelBooking(bookingId);

        ApiResponse<BookingDto> response =
                new ApiResponse<>(
                        "Booking cancelled successfully",
                        true,
                        booking
                );

        return ResponseEntity.ok(response);
    }

    // GET BOOKINGS BY USER ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<BookingDto>>> getBookingsByUserId(
            @PathVariable String userId) {

        List<BookingDto> bookings =
                service.getBookingsByUserId(userId);

        ApiResponse<List<BookingDto>> response =
                new ApiResponse<>(
                        "User bookings fetched successfully",
                        true,
                        bookings
                );

        return ResponseEntity.ok(response);
    }
}