package com.example.reservationrestapi.exceptions.openingdate;

public class OpeningsDateNotFoundException extends RuntimeException{

    public OpeningsDateNotFoundException(Integer id) {
        super("Could not find openingsdate with id: " + id);
    }
}
