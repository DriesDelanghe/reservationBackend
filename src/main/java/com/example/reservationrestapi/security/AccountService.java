package com.example.reservationrestapi.security;

import com.example.reservationrestapi.model.Account;
import com.example.reservationrestapi.repositories.AccountRepository;
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

    @PostConstruct
    public void initialize(){
        if (accountRepository.findAccountByUsername("admin_tjok") == null){
            save(new Account("admin_tjok", "test", "ADMIN"));
        }
        if(accountRepository.findAccountByUsername("anonymous") == null) {
            save(new Account("anonymous", "Pr0t3ct3d_", "ANONYMOUS"));
        }
    }

    @Transactional
    public Account save(Account user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountRepository.save(user);
    }

}
