package com.example.reservationrestapi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OpeningDate {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opening_generator")
    @SequenceGenerator(name = "opening_generator", sequenceName = "op_seq", allocationSize = 1)
    @Id
    private Integer id;

    private Date openingDate;
    private String openingHour;
    private String closingHour;

    public OpeningDate() {
    }

    public OpeningDate(Date openingDate, String openingHour, String closingHour) {
        this.openingDate = openingDate;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingsDate) {
        this.openingDate = openingsDate;
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(String openingHour) {
        this.openingHour = openingHour;
    }

    public String getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(String closingHour) {
        this.closingHour = closingHour;
    }
}
