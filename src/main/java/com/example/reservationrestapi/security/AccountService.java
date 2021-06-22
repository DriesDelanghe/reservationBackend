package com.example.reservationrestapi.security;

import com.example.reservationrestapi.model.Account;
import com.example.reservationrestapi.model.Email;
import com.example.reservationrestapi.repositories.AccountRepository;
import com.example.reservationrestapi.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailRepository emailRepository;

    @PostConstruct
    public void initialize(){
        if (accountRepository.findAccountByUsername("admin_tjok") == null){
            save(new Account("admin_tjok", "test", "ADMIN", false), "tjok2540@gmail.com");
        }
        if(accountRepository.findAccountByUsername("anonymous") == null) {
            save(new Account("anonymous", "Pr0t3ct3d_", "ANONYMOUS", false), null);
        }
        if (accountRepository.findAccountByUsername("driesD") == null) {
            save(new Account("driesD", "test", "USER", true), "dries.delanghe.1998@gmail.com");
        }
    }

    @Transactional
    public Account save(Account user, String email){
        user.setEmail(emailRepository.save(new Email(email)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountRepository.save(user);
    }

}
