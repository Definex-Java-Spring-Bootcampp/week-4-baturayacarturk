package com.kredinbizde.credit.card.kredinbizdecreditcard.listener;


import com.kredinbizde.credit.card.kredinbizdecreditcard.entity.CreditCard;
import com.kredinbizde.credit.card.kredinbizdecreditcard.repository.CreditCardRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQListener {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @RabbitListener(queues = "card")
    public void receiveMessage(CreditCard message) {
        System.out.println("Received message: " + message);
        creditCardRepository.save(message);
    }


}