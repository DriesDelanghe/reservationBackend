package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.exceptions.reservation.ReservationNotFoundException;
import com.example.reservationrestapi.model.Reservation;
import com.example.reservationrestapi.repositories.EmailRepository;
import com.example.reservationrestapi.repositories.PersonRepository;
import com.example.reservationrestapi.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/reservation")
public class reservationController {

    Logger logger = LoggerFactory.getLogger(reservationController.class);

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EmailRepository emailRepository;

    @GetMapping("/all")
    public List<Reservation> getAllReservations(){
        return (List<Reservation>) reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getOnePerson(@PathVariable Integer id){
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @PutMapping({"/{id}", "/"})
    public Reservation replaceReservation(@RequestBody Reservation newReservation,
                                          @PathVariable(required = false) Integer id){

        logger.info(String.valueOf(newReservation));
        if (id == null){
            personRepository.saveAll(newReservation.getPersonList());
            if (newReservation.isConfirmation()){
                emailRepository.save(newReservation.getEmail());
            }
            return reservationRepository.save(newReservation);
        }
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setPersonList(newReservation.getPersonList());
            reservation.setEmail(reservation.getEmail());
            reservation.setOpeningDateList(reservation.getOpeningDateList());
            return reservationRepository.save(reservation);
        }).orElseGet(() -> {
            newReservation.setId(id);
            return reservationRepository.save(newReservation);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Integer id){
        try{
            reservationRepository.deleteById(id);
        }catch (Exception e){
            throw new ReservationNotFoundException(id);
        }
    }
}
