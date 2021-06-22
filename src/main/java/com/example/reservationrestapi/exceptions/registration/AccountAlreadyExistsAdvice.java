package com.example.reservationrestapi.exceptions.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler({AccountAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String AccountAlreadyExistsHandler(AccountAlreadyExistsException ex){
        return ex.getMessage();
    }
}
