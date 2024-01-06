package com.mappy.accountservice.controller;

import com.mappy.accountservice.dto.AccountDto;
import com.mappy.accountservice.model.Account;
import com.mappy.accountservice.publisher.RabbitMQJsonProducer;
import com.mappy.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    private RabbitMQJsonProducer jsonProducer;
    @Autowired
    public AccountController(AccountService accountService, RabbitMQJsonProducer jsonProducer) { this.accountService = accountService; this.jsonProducer = jsonProducer; }
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Account account) {
        jsonProducer.sendJsonMessage(account);
        return ResponseEntity.ok("Account data sent to RabbitMQ ...");
    }
    @GetMapping
    public Iterable<Account> getAll() { return accountService.getAll(); }
    @GetMapping(path="/{accountId}")
    public Optional<Account> getById(@PathVariable Long accountId) { return accountService.getById(accountId); }
    @PostMapping
    public Account save(@RequestBody Account account) {
        sendJsonMessage(account);
        return accountService.save(account); }
    @PutMapping
    public Account update(@RequestBody Account account) {
        sendJsonMessage(account);
        return accountService.update(account);
    }
    @DeleteMapping
    public void delete(@RequestBody Account account) { accountService.delete(account); }
}
