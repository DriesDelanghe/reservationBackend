package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.exceptions.email.EmailNotFoundException;
import com.example.reservationrestapi.model.Email;
import com.example.reservationrestapi.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/data/email")
public class EmailController {

    @Autowired
    EmailRepository emailRepository;

    @GetMapping("/all")
    public List<Email> getAllEmail(){
        return (List<Email>) emailRepository.findAll();
    }

    @GetMapping("/{id}")
    public Email getOneEmail(@PathVariable Integer id){
        return emailRepository.findById(id).orElseThrow(() -> new EmailNotFoundException(id));
    }

    @PutMapping({"/{id}", "/"})
    public Email replaceEmail(@RequestBody Email newEmail,
                              @PathVariable(required = false) Integer id){
        if (id == null){
            return emailRepository.save(newEmail);
        }
        return emailRepository.findById(id).map(email -> {
            email.setEmail(newEmail.getEmail());
            return emailRepository.save(email);
        }).orElseGet(() -> {
            newEmail.setId(id);
            return emailRepository.save(newEmail);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteEmail(@PathVariable Integer id){
        try {
            emailRepository.deleteById(id);
        }catch (Exception e){
           throw new EmailNotFoundException(id);
        }
    }
}
