package com.mappy.accountservice.controller;

import com.mappy.accountservice.model.Account;
import com.mappy.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) { this.accountService = accountService; }
    @GetMapping
    public Iterable<Account> getAll() { return accountService.getAll(); }
    @GetMapping(path="/{accountId}")
    public Optional<Account> getById(@PathVariable Long accountId) { return accountService.getById(accountId); }
    @PostMapping
    public Account save(@RequestBody Account account) { return accountService.save(account); }
    @PutMapping
    public Account update(@RequestBody Account account) { return accountService.update(account); }
    @DeleteMapping
    public void delete(@RequestBody Account account) { accountService.delete(account); }
}
