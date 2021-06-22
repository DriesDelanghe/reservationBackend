package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.exceptions.registration.AccountAlreadyExistsException;
import com.example.reservationrestapi.model.Account;
import com.example.reservationrestapi.model.Email;
import com.example.reservationrestapi.model.RegistrationAccount;
import com.example.reservationrestapi.repositories.AccountRepository;
import com.example.reservationrestapi.security.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;


    @GetMapping("/authenticate")
    public AuthenticationBean authenticate(Principal principal) {
        if (principal != null) {
            Account u = accountRepository.findAccountByUsername(principal.getName());
            if (u != null) {
                return new AuthenticationBean(u.getUsername(), u.getRole(), u.isUseEmail(), u.getEmail());
            }
        }
        return new AuthenticationBean("anonymous", "ANONYMOUS", false, null);
    }

    @PostMapping("/data/register")
    public AuthenticationBean registerUser(@RequestBody RegistrationAccount ra) {
        if (accountRepository.findAccountByUsername(ra.getUsername()) != null) {
            throw new AccountAlreadyExistsException(ra.getUsername());
        }
        Account registeredAccount = accountService.save(new Account(ra.getUsername(), ra.getPassword(), "USER", ra.isUseEmail()), ra.getEmail());

        return new AuthenticationBean(registeredAccount.getUsername(), registeredAccount.getRole(), registeredAccount.isUseEmail(), registeredAccount.getEmail());
    }

    @Data
    @AllArgsConstructor
    class AuthenticationBean {
        private String username;
        private String role;
        private boolean useEmail;
        private Email email;
    }


}
