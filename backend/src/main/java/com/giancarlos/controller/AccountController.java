package com.giancarlos.controller;

import com.giancarlos.model.Account;
import com.giancarlos.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    @GetMapping("/user/info/{username}")
    public ResponseEntity<List<Integer>> getAccountInfo(@PathVariable String username) {
        List<Integer> info = accountService.getAccountInfo(username);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("/address/{username}")
    public ResponseEntity<String> getAccountEmail(@PathVariable String username) {
        String address = accountService.getAccountAddress(username);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
