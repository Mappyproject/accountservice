package com.mappy.accountservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    // account feed queue variables
    @Value("${rabbitmq.queue.feed.name}")
    private String feedQueue;
    @Value("${rabbitmq.exchange.feed.name}")
    private String feedExchange;
    @Value("${rabbitmq.routing.feed.key}")
    private String routingFeedKey;

    // spring bean for queue (store json messages)
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }
    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // feed queue and exchange
    @Bean
    public Queue feedQueue() {
        return new Queue(feedQueue);
    }
    @Bean
    public TopicExchange feedExchange() {
        return new TopicExchange(exchange);
    }

    // binding between queue and exchange using routing key
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    // binding between account and feed
    @Bean
    public Binding feedBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(feedExchange())
                .with(routingFeedKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
}