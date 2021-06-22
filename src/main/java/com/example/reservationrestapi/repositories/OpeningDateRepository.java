package com.example.reservationrestapi.repositories;

import com.example.reservationrestapi.model.OpeningDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpeningDateRepository extends CrudRepository<OpeningDate, Integer> {

    @Query("select COUNT(pl.id) as amountOfPeople from Reservation re join re.personList pl join re.openingDateList dl where dl in (select op from OpeningDate op where op.id = :id) ")
    Integer amountOfReservations(@Param("id") Integer id);

    @Query("select op from OpeningDate op where op.activeDate = true")
    List<OpeningDate> getActiveDates();

    @Query("select op from OpeningDate op where op.activeDate = false")
    List<OpeningDate> getInactiveDates();
}
