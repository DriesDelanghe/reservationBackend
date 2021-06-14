package com.example.reservationrestapi.repositories;

import com.example.reservationrestapi.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}
