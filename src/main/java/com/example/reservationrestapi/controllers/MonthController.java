package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.model.Month;
import com.example.reservationrestapi.repositories.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonthController {

    @Autowired
    MonthRepository monthRepository;

    @GetMapping({"/data/month"})
    public List<Month> getMonthNames(){
        return (List<Month>) monthRepository.findAll();
    }
}
