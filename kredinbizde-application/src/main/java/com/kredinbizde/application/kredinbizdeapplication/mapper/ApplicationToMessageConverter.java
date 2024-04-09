package com.kredinbizde.application.kredinbizdeapplication.mapper;

import com.kredinbizde.application.kredinbizdeapplication.entity.Application;
import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import com.kredinbizde.application.kredinbizdeapplication.producers.requests.CreateApplicationMessageRequest;
import com.kredinbizde.application.kredinbizdeapplication.producers.requests.CreateCardMessageRequest;
import com.kredinbizde.application.kredinbizdeapplication.producers.requests.LoanMessageRequest;
import com.kredinbizde.application.kredinbizdeapplication.producers.requests.UpdateApplicationMessageRequest;
import com.kredinbizde.application.kredinbizdeapplication.request.CreateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.request.UpdateApplicationRequest;


public class ApplicationToMessageConverter {

    public static CreateApplicationMessageRequest createApplicationRequestToMessageRequest(CreateApplicationRequest request,String applicationId){
        CreateApplicationMessageRequest response = new CreateApplicationMessageRequest();
        response.setEmail(request.getEmail());
        response.setApplicationId(applicationId);
        response.setBankId(request.getBankId());
        response.setApplicationStatus(ApplicationStatus.INITIAL);
        if (request.getLoanId() != null) {
            response.setLoanId(request.getLoanId());
        }
        if (request.getCreditCardId() != null) {
            response.setCreditCardId(request.getCreditCardId());
        }
        response.setBankName(request.getBankName().toLowerCase());
        response.setOperationType(".create");
        return response;
    }
    public static UpdateApplicationMessageRequest updateApplicationMessageRequest(UpdateApplicationRequest request){
        UpdateApplicationMessageRequest response = new UpdateApplicationMessageRequest();
        response.setApplicationId(request.getApplicationId());
        response.setApplicationStatus(request.getApplicationStatus());
        response.setEmail(request.getEmail());
        response.setOperationType(".update");
        return response;
    }

    public static CreateCardMessageRequest createCardMessageRequest(CreateApplicationMessageRequest createApplicationMessageRequest, Application application){
        CreateCardMessageRequest request = new CreateCardMessageRequest();
        request.setBankId(createApplicationMessageRequest.getBankId());
        request.setBankName(createApplicationMessageRequest.getBankName());
        request.setUserEmail(createApplicationMessageRequest.getEmail());
        request.setApplicationId(createApplicationMessageRequest.getApplicationId());
        request.setCreditCardId(createApplicationMessageRequest.getCreditCardId());
        request.setApplicationDate(application.getApplicationDate());
        return request;
    }

    public static LoanMessageRequest createLoanMessageRequest(CreateApplicationMessageRequest createApplicationRequest,Application application){
        LoanMessageRequest request = new LoanMessageRequest();
        request.setBankId(createApplicationRequest.getBankId());
        request.setBankName(createApplicationRequest.getBankName());
        request.setUserEmail(createApplicationRequest.getEmail());
        request.setApplicationId(createApplicationRequest.getApplicationId());
        request.setLoanId(createApplicationRequest.getLoanId());
        request.setApplicationDate(application.getApplicationDate());
        return request;
    }
}
