package com.kredinbizde.notificationservice.listener;

import com.kredinbizde.notificationservice.dto.NotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {

    @RabbitListener(queues = "notify")
    public void sendNotification(NotificationDTO notificationDTO) {

        log.info("kuyruktan okudun: {}", notificationDTO);

    }


}
