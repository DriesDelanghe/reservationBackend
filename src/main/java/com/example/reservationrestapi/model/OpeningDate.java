package com.example.reservationrestapi.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OpeningDate implements Comparable<OpeningDate> {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opening_generator")
    @SequenceGenerator(name = "opening_generator", sequenceName = "op_seq", allocationSize = 1)
    @Id
    private Integer id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date openingDate;
    private String openingHour;
    private String closingHour;
    @Column(columnDefinition = "integer default 180")
    private Integer reservationLimit;
//    @Column(columnDefinition = "integer default 100")
    private Integer reservationAmount;
    private boolean activeDate;
    private boolean removed;
    private String eventName;

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

    public Integer getReservationLimit() {
        return reservationLimit;
    }

    public void setReservationLimit(Integer reservationLimit) {
        this.reservationLimit = reservationLimit;
    }

    public Integer getReservationAmount() {
        return reservationAmount;
    }

    public void setReservationAmount(Integer reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public boolean isActiveDate() {
        return activeDate;
    }

    public void setActiveDate(boolean activeDate) {
        this.activeDate = activeDate;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "OpeningDate{" +
                "id=" + id +
                ", openingDate=" + openingDate +
                ", openingHour='" + openingHour + '\'' +
                ", closingHour='" + closingHour + '\'' +
                ", reservationLimit=" + reservationLimit +
                ", reservationAmount=" + reservationAmount +
                ", activeDate=" + activeDate +
                ", removed=" + removed +
                '}';
    }

    @Override
    public int compareTo(OpeningDate o) {
        return this.getOpeningDate().compareTo(o.getOpeningDate());
    }
}
