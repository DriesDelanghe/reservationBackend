package com.example.reservationrestapi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Person {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", sequenceName = "pe_seq", allocationSize = 1)
    @Id
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
