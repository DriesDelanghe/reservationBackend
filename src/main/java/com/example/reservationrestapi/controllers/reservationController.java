package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.exceptions.reservation.ReservationNotFoundException;
import com.example.reservationrestapi.model.Account;
import com.example.reservationrestapi.model.Reservation;
import com.example.reservationrestapi.repositories.AccountRepository;
import com.example.reservationrestapi.repositories.EmailRepository;
import com.example.reservationrestapi.repositories.PersonRepository;
import com.example.reservationrestapi.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
public class reservationController {

    Logger logger = LoggerFactory.getLogger(reservationController.class);

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    AccountRepository accountRepository;

    //ROLE_ADMIN only access
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/restricted/reservation")
    public List<Reservation> getAllReservations(){
        return (List<Reservation>) reservationRepository.findAll();
    }

    //general login access
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/protected/reservation/{id}")
    public Reservation getOnePerson(@PathVariable Integer id){
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN, ROLE_USER')")
    @GetMapping("/protected/reservation/user")
    public List<Reservation> getReservationsUser(Principal principal){
        if (principal != null){
            Account u = accountRepository.findAccountByUsername(principal.getName());
            return u.getReservations();
        }
        return null;
    }

    @PutMapping({"/data/reservation/{id}", "/data/reservation"})
    public Reservation replaceReservation(@RequestBody Reservation newReservation,
                                          @PathVariable(required = false) Integer id,
                                          Principal principal){

        if (id == null){
            return saveNewReservation(newReservation, principal);
        }

        return reservationRepository.findById(id).map(reservation -> {
            reservation.setPersonList(newReservation.getPersonList());
            reservation.setEmail(reservation.getEmail());
            reservation.setOpeningDateList(reservation.getOpeningDateList());
            reservation.setReservationDate(new Date());
            if (principal != null){
                Account u = accountRepository.findAccountByUsername(principal.getName());
                u.getReservations().add(reservation);
            }
            return reservationRepository.save(reservation);
        }).orElseGet(() -> saveNewReservation(newReservation, principal));
    }

    public Reservation saveNewReservation(Reservation newReservation, Principal principal){
        personRepository.saveAll(newReservation.getPersonList());
        if (newReservation.isConfirmation()){
            emailRepository.save(newReservation.getEmail());
        }
        if (principal != null){
            Account u = accountRepository.findAccountByUsername(principal.getName());
            u.getReservations().add(newReservation);
        }
        newReservation.setReservationDate(new Date());
        return reservationRepository.save(newReservation);
    }

    //general login access
    @DeleteMapping("/protected/reservation/{id}")
    public void deleteReservation(@PathVariable Integer id){
        try{
            reservationRepository.deleteById(id);
        }catch (Exception e){
            throw new ReservationNotFoundException(id);
        }
    }
}
