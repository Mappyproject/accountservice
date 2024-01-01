package com.mappy.accountservice.repository;

import com.mappy.accountservice.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface IAccountRepository extends CrudRepository<Account, Long> {
}
