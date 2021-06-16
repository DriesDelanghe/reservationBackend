package com.example.reservationrestapi.security;

import com.example.reservationrestapi.model.Account;
import com.example.reservationrestapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Account u = accountRepository.findAccountByUsername(username);
        if (u==null){
            throw new UsernameNotFoundException("User not found " + username);
        }

        User user = createUser(u);

        return user;
    }

    private User createUser(Account u) {
        return new User(u.getUsername(), u.getPassword(),createAuthorities(u));
    }

    private Collection<GrantedAuthority> createAuthorities(Account u){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+u.getRole()));
        return authorities;
    }
}
