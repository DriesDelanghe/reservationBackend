package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.exceptions.reservation.ReservationFullException;
import com.example.reservationrestapi.exceptions.reservation.ReservationNotFoundException;
import com.example.reservationrestapi.model.Account;
import com.example.reservationrestapi.model.OpeningDate;
import com.example.reservationrestapi.model.Person;
import com.example.reservationrestapi.model.Reservation;
import com.example.reservationrestapi.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
    @Autowired
    OpeningDateRepository openingDateRepository;

    //ROLE_ADMIN only access
    @GetMapping("/restricted/reservation")
    public List<Reservation> getAllReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    //general login access
    @GetMapping("/protected/reservation/{id}")
    public Reservation getOnePerson(@PathVariable Integer id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @GetMapping("/protected/reservation/user")
    public List<Reservation> getReservationsUser(Principal principal) {
        if (principal != null) {
            Account u = accountRepository.findAccountByUsername(principal.getName());
            return u.getReservations();
        }
        return null;
    }

    @PutMapping({"/data/reservation/{id}", "/data/reservation"})
    public Reservation replaceReservation(@RequestBody Reservation newReservation,
                                          @PathVariable(required = false) Integer id,
                                          Principal principal) {
        ArrayList<OpeningDate> openingDateArrayList = new ArrayList<>();
        newReservation.getOpeningDateList().forEach(openingDate -> openingDateArrayList.add(openingDateRepository.findById(openingDate.getId()).get()));
        AtomicBoolean sizeExceeded = new AtomicBoolean(false);
        openingDateArrayList.forEach(openingDate -> {
            if ((openingDate.getReservationLimit() - openingDate.getReservationAmount()) < newReservation.getPersonList().size()) {
                logger.warn("this is not right");
                sizeExceeded.set(true);
            }
        });
        if (sizeExceeded.get()) {
            throw new ReservationFullException(newReservation.getId());

        }
        if (id == null) {
            return saveNewReservation(newReservation, principal);
        }

        return reservationRepository.findById(id).map(reservation -> {
            List<Person> personList = reservationRepository.GetPeople(reservation.getId());
            List<OpeningDate> openingDateList = reservationRepository.getOpeningDates(reservation.getId());
            reservation.setPersonList(newReservation.getPersonList());
            personRepository.saveAll(reservation.getPersonList());
            if (newReservation.isConfirmation()) {
                reservation.setConfirmation(newReservation.isConfirmation());
                reservation.setEmail(emailRepository.save(newReservation.getEmail()));
            } else {
                reservation.setConfirmation(false);
                reservation.setEmail(null);
            }
            reservation.setOpeningDateList(newReservation.getOpeningDateList());
            Reservation savedReservation = reservationRepository.save(reservation);

            personList.forEach(person -> {
                if (!reservation.getPersonList().contains(person)) {
                    personRepository.delete(person);
                }
            });
            openingDateList.forEach(this::updateCountOpeningdate);
            return savedReservation;
        }).orElseGet(() -> saveNewReservation(newReservation, principal));
    }

    public Reservation saveNewReservation(Reservation newReservation, Principal principal) {
        personRepository.saveAll(newReservation.getPersonList());
        if (newReservation.isConfirmation()) {
            emailRepository.save(newReservation.getEmail());
        }
        if (principal != null) {
            Account u = accountRepository.findAccountByUsername(principal.getName());
            u.getReservations().add(newReservation);
        }
        newReservation.setReservationDate(new Date());
        Reservation reservation = reservationRepository.save(newReservation);
        reservation.getOpeningDateList().forEach(this::updateCountOpeningdate);
        return reservation;
    }

    public void updateCountOpeningdate(OpeningDate openingDate) {
        Integer count = openingDateRepository.amountOfReservations(openingDate.getId());
        logger.info(String.format("setting count of %s to %d", new SimpleDateFormat("dd/MM/yyyy").format(openingDate.getOpeningDate()), count));
        openingDate.setReservationAmount(count);
        openingDateRepository.save(openingDate);
    }

    //general login access
    @DeleteMapping("/protected/reservation/{id}")
    public void deleteReservation(@PathVariable Integer id,
                                  Principal principal) {
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            if (optionalReservation.isPresent()) {
                Reservation reservation = optionalReservation.get();
                List<Person> personList = reservation.getPersonList();
                reservation.getOpeningDateList().forEach(openingDate -> {
                    openingDate.setReservationAmount(openingDate.getReservationAmount() - reservation.getPersonList().size());
                    openingDateRepository.save(openingDate);
                });
                Account account = accountRepository.findAccountByUsername(principal.getName());
                logger.info(account.getUsername());
                account.getReservations().remove(reservation);
                accountRepository.save(account);
                reservationRepository.delete(reservation);
                personRepository.deleteAll(personList);

            } else {
                throw new ReservationNotFoundException(id);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new ReservationNotFoundException(id);
        }
    }
}
