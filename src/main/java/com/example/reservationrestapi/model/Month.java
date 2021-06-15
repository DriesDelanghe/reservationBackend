package com.example.reservationrestapi.model;

import javax.persistence.*;

@Entity
public class Month {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "month_generator")
    @SequenceGenerator(name = "month_generator", sequenceName = "mo_seq", allocationSize = 1, initialValue = 1)
    @Id
    private int id;
    private String monthName;

    public Month() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
}
