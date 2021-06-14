package com.example.reservationrestapi.repositories;

import com.example.reservationrestapi.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
