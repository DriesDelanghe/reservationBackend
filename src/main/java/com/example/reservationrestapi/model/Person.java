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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
