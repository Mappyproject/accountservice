package com.mappy.accountservice.consumer;

import com.mappy.accountservice.dto.AccountDto;
import com.mappy.accountservice.model.Account;
import com.mappy.accountservice.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
    @Autowired
    private AccountService accountService;

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(AccountDto accountDto) {
        LOGGER.info(String.format("Received Account data from auth service -> %s", accountDto.toString()));
        saveAccountData(accountDto);
    }
    public void saveAccountData(AccountDto accountDto) {
        Account account = new Account(accountDto.getName(), accountDto.getSurname(), accountDto.getPhoneNumber(), accountDto.getBirthDate());
        accountService.save(account);
    }
}
