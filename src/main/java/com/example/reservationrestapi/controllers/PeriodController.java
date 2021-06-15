package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.model.OpeningDate;
import com.example.reservationrestapi.repositories.OpeningDateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/data/period")
public class PeriodController {

    @Autowired
    OpeningDateRepository openingDateRepository;
    Logger logger = LoggerFactory.getLogger(PeriodController.class);

    @GetMapping("/all")
    public ArrayList<ArrayList<String>> getPeriodMatrix() {
        ArrayList<ArrayList<String>> periodMatrix = new ArrayList<>();
        List<OpeningDate> openingDates = (List<OpeningDate>) openingDateRepository.findAll();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(openingDates.get(0).getOpeningDate());
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(openingDates.get(openingDates.size() - 1).getOpeningDate());
        ArrayList<String> weekDates = new ArrayList<>();
        Calendar referenceDate = startDate;
        referenceDate.add(Calendar.DATE, -(referenceDate.get(Calendar.DAY_OF_WEEK) - 2));

        while (referenceDate.compareTo(endDate) <= 0) {
            if (referenceDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && !weekDates.isEmpty()) {
                periodMatrix.add(weekDates);
                weekDates = new ArrayList<>();
            }
            weekDates.add(dateFormat.format(referenceDate.getTime()));
            referenceDate.add(Calendar.DATE, 1);
        }
        if (!weekDates.isEmpty()) {

            periodMatrix.add(weekDates);
        }

        return periodMatrix;
    }
}
