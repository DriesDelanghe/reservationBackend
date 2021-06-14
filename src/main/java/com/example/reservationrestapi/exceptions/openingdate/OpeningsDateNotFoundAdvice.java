package com.example.reservationrestapi.exceptions.openingdate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OpeningsDateNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(OpeningsDateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String openingsDateNotFoundHandler(OpeningsDateNotFoundException ex) {
        return ex.getMessage();
    }
}
