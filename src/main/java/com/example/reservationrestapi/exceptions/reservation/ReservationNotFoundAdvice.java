package com.example.reservationrestapi.exceptions.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReservationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String reservationNotFoundHandler(ReservationNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReservationFullException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String reservationFullHandler(ReservationFullException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReservationByOpeningDateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String reservationByOpeningDateNotFoundHandler(ReservationNotFoundException ex){
        return ex.getMessage();
    }
}
