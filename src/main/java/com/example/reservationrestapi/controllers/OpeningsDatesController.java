package com.example.reservationrestapi.controllers;


import com.example.reservationrestapi.model.OpeningDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OpeningsDatesController {

    public ArrayList<OpeningDate> getOpeningsDates() throws Exception{
        ArrayList<OpeningDate> openingsDates = new ArrayList<>();
        openingsDates.add(new OpeningDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-11"), "18:00", "23:30"));
        openingsDates.add(new OpeningDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-12"), "18:00", "23:30"));
        openingsDates.add(new OpeningDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-13"), "18:00", "23:30"));
        openingsDates.add(new OpeningDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-14"), "18:00", "23:30"));
        openingsDates.add(new OpeningDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-15"), "18:00", "23:30"));
        openingsDates.add(new OpeningDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-16"), "18:00", "23:30"));
        return  openingsDates;
    }

    @GetMapping("/openingdates")
    public List<OpeningDate> getAllOpeningsDates(){
        try {
        return getOpeningsDates();
        }catch (Exception e){
            return null;
        }
    }

}
