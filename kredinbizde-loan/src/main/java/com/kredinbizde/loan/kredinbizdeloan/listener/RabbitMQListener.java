package com.kredinbizde.loan.kredinbizdeloan.listener;


import com.kredinbizde.loan.kredinbizdeloan.entity.Loan;
import com.kredinbizde.loan.kredinbizdeloan.repository.LoanRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQListener {
    @Autowired
    private LoanRepository loanRepository;

    @RabbitListener(queues = "loan")
    public void receiveMessage(Loan message) {
        System.out.println("Received message: " + message);
        loanRepository.save(message);
    }


}