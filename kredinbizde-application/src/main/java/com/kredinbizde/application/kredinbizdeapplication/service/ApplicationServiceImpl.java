package com.kredinbizde.application.kredinbizdeapplication.service;

import com.kredinbizde.application.kredinbizdeapplication.communication.user.OfferFeignClient;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.UserFeignClient;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.GetOfferResponse;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.GetUserResponse;
import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationType;
import com.kredinbizde.application.kredinbizdeapplication.mapper.ApplicationConverter;
import com.kredinbizde.application.kredinbizdeapplication.entity.Application;
import com.kredinbizde.application.kredinbizdeapplication.exception.BusinessException;
import com.kredinbizde.application.kredinbizdeapplication.exception.ValidationException;
import com.kredinbizde.application.kredinbizdeapplication.mapper.ApplicationToMessageConverter;
import com.kredinbizde.application.kredinbizdeapplication.mapper.BankNameConverter;
import com.kredinbizde.application.kredinbizdeapplication.producers.RabbitMQProducer;
import com.kredinbizde.application.kredinbizdeapplication.producers.requests.CreateApplicationMessageRequest;
import com.kredinbizde.application.kredinbizdeapplication.producers.requests.UpdateApplicationMessageRequest;
import com.kredinbizde.application.kredinbizdeapplication.repository.ApplicationRepository;
import com.kredinbizde.application.kredinbizdeapplication.request.CreateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.request.UpdateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.response.CreateApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetAllApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.UpdateApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.validation.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationRepository applicationRepository;
    private final ApplicationConverter applicationConverter;
    private final RequestValidator requestValidator;
    private final UserFeignClient userFeignClient;
    private final OfferFeignClient offerFeignClient;
    private final RabbitMQProducer producer;
    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationConverter applicationConverter, RequestValidator requestValidator, UserFeignClient userFeignClient, OfferFeignClient offerFeignClient, RabbitMQProducer producer) {
        this.applicationRepository = applicationRepository;
        this.applicationConverter = applicationConverter;
        this.requestValidator = requestValidator;
        this.userFeignClient = userFeignClient;
        this.offerFeignClient = offerFeignClient;
        this.producer = producer;
    }

    @Override
    public CreateApplicationResponse createApplication(CreateApplicationRequest createApplicationRequest) {
        requestValidator.validate(createApplicationRequest);
        Optional<GetUserResponse> userResponse = userFeignClient.getUserByEmail(createApplicationRequest.getEmail());
        if(userResponse.isEmpty()){
            throw  new BusinessException("Please sign in first");
        }
        Optional<GetOfferResponse> offerResponse= offerFeignClient.findByParams(createApplicationRequest.getBankId(),createApplicationRequest.getCreditCardId(),null,createApplicationRequest.getLoanId(),null);
        if(offerResponse.isEmpty()){
            throw new BusinessException("Respected offer does not exists. Please check again");
        }
        List<Application> existingApplications = applicationRepository.findByEmailAndCreditCardIdOrEmailAndLoanId(createApplicationRequest.getEmail(), createApplicationRequest.getCreditCardId(), createApplicationRequest.getEmail(), createApplicationRequest.getLoanId());
        if(!existingApplications.isEmpty()){
            throw new BusinessException("An application with the same credit card ID or loan ID already exists for this email");
        }
        Application applicationEntity = applicationConverter.createApplicationRequestToApplication(createApplicationRequest);
        Application savedApplication = applicationRepository.save(applicationEntity);
        String routingKey = BankNameConverter.determineRoutingKey(createApplicationRequest.getBankName());
        CreateApplicationMessageRequest request = ApplicationToMessageConverter.createApplicationRequestToMessageRequest(createApplicationRequest,savedApplication.getId());
        producer.sendMessage(request,routingKey+request.getOperationType());
        return applicationConverter.applicationToCreateApplicationResponse(applicationEntity);
    }

    @Override
    public GetApplicationResponse getApplicationById(String id) {
        if(id == null){
            throw new ValidationException("id field can not be null");
        }
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        if(optionalApplication.isEmpty()){
            throw new BusinessException(id +" id is not exists");
        }
        return applicationConverter.mapApplicationToResponse(optionalApplication.get());

    }

    @Override
    public GetAllApplicationResponse getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applicationConverter.mapApplicationsToResponse(applications);
    }

    @Override
    public UpdateApplicationResponse updateApplication(UpdateApplicationRequest updateApplicationRequest) {
        requestValidator.validate(updateApplicationRequest);
        Optional<Application> optionalApplication = applicationRepository.findById(updateApplicationRequest.getApplicationId());
        if (optionalApplication.isEmpty()) {
            throw new BusinessException("Application does not exists");
        }
        Optional<GetUserResponse> userResponse = userFeignClient.getUserByEmail(updateApplicationRequest.getEmail());
        if(userResponse.isEmpty()){
            throw new BusinessException("Please check your email");
        }

        if (!updateApplicationRequest.getApplicationStatus().equals(ApplicationStatus.CANCELLED)) {
            throw new BusinessException("You only can Cancel the application");
        }
        optionalApplication.get().setApplicationStatus(updateApplicationRequest.getApplicationStatus());
        applicationRepository.save(optionalApplication.get());
        String routingKey = BankNameConverter.determineRoutingKey(optionalApplication.get().getBankName());
        UpdateApplicationMessageRequest request = ApplicationToMessageConverter.updateApplicationMessageRequest(updateApplicationRequest);
        producer.sendMessage(request,routingKey+request.getOperationType());
        return applicationConverter.mapApplicationToUpdateResponse(optionalApplication.get());
    }

}
