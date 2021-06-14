package com.example.reservationrestapi.repositories;

import com.example.reservationrestapi.model.OpeningDate;
import org.springframework.data.repository.CrudRepository;

public interface OpeningDateRepository extends CrudRepository<OpeningDate, Integer> {
}
