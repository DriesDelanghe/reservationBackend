package com.example.reservationrestapi.exceptions.reservation;

public class ReservationByOpeningDateException extends RuntimeException{
    public ReservationByOpeningDateException(Integer openingDateId){
        super(String.format("Error finding reservations by openingDate with id %d", openingDateId));
    }
}
