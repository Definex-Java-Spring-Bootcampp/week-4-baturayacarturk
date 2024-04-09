package com.patika.garantiservice.listener;

import com.patika.garantiservice.dto.response.KredinbizdenApplicationResponse;
import com.patika.garantiservice.dto.response.KredinbizdenUpdateApplicationResponse;
import com.patika.garantiservice.enums.ApplicationStatus;
import com.patika.garantiservice.producer.RabbitMQProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class KredinBizdeApplicationListener {
    private final RabbitMQProducer producer;

    public KredinBizdeApplicationListener(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @RabbitListener(queues = "garanti.create")
    public void receiveMessage(KredinbizdenApplicationResponse message) {
        System.out.println("Received message: " + message);
        message.setApplicationStatus(ApplicationStatus.APPROVED);
        producer.sendMessage(message,"status.update.garanti");
    }
    @RabbitListener(queues = "garanti.update")
    public void receiveMessage(KredinbizdenUpdateApplicationResponse message) {
        System.out.println("Received message: " + message);

    }

}
