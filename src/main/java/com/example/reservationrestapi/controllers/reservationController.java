package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.exceptions.reservation.ReservationNotFoundException;
import com.example.reservationrestapi.model.Reservation;
import com.example.reservationrestapi.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/reservation")
public class reservationController {

    @Autowired
    ReservationRepository reservationRepository;

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
        if (id == null){
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
