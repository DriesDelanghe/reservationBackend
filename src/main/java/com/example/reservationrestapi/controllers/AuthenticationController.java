package com.example.reservationrestapi.controllers;

import com.example.reservationrestapi.model.Account;
import com.example.reservationrestapi.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/authenticate")
    public AuthenticationBean authenticate(Principal principal) {
        log.info("##### authenticate");
        if (principal != null) {
            Account u = accountRepository.findAccountByUsername(principal.getName());
            if (u != null) {
                return new AuthenticationBean(u.getUsername(), u.getRole());
            }
        }
        return new AuthenticationBean("anonymous", "ANONYMOUS");
    }
    @GetMapping("/protected/account")
    public Account getAccount(Principal principal){
        logger.info(accountRepository.findAccountByUsername(principal.getName()).toString());
        return accountRepository.findAccountByUsername(principal.getName());
    }

    @Data
    @AllArgsConstructor
    class AuthenticationBean {
        private String username;
        private String role;
    }


}
