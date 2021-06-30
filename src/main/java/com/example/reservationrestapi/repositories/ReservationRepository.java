package com.example.reservationrestapi.repositories;

import com.example.reservationrestapi.model.OpeningDate;
import com.example.reservationrestapi.model.Person;
import com.example.reservationrestapi.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    @Query("select op from Reservation re join re.openingDateList op where re.id = :id")
    List<OpeningDate> getOpeningDates(@Param("id") Integer reservationId);

    @Query("select pe from Reservation re join re.personList pe where re.id = :id")
    List<Person> GetPeople(@Param("id") Integer reservationId);

    @Query("select re from Reservation re join re.openingDateList op where :openingDate in (op)")
    List<Reservation> getAllReservationsByOpeningDate(@Param("openingDate") OpeningDate openingDate);
}
