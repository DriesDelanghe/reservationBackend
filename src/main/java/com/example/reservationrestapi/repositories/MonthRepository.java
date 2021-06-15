package com.example.reservationrestapi.repositories;

import com.example.reservationrestapi.model.Month;
import org.springframework.data.repository.CrudRepository;

public interface MonthRepository extends CrudRepository<Month, Integer> {
}
