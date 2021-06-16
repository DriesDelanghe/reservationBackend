package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.exceptions.email.EmailNotFoundException;
import com.example.reservationrestapi.model.Email;
import com.example.reservationrestapi.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    EmailRepository emailRepository;

    //ROLE_ADMIN only access
    @GetMapping("/restricted/email")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Email> getAllEmail(){
        return (List<Email>) emailRepository.findAll();
    }

    //general login access
    @GetMapping("/protected/email/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public Email getOneEmail(@PathVariable Integer id){
        return emailRepository.findById(id).orElseThrow(() -> new EmailNotFoundException(id));
    }

    @PutMapping({"/data/email/{id}", "/data/email"})
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

    //general login access
    @DeleteMapping("/protected/email/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public void deleteEmail(@PathVariable Integer id){
        try {
            emailRepository.deleteById(id);
        }catch (Exception e){
           throw new EmailNotFoundException(id);
        }
    }
}
