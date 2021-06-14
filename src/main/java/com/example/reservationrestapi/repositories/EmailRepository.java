package com.example.reservationrestapi.repositories;

import com.example.reservationrestapi.model.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Integer> {
}
