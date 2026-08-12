package com.crms.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEvent {

    private String bookingId;
    private String userId;
    private String carId;
    private String status;
    private double totalAmount;
}