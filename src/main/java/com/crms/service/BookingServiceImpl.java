package com.crms.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crms.dto.BookingDto;
import com.crms.dto.NotificationDto;
import com.crms.entities.Booking;
import com.crms.entities.Notification;
import com.crms.mapper.BookingMapper;
import com.crms.repository.BookingRepository;
import com.crms.repository.NotificationRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private BookingMapper mapper;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public BookingDto createBooking(BookingDto dto, MultipartFile file) {

        Booking booking = mapper.dtoToEntity(dto);
        
        try {
			String fileName = FileService.saveImage(file);
			booking.setAadharCard(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

        // Set created time automatically
        booking.setCreatedAt(new Date());

        // Default status
        if (booking.getStatus() == null) {
            booking.setStatus("PENDING");
        }

        Booking saved = repository.save(booking);
        
        NotificationDto notificationDto = new NotificationDto();
        
        notificationDto.setMessage(
                "New booking created successfully. Booking ID : " + saved.getBookingId());

        notificationDto.setIsRead("NO");

        notificationDto.setCreatedAt(new Date());

        // send notification to user who booked
        notificationDto.setUserId(saved.getUserId());

        notificationDto.setBookingId(saved.getBookingId());
        
        notificationService.createNotification(notificationDto);
        
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
    public BookingDto updateBooking(String bookingId, BookingDto dto, MultipartFile file) {

        Booking existing = repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        existing.setPickupLocation(dto.getPickupLocation());
        existing.setDropLocation(dto.getDropLocation());
        existing.setPickupDate(dto.getPickupDate());
        existing.setPickupTime(dto.getPickupTime());
        existing.setReturnDate(dto.getReturnDate());
        existing.setReturnTime(dto.getReturnTime());
        existing.setDrivingOption(dto.getDrivingOption());
        existing.setTotalAmount(dto.getTotalAmount());
        existing.setStatus(dto.getStatus());
        existing.setUserId(dto.getUserId());
        existing.setCarId(dto.getCarId());
        existing.setDriverId(dto.getDriverId());
        
        try {
        	if(file != null && !file.isEmpty()) {
        	
        		FileService.deleteImage(existing.getAadharCard());
        	
				String fileName = FileService.saveImage(file);
				
				existing.setAadharCard(fileName);
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Booking updated = repository.save(existing);
        
        NotificationDto notificationDto = new NotificationDto();
        
        notificationDto.setMessage(
                "Booking updated successfully. Booking ID : " + updated.getBookingId());

        notificationDto.setIsRead("NO");

        notificationDto.setCreatedAt(new Date());

        // send notification to user who booked
        notificationDto.setUserId(updated.getUserId());

        notificationDto.setBookingId(updated.getBookingId());
        
        notificationService.createNotification(notificationDto);
        
        return mapper.entityToDto(updated);
    }

    @Override
    public void deleteBooking(String bookingId) {
        repository.deleteById(bookingId);
    }
    
    @Override
    public BookingDto approveBooking(String bookingId) {

        Booking booking = repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // approve booking
        booking.setStatus("APPROVED");

        Booking updatedBooking = repository.save(booking);

        // ===============================
        // CREATE NEW NOTIFICATION ALWAYS
        // ===============================
        Notification notification = new Notification();

        notification.setMessage(
                "Your booking has been approved. Booking ID : "
                        + updatedBooking.getBookingId());

        notification.setIsRead("NO");

        notification.setCreatedAt(new Date());

        notification.setUserId(updatedBooking.getUserId());

        notification.setBookingId(updatedBooking.getBookingId());

        notificationRepository.save(notification);

        return mapper.entityToDto(updatedBooking);
    }
    
    @Override
    public BookingDto cancelBooking(String bookingId) {

        Booking booking = repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // cancel booking
        booking.setStatus("CANCELLED");

        Booking updatedBooking = repository.save(booking);

        // ===============================
        // CREATE NEW NOTIFICATION ALWAYS
        // ===============================
        Notification notification = new Notification();

        notification.setMessage(
                "Your booking has been cancelled. Booking ID : "
                        + updatedBooking.getBookingId());

        notification.setIsRead("NO");

        notification.setCreatedAt(new Date());

        notification.setUserId(updatedBooking.getUserId());

        notification.setBookingId(updatedBooking.getBookingId());

        notificationRepository.save(notification);

        return mapper.entityToDto(updatedBooking);
    }
    
    @Override
    public List<BookingDto> getBookingsByUserId(String userId) {

        List<Booking> bookings = repository.findByUserId(userId);

        return bookings.stream()
                .map(mapper::entityToDto)
                .toList();
    }
}