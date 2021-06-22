package com.example.reservationrestapi.exceptions.reservation;

public class ReservationFullException extends RuntimeException{

    public ReservationFullException(Integer reservationId){
        super(String.format("reservation with id %d exceeded allowed amount of reservations for an openingdate", reservationId));
    }
}
