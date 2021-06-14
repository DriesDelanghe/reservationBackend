package com.example.reservationrestapi.exceptions.person;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(Integer id){
        super("could not find person with id: " + id);
    }
}
