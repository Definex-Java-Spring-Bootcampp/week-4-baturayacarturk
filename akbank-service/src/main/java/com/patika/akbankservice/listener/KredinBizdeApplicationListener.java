package com.patika.akbankservice.listener;

import com.patika.akbankservice.dto.response.KredinbizdenCreateApplicationResponse;
import com.patika.akbankservice.dto.response.KredinbizdenUpdateApplicationResponse;
import com.patika.akbankservice.enums.ApplicationStatus;
import com.patika.akbankservice.producer.RabbitMQProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KredinBizdeApplicationListener {
    @Autowired
    private RabbitMQProducer producer;

    @RabbitListener(queues = "akbank.create")
    public void receiveMessage(KredinbizdenCreateApplicationResponse message) {
        System.out.println("Received message: " + message);
        message.setApplicationStatus(ApplicationStatus.APPROVED);
        producer.sendMessage(message,"status.update.akbank");
    }
    @RabbitListener(queues = "akbank.update")
    public void receiveMessage(KredinbizdenUpdateApplicationResponse message) {
        System.out.println("Received message: " + message);
    }

}
