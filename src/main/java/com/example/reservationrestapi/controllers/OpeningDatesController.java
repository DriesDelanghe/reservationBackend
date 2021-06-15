package com.example.reservationrestapi.controllers;


import com.example.reservationrestapi.exceptions.openingdate.OpeningsDateNotFoundException;
import com.example.reservationrestapi.model.OpeningDate;
import com.example.reservationrestapi.repositories.OpeningDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/openingdates")
public class OpeningDatesController {

    @Autowired
    OpeningDateRepository openingDateRepository;

    @GetMapping("/all")
    public List<OpeningDate> getAllOpeningsDates(){
        return (List<OpeningDate>) openingDateRepository.findAll();
    }

    @GetMapping("/{id}")
    public OpeningDate getOneOpeningsdate(@PathVariable Integer id){
        return openingDateRepository.findById(id).orElseThrow(() -> new OpeningsDateNotFoundException(id));
    }

    @PutMapping({"/{id}", "/"})
    public OpeningDate replaceOpeningsDate(@RequestBody OpeningDate newOpeningDate,
                                           @PathVariable(required = false) Integer id){
        if (id == null){
            return openingDateRepository.save(newOpeningDate);
        }
        return openingDateRepository.findById(id).map(openingDate -> {
            openingDate.setOpeningHour(newOpeningDate.getOpeningHour());
            openingDate.setClosingHour(newOpeningDate.getClosingHour());
            return openingDateRepository.save(openingDate);
        }).orElseGet(() -> {
            newOpeningDate.setId(id);
            return openingDateRepository.save(newOpeningDate);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteOpeningsDate(@PathVariable Integer id) {
        try {
            openingDateRepository.deleteById(id);
        }catch (Exception e){
            throw  new OpeningsDateNotFoundException(id);
        }
    }
}
