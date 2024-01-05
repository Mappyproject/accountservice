package com.mappy.accountservice.publisher;

import com.mappy.accountservice.dto.AccountDto;
import com.mappy.accountservice.dto.AccountFeedDto;
import com.mappy.accountservice.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.exchange.feed.name}")
    private String exchange;
    @Value("${rabbitmq.routing.feed.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Account account) {
        AccountFeedDto accountData = new AccountFeedDto(account.getId(), account.getProject_ids());
        LOGGER.info(String.format("Json message sent -> %s", accountData.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, accountData);
    }
}
