package com.kredinbizde.application.kredinbizdeapplication.listener;

import com.kredinbizde.application.kredinbizdeapplication.entity.Application;
import com.kredinbizde.application.kredinbizdeapplication.exception.BusinessException;
import com.kredinbizde.application.kredinbizdeapplication.mapper.ApplicationToMessageConverter;
import com.kredinbizde.application.kredinbizdeapplication.producers.RabbitMQProducer;
import com.kredinbizde.application.kredinbizdeapplication.producers.requests.CreateApplicationMessageRequest;
import com.kredinbizde.application.kredinbizdeapplication.producers.requests.NotificationDto;
import com.kredinbizde.application.kredinbizdeapplication.repository.ApplicationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private RabbitMQProducer rabbitMQProducer;
    @RabbitListener(queues = "status.update.garanti")
    public void receiveMessageFromGaranti(CreateApplicationMessageRequest message) {
        processMessage(message);
    }
    @RabbitListener(queues = "status.update.akbank")
    public void receiveMessageFromAkbank(CreateApplicationMessageRequest message) {
        processMessage(message);

    }
    private void processMessage(CreateApplicationMessageRequest message) {
        System.out.println("Received message: " + message);
        String applicationId = message.getApplicationId();
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new BusinessException("Application not found with ID: " + applicationId));
        application.setApplicationStatus(message.getApplicationStatus());
        if(message.getLoanId()==null){
            var creditCardResponse = ApplicationToMessageConverter.createCardMessageRequest(message,application);
            rabbitMQProducer.sendMessage(creditCardResponse,"card");
        }
        else{
            var loanReponse = ApplicationToMessageConverter.createLoanMessageRequest(message,application);
            rabbitMQProducer.sendMessage(loanReponse,"loan");
        }
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEmail(message.getEmail());
        notificationDto.setMessage("your application is approved via bank");
        rabbitMQProducer.sendMessage(notificationDto,"notify");
        applicationRepository.save(application);
        System.out.println("Application status updated successfully.");
    }

}
