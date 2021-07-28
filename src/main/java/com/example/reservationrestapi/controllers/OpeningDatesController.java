package com.example.reservationrestapi.controllers;


import com.example.reservationrestapi.exceptions.openingdate.OpeningsDateNotFoundException;
import com.example.reservationrestapi.model.OpeningDate;
import com.example.reservationrestapi.repositories.OpeningDateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OpeningDatesController {

    Logger logger = LoggerFactory.getLogger(OpeningDatesController.class);

    @Autowired
    OpeningDateRepository openingDateRepository;

    @GetMapping("/data/openingdates")
    public List<OpeningDate> getAllActiveOpeningsDates(){
        return openingDateRepository.getActiveDates();
    }

    @GetMapping("/restricted/openingdates")
    public List<OpeningDate> getAllOpeningDates() {
        return openingDateRepository.getOpeningDates();
    }

    @GetMapping("/restricted/openingdates/removed")
    public List<OpeningDate> getAllInactiveOpeningDates(){
        return openingDateRepository.getRemovedOpeningDates();
    }

    //ROLE_ADMIN only access
    @GetMapping("/restricted/openingdates/{id}")
    public OpeningDate getOneOpeningsdate(@PathVariable Integer id){
        return openingDateRepository.findById(id).orElseThrow(() -> new OpeningsDateNotFoundException(id));
    }

    //ROLE_ADMIN only access
    @PutMapping({"/restricted/openingdates/{id}", "/restricted/openingdates/"})
    public OpeningDate replaceOpeningsDate(@RequestBody OpeningDate newOpeningDate,
                                           @PathVariable(required = false) Integer id){
        if (id == null){
            return openingDateRepository.save(newOpeningDate);
        }
        return openingDateRepository.findById(id).map(openingDate -> {
            openingDate.setOpeningHour(newOpeningDate.getOpeningHour());
            openingDate.setClosingHour(newOpeningDate.getClosingHour());
            openingDate.setOpeningDate(newOpeningDate.getOpeningDate());
            openingDate.setEventName(newOpeningDate.getEventName());
            
            openingDate.setActiveDate(newOpeningDate.isActiveDate());
            openingDate.setRemoved(newOpeningDate.isRemoved());

            openingDate.setReservationLimit(newOpeningDate.getReservationLimit());

            return openingDateRepository.save(openingDate);
        }).orElseGet(() -> {
            newOpeningDate.setId(id);
            return openingDateRepository.save(newOpeningDate);
        });
    }

    //ROLE_ADMIN only access
    @DeleteMapping("/restricted/openingdates/{id}")
    public void deleteOpeningsDate(@PathVariable Integer id) {
        try {
            openingDateRepository.deleteById(id);
        }catch (Exception e){
            throw  new OpeningsDateNotFoundException(id);
        }
    }
}
