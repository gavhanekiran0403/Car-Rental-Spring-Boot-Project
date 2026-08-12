package com.crms.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    private static final String TOPIC = "booking-topic";

    @Autowired
    private KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public void sendBookingEvent(BookingEvent bookingEvent) {

        kafkaTemplate.send(
                TOPIC,
                bookingEvent.getBookingId(),
                bookingEvent
        );

        System.out.println(
                "Booking event sent to Kafka: "
                        + bookingEvent.getBookingId()
        );
    }
}