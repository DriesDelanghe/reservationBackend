package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.model.OpeningDate;
import com.example.reservationrestapi.repositories.OpeningDateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@RestController
public class PeriodController {

    @Autowired
    OpeningDateRepository openingDateRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/data/period")
    public ArrayList<ArrayList<String>> getPeriodMatrix() {
        ArrayList<ArrayList<String>> periodMatrix = new ArrayList<>();
        List<OpeningDate> openingDates = openingDateRepository.getActiveDates();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Collections.sort(openingDates);

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

    @GetMapping("/data/calendar/period")
    public ArrayList<ArrayList<ArrayList<String>>> getPeriodMatrixByMonth() {
        ArrayList<ArrayList<ArrayList<String>>> monthPeriodMatrix = new ArrayList<>();
        ArrayList<Calendar> dateList = new ArrayList<>();

        List<OpeningDate> openingDates = openingDateRepository.getActiveDates();

        Collections.sort(openingDates);

        openingDates.forEach(object -> {
            if (!dateList.stream().anyMatch(calendar -> {
                Calendar reference = Calendar.getInstance();
                reference.setTime(object.getOpeningDate());
                return calendar.get(Calendar.MONTH) == reference.get(Calendar.MONTH);
            })) {
                Calendar date = Calendar.getInstance();
                date.setTime(object.getOpeningDate());
                dateList.add(date);
            }
        });

        Collections.sort(dateList);

        for (Calendar date : dateList) {

            Calendar startDate = Calendar.getInstance();
            startDate.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 1);
            startDate.add(Calendar.DATE, -(startDate.get(Calendar.DAY_OF_WEEK) > 1 ?
                    (startDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY) : 6));

            ArrayList<ArrayList<String>> periodMatrix = new ArrayList<>();

            ArrayList<String> weekDays = new ArrayList<>();

            while (startDate.get(Calendar.MONTH) <= date.get(Calendar.MONTH) ||
                    startDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                if (!weekDays.isEmpty() && startDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    periodMatrix.add(weekDays);
                    weekDays = new ArrayList<>();
                }
                weekDays.add(new SimpleDateFormat("yyyy-MM-dd").format(startDate.getTime()));
                startDate.add(Calendar.DATE, 1);
            }
            if (!weekDays.isEmpty()){
                periodMatrix.add(weekDays);
            }
            monthPeriodMatrix.add(periodMatrix);
        }

        return monthPeriodMatrix;
    }
}
