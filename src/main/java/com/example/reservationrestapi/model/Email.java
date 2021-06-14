package com.example.reservationrestapi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Email {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_generator")
    @SequenceGenerator(name = "email_generator", sequenceName = "em_seq", allocationSize = 1)
    @Id
    private Integer id;
    @NotNull
    private String email;

    public Email() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
