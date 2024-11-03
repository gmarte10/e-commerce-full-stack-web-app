package com.giancarlos.controller;

import com.giancarlos.model.Account;
import com.giancarlos.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public Account register(@RequestBody Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return accountService.register(account);
    }

    @PostMapping("/login")
    public String login(@RequestBody Account account) {
        return accountService.verify(account);
    }

    @GetMapping("/temp")
    public String temp() {
        return "temp";
    }
}
