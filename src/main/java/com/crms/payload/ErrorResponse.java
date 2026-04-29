package com.crms.payload;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private int statusCode;
    private boolean success;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.success = false;
        this.timestamp = LocalDateTime.now();
    }
}