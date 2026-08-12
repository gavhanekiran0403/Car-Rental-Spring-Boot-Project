package com.crms.kafka;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.crms.dto.NotificationDto;
import com.crms.service.NotificationService;

@Service
public class BookingConsumer {

	@Autowired
    private NotificationService notificationService;

    @KafkaListener(
            topics = "booking-topic",
            groupId = "notification-group"
    )
    public void consumeBookingEvent(BookingEvent event) {

        System.out.println(
                "Booking event received: "
                        + event.getBookingId()
        );

        NotificationDto notificationDto =
                new NotificationDto();

        notificationDto.setMessage(
                "New booking created successfully. Booking ID : "
                        + event.getBookingId()
        );

        notificationDto.setIsRead("NO");

        notificationDto.setCreatedAt(new Date());

        notificationDto.setUserId(
                event.getUserId()
        );

        notificationDto.setBookingId(
                event.getBookingId()
        );

        notificationService.createNotification(
                notificationDto
        );

        System.out.println(
                "Notification created for booking: "
                        + event.getBookingId()
        );
    }
}