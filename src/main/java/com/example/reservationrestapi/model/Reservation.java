package com.example.reservationrestapi.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Reservation {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_generator")
    @SequenceGenerator(name = "reservation_generator", sequenceName = "re_seq", allocationSize = 1)
    @Id
    private Integer id;
    @ManyToOne
    private Email email;
    @ManyToMany
    private List<OpeningDate> openingDateList;
    @OneToMany
    private List<Person> personList;
    private boolean confirmation = false;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date reservationDate;


    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<OpeningDate> getOpeningDateList() {
        return openingDateList;
    }

    public void setOpeningDateList(List<OpeningDate> openingDateList) {
        this.openingDateList = openingDateList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }
}
