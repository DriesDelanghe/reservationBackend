package com.example.reservationrestapi.exceptions.registration;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException (String username){
        super(String.format("Account with username %s already exists.", username));
    }
}
