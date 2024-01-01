package com.mappy.accountservice.service;

import com.mappy.accountservice.model.Account;

import java.util.Optional;

public interface IAccountService {
    Account save(Account account);
    Iterable<Account> getAll();
    Optional<Account> getById(Long id);
    Account update(Account account);
    void delete(Account account);
}
