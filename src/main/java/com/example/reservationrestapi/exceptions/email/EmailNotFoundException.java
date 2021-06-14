package com.example.reservationrestapi.exceptions.email;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(Integer id) {
        super("Could not find email with id: " + id);
    }
}
