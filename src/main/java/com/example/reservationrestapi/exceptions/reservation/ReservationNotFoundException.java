package com.example.reservationrestapi.exceptions.reservation;

public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(Integer id){
        super("could not find reservation with id: " + id);
    }
}
