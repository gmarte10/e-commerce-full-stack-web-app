package com.giancarlos.service;

import com.giancarlos.model.Account;
import com.giancarlos.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public Account register(Account account) {
        return accountRepository.save(account);

    }

    public String verify(Account account) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(account.getUsername());
        } else {
            return "Failed to authenticate";
        }
    }
}